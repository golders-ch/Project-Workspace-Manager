# ADR-001: Entwicklungsumgebung und UML-Tool

**Datum:** 2026-03-20
**Status:** Entschieden

## Kontext
Für die Entwicklung des Project-Workspace-Managers müssen geeignete Werkzeuge für die Softwareentwicklung und die Erstellung von UML-Diagrammen festgelegt werden.

## Entscheidung
Wir verwenden **Eclipse** als Entwicklungsumgebung und **Visual Paradigm** für die Erstellung von UML-Diagrammen.

## Begründung
- Eclipse ist eine etablierte, kostenlose Open-Source-IDE mit breiter Community und Plugin-Unterstützung
- Visual Paradigm bietet umfassende UML-Unterstützung (Klassen-, Sequenz-, Use-Case-Diagramme etc.)
- Beide Tools sind plattformunabhängig (Windows, macOS, Linux)
- Wird in der Schule vom Dozenten genutzt und im Unterricht verwendet.

## Konsequenzen
- Alle Entwickler installieren Eclipse in derselben Version (siehe Installationscheckliste)
- UML-Diagramme werden aus Visual Paradigm exportiert (PNG/SVG) und in `docs/architektur/` abgelegt
- Visual Paradigm Projektdateien (.vpp) werden ebenfalls ins Repository eingecheckt
