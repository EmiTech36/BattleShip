package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;

	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;

	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	public void testEmptyOcean() {

		//tests that all locations in the ocean are "empty"

		Ship[][] ships = ocean.getShipArray();

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];

				assertEquals("empty", ship.getShipType());
			}
		}

		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());

		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());

		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}

	@Test
	void testPlaceAllShipsRandomly() {

		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();

		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}

		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}

		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);

		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE;
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);

		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);


		//TODO
		//test destroyer
		assertTrue(ocean.isOccupied(1, 5)); // Bow of the destroyer
		assertTrue(ocean.isOccupied(0, 5)); //still a part of the destroyer
		assertFalse(ocean.isOccupied(2, 5)); //not a ship

		//test submarine
		assertTrue(ocean.isOccupied(0, 0));
		assertFalse(ocean.isOccupied(0, 1));

		Cruiser cruiser = new Cruiser();

		cruiser.placeShipAt(7, 8, true, ocean);
		assertTrue(ocean.isOccupied(7, 8)); // Bow of the cruiser
		assertTrue(ocean.isOccupied(7, 7));
		assertTrue(ocean.isOccupied(7, 6));
		assertFalse(ocean.isOccupied(7, 9)); // Unoccupied cell
		assertFalse(ocean.isOccupied(8, 8));


	}

	@Test
	void testShootAt() {
		assertFalse(ocean.shootAt(0, 1));

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean); //create a vertical destroyer at 1,5

		assertTrue(ocean.shootAt(1, 5)); //shoot at destroyer
		assertFalse(destroyer.isSunk()); //not sunk yet
		assertTrue(ocean.shootAt(0, 5)); //also part of the destroyer
		assertTrue(destroyer.isSunk()); //now sunk

		//TODO
		//Create a horizontal battleship at 6,6
		Battleship battleship = new Battleship();
		//create and place a new horizontal battleship at 6,6
		battleship.placeShipAt(6, 6, true, ocean);
		assertTrue(ocean.shootAt(6, 5)); //shoot at part of the battleship
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(6, 4)); //shoot at part of the battleship


		Submarine submarine = new Submarine();
		//create and place a new horizontal battleship at 8,2
		submarine.placeShipAt(8, 2, true, ocean);
		assertTrue(ocean.shootAt(8, 2)); //shoot at part of the battleship
		assertTrue(submarine.isSunk()); //sunk already



	}

	@Test
	void testGetShotsFired() {

		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired()); //4 shots fired (even to nothing)

		Destroyer destroyer = new Destroyer(); //create a vertical destroyer and place it to the ocean
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		Ship submarine = new Submarine(); //create a submarine and place it to the ocean
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5)); //shoot at part of the destroyer
		assertFalse(destroyer.isSunk()); //but not sunk yet
		assertTrue(ocean.shootAt(0, 5)); //shoot at the other part of the destroyer
		assertTrue(destroyer.isSunk()); //now sunk
		assertEquals(6, ocean.getShotsFired()); //2 + 4 = 6 shots

		//TODO
		//More tests
		Cruiser cruiser = new Cruiser(); //create a horizontal cruiser and place it to the ocean
		cruiser.placeShipAt(7, 8, true, ocean);
		assertTrue(ocean.shootAt(7, 8));//hit part of the cruiser
		assertTrue(ocean.shootAt(7, 7));//hit part of the cruiser
		assertFalse(ocean.shootAt(7, 9));//hit at an empty sea

		assertEquals(9, ocean.getShotsFired());

	}

	@Test
	void testGetHitCount() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(0, 5));
		assertFalse(destroyer.isSunk()); //not sunk yet
		assertEquals(1, ocean.getHitCount());

		//TODO
		assertTrue(ocean.shootAt(0, 5)); // Hitting the sunk Destroyer again
		assertEquals(2, ocean.getHitCount()); // Hit count + 1

		assertTrue(ocean.shootAt(1, 5));
		assertTrue(destroyer.isSunk());  // Destroyer is sunk now
		assertEquals(3, ocean.getHitCount()); // Total hitcount is now 3

		assertFalse(ocean.shootAt(0, 5)); // Hitting the sunk Destroyer again
		assertEquals(3, ocean.getHitCount()); // Hit count + 1
	}

	@Test
	void testGetShipsSunk() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());

		//TODO

		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk()); //this time the destroyer is sunk
		//Create a submarine
		Submarine submarine = new Submarine();
		submarine.placeShipAt(0, 0, false, ocean);
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());


        //create a vertical battleship at 7,7
		Battleship battleship = new Battleship();
		row = 7;
		column = 7;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		//ocean.printWithShips();

		assertTrue(ocean.shootAt(7, 7));
		assertFalse(battleship.isSunk()); //not sunk yet
		assertEquals(4, ocean.getHitCount());
		assertTrue(ocean.shootAt(6, 7));
		assertEquals(2, ocean.getShipsSunk());



	}

	@Test
	void testGetShipArray() {

		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		for (int row = 0; row < OCEAN_SIZE; row++) {
			for (int col = 0; col < OCEAN_SIZE; col++) {
				assertEquals("empty", shipArray[row][col].getShipType());
			}
		}
		//TODO
		//More tests
		Destroyer destroyer = new Destroyer();
		int row = 2;
		int column = 5;
		boolean horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals("destroyer", shipArray[2][5].getShipType());
		assertEquals("destroyer", shipArray[2][4].getShipType());
		assertEquals("empty", shipArray[2][3].getShipType());
		assertEquals("empty", shipArray[3][4].getShipType());

		//Create a submarine
		Submarine submarine = new Submarine();
		row = 0;
		column = 0;
		submarine.placeShipAt(row, column, true, ocean);
		assertEquals("submarine", shipArray[0][0].getShipType()); //this cell is a submarine
		assertEquals("empty", shipArray[1][0].getShipType()); // Adjacent cell empty
		assertEquals("empty", shipArray[0][1].getShipType()); //  Adjacent cell empty


		//Create a submarine
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 3;
		cruiser.placeShipAt(row, column, true, ocean);
		ocean.printWithShips();
		assertEquals("cruiser", shipArray[9][2].getShipType()); //this cell is a cruiser
		assertEquals("empty", shipArray[9][4].getShipType()); // Adjacent cell empty
		assertEquals("empty", shipArray[8][3].getShipType()); //  Adjacent cell empty

	}
}
