# Project-Workspace-Manager

M365 Self-Service-Anwendung zur automatisierten Verwaltung von SharePoint-Projektarbeitsbereichen.

## Projektbeschreibung

Der Project-Workspace-Manager ermöglicht Projektmanagern die eigenständige Erstellung standardisierter SharePoint-Projektarbeitsbereiche ohne IT-Eingriff. Die Lösung wird als Java-Anwendung umgesetzt, die über die Microsoft Graph API auf Microsoft 365 (SharePoint Online) zugreift.

## Ordnerstruktur

```
Project-Workspace-Manager/
│
├── docs/                          # Dokumentation
│   ├── anforderungen/             # Funktionale & nicht-funktionale Anforderungen
│   ├── architektur/               # Systemarchitektur & Diagramme
│   ├── besprechungen/             # Besprechungsprotokolle
│   └── projektumgebung/           # Projektumgebung (Aufgabe 2)
│
├── prototype-java/                # Java-Prototyp (OOD-Implementierung, Iteration 1)
│
├── src/                           # Backend-Definitionen
│   └── sharepoint/                # SharePoint-Backend (von der Java-App via Graph bereitgestellt)
│       ├── templates/             # Site Templates (.xml)
│       └── listen/                # Listen-Schemas (JSON)
│
├── installation/                  # Installation & Setup
│   ├── checklisten/               # Schritt-für-Schritt Installationschecklisten
│   └── konfiguration/             # Konfigurationsvorlagen
│
├── tests/                         # Tests & Qualitätssicherung
│   ├── uat/                       # User Acceptance Test Protokolle
│   └── testfaelle/                # Testfallbeschreibungen je Anforderung
│
└── Projekt-Workspace-Manager_Projektdokumentation.md   # Hauptprojektdokument
```

## Technologie-Stack

| Komponente | Technologie |
|---|---|
| Anwendung / Logik | Java (Konsolen-Anwendung, später grafische Oberfläche) |
| Backend / Storage | SharePoint Online |
| API | Microsoft Graph API |
| Authentifizierung | Microsoft Entra ID (Azure AD) |
| Versionskontrolle | GitHub |

## Projektdokumentation

Die vollständige Projektdokumentation befindet sich in [`Projekt-Workspace-Manager_Projektdokumentation.md`](./Projekt-Workspace-Manager_Projektdokumentation.md).
