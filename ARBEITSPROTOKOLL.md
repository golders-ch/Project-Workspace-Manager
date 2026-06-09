# Arbeitsprotokoll – Project-Workspace-Manager

Dieses Protokoll dokumentiert alle Arbeitsschritte und Fortschritte im Projektverlauf.

---

## 2026-06-09

**Teilnehmer:** Benjamin Golder
**Dauer:** ca. 1h

### Erledigtes

**Code-Vereinfachung Java-Prototyp (Iteration 1)**

Ziel: Der Prototyp-Code soll dem im Unterricht behandelten Niveau entsprechen und nachvollziehbar selbst geschrieben wirken. Die im OOD-Modell (Aufgabe 4) vorgegebene Architektur (3 Schichten, Singleton, Factory, Repository) bleibt unverändert – nur der Implementierungsstil wurde verschlankt.

- **Singletons vereinfacht:** `ProjektarbeitsbereichFactory` und `MockDataAccess` nutzen statt des Bill-Pugh-Holder-Idioms nun einen einfachen Lazy-Singleton (`if (instance == null) ...`).
- **`IDataAccess` entgenerisiert:** Die generischen Class-Token-Methoden (`<T> T lesen(..., Class<T>)`) wurden durch einfache `Object`-Rückgaben ersetzt; der Cast auf `Projektarbeitsbereich` erfolgt nun explizit im `ProjektarbeitsbereichRepository`.
- **`MockDataAccess` vereinfacht:** `synchronized` entfernt (Prototyp ist einthreadig) und `computeIfAbsent(k -> ...)` durch ein einfaches `if (!containsKey)` ersetzt.
- **Javadoc entschärft:** Fortgeschrittene Fachbegriffe (Bill-Pugh-Idiom, Initialization-on-demand, Dependency-Inversion-Prinzip) durch einfache deutsche Kommentare ersetzt.

### Verifikation
- `javac` (JDK 21): alle Production- + Test-Dateien kompilieren ohne Fehler
- JUnit Console Launcher: **17/17 Tests weiterhin grün** (9 Business + 8 Persistence)
- `Application.main`: Endbenutzertest gibt die 4 Projektarbeitsbereiche unverändert korrekt aus

### Nächste Schritte
- Dennis: Gegenlesen, ob der vereinfachte Stil verständlich ist
- Code ↔ UML-Modell beim nächsten Mal kurz gegenprüfen (Patterns sind unverändert vorhanden)

---

## 2026-05-29

**Teilnehmer:** Benjamin Golder
**Dauer:** ca. 2h

### Erledigtes

**Aufgabe 5 – Objektorientierte Implementation (Iteration 1)**

- Neues Eclipse-Java-Projekt `prototype-java/` im Repo-Root angelegt
  - `.project` + `.classpath` mit JRE-21-Container, getrennten Source-Foldern `src/` und `test/`
  - JUnit Platform Console Standalone 1.10.2 als lokale Lib in `lib/`
- Java-Packages gemäss Vorgabe Aufgabe 5:
  - `ch.juve.pwm.presentation`, `ch.juve.pwm.business`, `ch.juve.pwm.persistence`
  - `ch.juve.pwm.test.business`, `ch.juve.pwm.test.persistence`
- **Auftrag 1** – Fachliche Klasse: `Projektarbeitsbereich` (FA01, Score 90); Attribute der 1. Iteration: `id` + `name`
- **Auftrag 2** – Designmodell implementiert:
  - **Persistence:** `IDataAccess` (Singleton-Vertrag, generische CRUD-Schnittstelle), `MockDataAccess` (in-memory Map, Singleton), `ProjektarbeitsbereichRepository`
  - **Business:** Domain-Klasse `Projektarbeitsbereich`, `IProjektarbeitsbereichRepository`, `IArbeitsbereichService` + `ArbeitsbereichService`, `ProjektarbeitsbereichFactory` (Singleton, Bill-Pugh-Holder)
  - **Presentation:** `ConsoleClient` (Konsolen-Ausgabe), `Application` (`main`-Methode, Schichten-Verdrahtung)
  - Anmerkung: Im Prototyp ersetzt `MockDataAccess` die designseitige `SharePointDataAccess`; Schnittstelle `IDataAccess` bleibt identisch (Vorgabe Mock-DB)
