# Aufgabe 4 – Objektorientiertes Design (OOD)

**Projekt:** Project-Workspace-Manager
**Kurs:** Software Engineering 1, Frühjahrssemester 2026
**Team:** Benjamin Golder, Dennis Eberhard
**Dozent:** Arif Chughtai
**Basis:** Aufgabe 3 (Fachklassenmodell) sowie FA01 – Projektarbeitsbereich erstellen (Score 90) und FA02 – Vordefinierte Projektvorlage auswählen (Score 81)

---

## Architektur-Vorgabe

Es wird die im Unterricht vorgegebene logische **3-Schichtenarchitektur (Clean Architecture)** umgesetzt:

| Schicht | Inhalt | Abhängigkeit |
|---|---|---|
| **Presentation Layer** | `ConsoleClient` – Eingabe/Ausgabe über Konsole | nutzt Domain |
| **Business (Domain) Layer** | Domain Services, Domain Components, Domain Objects, Factories, Repository-Schnittstellen | nutzt Persistence |
| **Persistence Layer** | `DataAccess` (SharePoint via Microsoft Graph), konkrete Repository-Implementierungen | – |

Die technische Abhängigkeitsrichtung ist strikt: **Presentation → Business → Persistence**. Die Repository-**Schnittstellen** werden in der Business-Schicht definiert; die **Implementierungen** liegen in der Persistence-Schicht (Dependency Inversion).

---

## Annahmen

- Die Authentifizierung des Projektmanagers über Microsoft Entra ID ist vorgelagert; im Designmodell wird ein bereits authentifizierter `Benutzer` übergeben.
- Persistenz erfolgt in SharePoint Online (Listen + Site-Provisionierung) über die Microsoft Graph API. Im Designmodell wird dies als generische `IDataAccess`-Schnittstelle gekapselt, damit die konkrete Technologie austauschbar bleibt.
- Für die 1. Iteration genügt eine konsolenbasierte Presentation-Schicht (`ConsoleClient`). Eine zukünftige Power-App-Oberfläche kann die gleichen Business-Schnittstellen nutzen.
- Die `Projektvorlage`-Definitionen (Standard / Kundenprojekt / Intern) sind statisch konfiguriert und werden zum Programmstart über die `ProjektvorlageFactory` instanziert; ihre Persistierung beschränkt sich auf Lesen (deshalb keine `save`/`update`-Operationen am Vorlagen-Repository).
- Eine Konkrete `Berechtigung` für den Eigentümer wird beim Erstellen des Arbeitsbereichs automatisch vergeben (FA01, Schritt 6).

---

## Eingesetzte Design Patterns

| Pattern | Einsatz | Begründung |
|---|---|---|
| **Singleton** | `SharePointDataAccess`, `ProjektarbeitsbereichFactory`, `ProjektvorlageFactory` | Nur eine zentrale Instanz für den (teuren) Graph-API-Zugriff bzw. die Erzeugungslogik. |
| **Factory** | `ProjektarbeitsbereichFactory` (FA01: erzeugt Arbeitsbereich inkl. Namenskonvention, Defaults, Initialstatus), `ProjektvorlageFactory` (FA02: erzeugt konkrete Vorlagen-Subklassen je Typ) | Erzeugungslogik wird aus den Services herausgelöst; Verstoss gegen Open/Closed wird vermieden, wenn neue Vorlagentypen ergänzt werden. |
| **Repository** | `IProjektarbeitsbereichRepository`, `IProjektvorlageRepository`, `IBenutzerRepository`, `IAuditLogRepository` | Persistenz wird hinter einer fachlichen Schnittstelle gekapselt; die Business-Schicht kennt SharePoint nicht. |

---

## Auftrag 1 – Designmodell Persistence-Schicht

> PlantUML-Quelle: `persistence-schicht.puml`

Die Persistence-Schicht stellt die Repository-Implementierungen sowie eine zentrale `IDataAccess`-Komponente bereit, die als Singleton den Zugriff auf SharePoint bündelt.

