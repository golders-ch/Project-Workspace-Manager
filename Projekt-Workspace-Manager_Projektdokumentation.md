# Project – Workspace – Manager

Projektdokumentation für M365-Self-Service-Anwendung zur automatisierten SharePoint-Projektarbeitsbereich-Verwaltung

---

# Projektauftrag und Analyse

## Grundlagen und strategische Ausrichtung

Dieser erste Auftrag umfasst die grundlegende Projektdefinition und strategische Ausrichtung des Projekt-Workspace-Managers. Er beinhaltet die Unternehmensbeschreibung, den formalen Projektauftrag mit Ausgangslage und Zielen, eine detaillierte Stakeholder-Analyse sowie die zentrale Problemstellung aus Kundensicht. Diese Komponenten bilden das Fundament für die spätere Anforderungsanalyse und stellen sicher, dass alle Projektbeteiligten ein gemeinsames Verständnis über Kontext, Ziele und Erwartungen haben.

---

# Unternehmensbeschreibung

## project-workspace.ch AG

Die project-workspace.ch AG ist ein innovatives Startup mit Sitz in Kloten (ZH), das sich auf die Entwicklung und den Vertrieb moderner Softwarelösungen für das Projektmanagement spezialisiert hat. Das Unternehmen wurde mit dem Ziel gegründet, die digitale Zusammenarbeit in Organisationen nachhaltig zu verbessern und effiziente Tools bereitzustellen, die den Arbeitsalltag von Projektmanagern und Teams erleichtern. Aktuell beschäftigt die AG zwei engagierte Mitarbeiter, die sowohl für die technische Entwicklung als auch für den Vertrieb der Hauptlösung, Project-Workspace, verantwortlich sind. Die Software Project-Workspace ermöglicht Unternehmen die automatisierte Verwaltung von SharePoint-Projektarbeitsbereichen und unterstützt sie dabei, Projekte schneller zu starten, Prozesse zu standardisieren und die IT-Abteilung zu entlasten. Durch den Einsatz neuester Technologien und einen klaren Fokus auf Benutzerfreundlichkeit und Skalierbarkeit positioniert sich project-workspace.ch AG als zuverlässiger Partner für Organisationen, die ihre Projektlandschaft digital und effizient gestalten möchten.

---

# Projektauftrag

## Ausgangslage, Aufgabenstellung und Ziele

- Verkürzung der Bereitstellungszeit für SharePoint-Projektarbeitsbereiche von bisher 3–5 Tagen auf unter 5 Minuten
- Reduzierung des administrativen Aufwands für die IT-Abteilungen um mindestens 60 % durch Self-Service-Funktionen
- Standardisierung der Arbeitsbereich-Strukturen zur Förderung einer effizienteren Zusammenarbeit und besseren Dokumentation
- Steigerung der Autonomie und Effizienz der Anwender durch eigenständige Einrichtung von SharePoint-Arbeitsbereichen

Im herkömmlichen Vorgehen vieler Unternehmen ist die Erstellung neuer SharePoint-Sites ausschliesslich Aufgabe der IT-Abteilung. Dies führt in der Praxis zu langwierigen Prozessen (Antragsstellungen, Freigaben), die die Projekt- und Arbeitsgeschwindigkeit deutlich ausbremsen. Alternativ führt eine offene Struktur, bei der alle Mitarbeitenden eigenständig Sites anlegen dürfen, meist zu Datenchaos und mangelnder Governance. Die angebotene Lösung schafft einen Mittelweg: Sie verbindet die Vorteile eines schnellen, kontrollierten Self-Service mit klaren Standards und Regeln.

---

# Stakeholder-Analyse

## Identifikation und Charakterisierung der Projektbeteiligten

| Stakeholder-Gruppe | Hauptinteressen | Erwartungen an die Lösung |
|---|---|---|
| Kunden (Unternehmensleitung und PMO) | Strategische Kennzahlen, Governance-Konformität, ROI durch IT-Kostensenkung | Effizienzsteigerung im Projektmanagement, Compliance-Nachweise, Unterstützung unternehmensweiter Projektinitiativen |
| Benutzer (Projektmanager und Teams) | Benutzerfreundlichkeit, schnelle Verfügbarkeit, Flexibilität | Intuitive Bedienung, zuverlässige Arbeitsbereich-Erstellung, nahtlose Integration mit MS Teams und SharePoint |
| IT-Abteilung | Systemstabilität, Sicherheit, reduzierter Administrationsaufwand | Automatisierte Provisionierung, standardisierte Konfigurationen, Self-Service-Modell |
| Compliance-Team | Datenschutz, Sicherheit, regulatorische Konformität | Zugriffskontrolle, Datenklassifizierung, Audit-Trails, Einhaltung von Governance-Richtlinien |

