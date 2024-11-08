
public abstract class Ship {
	// the row that contains the bow (front part of the ship)
	private int bowRow;
	// the column that contains the bow (front part of the ship)
	private int bowColumn;
	//the length of the ship
	private int length;
	// a boolean that represents whether the ship is going to be placed horizontallly or vertically
	private boolean horizontal;
	// an array od booleans that indicate whether that part of the ship has been hit or not
	private boolean [] hit;
	
	
	//Getters:
	
	// this constructor sets the length property of the particular ship and initializes the hit array based on that length.	
	public Ship(int length) {

	}
	
	// returns the ship length
	public int getLength() {
		
	}
	
	// returns the row correspondinng to the position of the bow
	public int getBowRow() {
		
	}
	
	// returns the bow column location
	public int getBowColumn() {
		
	}
	
	// returns the hit array
	public boolean[] getHit() {
		
	}
	
	// returns whether the ship is horizontal or not
	public boolean isHorizontal() {
		
	}
	
	
	// Setters
	
	// sets the value of bowRow
	public void setBowRow(int row) {
		
	}
	
	// sets the value of bowColumn
	public void setBowColumn (int column){
		
	}
	
	// sets the value of the instance variable horizontal
	public void setHorizontal (boolean horizontal) {
		
	}
	
	
	// Abstract Methods
	
	// returns the type of ship as a String. Every specific type of Ship (e.g. Battleship, Cruiser, etc.) has to override and implement this method
	// and return the corresponding ship type.
	public abstract String getShipType() {
		
	}
	
	
	//Other Methods
	
	/**
	 *  Based on the given row, column, and orientation, returns true if it is okay to put a ship of this length with its bow in this location;
	 *  false otherwise. Teh ship must not overlap another, or touch another ship (vertically, horizontally, or diagonally), and it must not "stick out"
		beyond the array. Does not actually change either the ship or the Ocean - it just says if it is leagal to do so.
	 */
	boolean okToPlaceShipAt( int row, int column, boolean horizontal, Ocean ocean) {
		
	}
	
	/**
	 * 'Puts' the ship in the ocean. This involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship, and it also involves
	 * putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean object. (Note: This will be as many as four 
	 * identical references; you can't refer to a 'part' of a ship, only to the whole ship.)
	 * 
	 * For placement consistency (although it doesn't really affect how you play the game), let's agree that horizontal ships face East (bow at right end) and 
	 * vertical ships face South (bow at bottom end). 
	 * This means if you place a horizontal battleship at location (9,8) in the ocean, the bow is at location (9,8) and the rest of the shp occupies locations: (9,7)
	 * , (9,6), (9,5).
	 * If you place a vertical cruiser at location (4,0) in the ocean, the bow is at lcoation (4,0) and the rest of the ship occupies locations (3,0), (2,0).
	 */
	void placeShipAt (int row, int column, boolean horizontal, Ocean ocean) {
		
	}
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, mark that part of the ship as “hit” (in the hit array, index 0 indicates 
	 * the bow) and return true; otherwise return false.
	 */
	boolean shootAt(int row, int column){
		
	}
	
	/*
	 * Return true if every part of the ship has been hit, false otherwise
	 */
	boolean isSunk(){
		
	}
	
	/*
	 * Returns a single-character String to use in the Ocean’s print method. This method should return ”s” if the ship has been sunk and ”x” if it has not been sunk. 
	 * This method can be used to print out locations in the ocean that have been shot at; it should not be used to print locations that have not been shot at. Since toString
	 * behaves exactly the same for all ship types, it is placed here in the Ship class.
	 */
	@Override
	public String toString() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	// 