**Schnittstellen / Klassen:**
- `IDataAccess` *(Singleton)* – generisches CRUD-Gateway
- `SharePointDataAccess` *(konkret, Singleton-Realisierung)*
- `IProjektarbeitsbereichRepository`, `IProjektvorlageRepository`, `IBenutzerRepository`, `IAuditLogRepository` *(Repository-Schnittstellen, in Business-Schicht definiert, hier realisiert)*
- `ProjektarbeitsbereichRepository`, `ProjektvorlageRepository`, `BenutzerRepository`, `AuditLogRepository` *(konkrete Implementierungen)*

Jede konkrete Repository-Klasse delegiert die technischen Lese-/Schreibzugriffe an `IDataAccess`.

![Persistence-Schicht](persistence-schicht.png)

---

## Auftrag 2 – Designmodell Business-Schicht

> PlantUML-Quelle: `business-schicht.puml`

**Schnittstellen / Domain Services:**
- `IArbeitsbereichService` / `ArbeitsbereichService` – Orchestriert FA01 (Arbeitsbereich erstellen, finden, auflisten)
- `IVorlagenService` / `VorlagenService` – Orchestriert FA02 (Vorlagen laden, finden)

**Domain Components (interne Helfer der Services):**
- `NamenskonventionValidator` – Bildet/prüft den Namen nach `PRJ-[Jahr]-[Kürzel]-[Name]`
- `BerechtigungsManager` – Vergibt Owner-Berechtigung an den Ersteller

**Factories (Singleton):**
- `ProjektarbeitsbereichFactory.erstelle(...)` – Bündelt Defaults: Initialstatus = `BEANTRAGT`, Erstellungsdatum, Owner, Default-Ordnerstruktur gemäss Vorlage.
- `ProjektvorlageFactory.erstelleVorlage(typ)` – Liefert je nach `VorlagenTyp` eine `StandardVorlage`, `KundenprojektVorlage` oder `InternVorlage`.

**Repository-Schnittstellen** (Definition in Business-Schicht, Realisierung in Persistence-Schicht).

**Domain Objects** (aus dem Fachklassenmodell von Aufgabe 3): `Projektarbeitsbereich`, `Projektmetadaten`, `Projektvorlage` *(abstrakt)*, `StandardVorlage`, `KundenprojektVorlage`, `InternVorlage`, `Ordner`, `Dokumentvorlage`, `Benutzer`, `Berechtigung`, `AuditLogEintrag`. Enumerationen: `ArbeitsbereichStatus`, `BenutzerRolle`, `VorlagenTyp`.

![Business-Schicht](business-schicht.png)

---

## Auftrag 3 – Integriertes Designmodell

### UML Komponentendiagramm

> PlantUML-Quelle: `integriert-komponentendiagramm.puml`

Die Presentation-Komponente `ConsoleClient` nutzt die Business-Komponente über die Service-Schnittstellen `IArbeitsbereichService` und `IVorlagenService`. Die Business-Komponente nutzt die Persistence-Komponente ausschliesslich über die Repository-Schnittstellen.

![Komponentendiagramm](integriert-komponentendiagramm.png)

### UML Klassendiagramm (integriert)

> PlantUML-Quelle: `integriert-klassendiagramm.puml`

Das integrierte Klassendiagramm zeigt alle drei Schichten in einem Diagramm. Pfeile zwischen den Schichten entsprechen ausschliesslich der erlaubten Abhängigkeitsrichtung (Presentation → Business → Persistence). Die Repository-Schnittstellen liegen in der Business-Schicht und werden in der Persistence-Schicht realisiert.

![Integriertes Klassendiagramm](integriert-klassendiagramm.png)

---

## Auftrag 4 – Überprüfung mit Sequenzdiagramm (FA01 Normalablauf)

> PlantUML-Quelle: `sequenzdiagramm-fa01.puml`

Geprüfter Anwendungsfall: **FA01 – Projektarbeitsbereich erstellen** (Normalablauf, ohne Fehlerpfade).

