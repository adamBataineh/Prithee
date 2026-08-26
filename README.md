# Prithee

A command-line missing-word game based on the prologue to Shakespeare's *Romeo and Juliet*.

Each round selects a word, displays the text only up to that point, and asks the player to restore the missing token. The game ends after three correct or three incorrect answers.

## Highlights

- Preserves the original line structure
- Indexes every possible missing-word position
- Avoids selecting the same word in consecutive rounds
- Checks answers without regard to letter case
- Includes ten dependency-free behavior checks

## Run

Requires a Java Development Kit.

```bash
mkdir -p out
javac -d out src/prithee/main.java src/prithee/tests.java
java -cp out prithee.main
```

## Test

```bash
java -cp out prithee.tests
```
