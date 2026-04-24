# Besprechung – 2026-04-24 Wochensync Start OOD – Schichten & Patterns

**Datum:** 2026-04-24
**Teilnehmer:** Benjamin Golder, Dennis Eberhard
**Dauer:** ca. 1h
**Format:** Remote (Teams-Anruf)

---

## Traktanden
1. Architektur Aufgabe 4 (3-Schichten)
2. Pflicht-Patterns (Singleton, Factory, Repository)
3. Aufteilung erste Designarbeit

## Besprochenes

### 3-Schichtenarchitektur
Übernahme einer logischen 3-Schichtenarchitektur: Presentation (`ConsoleClient`) → Business (Domain/Services) → Persistence (`DataAccess`). Die Schichten sollen nur über Schnittstellen kommunizieren, damit Persistenz austauschbar bleibt (SharePoint vs. Mock).

### Pflicht-Patterns
Einsatz der geforderten Patterns geklärt:
- **Singleton:** Factories und der zentrale DataAccess.
- **Factory:** Erzeugung von `Projektarbeitsbereich` / `Projektvorlage`.
- **Repository:** Kapselung des Datenzugriffs hinter Schnittstellen.

### Aufteilung
Benjamin beginnt mit den Designmodellen (Business- und Persistence-Schicht) als Entwurf; Dennis prüft die Pattern-Anwendung kritisch gegen.

## Beschlüsse
- 3-Schichtenarchitektur als Grundlage festgelegt.
- Pattern-Einsatz wie oben.
- Benjamin erstellt Entwurf der Designmodelle.

## Offene Punkte / Folgeaufgaben

| Aufgabe | Verantwortlich | Fällig bis |
|---|---|---|
| Entwurf Designmodelle Business + Persistence | Benjamin | nächster Sync |
| Pattern-Einsatz gegenprüfen | Dennis | nächster Sync |
