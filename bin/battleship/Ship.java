package battleship;

public abstract class Ship {
	// the row that contains the bow (front part of the ship)
	private int bowRow;
	// the column that contains the bow (front part of the ship)
	private int bowColumn;
	//the length of the ship
	private int length;
	// a boolean that represents whether the ship is going to be placed horizontally or vertically
	private boolean horizontal;
	// an array of booleans that indicate whether that part of the ship has been hit or not
	private boolean [] hit;
	
	
	//Getters:
	
	// this constructor sets the length property of the particular ship and initializes the hit array based on that length.	
	public Ship(int length) {
		this.length = length; // set length
        hit = new boolean[this.length]; // Initialize array with given length
        for (int i = 0; i < this.length; i++) {
            hit[i] = false; // Set each position to true
        }

	}
	
	// returns the ship length
	public int getLength() {
		return this.length;
	}
	
	// returns the row corresponding to the position of the bow
	public int getBowRow() {
		return this.bowRow;
	}
	
	// returns the bow column location
	public int getBowColumn() {
		return this.bowColumn;
	}
	
	// returns the hit array
	public boolean[] getHit() {
		return this.hit;
	}
	
	// returns whether the ship is horizontal or not
	public boolean isHorizontal() {
		return this.horizontal ;
	}
	
	
	// Setters
	
	// sets the value of bowRow
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	// sets the value of bowColumn
	public void setBowColumn (int column){
		this.bowColumn = column;
	}
	
	// sets the value of the instance variable horizontal
	public void setHorizontal (boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	
	// Abstract Methods
	
	// returns the type of ship as a String. Every specific type of Ship (e.g. Battleship, Cruiser, etc.) has to override and implement this method
	// and return the corresponding ship type.
	public abstract String getShipType();
	
	
	//Other Methods
	
	/**
	 *  Based on the given row, column, and orientation, returns true if it is okay to put a ship of this length with its bow in this location;
	 *  false otherwise. The ship must not overlap another, or touch another ship (vertically, horizontally, or diagonally), and it must not "stick out"
		beyond the array. Does not actually change either the ship or the Ocean - it just says if it is legal to do so.
	 */
	boolean okToPlaceShipAt( int row, int column, boolean horizontal, Ocean ocean) {
		boolean occupied;
		// horizontal ship
		if (horizontal == true) {
			//row above:
			if (0 <= row-1) {
				for (int i = column-2; i < this.length; i++) {
				    occupied = ocean.isOccupied(row-1, i);
				    if (occupied == false) {
				    	return false;
				    }
				}
			}
			// given row
			for (int i = column-2; i < this.length; i++) {
			    occupied = ocean.isOccupied(row, i);
			    if (occupied == false) {
			    	return false;
			    }
			}
			// row below
			if (row+1 <= 9) {
				for (int i = column-2; i < this.length; i++) {
				    occupied = ocean.isOccupied(row+1, i);
				    if (occupied == false) {
				    	return false;
				    }
				}
			}
			
		}
		// vertical ship
		if (horizontal == false) {
			//left column:
			if (0 <= column-1) {
				for (int i = row-2; i < this.length; i++) {
				    occupied = ocean.isOccupied(i,column-1);
				    if (occupied == false) {
				    	return false;
				    }
				}
			}
			// given column
			for (int i = row-2; i < this.length; i++) {
			    occupied = ocean.isOccupied(i,column);
			    if (occupied == false) {
			    	return false;
			    }
			}
			// right column
			if (column+1 <= 9) {
				for (int i = row-2; i < this.length; i++) {
				    occupied = ocean.isOccupied(i,column+1);
				    if (occupied == false) {
				    	return false;
				    }
				}
			}	
		}
		return true;
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
	 * If you place a vertical cruiser at location (4,0) in the ocean, the bow is at location (4,0) and the rest of the ship occupies locations (3,0), (2,0).
	 */
	void placeShipAt (int row, int column, boolean horizontal, Ocean ocean) {
		
		this.setBowRow(row); // set row
		this.setBowColumn(column); // set column
		this.setHorizontal(horizontal); // set horizontal
		
	    // Place the ship in the 'ships' array in the Ocean object
	    if (horizontal && column-this.length >= 0 && column <= 9) { // checks for index
	        // Place the ship horizontally (from bow to left)
	        for (int i = 0; i < this.length; i++) {
	            ocean.getShipsArray()[row][column - i] = this;
	        }
	    } else if (!horizontal && row-this.length >= 0 && row <= 9){ // checks for index
	        // Place the ship vertically (from bow upwards)
	        for (int i = 0; i < this.length; i++) {
	            ocean.getShipsArray()[row - i][column] = this;
	        }
	    }
	}

	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, mark that part of the ship as “hit” (in the hit array, index 0 indicates 
	 * the bow) and return true; otherwise return false.
	 */
	boolean shootAt(int row, int column){
	    // If the ship is already sunk, return false
	    if (this.isSunk()) {
	        return false;
	    }

	    // Determine the length of the ship
	    int length = this.getLength();

	    // Check if the shot is hitting any part of the ship based on its orientation
	    if (this.horizontal) {
	        // Horizontal ship: check if the shot matches any position from bow to the left
	        if (row == this.bowRow && column <= this.bowColumn && column > this.bowColumn - length) {
	            int hitIndex = this.bowColumn - column;  // Calculate the index in the hit array
	            this.hit[hitIndex] = true;  // Mark this part of the ship as hit
	            return true;
	        }
	    } else {
	        // Vertical ship: check if the shot matches any position from bow upwards
	        if (column == this.bowColumn && row <= this.bowRow && row > this.bowRow - length) {
	            int hitIndex = this.bowRow - row;  // Calculate the index in the hit array
	            this.hit[hitIndex] = true;  // Mark this part of the ship as hit
	            return true;
	        }
	    }

	    // If the shot does not hit any part of the ship, return false
	    return false;

	}
	
	/*
	 * Return true if every part of the ship has been hit, false otherwise
	 */
	boolean isSunk(){
        for (int i = 0; i < this.length; i++) {
            if (hit[i] == false) {
            	return false;
            }
        }
		return true;
	}
	
	/*
	 * Returns a single-character String to use in the Ocean’s print method. This method should return ”s” if the ship has been sunk and ”x” if it has not been sunk. 
	 * This method can be used to print out locations in the ocean that have been shot at; it should not be used to print locations that have not been shot at. Since 
	 * toString behaves exactly the same for all ship types, it is placed here in the Ship class.
	 */
	@Override
	public String toString() {
	    // Check if the ship has been sunk
	    if (this.isSunk()) {
	        return "s";  // Return "s" if the ship is sunk
	    } else {
	        return "x";  // Return "x" if the ship is not sunk
	    }
	}
}

