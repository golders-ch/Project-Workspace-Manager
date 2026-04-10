# Aufgabe 3 – Objektorientierte Analyse (OOA)

**Projekt:** Project-Workspace-Manager  
**Kurs:** Software Engineering 1, Frühjahrssemester 2026  
**Team:** Benjamin Golder, Dennis Eberhard  
**Basis:** FA01 – Projektarbeitsbereich erstellen (Score 90) & FA02 – Vordefinierte Projektvorlage auswählen (Score 81)

---

## Annahmen

- Der `Benutzer` repräsentiert ausschliesslich einen authentifizierten Projektmanager. Die Authentifizierung über Microsoft Entra ID ist Voraussetzung und nicht Teil des Fachklassenmodells.
- `Projektmetadaten` werden als eigenständige Klasse modelliert, da sie separat persistiert und für Reporting genutzt werden (Nachbedingung FA01).
- Die Namenskonvention (`PRJ-[Jahr]-[Kürzel]-[Name]`) ist eine fachliche Regel, die beim Erstellen des `Projektarbeitsbereichs` angewendet wird; sie wird nicht als eigene Klasse modelliert.
- Der Zustand `ARCHIVIERT` wird im Zustandsmodell ergänzt (Anforderung FA10), da er die Vollständigkeit des Lebenszyklus eines `Projektarbeitsbereichs` sicherstellt.

---

## 1. Fachklassenmodell (UML-Klassendiagramm)

> Die Visual-Paradigm-Datei befindet sich unter `docs/analyse/fachklassenmodell.puml`  
> (Import in Visual Paradigm: Diagram > New > PlantUML Diagram → Code einfügen)

### Klassenkandidaten (Identifikation)

Aus den Use-Cases FA01 und FA02 wurden folgende Klassenkandidaten abgeleitet:

| Kandidat | Herkunft |
|---|---|
| Projektarbeitsbereich | Zentrales Ergebnis von FA01 – wird erstellt und verwaltet |
| Projektmetadaten | FA01: Felder Projektcode, Kostenstelle, Start-/Enddatum, Projektleiter |
| Projektvorlage | FA02: Dropdown mit Standard, Kundenprojekt, Intern |
| Ordner | FA01/FA02: Ordnerstruktur (Dokumente, Besprechungen, Berichte, Vorlagen) |
| Dokumentvorlage | FA02: Template-spezifische Standarddokumente |
| Benutzer | FA01: Akteur Projektmanager, erhält Owner-Berechtigung |
| Berechtigung | FA01: Basisberechtigung (Owner, Member, Visitor) konfigurieren |
| AuditLogEintrag | FA01 Nachbedingung: Audit-Log mit Zeitstempel und Benutzer |

### Vererbungsoptimierung

Die drei konkreten Vorlagentypen **StandardVorlage**, **KundenprojektVorlage** und **InternVorlage** unterscheiden sich in ihren Ordnerstrukturen, Dokumentvorlagen und Konfigurationen. Sie werden als Unterklassen der abstrakten Klasse **Projektvorlage** modelliert, da:
- gemeinsame Attribute (`id`, `bezeichnung`, `beschreibung`) und Assoziationen (zu `Ordner`, `Dokumentvorlage`) einmal zentral definiert werden,
- `KundenprojektVorlage` ein spezifisches Attribut (`kundenbereichAktiv`) aufweist,
- das Prinzip der Generalisierung den Erweiterungsaufwand bei neuen Vorlagentypen reduziert.

### Klassendiagramm

```
┌─────────────────────────────┐
│      Projektarbeitsbereich  │
├─────────────────────────────┤
│ + id : String               │
│ + name : String             │
│ + beschreibung : String     │
│ + url : String              │
│ + status : Arbeitsbereich-  │
│           Status            │
│ + erstellungsDatum : Date   │
└──────────┬──────────────────┘
           │ 1
    ┌──────┴────────┬──────────────┬──────────────┐
    │ 1             │ 1            │ 1..*          │ 0..*
    ▼               ▼              ▼               ▼
[Projektmetadaten] [basiert auf→] [AuditLogEintrag] [Berechtigung]
                  Projektvorlage
```

*(Vollständiges Diagramm → Visual Paradigm / fachklassenmodell.puml)*

---

## 2. Zustandsmodell: Projektarbeitsbereich

> Die Visual-Paradigm-Datei befindet sich unter `docs/analyse/zustandsmodell.puml`

Die Klasse `Projektarbeitsbereich` wurde gewählt, da sie das zentrale Fachkonzept der beiden wichtigsten Anforderungen darstellt und den vollständigen Lebenszyklus eines Arbeitsprojekts abbildet.

### Fachliche Zustände

| Zustand | Beschreibung |
|---|---|
| **Entwurf** | Projektmanager hat das Formular (teilweise) ausgefüllt und gespeichert. Der Arbeitsbereich existiert noch nicht in SharePoint. Änderungen sind möglich. |
| **In Erstellung** | Erstellung wurde bestätigt (Validierung erfolgreich). Das System führt die Provisionierung in SharePoint aus (Site, Ordnerstruktur, Basisberechtigung, Metadaten). |
| **Aktiv** | SharePoint-Site wurde erfolgreich erstellt. Projektmanager hat Owner-Berechtigung. Metadaten sind für Reporting verfügbar. |
| **Fehlgeschlagen** | Während der Erstellung ist ein technischer Fehler aufgetreten (API-Fehler, SharePoint nicht verfügbar). Der Benutzer wird informiert. |
| **Archiviert** | Das Projekt ist abgeschlossen. Der Arbeitsbereich ist schreibgeschützt. *(Ergänzt nach Überprüfung des Zustandsmodells, basierend auf FA10.)* |