- **Auftrag 3** – Unit-Tests Persistence-Schicht: 8 JUnit-5-Tests in `ProjektarbeitsbereichRepositoryTest`
- **Auftrag 4** – Unit-Tests Business-Schicht: 9 JUnit-5-Tests in `ArbeitsbereichServiceTest` (inkl. `FakeRepository` für Isolation gegen Persistence)
- **Auftrag 5** – Endbenutzertest: `Application.main` erzeugt 4 Projektarbeitsbereiche mit harten Testdaten und gibt sie auf der Konsole aus
- **Auftrag 6** – Javadoc: Datei-Header für alle Klassen/Schnittstellen, vollständige Methoden-Dokumentation in Schnittstellen und der Factory, zusammengefasste Getter/Setter-Doku; HTML-Doku generiert nach `prototype-java/docs/javadoc/` (Einstieg: `index.html`)
- `prototype-java/README.md` mit Build-, Test- und Javadoc-Anleitung sowie Eclipse-Import-Anleitung erstellt
- `.gitignore` für `bin/`, `.settings/`, `docs/javadoc/`

### Verifikation
- `javac` (JDK 21): 10 Production- + 2 Test-Dateien kompilieren ohne Fehler/Warnungen
- JUnit Console Launcher: **17/17 Tests grün** (9 Business + 8 Persistence)
- `Application.main`: Ausgabe der 4 Projektarbeitsbereiche auf der Konsole funktioniert wie spezifiziert

### Nächste Schritte
- Dennis: Code Review, ggf. zusätzliche Tests/Annahmen ergänzen
- Mit Dozent in der nächsten Besprechung die Wahl der fachlichen Klasse (`Projektarbeitsbereich`) und der zwei Attribute (`id`, `name`) bestätigen lassen
- Auftrag 7 (Präsentation): Folien-Struktur und Aufgabenverteilung im nächsten Team-Meeting festlegen

---

## 2026-05-19

**Teilnehmer:** Benjamin Golder
**Dauer:** ca. 1h

### Erledigtes

**Aufgabe 4 – Diagramme mit Visual Paradigm nachgebaut**
- Vier UML-Designdiagramme in Visual Paradigm nachmodelliert und als Bild exportiert:
  - `business-schicht.png` (Business-Schicht)
  - `persistence-schicht.png` (Persistence-Schicht)
  - `integriert-klassendiagramm.png` (integriertes Klassendiagramm, alle 3 Schichten)
  - `integriert-komponentendiagramm.png` (integriertes Komponentendiagramm)
- Bisherige aus PlantUML generierte PNGs im Repo durch die neuen Visual-Paradigm-Exporte ersetzt
- Alle `.puml`-Quelldateien in `docs/design/` entfernt (nicht mehr benötigt, da Modellierung jetzt in Visual Paradigm erfolgt)
- `aufgabe-4-ood.md` angepasst: PlantUML-Quellenhinweise und Dateiübersicht aktualisiert (Modellierungstool nun Visual Paradigm)
- `sequenzdiagramm-fa01.png` entfernt: Das Sequenzdiagramm wird in einer späteren Iteration ergänzt (siehe Beschluss vom 2026-05-15). Hinweis in `aufgabe-4-ood.md` ergänzt.

---

## 2026-02-27

**Teilnehmer:** Benjamin Golder, Dennis Eberhard
**Dauer:** ca. 3h

### Erledigtes
- Projektthema gewählt: **Project Workspace Manager** (SharePoint Management Tool)
- Ersten Entwurf der Projektdokumentation erstellt
- PowerPoint-Präsentation für das Projekt erstellt

---

## 2026-03-20

**Teilnehmer:** Benjamin
**Dauer:** ca. 3h

### Erledigtes