---

# Problemstellung

## Warum benötigen Kunden das Software-Produkt?

Kunden stehen vor dem Problem, dass die Bereitstellung von Projektarbeitsbereichen in SharePoint zeitaufwendig, unflexibel und fehleranfällig ist. Projektteams müssen oftmals mehrere Tage auf die Freischaltung ihres Arbeitsbereichs warten, was wertvolle Zeit kostet und den schnellen Projektstart erschwert. Diese Verzögerungen beeinträchtigen die Wettbewerbsfähigkeit erheblich, insbesondere in dynamischen Märkten mit hohem Projektaufkommen.

Zudem entstehen durch nicht standardisierte Arbeitsbereichserstellungen Inkonsistenzen, die die Zusammenarbeit behindern und den Wissenstransfer erschweren. Fehlende automatisierte Kontrollmechanismen erhöhen das Risiko von Zugriffsfehlern und Sicherheitslücken.

Die Software löst dieses Problem durch Automatisierung, Standardisierung und Self-Service. Projektteams können eigenständig und unmittelbar neue Arbeitsbereiche anlegen, während Governance-Vorgaben sowie Sicherheits- und Compliance-Anforderungen automatisch eingehalten werden.

---

# Anforderungsanalyse

## Funktionale und nicht-funktionale Spezifikationen

Der zweite Auftrag fokussiert sich auf die detaillierte Anforderungsanalyse. Er umfasst Priorisierungskriterien für funktionale Anforderungen, eine priorisierte Anforderungsliste, nicht-funktionale Anforderungen sowie detaillierte Use-Case-Spezifikationen der zwei höchstpriorisierten Funktionen.

---

# Priorisierungskriterien für funktionale Anforderungen

## Geschäftswert und technische Machbarkeit als Bewertungsmassstäbe

**Kriterium 1 – Geschäftswert (Skala 1–10):** Bewertet den direkten Nutzen für die Stakeholder. Faktoren: Zeitersparnis, Reduktion von IT-Aufwand, Verbesserung der Governance-Konformität, Beitrag zu strategischen Zielen, Benutzerzufriedenheit.

**Kriterium 2 – Technische Machbarkeit (Skala 1–10, höhere Werte = einfachere Umsetzung):** Bewertet Implementierungskomplexität und Ressourcenanforderungen. Faktoren: Verfügbarkeit von M365 APIs, Entwicklungszeit, Abhängigkeiten, UI-Komplexität, Spezialkenntnisse.

**Gesamtpriorität:** Geschäftswert × Technische Machbarkeit = Prioritätsscore (max. 100)

---

# Funktionale Anforderungen (priorisiert)

## Nach Priorität absteigend sortierte Anforderungsliste

| ID | Anforderung | Geschäftswert | Machbarkeit | Score |
|---|---|---|---|---|
| **FA01** | Projektarbeitsbereich erstellen | 10 | 9 | **90** |
| **FA02** | Vordefinierte Projektvorlagen auswählen | 9 | 9 | **81** |
| **FA03** | Basisberechtigung konfigurieren | 9 | 8 | **72** |
| **FA04** | Automatische Namenskonvention anwenden | 8 | 9 | **72** |
| **FA05** | Projekt-Metadaten erfassen | 7 | 9 | **63** |
| **FA06** | Teams-Integration aktivieren | 8 | 7 | **56** |
| **FA07** | Dokumentvorlagen initialisieren | 6 | 9 | **54** |
| **FA08** | Dashboard zur Arbeitsbereichs-Übersicht | 7 | 7 | **49** |
| **FA09** | Genehmigungs-Workflow für sensible Projekte | 7 | 6 | **42** |
| **FA10** | Automatische Archivierung nach Projektende | 6 | 6 | **36** |

### Beschreibungen

