# SchrottCraft

Ein absichtlich mieses Minecraft/Paper-Plugin, das gerade noch so funktioniert.

## Was es tut

- Bloecke abbauen gibt Schrottpunkte.
- Bloecke platzieren kostet jeweils einen Schrottpunkt.
- `/schrott` oder `/schrott score` zeigt den aktuellen Punktestand.
- `/schrott help` zeigt die zwei Kommandos, die dieses Meisterwerk besitzt.

## Build

```bash
./mvnw package
```

Die fertige Datei liegt danach unter:

```text
target/schrottcraft-1.0.0.jar
```

## Installation

1. Paper-Server starten, damit der `plugins`-Ordner existiert.
2. `target/schrottcraft-1.0.0.jar` in den `plugins`-Ordner kopieren.
3. Server neu starten.

## Contest

Es gibt einen absichtlich eingebauten Bug. Er ist nicht im README dokumentiert und soll gefunden werden.