**Repository & Grundstruktur**
- GitHub Repository `Project-Workspace-Manager` erstellt (public)
- Initial Commit mit leerer README.md
- Testvorgänge (Test-Dateien) angelegt und wieder entfernt

**Projektdokumentation (Aufgabe 1 & 2 – Anforderungsanalyse)**
- Hauptprojektdokument `Projekt-Workspace-Manager_Projektdokumentation.md` erstellt
- Unternehmensbeschreibung (project-workspace.ch AG, Sitz Kloten ZH) dokumentiert
- Projektauftrag mit Ausgangslage und Zielen definiert
- Stakeholder-Analyse erstellt (Unternehmensleitung/PMO, Projektmanager, IT, Compliance)
- Problemstellung aus Kundensicht formuliert
- 10 funktionale Anforderungen (FA01–FA10) priorisiert und beschrieben
- Nicht-funktionale Anforderungen in 8 Kategorien definiert (Performance, Sicherheit, Benutzerfreundlichkeit, Compliance, Zuverlässigkeit, Wartbarkeit, Skalierbarkeit, Kompatibilität)
- Use-Case-Spezifikationen für FA01 und FA02 detailliert ausgearbeitet
- Systemkontext und Umsysteme dokumentiert (Entra ID, SharePoint, Teams, Graph API, Power Platform)
- Erfolgskriterien und Akzeptanzbedingungen festgelegt
- Iterationsplanung: MVP = FA01–FA04 (Score > 70)

**Ordnerstruktur (Aufgabe 2 – Projektumgebung)**
- Vollständige Ordnerstruktur im Repository aufgebaut:
  - `docs/` (anforderungen, architektur, besprechungen, projektumgebung, entscheidungen)
  - `src/` (power-apps, power-automate, sharepoint mit templates/scripts/listen)
  - `installation/` (checklisten, konfiguration)
  - `tests/` (uat, testfaelle)
- README.md mit Projektbeschreibung, Strukturübersicht und Technologie-Stack aktualisiert

**Entscheidungen / ADRs**
- ADR-Struktur unter `docs/entscheidungen/` eingeführt
- ADR-001 angelegt: Entwicklungsumgebung → Eclipse (IDE) und Visual Paradigm (UML)
- ADR-002 angelegt: GitHub als Versionskontrolle und Kollaborationsplattform
- ADR-003 angelegt: Umfang der 1. Iteration (MVP: FA01–FA04)

**Besprechungsprotokolle**
- Vorlage `_VORLAGE.md` für Besprechungsprotokolle erstellt
- Erstes Besprechungsprotokoll `2026-03-20_Projektstart.md` angelegt (Benjamin & Dennis Eberhard)
- Zweites Besprechungsprotokoll `2026-03-20_GitHub-Eclipse-Integration.md` angelegt
- Arbeitsprotokoll `ARBEITSPROTOKOLL.md` angelegt und mit bisherigem Fortschritt befüllt

### Nächste Schritte
- Kapitel «Projektumgebung» in Hauptdokumentation einbauen (alle 7 Punkte Aufgabe 2)
- Teammitglieder und Arbeitsaufteilung dokumentieren (Punkt 3: Organisation Arbeitsgruppe)
- Installationschecklisten für Eclipse, Visual Paradigm und GitHub erstellen (Punkt 5)
- Glossar anlegen (Punkt 7)

---

## 2026-04-10

**Teilnehmer:** Benjamin
**Dauer:** ca. 2h

### Erledigtes

**Aufgabe 3 – Objektorientierte Analyse (OOA)**
- Ordner `docs/analyse/` angelegt
- Fachklassenmodell erarbeitet (Basis: FA01 & FA02, Iteration 1)
  - 8 Klassenkandidaten identifiziert und begründet
  - Vererbungshierarchie für Projektvorlage (Standard / Kundenprojekt / Intern)
  - Attribute und Assoziationen vollständig modelliert
  - PlantUML-Datei `fachklassenmodell.puml` erstellt (Import in Visual Paradigm)