- **FA01** – Benutzer können über ein Formular einen neuen SharePoint-Projektarbeitsbereich mit vordefinierter Struktur erstellen.
- **FA02** – System bietet mindestens drei Projektvorlagen (Standard, Kundenprojekt, Intern) mit unterschiedlichen Ordnerstrukturen und Einstellungen.
- **FA03** – Projektmanager können Projektmitglieder mit Standardrollen (Owner, Member, Visitor) hinzufügen.
- **FA04** – System erzwingt standardisierte Benennungsregeln (z.B. `PRJ-[Jahr]-[Kürzel]-[Name]`).
- **FA05** – Erfassung von Projektcode, Kostenstelle, Startdatum, Enddatum und Projektleiter.
- **FA06** – Option zum automatischen Erstellen eines verbundenen Microsoft Teams für den Projektarbeitsbereich.
- **FA07** – Automatisches Bereitstellen von Standarddokument-Templates basierend auf gewählter Projektvorlage.
- **FA08** – Zentrale Übersicht aller vom Benutzer erstellten oder zugewiesenen Projektarbeitsbereiche.
- **FA09** – Optionaler Genehmigungsprozess für Arbeitsbereiche mit externem Zugriff oder erhöhter Vertraulichkeit.
- **FA10** – System markiert abgeschlossene Arbeitsbereiche als schreibgeschützt und verschiebt sie in Archivbereich.

---

# Nicht-funktionale Anforderungen

## Qualitätsmerkmale und Systemeinschränkungen

### Performance
- Erstellung eines Projektarbeitsbereichs innerhalb von 5 Minuten
- Benutzeroberfläche reagiert innerhalb von 1 Sekunde auf Eingaben

### Sicherheit
- Verschlüsselte Datenübertragungen
- Authentifizierung ausschliesslich über Microsoft Entra ID (Azure AD)
- Prinzip der geringsten Rechte (Least Privilege)
- Rollenbasierte Zugriffskontrolle (RBAC) obligatorisch

### Benutzerfreundlichkeit
- Bedienbar ohne SharePoint-Spezialkenntnisse
- Maximal 8–10 Eingabefelder im Formular
- Verfügbar in Deutsch und Englisch
- Kontextsensitive Hilfetexte und Tooltips

### Compliance
- DSGVO-konform
- Audit-Logs für alle Erstellungs- und Berechtigungsänderungen
- Unterstützung von Datenschutz-Klassifizierungen

### Zuverlässigkeit
- Verfügbarkeit von 99,5 % während der Geschäftszeiten
- Aussagekräftige Fehlermeldungen mit Lösungsvorschlägen

### Wartbarkeit
- Microsoft Entwicklungsstandards
- Ausführliche Dokumentation
- Modulare Architektur für einfache Erweiterungen

### Skalierbarkeit
- Mindestens 100 gleichzeitige Benutzer
- Skalierbar auf bis zu 1.000 Mitarbeiter

### Kompatibilität
- Vollständige Integration in Microsoft 365 (SharePoint Online, Teams, Power Platform)
- Unterstützung moderner Webbrowser (Chrome, Edge, Firefox, Safari)

---

# Use-Case-Spezifikation 1 (Höchste Priorität)

## Projektarbeitsbereich erstellen

**Titel:** Projektarbeitsbereich erstellen

**Aktoren:** Primär: Projektmanager | Sekundär: System (Projekt-Workspace-Manager), SharePoint Online API

**Vorbedingungen:**
- Projektmanager ist authentifiziert und hat Berechtigung zur Erstellung
- Zugriff auf die Anwendung besteht
- Mindestens eine Projektvorlage ist konfiguriert

**Hauptablauf:**

1. Projektmanager navigiert zur Funktion „Neuen Projektarbeitsbereich erstellen"
2. System zeigt Formular mit Feldern: Projektname, Projektbeschreibung, Projektvorlage (Dropdown), Kostenstelle, Startdatum, Enddatum
3. Projektmanager füllt Pflichtfelder aus und wählt Projektvorlage (Standard, Kundenprojekt oder Intern)
4. System validiert Eingaben: vollständige Pflichtfelder, Namenskonvention, Eindeutigkeit des Namens
5. Projektmanager klickt auf „Arbeitsbereich erstellen"
6. System erstellt SharePoint-Site mit gewählter Vorlage, wendet Namenskonvention an (`PRJ-[Jahr]-[Kürzel]-[Name]`), richtet Ordnerstruktur ein (Dokumente, Besprechungen, Berichte, Vorlagen), konfiguriert Basisberechtigung und speichert Metadaten
7. System zeigt Bestätigungsmeldung mit Link zum neuen Arbeitsbereich
8. Projektmanager wird zum neuen SharePoint-Projektarbeitsbereich weitergeleitet

**Nachbedingungen:**
- Neuer SharePoint-Projektarbeitsbereich mit standardisierter Struktur existiert
- Projektmanager hat Owner-Berechtigungen
- Projekt-Metadaten sind gespeichert und für Reporting verfügbar
- Audit-Log dokumentiert die Erstellungsaktion mit Zeitstempel und Benutzer

