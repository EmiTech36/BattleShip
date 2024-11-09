package battleship;

/**
 * Describes a ship of length 3
 */
public class Cruiser extends Ship{
	
	private static final int LENGTH = 3;
	private static final String TYPE = "cruiser";
	
	public Cruiser() {
		// TODO Auto-generated constructor stub
		super(LENGTH);
	}
	
	/**
	 * Returns one fo the stringi 'battleship", 'cruiser", "destroyer", or "submarine" as appropriate. Again, these types of hard-coded string values are good candidates
	 * for static final variables
	 * This method can be uesful for identifying what type of ship you are dealing with, at any given point in time, and eliminates the need to use instanceof
	 * @return TYPE
	 */
	@Override
	public String getShipType() {
		return this.TYPE;
	}

}

