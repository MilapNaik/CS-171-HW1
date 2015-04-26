/*THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
  CODE WRITTEN BY OTHER STUDENTS. Milap Naik */

import java.util.ArrayList;
//import java.util.Random;
import javax.swing.JOptionPane;

public class GuessingGame {
	ArrayList<Integer> numbers = new ArrayList<Integer>(9000);

	public int x;
	public int Guesses;
	public int y;

	public GuessingGame() {
		for (int i = 1000; i < 10000; i++) {
			numbers.add(i);
		}

	} // creates arraylist of all possible guesses

	public int myGuessIs() {
		Guesses = Guesses + 1;
		y = (int) (Math.random() * (numbers.size()));
		x = numbers.get(y);
		return x;
	}

	public int totalNumGuesses() {
		return Guesses;
	}

	public void updateMyGuess(int nmatches) {
		System.out.println(numbers.size());
		if (numbers.size() == 0) {
			System.exit(0);
		}
		String myString = Integer.toString(x);

		char a = myString.charAt(0);
		char b = myString.charAt(1);
		char c = myString.charAt(2);
		char d = myString.charAt(3);

		for (int i = 0; i < numbers.size(); i++) {
			int match = 0;
			String h = Integer.toString(numbers.get(i));
			if (a == h.charAt(0)) {
				match++;
			}
			if (b == h.charAt(1)) {
				match++;
			}
			if (c == h.charAt(2)) {
				match++;
			}
			if (d == h.charAt(3)) {
				match++;
			}
			if (nmatches != match) {
				numbers.remove(i);
				i--;

			}
		}
	} // closes updatemyguess method

	public static void main(String[] args) {

		GuessingGame gamer = new GuessingGame();

		JOptionPane
				.showMessageDialog(
						null,
						"Think of a number between 1000 and 9999.\n Click OK when you are ready...",
						"Let's play a game", JOptionPane.INFORMATION_MESSAGE);
		int numMatches = 0;
		int myguess = 0;

		do {
			myguess = gamer.myGuessIs();
			if (myguess == -1) {
				JOptionPane
						.showMessageDialog(
								null,
								"I don't think your number exists.\n I could be wrong though...",
								"Mistake", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			String userInput = JOptionPane
					.showInputDialog("I guess your number is " + myguess
							+ ". How many digits did I guess correctly?");
			// quit if the user input nothing (such as pressed ESC)
			if (userInput == null)
				System.exit(0);
			// parse user input, pop up a warning message if the input is
			// invalid
			try {
				numMatches = Integer.parseInt(userInput.trim());
			} catch (Exception exception) {
				JOptionPane
						.showMessageDialog(
								null,
								"Your input is invalid. Please enter a number between 0 and 4",
								"Warning", JOptionPane.WARNING_MESSAGE);
				numMatches = 0;
			}
			// the number of matches must be between 0 and 4
			if (numMatches < 0 || numMatches > 4) {
				JOptionPane
						.showMessageDialog(
								null,
								"Your input is invalid. Please enter a number between 0 and 4",
								"Warning", JOptionPane.WARNING_MESSAGE);
				numMatches = 0;
			}
			if (numMatches == 4)
				break;
			// update based on user input
			gamer.updateMyGuess(numMatches);

		} while (true);

		// the game ends when the user says all 4 digits are correct
		System.out.println("Aha, I got it, your number is " + myguess + ".");
		System.out
				.println("I did it in " + gamer.totalNumGuesses() + " turns.");
	}
}