---

# Use-Case-Spezifikation 2 (Zweithöchste Priorität)

## Vordefinierte Projektvorlage auswählen

**Titel:** Vordefinierte Projektvorlage auswählen

**Aktoren:** Primär: Projektmanager | Sekundär: System (Projekt-Workspace-Manager)

**Vorbedingungen:**
- Projektmanager befindet sich im Erstellungsformular
- Mindestens drei Projektvorlagen (Standard, Kundenprojekt, Intern) sind konfiguriert
- Jede Vorlage verfügt über definierte Ordnerstrukturen, Dokumentvorlagen und Standardeinstellungen

**Hauptablauf:**

1. System zeigt Dropdown-Feld „Projektvorlage" im Erstellungsformular
2. Projektmanager klickt auf Dropdown-Feld
3. System zeigt verfügbare Vorlagen mit Beschreibungen:
   - **Standard** – allgemeine Projekte mit Basisdokumentation
   - **Kundenprojekt** – externe Stakeholder, erweiterte Berechtigungen, Kundenbereich
   - **Intern** – interne Initiativen, vereinfachte Struktur
4. Projektmanager wählt passende Vorlage basierend auf Projekttyp
5. System zeigt Vorschau der Ordnerstruktur und enthaltenen Dokument-Templates
6. Projektmanager bestätigt Auswahl durch Fortsetzen des Formulars
7. System merkt gewählte Vorlage für späteren Erstellungsprozess vor

**Nachbedingungen:**
- Gewählte Projektvorlage ist für den Erstellungsprozess gespeichert
- Benutzer hat klare Vorstellung von Struktur und Inhalten des zukünftigen Arbeitsbereichs
- System ist bereit, Vorlage bei finaler Bestätigung anzuwenden
- Vorlagen-Auswahl ist noch änderbar bis zur endgültigen Erstellung

---

# Erweiterte Anforderungsdetails

## Ergänzende Spezifikationen