### Übergänge

| Von | Nach | Auslöser / Bedingung |
|---|---|---|
| [Start] | Entwurf | Formular gespeichert |
| Entwurf | Entwurf | Formular geändert |
| Entwurf | In Erstellung | Erstellung bestätigt [Pflichtfelder vollständig, Name eindeutig] |
| Entwurf | [Ende] | Entwurf verworfen |
| In Erstellung | Aktiv | SharePoint-Site erfolgreich erstellt |
| In Erstellung | Fehlgeschlagen | API-Fehler / SharePoint nicht verfügbar |
| Fehlgeschlagen | In Erstellung | Erstellung wiederholt |
| Aktiv | Archiviert | Projekt abgeschlossen (Enddatum überschritten oder manuell ausgelöst) |
| Archiviert | [Ende] | – |

### Ergänzung des Fachklassenmodells

Nach der Überprüfung wurde der Enumeration `ArbeitsbereichStatus` der Wert `ARCHIVIERT` hinzugefügt. Das Attribut `status` in `Projektarbeitsbereich` deckt damit den vollständigen Lebenszyklus ab.

---

## 3. Glossar

> **Hinweis:** Die folgenden Beschreibungen erläutern die fachliche Verantwortlichkeit jeder Klasse im Kontext des Project-Workspace-Managers. OO-Konzepte und UML-Formalismen werden nicht erklärt.

---

### Projektarbeitsbereich

Repräsentiert einen SharePoint-Projektarbeitsbereich, der für ein konkretes Projekt eingerichtet wurde. Er ist das zentrale Ergebnis des Erstellungsprozesses und bündelt alle projektrelevanten Informationen: den aktuellen Lebenszyklusstatus, den Entstehungszeitpunkt und die URL für den direkten Zugriff. Er bildet den gemeinsamen digitalen Ort, an dem ein Projektteam Dokumente, Besprechungen und Berichte ablegt.

---

### Projektmetadaten

Hält die fachlichen Kenndaten eines Projekts fest, die über den reinen Arbeitsbereich hinausgehen: Projektcode, Kostenstelle, Laufzeitraum und verantwortlicher Projektleiter. Diese Daten ermöglichen die unternehmensweite Auswertung und Nachvollziehbarkeit von Projekten (Reporting, Governance).

---

### Projektvorlage *(abstrakt)*

Definiert einen wiederverwendbaren Standard für Struktur und Inhalte eines neuen Projektarbeitsbereichs. Sie stellt sicher, dass jeder Arbeitsbereich konsistent aufgebaut ist und die für den jeweiligen Projekttyp relevanten Ordner und Dokumente enthält. Als abstrakte Klasse gibt sie den gemeinsamen Rahmen vor, den die konkreten Vorlagentypen ausfüllen.

---

### StandardVorlage

Konkrete Vorlage für allgemeine Projekte ohne besondere Anforderungen. Sie enthält eine Basisordnerstruktur mit den grundlegenden Bereichen Dokumentation und Kommunikation.

---

### KundenprojektVorlage

Konkrete Vorlage für Projekte mit externen Stakeholdern. Sie ergänzt die Standardstruktur um einen separaten Kundenbereich mit eingeschränkten Zugriffsrechten und projektspezifischen Kundendokumenten.

---

### InternVorlage

Konkrete Vorlage für interne Initiativen und Vorhaben ohne externen Zugriff. Sie bietet eine vereinfachte Struktur, die auf die internen Kommunikations- und Dokumentationsanforderungen ausgerichtet ist.

---

### Ordner

Stellt einen vordefinierten Ablagebereich innerhalb eines Projektarbeitsbereichs dar. Die Reihenfolge und Benennung der Ordner sind durch die gewählte Vorlage festgelegt und gewährleisten eine einheitliche Dokumentenablage über alle Projekte hinweg.

---

### Dokumentvorlage

Repräsentiert ein Standarddokument, das beim Erstellen eines Arbeitsbereichs automatisch bereitgestellt wird. Sie stellt sicher, dass Projektteams sofort mit vorgefertigten, vorlagenkonformen Dokumenten arbeiten können (z. B. Projektcharter, Statusbericht).

---

### Benutzer

Repräsentiert eine authentifizierte Person, die berechtigt ist, Projektarbeitsbereiche zu erstellen und zu verwalten. Im System entspricht dies typischerweise einem Projektmanager. Der Benutzer ist verantwortlich für die korrekte Angabe der Projektinformationen und trägt die Eigentümerverantwortung für die erstellten Arbeitsbereiche.

---

### Berechtigung

Legt fest, welche Zugriffsrechte ein bestimmter Benutzer auf einen Projektarbeitsbereich hat. Sie unterscheidet zwischen den Rollen Owner (voller Zugriff und Verwaltungsrechte), Member (Lese- und Schreibzugriff) und Visitor (nur Lesezugriff). Durch die Basisberechtigung wird der erstellende Projektmanager automatisch als Owner eingesetzt.

---

### AuditLogEintrag

Protokolliert jede relevante Aktion auf einem Projektarbeitsbereich (insbesondere die Erstellung) mit Zeitstempel, ausführendem Benutzer und Ergebnis. Er dient der Nachvollziehbarkeit, der Compliance-Konformität (DSGVO) und der Fehleranalyse. Jeder Eintrag ist unveränderlich.
