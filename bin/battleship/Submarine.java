package battleship;

/**
 * Describes a ship of length 1
 */
public class Submarine extends Ship{

	private static final int LENGTH = 1;
	private static final String TYPE = "submarine";
	
	public Submarine() {
		// TODO Auto-generated constructor stub
		super(LENGTH);	
	}
	
	/**
	 * Returns one of the string 'battleship", 'cruiser", "destroyer", or "submarine" as appropriate. Again, these types of hard-coded string values are good candidates
	 * for static final variables
	 * This method can be useful for identifying what type of ship you are dealing with, at any given point in time, and eliminates the need to use instanceof
	 * @return TYPE
	 */
	@Override
	public String getShipType() {
		return this.TYPE;
	}

}