### Fehlerbehandlung und Alternativszenarien
- Bei Validierungsfehlern: spezifische, benutzerfreundliche Fehlermeldungen (z.B. „Projektname existiert bereits")
- Bei API-Fehlern oder SharePoint-Nichtverfügbarkeit: Benutzer wird informiert, Anfrage kann später wiederholt werden
- Entwürfe können zwischengespeichert werden

### Berechtigungs- und Compliance-Anforderungen
- Nur authentifizierte Benutzer mit Rolle „Projektmanager" oder höher können Arbeitsbereiche erstellen
- Jede Erstellungsaktion wird auditiert (Benutzer, Zeitstempel, Optionen, Ergebnis)
- Optionaler Genehmigungsprozess (FA09) für Projekte mit externem Zugriff oder erhöhter Vertraulichkeit

### Integrations-Anforderungen
- Nahtlose Integration in Microsoft Teams als App oder Tab
- Single Sign-On über Microsoft Entra ID
- Microsoft Graph API für SharePoint-Operationen
- Benachrichtigungen über Teams-Kanäle oder E-Mail

### Zukünftige Erweiterungen
- Detailliertes Dashboard mit Projektstatistiken und Nutzungsmetriken
- Automatisierte Lifecycle-Verwaltung (Archivierungs- und Löschrichtlinien)
- Erweiterte Berechtigungsverwaltung mit benutzerdefinierten Rollen
- Granulare Reporting-Funktionen für PMO und Management

---

# Anforderungszusammenfassung

## Quantitative Übersicht der Anforderungsverteilung

Die Anforderungsanalyse umfasst insgesamt **10 funktionale Anforderungen** und **8 Kategorien nicht-funktionaler Anforderungen**.

| Prioritätsstufe | Anforderungen | Score-Bereich |
|---|---|---|
| Iteration 1 (MVP) | FA01, FA02, FA03, FA04 | > 70 |
| Iteration 2 | FA05, FA06, FA07 | 50–65 |
| Spätere Iterationen | FA08, FA09, FA10 | < 50 |

Mit 40 % der Anforderungen in der ersten Iteration wird ein solides Fundament für das Minimum Viable Product (MVP) geschaffen. Die verbleibenden Anforderungen verteilen sich gleichmässig auf die zweite (30 %) und spätere Iterationen (30 %), was eine schrittweise Funktionserweiterung basierend auf Anwenderfeedback ermöglicht.

Die nicht-funktionalen Anforderungen verteilen sich auf 8 Qualitätskategorien, wobei **Sicherheit, Performance und Benutzerfreundlichkeit** die höchsten Prioritäten darstellen.

---

# Erfolgskriterien und Akzeptanzbedingungen

## Messbare Ziele für den Projektabschluss

### Technische Erfolgskriterien
- Vollständige Implementierung und erfolgreiche Tests von FA01–FA04
- Erstellungszeit für Projektarbeitsbereiche < 5 Minuten
- Alle nicht-funktionalen Anforderungen in Sicherheit, Performance und Benutzerfreundlichkeit erfüllt
- ≥ 95 % der Erstellungsversuche ohne Fehler abgeschlossen

### Geschäftliche Erfolgskriterien
- Reduktion der IT-Administrationsanfragen für SharePoint-Sites um ≥ 60 % innerhalb von 3 Monaten
- ≥ 80 % der Projektmanager nutzen die Self-Service-Lösung innerhalb von 6 Monaten
- Benutzerzufriedenheit ∅ 4/5 Sternen in Nutzerbefragungen
- ROI erreicht innerhalb von 12 Monaten durch eingesparte IT-Ressourcen

### Akzeptanzbedingungen
- Erfolgreiche User Acceptance Tests (UAT) mit ≥ 10 Projektmanagern aus verschiedenen Abteilungen
- Vollständige Dokumentation (Benutzerhandbuch, Administratorenhandbuch, technische Systemdokumentation)
- Schulung für alle Projektmanager (≥ 90 % Teilnahmequote) abgeschlossen
- Formale Freigabe durch PMO und IT-Leitung erteilt

---

# Systemkontext

## Einbettung des Projekt-Workspace-Managers in die Systemlandschaft

Der Systemkontext beschreibt die Einbettung des Projekt-Workspace-Managers in seine technische und organisatorische Umgebung sowie die Interaktionen mit den relevanten Umsystemen.

## Benutzergruppen

- **Projektmanager** – Primäre Benutzer. Erstellen Arbeitsbereiche, wählen Vorlagen, erfassen Metadaten und verwalten Projektmitglieder.
- **Projektteams** – Nutzen die bereitgestellten Arbeitsbereiche zur Zusammenarbeit, Dokumentenablage und Kommunikation.
- **IT-Abteilung und Compliance-Team** – Zentrale Stakeholder, profitieren von reduziertem Aufwand und eingehaltenen Governance-Vorgaben.

---

# Umsysteme

| Umsystem | Funktion |
|---|---|
| **Microsoft Entra ID (Azure AD)** | Authentifizierung und Autorisierung der Benutzer |
| **SharePoint Online** | Zentrales Zielsystem zur Erstellung und Verwaltung der Projektarbeitsbereiche |
| **Microsoft Teams** | Optional angebunden für Teamerstellung und Zusammenarbeit |
| **Microsoft Graph API** | Technische Schnittstelle für Site-Erstellung, Berechtigungen und Metadaten |
| **Power Platform (Power Apps & Power Automate)** | Technologische Basis für Benutzeroberfläche, Geschäftslogik und Automatisierungen |

---

# Zusammenfassung und nächste Schritte

## Übergang von Anforderungsanalyse zur Implementierung

Auftrag 1 hat den Kontext durch Unternehmensbeschreibung, Projektauftrag, Stakeholder-Analyse und Problemstellung etabliert. Auftrag 2 hat systematisch funktionale und nicht-funktionale Anforderungen definiert, priorisiert und die zwei wichtigsten Use-Cases detailliert spezifiziert.

**Nächste Schritte:**

1. **Systemarchitektur-Design** – basierend auf den identifizierten Anforderungen unter Nutzung von Power Apps, Power Automate und SharePoint Framework
2. **Entwicklungsplanung** – Sprint-Einteilung, erste Iteration adressiert FA01–FA04
3. **Prototyp-Entwicklung** – für Benutzer-Feedback und frühzeitige Validierung der UX
4. **Sicherheits- und Compliance-Review** – durch IT-Security und Datenschutzbeauftragte vor Produktivsetzung
5. **Pilot-Phase** – mit 15–20 ausgewählten Projektmanagern für 4 Wochen
6. **Vollständiger Rollout** – mit begleitenden Schulungen, Kommunikationskampagne und Support-Bereitschaft

Die strukturierte Anforderungsdokumentation ermöglicht agile Entwicklung mit klaren Akzeptanzkriterien für jede User Story. Regelmässige Reviews mit Stakeholdern stellen sicher, dass die Lösung kontinuierlich an Geschäftsbedürfnisse angepasst wird.
