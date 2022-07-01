package manager;

/**
 * A type of {@link Property} which acts as a place of residence.
 * 
 * @author mdixon
 */
abstract class Domicile extends Property {

	private int bedrooms;
	private boolean isFurnished;

	
	/**
	 * @return the number of bedrooms
	 */
	public int getBedrooms() {
		return bedrooms;
	}
	
	/**
	 * @return true if furnished
	 */
	public boolean isFurnished() {
		return isFurnished;
	}
	
	
	/**
	 * Constructor
	 * 
	 * @param addr the address of the domicile
	 * @param bedrooms the number of bedrooms
	 */
	Domicile(String addr, int bedrooms) {
		super(addr);
		this.bedrooms = bedrooms;
		
	}

}
