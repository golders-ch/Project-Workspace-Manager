# Arbeitsprotokoll – Project-Workspace-Manager

Dieses Protokoll dokumentiert alle Arbeitsschritte und Fortschritte im Projektverlauf.

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
