# Fachklassenmodell – Project-Workspace-Manager

**Umgesetzte funktionale Anforderungen:**
- **FA01** – Projektarbeitsbereich erstellen (Prioritätsscore: 90)
- **FA02** – Vordefinierte Projektvorlage auswählen (Prioritätsscore: 81)

![Fachklassenmodell](Fachklassenmodell_Project-Workspace-Manager.png)

---

## Klassen

### Projektarbeitsbereich
Zentrale Klasse des Modells. Repräsentiert einen erstellten SharePoint-Projektarbeitsbereich (FA01). Speichert die eindeutige ID, den nach Namenskonvention `PRJ-[Jahr]-[Kürzel]-[Name]` formatierten Namen, eine Beschreibung, die URL der SharePoint-Site, den aktuellen Lebenszyklus-Status sowie das Erstellungsdatum.

| Attribut | Typ | Beschreibung |
|---|---|---|
| id | String | Eindeutiger Bezeichner |
| name | String | Name gemäss Namenskonvention |
| beschreibung | String | Kurzbeschreibung des Projekts |
| url | String | URL zur SharePoint-Site |
| status | ArbeitsbereichStatus | Aktueller Zustand im Lebenszyklus |
| erstellungsDatum | Date | Datum der Erstellung |

**Assoziationen:**
- Hat genau eine `Projektmetadaten` (Komposition)
- Hat eine oder mehrere `AuditLogEintrag`-Einträge (Komposition)
- Basiert auf genau einer `Projektvorlage`
- Hat null oder mehrere `Berechtigung`-Einträge (Komposition)
- Wird von einem `Benutzer` erstellt

---

### Projektmetadaten
Hält die fachlichen Kenndaten eines Projekts (FA01, Schritt 2: Formularfelder). Wird beim Erstellen des Arbeitsbereichs erfasst und separat persistiert, damit sie für unternehmensweites Reporting zur Verfügung stehen.

| Attribut | Typ | Beschreibung |
|---|---|---|
| projektCode | String | Eindeutiger Projektcode |
| kostenstelle | String | Kostenstelle für Verrechnung |
| startDatum | Date | Geplantes Projektstart-Datum |
| endDatum | Date | Geplantes Projektende-Datum |
| projektleiter | String | Name des verantwortlichen Projektleiters |

---

### Benutzer
Repräsentiert einen authentifizierten Projektmanager, der berechtigt ist, Projektarbeitsbereiche zu erstellen (FA01, Akteur). Die Authentifizierung erfolgt über Microsoft Entra ID (ausserhalb des Fachmodells).

| Attribut | Typ | Beschreibung |
|---|---|---|
| id | String | Eindeutiger Bezeichner (aus Entra ID) |
| name | String | Vollständiger Name |
| email | String | E-Mail-Adresse |

**Assoziationen:**
- Erstellt null oder mehrere `Projektarbeitsbereich`-Objekte
- Ist null oder mehreren `Berechtigung`-Einträgen zugewiesen

---

### Berechtigung
Definiert die Zugriffsrechte eines Benutzers auf einen bestimmten Projektarbeitsbereich (FA01, Schritt 6: Basisberechtigung konfigurieren). Der erstellende Projektmanager erhält automatisch die Rolle OWNER.

| Attribut | Typ | Beschreibung |
|---|---|---|
| rolle | BenutzerRolle | Zugriffsrolle: OWNER, MEMBER oder VISITOR |

---

### AuditLogEintrag
Protokolliert jede Aktion auf einem Projektarbeitsbereich unveränderlich (FA01, Nachbedingung: Audit-Log mit Zeitstempel und Benutzer). Dient der Compliance-Konformität (DSGVO) und Nachvollziehbarkeit.

| Attribut | Typ | Beschreibung |
|---|---|---|
| zeitstempel | DateTime | Zeitpunkt der Aktion |
| aktion | String | Beschreibung der ausgeführten Aktion |
| ergebnis | String | Ergebnis der Aktion (Erfolg / Fehler) |