**Ablauf:**
1. `Projektmanager` füllt das Formular im `ConsoleClient` aus und wählt eine Vorlage.
2. `ConsoleClient` lädt verfügbare Vorlagen über `IVorlagenService.vorlagenLaden()`.
3. `ConsoleClient` ruft `IArbeitsbereichService.arbeitsbereichErstellen(...)` auf.
4. Der Service holt die Vorlage (`vorlageFinden`), bildet/validiert den Namen (`NamenskonventionValidator`), erzeugt den Arbeitsbereich über die `ProjektarbeitsbereichFactory`.
5. Der `BerechtigungsManager` setzt den Ersteller als Owner.
6. Das `IProjektarbeitsbereichRepository` persistiert den Arbeitsbereich; intern delegiert es an `IDataAccess`.
7. Das `IAuditLogRepository` legt einen unveränderlichen Audit-Eintrag an.
8. Der erstellte `Projektarbeitsbereich` wird an den `ConsoleClient` zurückgegeben und mit URL/Bestätigung dem Projektmanager angezeigt.

![Sequenzdiagramm FA01](sequenzdiagramm-fa01.png)

### Erkenntnisse aus der Überprüfung

- Die Verantwortlichkeitsverteilung Service / Factory / Repository ist trennscharf: der Service orchestriert, die Factory erzeugt, das Repository persistiert.
- Der `BerechtigungsManager` wurde als eigene Domain-Component eingeführt, weil die Owner-Vergabe sowohl in FA01 (Erstellung) als auch in einer späteren Erweiterung (Mitglieder hinzufügen) wiederverwendet wird.
- Das Sequenzdiagramm hat eine Lücke aufgedeckt: `IAuditLogRepository.save(...)` muss zwingend nach dem erfolgreichen `save` des Arbeitsbereichs aufgerufen werden (Reihenfolge im Service). Diese Reihenfolge ist im Service-Implementierungstext verbindlich.
- Keine zusätzlichen Klassen waren nötig; das Designmodell ist für den Normalablauf vollständig.

---

## Auftrag 5 – Glossar (Design)

> Beschrieben werden ausschliesslich Verantwortlichkeiten. Triviale UML-Informationen und allgemeine OO-Konzepte werden nicht wiederholt.

### Presentation-Schicht

**ConsoleClient** – Stellt die konsolenbasierte Benutzerinteraktion bereit, fordert Domain-Operationen über die Service-Schnittstellen an und zeigt das Ergebnis (z. B. URL des neuen Arbeitsbereichs, Vorlagenliste) auf der Konsole an. Enthält keine fachliche Logik.

### Business-Schicht – Schnittstellen

**IArbeitsbereichService** – Fasst alle Anwendungsfälle rund um den Lebenszyklus eines Projektarbeitsbereichs zusammen (Erstellen, Finden, Auflisten). Einziger Eintrittspunkt der Presentation-Schicht für FA01.

**IVorlagenService** – Stellt die in FA02 benötigten Vorlagenoperationen bereit (alle Vorlagen laden, Vorlage anhand der ID finden).

**IProjektarbeitsbereichRepository** – Fachliche Schnittstelle für die Persistenz eines Projektarbeitsbereichs (CRUD). Verbirgt vor der Business-Schicht, dass die Speicherung in SharePoint erfolgt.

**IProjektvorlageRepository** – Liefert die im System hinterlegten Vorlagen. Lesezugriff genügt, da Vorlagen statisch konfiguriert sind.

**IBenutzerRepository** – Liefert Benutzerinformationen (typischerweise aus dem Benutzerverzeichnis). Wird vom Service benötigt, um den Ersteller in `Berechtigung` und `AuditLogEintrag` einzutragen.

**IAuditLogRepository** – Persistiert unveränderliche Audit-Einträge und ermöglicht deren Abruf je Arbeitsbereich. Erfüllt die Compliance-Nachbedingung von FA01.

### Business-Schicht – Klassen

