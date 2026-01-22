package prithee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {

    private static final String[] LINES = {
            "Two households, both alike in dignity,",
            "In fair Verona, where we lay our scene,",
            "From ancient grudge break to new mutiny,",
            "Where civil blood makes civil hands unclean.",
            "From forth the fatal loins of these two foes",
            "A pair of star-cross’d lovers take their life;",
            "Whose misadventured piteous overthrows",
            "Do with their death bury their parents’ strife.",
            "The fearful passage of their death-mark’d love,",
            "And the continuance of their parents’ rage,",
            "Which, but their children’s end, nought could remove,",
            "Is now the two hours’ traffic of our stage;",
            "The which if you with patient ears attend,",
            "What here shall miss, our toil shall strive to mend."
    };

    // Stores one possible missing-word location
    public static class Spot {
        public int lineIndex;
        public int wordIndex;
        public String word;

        public Spot(int lineIndex, int wordIndex, String word) {
            this.lineIndex = lineIndex;
            this.wordIndex = wordIndex;
            this.word = word;
        }
    }

    public static void main(String[] args) {
        play();
    }

    public static void play() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        List<Spot> spots = buildSpots(LINES);
        if (spots.isEmpty()) {
            System.out.println("No words found.");
            return;
        }

        int correct = 0;
        int incorrect = 0;
        int lastSpotIndex = -1;

        System.out.println("Sonnet Missing-Word Game");
        System.out.println("Ends at 3 correct OR 3 incorrect.\n");

        while (correct < 3 && incorrect < 3) {
            int spotIndex = pickDifferentIndex(rand, spots.size(), lastSpotIndex);
            lastSpotIndex = spotIndex;

            Spot s = spots.get(spotIndex);

            System.out.print(buildPrompt(LINES, s));
            System.out.print("Missing word: ");
            String guess = sc.nextLine().trim();

            if (isCorrect(guess, s.word)) {
                correct++;
                System.out.println("Correct.\n");
            } else {
                incorrect++;
                System.out.println("Incorrect. Correct word was: " + s.word + "\n");
            }

            System.out.println("Score -> Correct: " + correct + " | Incorrect: " + incorrect + "\n");
        }

        if (correct == 3) {
            System.out.println("You got 3 correct. Done.");
        } else {
            System.out.println("You got 3 incorrect. Done.");
        }
    }

    // Build list of all word spots in the sonnet
    public static List<Spot> buildSpots(String[] lines) {
        List<Spot> spots = new ArrayList<>();
        for (int li = 0; li < lines.length; li++) {
            String[] words = lines[li].split("\\s+");
            for (int wi = 0; wi < words.length; wi++) {
                if (!words[wi].isEmpty()) {
                    spots.add(new Spot(li, wi, words[wi]));
                }
            }
        }
        return spots;
    }

    // Choose a random index not equal to lastIndex (if possible)
    public static int pickDifferentIndex(Random rand, int size, int lastIndex) {
        if (size <= 1) return 0;

        int idx;
        do {
            idx = rand.nextInt(size);
        } while (idx == lastIndex);

        return idx;
    }

    // Print sonnet up to the missing word, replace it with underscores, and stop.
    public static String buildPrompt(String[] lines, Spot spot) {
        StringBuilder sb = new StringBuilder();

        // print full lines before the missing line
        for (int i = 0; i < spot.lineIndex; i++) {
            sb.append(lines[i]).append("\n");
        }

        // print the missing line up to the missing word
        String[] words = lines[spot.lineIndex].split("\\s+");
        for (int i = 0; i < spot.wordIndex; i++) {
            sb.append(words[i]).append(" ");
        }

        // print blank and stop
        sb.append(underscores(spot.word.length())).append("\n\n");
        return sb.toString();
    }

    public static String underscores(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("_");
        return sb.toString();
    }

    public static boolean isCorrect(String guess, String expected) {
        return guess.trim().equalsIgnoreCase(expected.trim());
    }
}

