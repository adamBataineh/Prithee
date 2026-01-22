package prithee;

import java.util.List;
import java.util.Random;

public class tests {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testUnderscores();
        testIsCorrect();
        testBuildSpots();
        testBuildPromptStops();
        testPickDifferentIndex();

        System.out.println();
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }

    private static void testUnderscores() {
        check(main.underscores(5).equals("_____"), "underscores length 5");
    }

    private static void testIsCorrect() {
        check(main.isCorrect("Verona,", "verona,"), "isCorrect case-insensitive");
        check(!main.isCorrect("Verona", "Verona,"), "isCorrect punctuation matters");
    }

    private static void testBuildSpots() {
        String[] lines = {"A B", "C"};
        List<main.Spot> spots = main.buildSpots(lines);
        check(spots.size() == 3, "buildSpots counts words");
        check(spots.get(1).word.equals("B"), "buildSpots stores correct word");
    }

    private static void testBuildPromptStops() {
        String[] lines = {"A B C", "D E"};
        main.Spot spot = new main.Spot(0, 1, "B"); // blank "B"
        String prompt = main.buildPrompt(lines, spot);

        check(prompt.contains("A"), "prompt prints before blank");
        check(prompt.contains("_"), "prompt contains underscores");
        check(!prompt.contains("C"), "prompt does not print after blank on same line");
        check(!prompt.contains("D"), "prompt does not print later lines");
    }

    private static void testPickDifferentIndex() {
        Random r = new Random(0);
        int idx = main.pickDifferentIndex(r, 10, 3);
        check(idx != 3, "pickDifferentIndex not equal to last");
    }

    private static void check(boolean condition, String name) {
        if (condition) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            System.out.println("FAIL: " + name);
        }
    }
}