---

### Projektvorlage *(abstrakt)*
Abstrakte Basisklasse für alle Vorlagentypen (FA02). Definiert den gemeinsamen Rahmen: Bezeichnung, Beschreibung sowie die zugehörigen Ordner und Dokumentvorlagen. Wird beim Erstellen eines Arbeitsbereichs ausgewählt und bestimmt dessen Struktur.

| Attribut | Typ | Beschreibung |
|---|---|---|
| id | String | Eindeutiger Bezeichner |
| bezeichnung | String | Anzeigename im Dropdown (FA02, Schritt 3) |
| beschreibung | String | Kurzbeschreibung für die Vorschau (FA02, Schritt 5) |

**Assoziationen:**
- Definiert eine oder mehrere `Ordner` (Komposition)
- Enthält null oder mehrere `Dokumentvorlage`-Objekte (Komposition)

**Vererbung:** Die drei konkreten Vorlagentypen erben von dieser Klasse, da sie gemeinsame Attribute und Beziehungen teilen, sich jedoch in Struktur und Konfiguration unterscheiden.

---

### StandardVorlage
Konkrete Vorlage für allgemeine interne Projekte (FA02, Option „Standard"). Enthält eine Basisordnerstruktur: Dokumente, Besprechungen, Berichte, Vorlagen.

---

### KundenprojektVorlage
Konkrete Vorlage für Projekte mit externen Stakeholdern (FA02, Option „Kundenprojekt"). Ergänzt die Standardstruktur um einen separaten Kundenbereich mit eingeschränkten Zugriffsrechten.

| Attribut | Typ | Beschreibung |
|---|---|---|
| kundenbereichAktiv | Boolean | Gibt an, ob der Kundenbereich aktiviert ist |

---

### InternVorlage
Konkrete Vorlage für interne Initiativen ohne externen Zugriff (FA02, Option „Intern"). Bietet eine vereinfachte Ordnerstruktur für interne Vorhaben.

---

### Ordner
Repräsentiert einen vordefinierten Ablagebereich innerhalb eines Projektarbeitsbereichs (FA01, Schritt 6: Ordnerstruktur einrichten). Reihenfolge und Benennung sind durch die gewählte Vorlage festgelegt.

| Attribut | Typ | Beschreibung |
|---|---|---|
| bezeichnung | String | Name des Ordners (z. B. „Dokumente") |
| pfad | String | Relativer Pfad innerhalb der SharePoint-Site |
| reihenfolge | Integer | Anzeigereihenfolge im Arbeitsbereich |

---

### Dokumentvorlage
Repräsentiert ein Standarddokument, das beim Erstellen des Arbeitsbereichs automatisch bereitgestellt wird (FA02, Schritt 5: Vorschau der enthaltenen Dokument-Templates). Ermöglicht sofortigen Projektstart ohne manuelle Dokumenterstellung.

| Attribut | Typ | Beschreibung |
|---|---|---|
| bezeichnung | String | Anzeigename des Dokuments |
| dateiname | String | Dateiname inkl. Erweiterung |
| typ | String | Dokumenttyp (z. B. „docx", „xlsx") |

---

## Enumerationen

### ArbeitsbereichStatus
| Wert | Bedeutung |
|---|---|
| ENTWURF | Formular gespeichert, Arbeitsbereich noch nicht erstellt |
| IN_ERSTELLUNG | Provisionierung in SharePoint läuft |
| AKTIV | Arbeitsbereich erfolgreich erstellt und nutzbar |
| FEHLGESCHLAGEN | Erstellung fehlgeschlagen (technischer Fehler) |
| ARCHIVIERT | Projekt abgeschlossen, Arbeitsbereich schreibgeschützt |

### BenutzerRolle
| Wert | Bedeutung |
|---|---|
| OWNER | Voller Zugriff und Verwaltungsrechte |
| MEMBER | Lese- und Schreibzugriff |
| VISITOR | Nur Lesezugriff |
