import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        // Word list with hints
        String[][] wordsWithHints = {
            {"KOCZKODAJ", "If you can't spell this you fail"},
            {"MY DEAR STUDENTS", "A way to address your class"},
            {"BLOODY", "When frustrated, scream:"},
            {"DIA", "German software"},
            {"HARMFUL INTERNET USE", "The go-to bonus question"},
            {"CYBER BULLYING", "Prabhleen's go-to bonus question answer"},
            {"HARSH", "The poor TA"},
            {"RELAX", "Wally to Harsh"},
            {"WIKIPEDIA", "Wally's favourite website"},
            {"SCISSORS", "Wally stays strapped with what in his bag"},
            {"CHLOE", "Wally's pookie bae"},
            {"TANDY TROWER", "The name of the Microsoft man whose 52-minute video we have to suffer through, TWICE"},
            {"SEXY", "The favourite word of the Microsoft Man"},
            {"RCMP", "Wally: “You can report me to the ____ if you don’t like it”"},
            {"MY DOG", "Microsoft Man: “Even if you put lipstick on a dog, you still don’t wanna kiss it.” Wally: “You haven't seen ___.”"}
        };

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman: Wally's World!");

        for (int level = 0; level < wordsWithHints.length; level++) {
            String word = wordsWithHints[level][0];
            String hint = wordsWithHints[level][1];

            char[] guessedWord = new char[word.length()];
            for (int i = 0; i < guessedWord.length; i++) {
                guessedWord[i] = (word.charAt(i) == ' ') ? ' ' : '_';
            }

            int attemptsLeft = 6;
            boolean wordGuessed = false;

            System.out.println("\nLevel " + (level + 1));
            System.out.println("Hint: " + hint);

            while (attemptsLeft > 0) {
                System.out.println("\n" + getHangmanState(attemptsLeft));
                System.out.println("Word: " + formatGuessedWord(guessedWord));
                System.out.println("Attempts left: " + attemptsLeft);

                String input;
                do {
                    System.out.print("Guess a single letter: ");
                    input = scanner.nextLine().toUpperCase();
                } while (input.length() != 1); // Ensure the user inputs exactly one letter

                char guess = input.charAt(0);

                if (!processGuess(word, guessedWord, guess)) {
                    attemptsLeft--;
                    System.out.println("Wrong guess!");
                } else {
                    System.out.println("Good guess!");
                }

                if (String.valueOf(guessedWord).equals(word)) {
                    wordGuessed = true;
                    break;
                }
            }

            if (wordGuessed) {
                System.out.println("\nCongratulations my dear student! You guessed the word: " + word);
            } else {
                System.out.println("\n" + getHangmanState(0));
                System.out.println("Game Over for this level! The word was: " + word);
                System.out.println("\nBetter luck in the next level!");
            }

            System.out.println("\nMoving to the next level...\n");
        }

        System.out.println("You've completed all levels! Thanks for playing my dear student!");
        scanner.close();
    }

    private static boolean processGuess(String word, char[] guessedWord, char guess) {
        boolean correct = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedWord[i] = guess;
                correct = true;
            }
        }
        return correct;
    }

    private static String formatGuessedWord(char[] guessedWord) {
        StringBuilder formatted = new StringBuilder();
        for (char c : guessedWord) {
            formatted.append(c).append(' '); // Add a space after each character
        }
        return formatted.toString().trim(); 
    }

    private static String getHangmanState(int attemptsLeft) {
        switch (attemptsLeft) {
            case 6:
                return """
                   +---+
                   |   |
                       |
                       |
                       |
                       |
                 =========""";
            case 5:
                return """
                   +---+
                   |   |
                   O   |
                       |
                       |
                       |
                 =========""";
            case 4:
                return """
                   +---+
                   |   |
                   O   |
                   |   |
                       |
                       |
                 =========""";
            case 3:
                return """
                   +---+
                   |   |
                   O   |
                  /|   |
                       |
                       |
                 =========""";
            case 2:
                return """
                   +---+
                   |   |
                   O   |
                  /|\\  |
                       |
                       |
                 =========""";
            case 1:
                return """
                   +---+
                   |   |
                   O   |
                  /|\\  |
                  /    |
                       |
                 =========""";
            case 0:
                return """
                   +---+
                   |   |
                   O   |
                  /|\\  |
                  / \\  |
                       |
                 =========""";
            default:
                return "";
        }
    }
}
