# Java-Prototyp – Iteration 1 (Aufgabe 5)

Java-Prototyp gemäss Aufgabe 5 (Objektorientierte Implementation) der Projektarbeit Software Engineering 1, Frühjahrssemester 2026.

## Umsetzungsumfang

- **Funktionale Anforderung:** FA01 – Projektarbeitsbereich erstellen (Score 90)
- **Fachliche Klasse:** `Projektarbeitsbereich`
- **Persistierte Attribute (Iteration 1):** `id`, `name`
- **3-Schichten-Architektur (Clean Architecture):** Presentation → Business → Persistence
- **Design Patterns:** Singleton (Factory, DataAccess), Factory, Repository

## Package-Struktur

```
ch.juve.pwm.presentation     Application (main), ConsoleClient
ch.juve.pwm.business         Projektarbeitsbereich (Domain), Service-Schnittstelle + Implementierung,
                              Repository-Schnittstelle, Factory (Singleton)
ch.juve.pwm.persistence      IDataAccess (Singleton-Vertrag), MockDataAccess (in-memory),
                              ProjektarbeitsbereichRepository
ch.juve.pwm.test.business    JUnit-5-Tests für die Business-Schicht
ch.juve.pwm.test.persistence JUnit-5-Tests für die Persistence-Schicht
```

## Voraussetzungen

- JDK 21 (oder neuer)
- JUnit Platform Console Standalone 1.10.2 (liegt in `lib/`)

## Build (Kommandozeile)

```bash
# Produktiv-Code übersetzen
mkdir -p bin/main bin/test
find src -name "*.java" | xargs javac -d bin/main -sourcepath src

# Tests übersetzen
find test -name "*.java" | xargs javac -d bin/test \
    -cp "bin/main:lib/junit-platform-console-standalone-1.10.2.jar" \
    -sourcepath test
```

## Endbenutzertest ausführen (Auftrag 5)

```bash
java -cp bin/main ch.juve.pwm.presentation.Application
```

Erwartete Ausgabe: Vier Projektarbeitsbereiche werden mit harten Testdaten erstellt und auf der Konsole angezeigt.

## Unit-Tests ausführen (Aufträge 3 + 4)

```bash
java -jar lib/junit-platform-console-standalone-1.10.2.jar \
    execute --class-path "bin/main:bin/test" --scan-class-path
```

Erwartetes Ergebnis: 17/17 Tests grün (9 Business + 8 Persistence).

## Javadoc generieren (Auftrag 6)

```bash
javadoc -d docs/javadoc -sourcepath src -subpackages ch.juve.pwm \
    -encoding UTF-8 -charset UTF-8 -docencoding UTF-8 \
    -doctitle "Project-Workspace-Manager Prototyp Iteration 1" \
    -windowtitle "Project-Workspace-Manager - Javadoc" \
    -author -version -Xdoclint:none
```

Einstiegspunkt: `docs/javadoc/index.html`.

## Eclipse-Import

1. Eclipse: *File → Import → Existing Projects into Workspace*
2. Root-Verzeichnis: `prototype-java/`
3. Projekt erscheint als `Project-Workspace-Manager-Prototype`
4. Run-Konfiguration: Main-Klasse `ch.juve.pwm.presentation.Application`
5. Tests: Rechtsklick auf `test/` → *Run As → JUnit Test*

## Annahmen (Iteration 1)

- `Projektarbeitsbereich` wird auf zwei Attribute reduziert (`id`, `name`); weitere Attribute (Status, URL, Datumsfelder, Metadaten) folgen in späteren Iterationen.
- Statt der designseitigen `SharePointDataAccess` wird `MockDataAccess` verwendet (Vorgabe: in-memory Mock-DB).
- Keine Benutzereingaben; alle Testdaten sind im `Application.main` hart codiert.
- `ProjektvorlageFactory`, `VorlagenService` etc. (FA02) sind im Designmodell vorgesehen, aber für Iteration 1 nicht implementiert – die Aufgabe verlangt die Implementierung **einer** fachlichen Klasse.
