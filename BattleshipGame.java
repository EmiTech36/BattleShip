package battleship;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		// Step 1: Print welcome message

		printWelcome();

		// Step 2: Create an ocean and place ships randomly
		Ocean ocean = createGame();
		ocean.printWithShips();
		ocean.print();

		// Create a single Scanner instance for the entire game
		Scanner scanner = new Scanner(System.in);

		while (!ocean.isGameOver()) {
			// Step 3: Ask for where to hit
			toHit(ocean, scanner);

			// Step 4: Print the updated board
			ocean.print();

			// Step 5: Update hit count and print result
			printHit(ocean);
		}

		// Step 6: Print final result and close the scanner
		System.out.println("The game is over!");
		printResult(ocean);
		scanner.close();
	}

	// Function to ask for player inputs indicating a specific row and column to hit
	public static void toHit(Ocean ocean, Scanner scanner) {
		System.out.print("Enter row, column: ");
		String input = scanner.nextLine();

		// Split the input string by the comma
		String[] parts = input.split(",");

		// Parse row and column as integers
		int row = Integer.parseInt(parts[0].trim());
		int column = Integer.parseInt(parts[1].trim());

		// Perform the shot and display result
		boolean hitOrMiss = ocean.shootAt(row, column);
		if (hitOrMiss) {
			System.out.println("Hit!");
		} else {
			System.out.println("Miss!");
		}
	}

	// Print final result message
	public static void printResult(Ocean ocean) {
		int shots = ocean.getShotsFired();
		System.out.println(shots + " shots were required.");
	}

	// Print welcome message
	public static void printWelcome() {
		System.out.println("Welcome to the Battleship game.");
	}

	// Print hit count
	public static void printHit(Ocean ocean) {
		int hit = ocean.getHitCount();
		System.out.println("Hit count: " + hit);
	}

	// Create an ocean with ships filled
	public static Ocean createGame() {
		Ocean ocean = new Ocean();
		//ocean.placeAllShipsRandomly();
		return ocean;
	}
}
