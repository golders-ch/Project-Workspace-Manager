# Arbeitsprotokoll – Project-Workspace-Manager

Dieses Protokoll dokumentiert alle Arbeitsschritte und Fortschritte im Projektverlauf.

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
