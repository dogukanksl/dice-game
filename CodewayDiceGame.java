import java.util.Random;
import java.util.Scanner;

public class CodewayDiceGame {

    static final int TARGET_SCORE = 100;
    static final int NUM_PLAYERS = 3;

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int round = 1;
        int[] scores = new int[NUM_PLAYERS];

        System.out.print("Enter the target number of rounds (1-99): ");
        int targetRounds = scanner.nextInt();

        System.out.println("+-----------+-----------+-----------+-----------+-----------+-----------+-----------+");
        System.out.println("|   Round   |   DICE1   |   DICE2   |   DICE3   |   TOTAL1  |   TOTAL2  |   TOTAL3  |");
        System.out.println("+-----------+-----------+-----------+-----------+-----------+-----------+-----------+");

        while (round <= targetRounds) {
            int[] rolls = new int[NUM_PLAYERS];
            for (int i = 0; i < NUM_PLAYERS; i++) {
                rolls[i] = random.nextInt(6) + 1;
            }

            updateScores(scores, rolls);

            System.out.printf("|     %-4d  |     %-4d  |     %-4d  |     %-4d  |     %-4d  |     %-4d  |     %-4d  |%n",
                    round, rolls[0], rolls[1], rolls[2], scores[0], scores[1], scores[2]);
            System.out.println("+-----------+-----------+-----------+-----------+-----------+-----------+-----------+");
            if (checkWin(scores)) {
                System.out.println("+-----------+-----------+-----------+-----------+-----------+-----------+-----------+");
                System.out.println("Game over! Target score reached.");
                break;
            }

            round++;
        }

        scanner.close();
    }

    static void updateScores(int[] scores, int[] rolls) {
        if (rolls[0] == rolls[1] && rolls[0] != rolls[2]) {
            scores[0] += 2 * rolls[0];
            scores[1] += 2 * rolls[1];
            scores[2] += rolls[2];
        } else if (rolls[0] == rolls[2] && rolls[0] != rolls[1]) {
            scores[0] += 2 * rolls[0];
            scores[2] += 2 * rolls[2];
            scores[1] += rolls[1];
        } else if (rolls[1] == rolls[2] && rolls[1] != rolls[0]) {
            scores[1] += 2 * rolls[1];
            scores[2] += 2 * rolls[2];
            scores[0] += rolls[0];
        } else {
            scores[0] += rolls[0];
            scores[1] += rolls[1];
            scores[2] += rolls[2];
        }
    }

    static boolean checkWin(int[] scores) {
        for (int score : scores) {
            if (score >= TARGET_SCORE) {
                return true;
            }
        }
        return false;
    }
}






