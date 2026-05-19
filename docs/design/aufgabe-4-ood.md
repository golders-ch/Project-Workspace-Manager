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
| **Business (Domain) Layer** | Domain Services, Domain Objects, Factories, Repository-Schnittstellen | nutzt Persistence |
| **Persistence Layer** | `DataAccess` (SharePoint via Microsoft Graph), konkrete Repository-Implementierungen | – |

Die technische Abhängigkeitsrichtung ist strikt: **Presentation → Business → Persistence**. Die Repository-**Schnittstellen** werden in der Business-Schicht definiert; die **Implementierungen** liegen in der Persistence-Schicht (Dependency Inversion).

---

## Annahmen

- Die Authentifizierung des Projektmanagers über Microsoft Entra ID ist vorgelagert; im Designmodell wird ein bereits authentifizierter Benutzer-Kontext vorausgesetzt und ist deshalb nicht modelliert.
- Persistenz erfolgt in SharePoint Online (Listen + Site-Provisionierung) über die Microsoft Graph API. Im Designmodell wird dies als generische `IDataAccess`-Schnittstelle gekapselt, damit die konkrete Technologie austauschbar bleibt.
- Für die 1. Iteration genügt eine konsolenbasierte Presentation-Schicht (`ConsoleClient`). Eine zukünftige Power-App-Oberfläche kann die gleichen Business-Schnittstellen nutzen.
- Das Designmodell beschränkt sich auf die für FA01/FA02 zwingend benötigten Designklassen. Im Fachklassenmodell (Aufgabe 3) modellierte Objekte wie `Projektmetadaten`, `Ordner`, `Dokumentvorlage`, `Berechtigung` und `AuditLogEintrag` sind Eigenschaften des `Projektarbeitsbereichs` bzw. der `Projektvorlage` und werden in späteren Iterationen ergänzt.
- Die `Projektvorlage`-Definitionen (Standard / Kundenprojekt / Intern) sind statisch konfiguriert; ihre Persistierung beschränkt sich auf Lesen (deshalb keine `save`/`update`-Operationen am Vorlagen-Repository).

---

## Eingesetzte Design Patterns

| Pattern | Einsatz | Begründung |
|---|---|---|
| **Singleton** | `SharePointDataAccess`, `ProjektarbeitsbereichFactory`, `ProjektvorlageFactory` | Nur eine zentrale Instanz für den (teuren) Graph-API-Zugriff bzw. die Erzeugungslogik. |
| **Factory** | `ProjektarbeitsbereichFactory` (FA01: erzeugt Arbeitsbereich inkl. Defaults), `ProjektvorlageFactory` (FA02: erzeugt konkrete Vorlagen-Subklassen je Typ) | Erzeugungslogik wird aus den Services herausgelöst; das Hinzufügen weiterer Vorlagentypen ändert die Services nicht. |
| **Repository** | `IProjektarbeitsbereichRepository`, `IProjektvorlageRepository` | Persistenz wird hinter einer fachlichen Schnittstelle gekapselt; die Business-Schicht kennt SharePoint nicht. |

---

## Auftrag 1 – Designmodell Persistence-Schicht

Die Persistence-Schicht stellt die Repository-Implementierungen sowie eine zentrale `IDataAccess`-Komponente bereit, die als Singleton den Zugriff auf SharePoint bündelt.

**Schnittstellen / Klassen:**
- `IDataAccess` *(Singleton)* – generisches CRUD-Gateway
- `SharePointDataAccess` *(konkret, Singleton-Realisierung)*
- `IProjektarbeitsbereichRepository`, `IProjektvorlageRepository` *(Repository-Schnittstellen, in Business-Schicht definiert, hier realisiert)*
- `ProjektarbeitsbereichRepository`, `ProjektvorlageRepository` *(konkrete Implementierungen)*

Jede konkrete Repository-Klasse delegiert die technischen Lese-/Schreibzugriffe an `IDataAccess`.

![Persistence-Schicht](persistence-schicht.png)

---

## Auftrag 2 – Designmodell Business-Schicht