**ArbeitsbereichService** – Orchestriert FA01: validiert Namen, instanziert über die Factory, vergibt Berechtigung, persistiert über das Repository und schreibt einen Audit-Eintrag. Diese Klasse hält den Normalablauf des wichtigsten Anwendungsfalls zusammen.

**VorlagenService** – Lädt Vorlagen über das Vorlagen-Repository und stellt sie der Presentation-Schicht zur Auswahl bereit (FA02). Stellt sicher, dass beim ersten Zugriff über die `ProjektvorlageFactory` die konkreten Vorlagentypen erzeugt werden.

**ProjektarbeitsbereichFactory** *(Singleton)* – Kapselt die Erzeugung eines `Projektarbeitsbereich`-Objekts inklusive Setzen der Defaults (Initialstatus, Erstellungsdatum, URL-Schema, Default-Ordnerstruktur aus der Vorlage). Vermeidet, dass diese Logik mehrfach im Service-Code auftaucht.

**ProjektvorlageFactory** *(Singleton)* – Liefert je `VorlagenTyp` die korrekte konkrete Vorlagenklasse (`StandardVorlage`, `KundenprojektVorlage`, `InternVorlage`). Erlaubt das Hinzufügen weiterer Vorlagentypen, ohne die Services anzupassen.

**NamenskonventionValidator** – Bildet aus Eingabewerten einen normierten Arbeitsbereichsnamen nach `PRJ-[Jahr]-[Kürzel]-[Name]` und prüft die Eindeutigkeit (z. B. gegen das Repository). Kapselt die fachliche Namensregel an einem Ort.

**BerechtigungsManager** – Vergibt die Owner-Berechtigung an den erstellenden Projektmanager bzw. weitere Rollen. Trennt diese Querschnittslogik vom Service-Ablauf.

### Persistence-Schicht – Schnittstellen / Klassen

**IDataAccess** *(Singleton)* – Generische CRUD-Schnittstelle gegen die Speichertechnologie. Wird ausschliesslich von den konkreten Repositories aufgerufen.

**SharePointDataAccess** – Konkrete Realisierung gegen SharePoint Online via Microsoft Graph API. Verwaltet Verbindung, Authentifizierung-Token und die technischen CRUD-Operationen. Singleton, weil Verbindungs-/Token-State zentral gehalten werden muss.

**ProjektarbeitsbereichRepository** – Setzt `IProjektarbeitsbereichRepository` um, bildet einen `Projektarbeitsbereich` auf das SharePoint-Listen-/Site-Schema ab und delegiert die Datenoperationen an `IDataAccess`.

**ProjektvorlageRepository** – Liest die hinterlegten Vorlagenkonfigurationen (z. B. JSON-/SharePoint-Liste) und gibt fertige `Projektvorlage`-Objekte zurück.

**BenutzerRepository** – Liefert `Benutzer`-Objekte; im MVP wird die Quelle gegen Microsoft Entra ID via Graph API gelesen.

**AuditLogRepository** – Schreibt Audit-Einträge unveränderlich (SharePoint-Liste mit Append-only-Konfiguration) und ermöglicht das Lesen je Arbeitsbereich.

---

## Dateiübersicht

| Datei | Inhalt |
|---|---|
| `aufgabe-4-ood.md` | Dieses Dokument |
| `persistence-schicht.puml` | UML-Klassendiagramm Persistence-Schicht |
| `business-schicht.puml` | UML-Klassendiagramm Business-Schicht |
| `integriert-klassendiagramm.puml` | UML-Klassendiagramm integriert (alle 3 Schichten) |
| `integriert-komponentendiagramm.puml` | UML-Komponentendiagramm integriert |
| `sequenzdiagramm-fa01.puml` | UML-Sequenzdiagramm Normalablauf FA01 |

> Die `.puml`-Dateien werden in Visual Paradigm importiert (Diagram > New > PlantUML Diagram), exportiert als PNG und unter gleichem Dateinamen mit Endung `.png` in diesem Ordner abgelegt.
