# ADR-001: Power Platform als Entwicklungsplattform

**Datum:** 2026-03-20
**Status:** Entschieden

## Kontext
Für die Entwicklung des Project-Workspace-Managers muss eine Technologiebasis gewählt werden, die eine enge Integration mit Microsoft 365 (SharePoint, Teams, Entra ID) ermöglicht und gleichzeitig mit den vorhandenen Ressourcen umsetzbar ist.

## Entscheidung
Wir entwickeln den Project-Workspace-Manager auf Basis von **Microsoft Power Platform** (Power Apps als Frontend, Power Automate für die Automatisierungslogik).

## Begründung
- Vollständige native Integration in Microsoft 365 ohne zusätzliche Infrastruktur
- Kein eigener Backend-Server oder Cloud-Hosting nötig
- Authentifizierung über Microsoft Entra ID ist nativ integriert
- Microsoft Graph API direkt aus Power Automate nutzbar
- Geringer Infrastrukturaufwand für ein 2-Personen-Team

## Konsequenzen
- Abhängigkeit von Microsoft 365 Lizenz (Power Apps per App oder per User)
- Einschränkungen bezüglich komplexer UI-Logik in Canvas Apps
- Exportierte Artefakte (.msapp, .zip) werden in GitHub versioniert
