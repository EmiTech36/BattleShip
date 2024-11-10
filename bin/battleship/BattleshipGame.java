package battleship;

import java.util.Scanner;

public class BattleshipGame {

	/*
	 * The BattleshipGame class is the “main” class -- that is, it contains a main method. In this class you will set up the game; 
	 * accept ”shots” from the user; display the results; and print final scores. All input/output is done here (although some of 
	 * it is done by calling a print() method in the Ocean class.) All computation will be done in the Ocean class and the various Ship classes.
	 * To aid the user, row numbers should be displayed along the left edge of the array, and column numbers should be displayed along the top. 
	 * Numbers should be 0 to 9, not 1 to 10. The top left corner square should be 0, 0. Use different characters to indicate locations that contain a hit, 
	 * locations that contain a miss, and locations that have never been fired upon.
	 */
	public static void main(String[] args) {
		// step 1: print welcome message
		printWelcome();
		// step 2: create an ocean and place ships randomly
		Ocean ocean = createGame();
		
		while (!ocean.isGameOver()) {
			// step 3: ask for where to hit
			toHit(ocean);
			// step 4: print the updated board
			ocean.print();
			// step 5: update hit count and print result
			printHit(ocean);
		}
		// step 6: print final result
		System.out.println("The game is over!");
		printResult(ocean);
		
	}
	

	// need a function that asks for player inputs. THe input should indicate a specific row and column number to hit (r,c) The computer responds with one bit of
	// information saying "hit" or "miss".
	public static void toHit(Ocean ocean) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Where would you like to hit? Enter row, column");
		String input = scanner.nextLine();
        // Split the input string by the comma
        String[] parts = input.split(",");

        // Parse row and column as integers
        int row = Integer.parseInt(parts[0].trim());
        int column = Integer.parseInt(parts[1].trim());

		boolean hitOrMiss = ocean.shootAt(row, column);
		if (hitOrMiss){
			System.out.println("hit");
		} else {
			System.out.println("miss");
		}

		scanner.close();
	}
	
	// if a ship is hit but not sunk, then program does not provide any information about what kind of a ship was hit. However, when a ship is hit and sinks, the 
	//program prints out a message "You just sank a ship -(type)." After each shot, the computer re-displays the ocean with the new information.
	
	// print result message
	public static void printResult(Ocean ocean) {
		int shots = ocean.getShotsFired();
		System.out.println(shots + " shots were required.");
	}
	
	// print welcome message
	public static void printWelcome() {
		System.out.println("Welcome to battleship.");
	}
	
	// print hit count
	public static void printHit(Ocean ocean) {
		int hit = ocean.getHitCount();
		System.out.println("Hit count: " + hit);
		
	}
	
	// create an ocean with ships filled
	public static Ocean createGame() {
		
	}
}