- Zustandsmodell für Klasse `Projektarbeitsbereich` erstellt
  - 5 fachliche Zustände definiert: Entwurf, In Erstellung, Aktiv, Fehlgeschlagen, Archiviert
  - Alle Übergänge mit Auslösern und Bedingungen spezifiziert
  - PlantUML-Datei `zustandsmodell.puml` erstellt (Import in Visual Paradigm)
  - Fachklassenmodell ergänzt: Status `ARCHIVIERT` in Enum `ArbeitsbereichStatus` aufgenommen
- Glossar mit fachlicher Verantwortlichkeit aller 9 Klassen erstellt
- Vollständige OOA-Dokumentation unter `docs/analyse/aufgabe-3-ooa.md` abgelegt

### Nächste Schritte
- Diagramme in Visual Paradigm (.vpp) finalisieren und als Export (PNG/SVG) in `docs/analyse/` ablegen
- Dennis: Gegenlesen und ergänzen
- Rückmeldungen des Dozenten einarbeiten

---

## 2026-04-11

**Teilnehmer:** Benjamin
**Dauer:** ca. 0.5h

### Erledigtes

**Klassendiagramm (Aufgabe 3 – OOA)**
- Klassendiagramm aus Visual Paradigm als PNG exportiert
- Bild unter `docs/architektur/klassendiagramm.png` abgelegt
- `docs/architektur/README.md` mit Diagramm und Klassenbeschreibungen ergänzt

---

## 2026-04-11 (Unterricht)

**Teilnehmer:** Benjamin Golder, Dennis Eberhard, Arif Chughtai (Dozent), Klasse
**Dauer:** Unterrichtseinheit

### Erledigtes

**Diagrammbesprechung im Unterricht (Aufgabe 3 – OOA)**
- Klassen- und Zustandsdiagramm gemeinsam mit Dozent und Klasse besprochen
- Grundsätzlich korrekt; zwei Korrekturen im Klassendiagramm festgehalten:
  - Abhängigkeiten durch Assoziationen ersetzen
  - Multiplizität zwischen ProjektArbeitsbereich und ProjektVorlage anpassen (keine Komposition)
- Aktualisierte Diagramme (Klassen- und Zustandsdiagramm) in Repository eingecheckt
- Besprechungsprotokoll unter `docs/besprechungen/2026-04-11_Unterricht-Diagrammbesprechung.md` abgelegt

### Nächste Schritte
- Klassendiagramm in Visual Paradigm gemäss Feedback korrigieren und neu exportieren

---

## 2026-05-15

**Teilnehmer:** Benjamin
**Dauer:** ca. 2.5h

### Erledigtes

**Aufgabe 4 – Objektorientiertes Design (OOD)**
- Aufgabenstellung `projektarbeit_aufgabe_4.pdf` gesichtet (Auftrag 1–5, max. 39 Punkte)
- Architektur-Vorgabe übernommen: logische 3-Schichtenarchitektur (Clean Architecture) mit `ConsoleClient` → Domain → `DataAccess`
- Neuer Ordner `docs/design/` angelegt; Hauptdokument `aufgabe-4-ood.md` mit Annahmen, Pattern-Einsatz, allen 5 Aufträgen und Glossar verfasst

**Designmodelle (Auftrag 1 + 2)**
- Persistence-Schicht: `persistence-schicht.puml` (Repository-Implementierungen + `IDataAccess`/`SharePointDataAccess` als Singleton)
- Business-Schicht: `business-schicht.puml` mit:
  - Domain Services: `IArbeitsbereichService`/`ArbeitsbereichService`, `IVorlagenService`/`VorlagenService`
  - Domain Components: `NamenskonventionValidator`, `BerechtigungsManager`
  - Factories (Singleton): `ProjektarbeitsbereichFactory`, `ProjektvorlageFactory`
  - Repository-Schnittstellen (Definition in Business)
  - Domain Objects aus OOA inkl. Vererbung `Projektvorlage` → 3 konkrete Vorlagen
