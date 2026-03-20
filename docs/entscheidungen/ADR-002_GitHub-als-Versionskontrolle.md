# ADR-002: GitHub als Versionskontrolle und Kollaborationsplattform

**Datum:** 2026-03-20
**Status:** Entschieden

## Kontext
Für die Zusammenarbeit im Team und die Versionierung von Dokumentation und Entwicklungsartefakten wird eine Kollaborationsplattform benötigt.

## Entscheidung
Wir verwenden **GitHub** als zentrale Plattform für Versionskontrolle, Dokumentation und Aufgabenverwaltung.

## Begründung
- Bekannte und weit verbreitete Plattform mit guter Tooling-Unterstützung
- Kostenlos für öffentliche Repositories
- Markdown-Unterstützung für Projektdokumentation direkt im Repository
- Issues und Projects für einfaches Aufgabenmanagement nutzbar
- Ermöglicht nachvollziehbare Änderungshistorie für Dokumentation und Artefakte

## Konsequenzen
- Power Apps und Power Automate Artefakte müssen manuell exportiert und ins Repository eingecheckt werden (kein nativer Git-Connector)
- Alle Teammitglieder benötigen einen GitHub-Account
