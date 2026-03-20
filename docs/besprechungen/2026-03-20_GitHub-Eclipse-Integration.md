# Besprechung – 2026-03-20 GitHub & Eclipse Integration

**Datum:** 2026-03-20
**Teilnehmer:** Benjamin Golder, Dennis Eberhard
**Dauer:** k.A.

> Hinweis: Dieses Protokoll wurde auf Basis einer KI-generierten Besprechungszusammenfassung erstellt. Inhalte wurden nicht vollständig auf Richtigkeit geprüft.

---

## Traktanden
1. Projektorganisation und GitHub-Setup
2. GitHub-Integration in Eclipse
3. Projektstruktur und Dokumentation
4. KI-Tools im Projektworkflow

---

## Besprochenes

### Projektorganisation & GitHub-Setup
- Projektname **«Project Workspace Manager»** wurde definitiv festgelegt
- Dennis hat einen neuen GitHub-Account mit der Juventus-E-Mail-Adresse erstellt
- Benjamin hat Dennis zum Repository eingeladen und Admin-Rechte vergeben
- Repository wurde auf **public** gesetzt, damit der Dozent Zugriff hat
- Alternativen (GitLab, Gitea) wurden kurz verglichen — Entscheidung bleibt bei **GitHub** (→ ADR-002)
- GitHub als zentrale Plattform für Projektmanagement, Dokumentation und Versionskontrolle bestätigt

### GitHub-Integration in Eclipse
- Verschiedene Import-Methoden für das GitHub-Repository in Eclipse getestet
- **Smart Import**-Funktion in Eclipse erfolgreich verwendet
- Authentifizierungsprobleme mit GitHub-Token identifiziert und gelöst:
  - Neuer GitHub-Token generiert und in Eclipse konfiguriert
  - **Commit-Signierung in GitHub deaktiviert**, um Push-Probleme zu beheben
- Push/Commit/Pull-Workflow zwischen Eclipse und GitHub funktioniert nach Anpassungen korrekt
- Problem mit leerem Autor-Feld beim Commit besprochen (noch offen)
- Testordner erstellt, gepusht und erfolgreich wieder gelöscht — Synchronisation bestätigt

### Projektstruktur & Dokumentation
- Ordnerstruktur im GitHub-Repository gemeinsam aufgebaut und besprochen
- Entscheidung: Protokolle und Entscheidungsdokumente werden direkt im Repository geführt
- Besprechungsaufnahmen sollen als Entscheidungsprotokoll im System dokumentiert werden
- Notwendigkeit eines Projekttagebuchs / Arbeitsprotokolls festgehalten (→ `ARBEITSPROTOKOLL.md`)
- Diskussion über Views, Wikis und Roadmap-Funktionen in GitHub

### KI-Tools im Projektworkflow
- **KI-Tool** wird als Assistent für Dokumentation und Entscheidungsprotokolle eingesetzt
- Benjamin erklärt Dennis die Nutzung von KI-Tool für Projektarbeit
- Hinweis: Im Geschäft von Dennis sind nur Copilot und GPT für Textverbesserung erlaubt — KI-Tool nicht
- Datenschutzfrage beim Einsatz von KI-Tool in Unternehmen bleibt offen

---

## Beschlüsse

| Entscheidung | Verweis |
|---|---|
| GitHub als zentrale Kollaborationsplattform | ADR-002 |
| Eclipse als Entwicklungsumgebung | ADR-001 |
| Projektname: «Project Workspace Manager» | — |
| Commit-Signierung in GitHub deaktiviert | — |
| Protokolle und ADRs direkt im Repository führen | — |
| KI-Tool als Dokumentationsassistent einsetzen | — |
| Serientermin für regelmässige Besprechungen einrichten | offen |

---

## Offene Punkte / Folgeaufgaben

| Aufgabe | Verantwortlich | Fällig bis |
|---|---|---|
| Serientermin für Besprechungen einrichten | Benjamin & Dennis | k.A. |
| Autor-Feld beim Commit in Eclipse konfigurieren | Dennis | k.A. |
| Datenschutzfrage KI-Tool in Unternehmen klären | Dennis | k.A. |
| KI-Tool-Integration in Projektworkflow definieren | Benjamin | k.A. |
