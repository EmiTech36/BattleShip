
package battleship;
import java.util.Random;


public class Ocean {
	private Ship[][] ships = new Ship[10][10]; // used to quickly determine which ship is in any given location
	private int shotsFired; // the total number of shots fired by the user
	private int hitCount; // The number of times a shot his a ship. If the user shoots the same part of a ship more than once, every hit is counted
	private int shipsSunk; // The number of ships sunk (10 ships in all)

	/*
	 * Creates an "empty" ocean (and fills the ships array with EmptySea objects). You could create a private helper method to do this.
	 * Also, initialiizes any game variables, such as how many shots have been fired.
	 */
	public Ocean() {
		fill_ocean();//call the fill_ocean to set up the 10*10 sea
		shotsFired= 0;
		hitCount = 0;
		shipsSunk = 0;
	}
	private void fill_ocean(){
		for (int row = 0; row <10; row ++){
			for (int col = 0; col <10; col++){
				ships[row][col] = new EmptySea();
				ships[row][col].placeShipAt(row,col,true,this);
			}
		}
	}

	/*
	 * Place all ten ships randomly on the (initially empty) ocean. Place larger ships arrayshootat before smaller ones, or you may end up with no
	 * legal place to put a large ship. You will want to use the Random class in the java.util package, so look that up in the Java API.
	 *
	 * To help you write the code for this method, reference the printWithShips() method below. It will allow you to see where ships are actually
	 * being placed in the Ocean while you are writing and debugging your program.
	 */
	public void placeAllShipsRandomly() {

		Ship[] fleet = new Ship[10];
		for(int i=0; i<10;i++){
			if(i==0){
				fleet[i]= new Battleship();
			}else if(i>0 && i<=2){
				fleet[i]= new Cruiser();
			}else if(i>=3 && i<=5){
				fleet[i] = new Destroyer();
			}else {
				fleet[i] = new Submarine();
			}
		}
		Random random = new Random(); //initialize random
		for (int i=0; i<10; i++) {
			Ship ship = fleet[i];
			boolean isplaced = false;
			while (!isplaced) {
				int row = random.nextInt(10);
				int column = random.nextInt(10);
				boolean horizontal = random.nextBoolean();

				if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
					ship.placeShipAt(row, column, horizontal, this);
					isplaced = true;

				}
			}
		}
	}

	/*
	 * Returns true if the given location contains a ship, false if it does not
	 */
	public boolean isOccupied(int row,int column) {
		//Temporary patch to resolve out of bound issue
		if(row<0 || row > 9 || column < 0 || column > 9){
			return true;
		}
		Ship shipLocation = ships[row][column];
		if (shipLocation.getShipType().equals("empty")) {
			return false;
		} else {
			return true;
		}
	}


	/*
	 * Returns true if the given location contains a ”real” ship, still afloat, (not an EmptySea), false if it does not. In addition, this method
	 * updates the number of shots that have been fired, and the number of hits.
	 *
	 * Note: If a location contains a “real” ship, shootAt should return true every time the user shoots at that same location. Once a ship has
	 * been ”sunk”, additional shots at its location should return false.
	 */
	public boolean shootAt(int row,int col) {
		shotsFired = shotsFired + 1;
		Ship ship = ships[row][col]; //locate the ship to be shot
		if (this.isOccupied(row, col) && !ship.isSunk()) {
				hitCount = hitCount + 1;
				int ship_bowrow= ship.getBowRow();
				int ship_bowcol = ship.getBowColumn();
				if(row == ship_bowrow){ //Ship is placed horizontal
					ship.getHit()[ship_bowcol - col] = true;
				}else {
					ship.getHit()[ship_bowrow - row] = true;
				}
				if(ship.isSunk()){
					this.shipsSunk++;
				}
				return true;
		}
		if (!this.isOccupied(row,col)){ //When the location is an EmptySea object
			ship.getHit()[0] = true; // Set the EmptySea as Hit, so that the Print will show as “-" rather than "."

		}
		return false;

	}
	/*
	 * returns the number of shots fired (in the game)
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}

	/*
	 * Returns the number of hits recorded (in the game). All hits are counted, not just the first time a given square is hit.
	 */
	public int getHitCount() {
		return this.hitCount;
	}

	/*
	 * Returns the number of ships sunk (in the game)
	 */
	public int getShipsSunk() {
		return this.shipsSunk;
	}

	/*
	 * Returns true if all ships have been sunk, otherwise false.
	 */
	public boolean isGameOver() {
		if (shipsSunk == 10) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Returns the 10x10 array of Ships. The methods in the Ship class that take an Ocean parameter need to be able to look at the contents of
	 * this array; the placeShipAt() method even needs to modify it. While it is undesirable to allow methods in one class to directly access instance
	 * variables in another class, sometimes there is just no good alternative.
	 */
	public Ship[][] getShipArray(){
		return ships;
	}

	/*
	 * Prints the Ocean. To aid the user, row numbers should be displayed along the left edge of the array, and column numbers should be displayed along the top.
	 * Numbers should be 0 to 9, not 1 to 10.
	 * The top left corner square should be 0, 0.
	 * ‘x’: Use ‘x’ to indicate a location that you have fired upon and hit a (real) ship.(reference the description of toString in the Ship class).
	 * ‘-’: Use ‘-’ to indicate a location that you have fired upon and found nothing there. (reference the description of toString in the EmptySea class)
	 * ‘s’: Use ‘s’ to indicate a location containing a sunken ship. (reference the description of toString in the Ship class)
	 * ‘.’: and use ‘.’ (a period) to indicate a location that you have never fired upon.
	 * This is the only method in the Ocean class that does any input/output, and it is never called from within the Ocean class, only from the BattleshipGame class.
	 */
	public void print(){
		for(int row=0; row<11; row++){
			for(int col=0; col<11; col++) {
				if(row ==0 && col==0){
					System.out.print(" ");
					continue;
				}else if(row == 0){
					System.out.print(col-1);
					continue;
				}else if(col == 0){
					System.out.print(row-1);
					continue;
				}
				Ship current_pixel = ships[row-1][col-1];
				if(current_pixel.getBowColumn()==col-1 && current_pixel.getHit()[current_pixel.getBowRow()-row+1]){ //current pixel is same column as the Bow of the Ship -- Ship is vertical (or the location is a bow)
					 System.out.print(current_pixel);
				} else if(current_pixel.getBowRow()==row-1 && current_pixel.getHit()[current_pixel.getBowColumn()-col+1]) { //current pixel is same row as the Bow of the Ship -- Ship is horizontal (or the location is a bow)
						System.out.print(current_pixel);
				} else{
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}

	/*
	 * Used for debugging purpose only
	 * Like the print() method, this method prints the Ocean with row numbers displayed along the left edge of the array, and column numbers displayed along the top.
	 * Numbers should be 0 to 9, not 1 to 10. The top left corner square should be 0, 0.
	 * Unlike the print() method, this method shows the location of the ships. This method can be used during development and debugging, to see where ships are actually
	 * being placed in the Ocean. (The TAs may also use this method when running your program and grading.) It can be called from the BattleshipGame class, after creating
	 * the Ocean and placing ships in it.
	 * Be sure to comment out any call to this method before actually playing the game and before submitting your Java project.
	 * b’: Use ‘b’ to indicate a Battleship.
	 * ‘c’: Use ‘c’ to indicate a Cruiser.
	 * ‘d’: Use ‘d’ to indicate a Destroyer.
	 * ‘s’: Use ‘s’ to indicate a Submarine.
	 * ‘ ‘: Use ‘ ’ (single space) to indicate an EmptySea.
	 */
	public void printWithShips() {
		for(int row=0; row<11; row++){
			for(int col=0; col<11; col++) {
				if(row ==0 && col==0){
					System.out.print(" ");
					continue;
				}else if(row == 0){
					System.out.print(col-1);
					continue;
				}else if(col == 0){
					System.out.print(row-1);
					continue;
				}
				Ship current_pixel = ships[row-1][col-1];
				if(current_pixel.getShipType().equals("battleship")){
					System.out.print("b");
				}else if(current_pixel.getShipType().equals("cruiser")){
					System.out.print("c");
				}else if(current_pixel.getShipType().equals("destroyer")){
					System.out.print("d");
				}else if(current_pixel.getShipType().equals("submarine")){
					System.out.print("s");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