- Pflicht-Patterns Singleton / Factory / Repository sind in Stereotypen markiert; Methoden mit Rückgabe-/Parametertypen, keine Attribute

**Integriertes Designmodell (Auftrag 3)**
- `integriert-komponentendiagramm.puml`: Komponenten + Schnittstellen-Anbindung Presentation → Business → Persistence
- `integriert-klassendiagramm.puml`: alle Klassen/Schnittstellen aus allen 3 Schichten in einem Diagramm

**Sequenzdiagramm (Auftrag 4)**
- `sequenzdiagramm-fa01.puml`: Normalablauf FA01 (Projektarbeitsbereich erstellen)
- Überprüfung dokumentiert: Reihenfolge `Repository.save` vor `AuditLog.save` ist verbindlich
- Keine zusätzlichen Designklassen nötig

**Glossar (Auftrag 5)**
- Verantwortlichkeiten aller Schnittstellen und Klassen aus allen 3 Schichten beschrieben (im Hauptdokument)

**Diagramm-Vereinfachung (Iteration 2)**
- Diagramme bewusst auf das Prüfungs-Minimum verschlankt, um die Nachbildung in Visual Paradigm aufwandsarm zu halten
- Entfernt (für spätere Iterationen vorgesehen): `NamenskonventionValidator`, `BerechtigungsManager`, `IBenutzerRepository`, `IAuditLogRepository` sowie Domain-Objekte `Projektmetadaten`, `Ordner`, `Dokumentvorlage`, `Benutzer`, `Berechtigung`, `AuditLogEintrag` aus dem Designmodell
- Im Designmodell verbleiben: 2 Services + 2 Service-Schnittstellen, 2 Factories (Singleton), 2 Repository-Schnittstellen + Implementierungen, `IDataAccess`/`SharePointDataAccess` (Singleton), Vererbungshierarchie `Projektvorlage` → `StandardVorlage`/`KundenprojektVorlage`/`InternVorlage`
- Alle Pflicht-Patterns (Singleton, Factory, Repository), Schnittstellen mit Methodensignaturen, Vererbung und beide MVP-Use-Cases bleiben sichtbar
- PNGs neu generiert (PlantUML), Hauptdokument `aufgabe-4-ood.md` und Annahmen entsprechend angepasst

### Nächste Schritte
- Diagramme in Visual Paradigm nachbauen und als PNG nach `docs/design/` exportieren (für offizielle Abgabe)
- Dennis: Gegenlesen, ergänzen, Patterns kritisch prüfen
- Rückmeldungen des Dozenten einarbeiten und im Hauptdokument protokollieren

---

## 2026-05-15 (Besprechung Abend)

**Teilnehmer:** Benjamin Golder, Dennis Eberhard
**Dauer:** ca. 1h 30min (Remote, Teams)

### Erledigtes
- Stand Aufgabe 4 (Designdokumentation in `docs/design/`) gemeinsam durchgegangen
- Vereinfachung der Diagramme als angemessen bestätigt (Pflicht-Patterns, Vererbung, FA01/FA02 bleiben sichtbar)
- Arbeitsaufteilung für die Visual-Paradigm-Nachzeichnungen festgelegt:
  - **Dennis:** Business-Schicht-Klassendiagramm, Integriertes Komponentendiagramm
  - **Benjamin:** Persistence-Schicht-Klassendiagramm, Integriertes Klassendiagramm (bis 2026-05-17)
  - **Sequenzdiagramm FA01:** zurückgestellt bis nach Unterrichtsblock zum Thema
- Ausblick auf nächste Etappe (Implementierung) und Umgang mit KI-Unterstützung besprochen
- Besprechungsprotokoll unter `docs/besprechungen/2026-05-15_Aufgabe4-OOD-Arbeitsaufteilung.md` abgelegt

### Nächste Schritte
- Benjamin: VP-Diagramme bis 2026-05-17 nachbauen und PNGs in `docs/design/` einchecken
- Dennis: VP-Diagramme bis zur nächsten Besprechung nachbauen
- Nächste Synchronisation spätestens 2026-05-22