**Schnittstellen / Domain Services:**
- `IArbeitsbereichService` / `ArbeitsbereichService` – Orchestriert FA01 (Arbeitsbereich erstellen, finden)
- `IVorlagenService` / `VorlagenService` – Orchestriert FA02 (Vorlagen laden, finden)

**Factories (Singleton):**
- `ProjektarbeitsbereichFactory.erstelle(...)` – Bündelt die Defaults beim Erzeugen eines `Projektarbeitsbereichs` (Initialstatus, Erstellungsdatum, URL-Schema).
- `ProjektvorlageFactory.erstelleVorlage(typ)` – Liefert je nach `VorlagenTyp` eine `StandardVorlage`, `KundenprojektVorlage` oder `InternVorlage`.

**Repository-Schnittstellen** werden in der Business-Schicht definiert und in der Persistence-Schicht realisiert.

**Domain Objects** (aus dem Fachklassenmodell von Aufgabe 3, im Design für FA01/FA02 relevant): `Projektarbeitsbereich`, `Projektvorlage` *(abstrakt)* mit den Subklassen `StandardVorlage`, `KundenprojektVorlage`, `InternVorlage`.

**Enumeration:** `VorlagenTyp` (Diskriminator für die `ProjektvorlageFactory`).

![Business-Schicht](business-schicht.png)

---

## Auftrag 3 – Integriertes Designmodell

### UML Komponentendiagramm

Die Presentation-Komponente `ConsoleClient` nutzt die Business-Komponente über die Service-Schnittstellen `IArbeitsbereichService` und `IVorlagenService`. Die Business-Komponente nutzt die Persistence-Komponente ausschliesslich über die Repository-Schnittstellen.

![Komponentendiagramm](integriert-komponentendiagramm.png)

### UML Klassendiagramm (integriert)

Das integrierte Klassendiagramm zeigt alle drei Schichten in einem Diagramm. Pfeile zwischen den Schichten entsprechen ausschliesslich der erlaubten Abhängigkeitsrichtung (Presentation → Business → Persistence). Die Repository-Schnittstellen liegen in der Business-Schicht und werden in der Persistence-Schicht realisiert.

![Integriertes Klassendiagramm](integriert-klassendiagramm.png)

---

## Auftrag 4 – Überprüfung mit Sequenzdiagramm (FA01 Normalablauf)

Geprüfter Anwendungsfall: **FA01 – Projektarbeitsbereich erstellen** (Normalablauf, ohne Fehlerpfade).

**Ablauf:**
1. `Projektmanager` startet im `ConsoleClient` den Erstellungsvorgang.
2. `ConsoleClient` lädt verfügbare Vorlagen über `IVorlagenService.vorlagenLaden()`.
3. `ConsoleClient` ruft `IArbeitsbereichService.arbeitsbereichErstellen(name, vorlageId)` auf.
4. Der Service holt die Vorlage (`vorlageFinden`) und erzeugt den Arbeitsbereich über die `ProjektarbeitsbereichFactory` (Singleton-Zugriff via `getInstance()`).
5. Das `IProjektarbeitsbereichRepository` persistiert den Arbeitsbereich; intern delegiert es an `IDataAccess`.
6. Der erstellte `Projektarbeitsbereich` wird an den `ConsoleClient` zurückgegeben und mit URL/Bestätigung dem Projektmanager angezeigt.

> Das Sequenzdiagramm wird in einer späteren Iteration ergänzt (siehe Besprechungsprotokoll 2026-05-15).

### Erkenntnisse aus der Überprüfung

- Die Verantwortlichkeitsverteilung Service / Factory / Repository ist trennscharf: der Service orchestriert, die Factory erzeugt, das Repository persistiert.
- Der Singleton-Zugriff der Factory ist im Sequenzdiagramm explizit dargestellt (`getInstance()` vor der eigentlichen Methode `erstelle(...)`).
- Keine zusätzlichen Klassen waren nötig; das Designmodell ist für den Normalablauf vollständig.

---

## Auftrag 5 – Glossar (Design)

> Beschrieben werden ausschliesslich Verantwortlichkeiten. Triviale UML-Informationen und allgemeine OO-Konzepte werden nicht wiederholt.

