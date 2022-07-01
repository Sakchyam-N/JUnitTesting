package manager;

/**
 * A represent a Flat type {@link Domicile}.
 * 
 * @author mdixon
 */
public class Flat extends Domicile {

	private int occupants;
	
	/**
	 * Sets the number of occupants within the flat.
	 * 
	 * @param occupants the number of occupants within the flat.
	 */
	public void setOccupants(int occupants) {
		this.occupants = occupants;
	}
	
	/**
	 * Gets the number of occupants within the flat.
	 * 
	 * @return the number of occupants within the flat.
	 */
	public int getOccupants() {
		return occupants;
	}
	
	/**
	 * Constructor
	 * 
	 * @param addr the address of the flat
	 * @param bedrooms the number of bedrooms
	 */
	public Flat(String addr, int bedrooms) {
		
		super(addr, bedrooms);
	}
}
