package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		// test 2
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		// test 3
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
		// test 4
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		
		// test 5
		Ship emptySea = new EmptySea();
		assertEquals(1, emptySea.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		// test 2
		Ship cruiser = new Cruiser();
		int row2 = 1;
		int column2 = 5;
		boolean horizontal2 = false;
		battleship.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(row2, cruiser.getBowRow());
		
		// test 3
		Ship destroyer = new Destroyer();
		int row3 = 1;
		int column3 = 5;
		boolean horizontal3 = false;
		battleship.placeShipAt(row3, column3, horizontal3, ocean); // overwrite cruiser with destroyer
		assertEquals(row3, destroyer.getBowRow());
		
		// test 4
		Ship submarine = new Submarine();
		int row4 = 1;
		int column4 = 10; // out of bound, but this function does not check for index. 
		boolean horizontal4 = true;
		battleship.placeShipAt(row4, column4, horizontal4, ocean); // overwrite cruiser with destroyer
		assertNull(submarine.getBowRow());
		
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		// test 2
		Ship cruiser = new Cruiser();
		int row2 = 1;
		int column2 = 5;
		boolean horizontal2 = false;
		battleship.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(column2, cruiser.getBowColumn());
		
		// test 3
		Ship destroyer = new Destroyer();
		int row3 = 1;
		int column3 = 5;
		boolean horizontal3 = false;
		battleship.placeShipAt(row3, column3, horizontal3, ocean); // overwrite cruiser with destroyer
		assertEquals(column3, destroyer.getBowColumn());
		
		// test 4
		Ship submarine = new Submarine();
		int row4 = 1;
		int column4 = 10; // out of bound, but this function does not check for index. 
		boolean horizontal4 = true;
		battleship.placeShipAt(row4, column4, horizontal4, ocean); // overwrite cruiser with destroyer
		assertNull(submarine.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		// test 2
		ship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean[] hits2 = {false, false, false, true};
		ship.placeShipAt(row, column, horizontal, ocean);
		ship.shootAt(row, column);	
		assertArrayEquals(hits2, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertTrue(ship.getHit()[3]);
		
		// test 3
		ship = new Cruiser();
		int row1 = 1;
		int column1 = 3;
		boolean horizontal1 = true;
		boolean[] hits3 = {false, false, true};
		ship.placeShipAt(row1, column1, horizontal1, ocean);
		ship.shootAt(row1, column1);	
		assertArrayEquals(hits3, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertTrue(ship.getHit()[2]);
		
		// test 4
		ship.shootAt(9, 9);	// hit missed
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		// test 2
		Cruiser ship2 = new Cruiser();
		assertEquals("cruiser", ship2.getShipType());
		
		// test 3
		Submarine ship3 = new Submarine();
		assertEquals("submarine", ship3.getShipType());
		
		// test 4
		EmptySea ship4 = new EmptySea();
		assertEquals("empty", ship4.getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests	
		// test 2
		Ship cruiser = new Cruiser();
		int row1 = 0;
		int column1 = 4;
		boolean horizontal1 = true;
		battleship.placeShipAt(row1, column1, horizontal1, ocean);
		assertTrue(cruiser.isHorizontal());
		
		// test 3
		Ship destroyer = new Destroyer();
		int row2 = 0;
		int column2 = 4;
		boolean horizontal2 = true;
		battleship.placeShipAt(row2, column2, horizontal2, ocean);
		assertTrue(destroyer.isHorizontal());
		
		// test 4
		Ship emptySea = new EmptySea();
		int row3 = 0;
		int column3 = 4;
		boolean horizontal3 = false;
		battleship.placeShipAt(row3, column3, horizontal3, ocean);
		assertFalse(emptySea.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		// test 2
		Ship cruiser = new Cruiser();
		int row2 = 2;
		battleship.setBowRow(row2);
		assertEquals(row2, cruiser.getBowRow());
		
		// test 3
		Ship destroyer = new Destroyer();
		int row3 = 2;
		battleship.setBowRow(row3);
		assertEquals(row3, destroyer.getBowRow());
		
		// test 4
		Ship empty = new EmptySea();
		int row4 = 2;
		battleship.setBowRow(row4);
		assertEquals(row4, empty.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		// test 2
		Ship cruiser = new Cruiser();
		int column2 = 2;
		battleship.setBowRow(column2);
		assertEquals(column2, cruiser.getBowRow());
		
		// test 3
		Ship destroyer = new Destroyer();
		int column3 = 2;
		battleship.setBowRow(column3);
		assertEquals(column3, destroyer.getBowRow());
		
		// test 4
		Ship empty = new EmptySea();
		int column4 = 4;
		battleship.setBowRow(column4);
		assertEquals(column4, empty.getBowRow());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		// test 2
		Ship cruiser = new Cruiser();
		boolean horizontal2 = true;
		battleship.setHorizontal(horizontal2);
		assertEquals(horizontal2, cruiser.isHorizontal());
		
		// test 3
		Ship destroyer = new Destroyer();
		boolean horizontal3 = true;
		battleship.setHorizontal(horizontal3);
		assertEquals(horizontal3, destroyer.isHorizontal());
		
		// test 4
		Ship empty = new EmptySea();
		boolean horizontal4 = false;
		battleship.setHorizontal(horizontal4);
		assertEquals(horizontal4, empty.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
	}

}