### Presentation-Schicht

**ConsoleClient** – Stellt die konsolenbasierte Benutzerinteraktion bereit, fordert Domain-Operationen über die Service-Schnittstellen an und zeigt das Ergebnis (z. B. URL des neuen Arbeitsbereichs, Vorlagenliste) auf der Konsole an. Enthält keine fachliche Logik.

### Business-Schicht – Schnittstellen

**IArbeitsbereichService** – Fasst alle Anwendungsfälle rund um den Lebenszyklus eines Projektarbeitsbereichs zusammen (Erstellen, Finden). Einziger Eintrittspunkt der Presentation-Schicht für FA01.

**IVorlagenService** – Stellt die in FA02 benötigten Vorlagenoperationen bereit (alle Vorlagen laden, Vorlage anhand der ID finden).

**IProjektarbeitsbereichRepository** – Fachliche Schnittstelle für die Persistenz eines Projektarbeitsbereichs (CRUD). Verbirgt vor der Business-Schicht, dass die Speicherung in SharePoint erfolgt.

**IProjektvorlageRepository** – Liefert die im System hinterlegten Vorlagen. Lesezugriff genügt, da Vorlagen statisch konfiguriert sind.

### Business-Schicht – Klassen

**ArbeitsbereichService** – Orchestriert FA01: ermittelt die Vorlage, lässt den Arbeitsbereich von der Factory erzeugen und persistiert ihn über das Repository. Diese Klasse hält den Normalablauf des wichtigsten Anwendungsfalls zusammen.

**VorlagenService** – Lädt Vorlagen über das Vorlagen-Repository und stellt sie der Presentation-Schicht zur Auswahl bereit (FA02). Stellt sicher, dass beim ersten Zugriff über die `ProjektvorlageFactory` die konkreten Vorlagentypen erzeugt werden.

**ProjektarbeitsbereichFactory** *(Singleton)* – Kapselt die Erzeugung eines `Projektarbeitsbereich`-Objekts inklusive Setzen der Defaults (Initialstatus, Erstellungsdatum, URL-Schema). Vermeidet, dass diese Logik mehrfach im Service-Code auftaucht.

**ProjektvorlageFactory** *(Singleton)* – Liefert je `VorlagenTyp` die korrekte konkrete Vorlagenklasse (`StandardVorlage`, `KundenprojektVorlage`, `InternVorlage`). Erlaubt das Hinzufügen weiterer Vorlagentypen, ohne die Services anzupassen.

### Persistence-Schicht – Schnittstellen / Klassen

**IDataAccess** *(Singleton)* – Generische CRUD-Schnittstelle gegen die Speichertechnologie. Wird ausschliesslich von den konkreten Repositories aufgerufen.

**SharePointDataAccess** – Konkrete Realisierung gegen SharePoint Online via Microsoft Graph API. Verwaltet Verbindung, Authentifizierung-Token und die technischen CRUD-Operationen. Singleton, weil Verbindungs-/Token-State zentral gehalten werden muss.

**ProjektarbeitsbereichRepository** – Setzt `IProjektarbeitsbereichRepository` um, bildet einen `Projektarbeitsbereich` auf das SharePoint-Listen-/Site-Schema ab und delegiert die Datenoperationen an `IDataAccess`.

**ProjektvorlageRepository** – Liest die hinterlegten Vorlagenkonfigurationen und gibt fertige `Projektvorlage`-Objekte zurück.

---

## Dateiübersicht

| Datei | Inhalt |
|---|---|
| `aufgabe-4-ood.md` | Dieses Dokument |
| `persistence-schicht.png` | UML-Klassendiagramm Persistence-Schicht |
| `business-schicht.png` | UML-Klassendiagramm Business-Schicht |
| `integriert-klassendiagramm.png` | UML-Klassendiagramm integriert (alle 3 Schichten) |
| `integriert-komponentendiagramm.png` | UML-Komponentendiagramm integriert |

> Die Diagramme wurden in **Visual Paradigm** modelliert und als PNG exportiert. Das Sequenzdiagramm zu FA01 wird in einer späteren Iteration ergänzt.
