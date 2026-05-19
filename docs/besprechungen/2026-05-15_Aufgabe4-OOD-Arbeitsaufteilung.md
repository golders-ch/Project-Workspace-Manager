# Besprechung – 2026-05-15 Aufgabe 4 OOD / Arbeitsaufteilung

**Datum:** 2026-05-15
**Teilnehmer:** Benjamin Golder, Dennis Eberhard
**Dauer:** ca. 1h 30min
**Format:** Remote (Teams-Anruf)

---

## Traktanden
1. Lern-Setup für die Semesterprüfung (Copilot mit Schulmaterial)
2. Aufgabe 4 OOD – Stand und Arbeitsaufteilung
3. Sequenzdiagramm – Vorgehen
4. Implementierung – Ausblick auf nächste Iteration
5. Nächste Synchronisation

---

## Besprochenes

### Lern-Setup für die Semesterprüfung
Benjamin hat einen Copilot-Agent eingerichtet, der mit dem hinterlegten Schulmaterial trainiert wurde. Damit lassen sich Fragen Schritt für Schritt durchgehen und Probeprüfungen simulieren. Wird parallel zur Projektarbeit für die Prüfungsvorbereitung genutzt.

### Aufgabe 4 OOD – Stand
Benjamin hat den aktuellen Stand der Designdokumentation (in `docs/design/`) gezeigt. Vorhanden sind:
- Designmodell Business-Schicht (PlantUML + PNG)
- Designmodell Persistence-Schicht (PlantUML + PNG)
- Integriertes Klassendiagramm + Komponentendiagramm (PlantUML + PNG)
- Sequenzdiagramm FA01 (PlantUML + PNG, vereinfacht)
- Hauptdokument `aufgabe-4-ood.md` mit Annahmen, Pattern-Einsatz und Glossar

Die Diagramme wurden bewusst auf das Prüfungs-Minimum verschlankt, damit der Aufwand für das Nachbauen in Visual Paradigm tragbar bleibt. Alle Pflicht-Patterns (Singleton, Factory, Repository), die Vererbungshierarchie `Projektvorlage` und beide MVP-Use-Cases (FA01, FA02) sind weiterhin sichtbar.

### Arbeitsaufteilung Visual-Paradigm-Diagramme
Die `.puml`-Quellen dienen als Vorlage; die finalen Diagramme werden manuell in Visual Paradigm nachgezeichnet (Community Edition kann PlantUML-Import nicht zuverlässig).

| Diagramm | Verantwortlich |
|---|---|
| Designmodell Business-Schicht (Klassendiagramm) | Dennis |
| Integriertes Designmodell – Komponentendiagramm | Dennis |
| Designmodell Persistence-Schicht (Klassendiagramm) | Benjamin |
| Integriertes Designmodell – Klassendiagramm | Benjamin |
| Sequenzdiagramm FA01 | zurückgestellt (siehe unten) |

### Sequenzdiagramm – Vorgehen
Es ist unklar, ob der Dozent das Thema „Sequenzdiagramm" zu diesem Zeitpunkt bereits behandelt hat. Wenn er es im nächsten Unterrichtsblock noch zeigt, kann das Diagramm anschliessend ergänzt werden. Aufgabe 4 ist dadurch nicht blockiert – die anderen vier Diagramme sind die Hauptarbeit.

### Implementierung – Ausblick
Nach Abschluss von Aufgabe 4 ist die Implementierung die nächste Etappe. KI-Unterstützung (Copilot) wird dort sehr hilfreich sein, ist aber gleichzeitig eine „fiese Abkürzung": Verständnis und Kontrolle über den Code müssen erhalten bleiben, sonst entstehen später unauffindbare Probleme.

---

## Beschlüsse
- Arbeitsaufteilung der VP-Diagramme wie oben.
- Sequenzdiagramm vorerst zurückstellen, bis klar ist, ob der Dozent das Thema im Unterricht behandelt.
- Benjamin liefert seinen Anteil bis Sonntag (2026-05-17).
- Bei der Implementierung wird KI-Unterstützung genutzt, das Verständnis der Lösung bleibt aber Pflicht.

---

## Offene Punkte / Folgeaufgaben

| Aufgabe | Verantwortlich | Fällig bis |
|---|---|---|
| Business-Schicht-Klassendiagramm in VP nachbauen + PNG-Export | Dennis | nächste Besprechung |
| Integriertes Komponentendiagramm in VP nachbauen + PNG-Export | Dennis | nächste Besprechung |
| Persistence-Schicht-Klassendiagramm in VP nachbauen + PNG-Export | Benjamin | 2026-05-17 |
| Integriertes Klassendiagramm in VP nachbauen + PNG-Export | Benjamin | 2026-05-17 |
| VP-Exporte in `docs/design/` einchecken (PNG ersetzt PlantUML-Export) | Benjamin & Dennis | nach Fertigstellung |
| Sequenzdiagramm FA01 in VP nachbauen | offen | nach Unterrichtsblock zum Thema |
| Synchronisation des Stands | Benjamin & Dennis | spätestens 2026-05-22 |
