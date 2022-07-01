package manager;

/**
 * A guest within a hotel {@link Room}.
 * 
 * @author mdixon
 */
public class Guest extends Person {

	/**
	 * The guest's card number.
	 */
	private String cardNo;
	
	/**
	 * 
	 * @return the guest's card number.
	 */
	public String getCardNo() {
		return cardNo;
	}
	
	///////////////////////////////////////
	
	/**
	 * Constructor
	 * 
	 * @param name the name of the guest
	 * @param cardNo the guest's card number.
	 */
	public Guest(String name, String cardNo) {
		super(name);
		this.cardNo = cardNo;
	}

	public Guest() {
		super("");
		cardNo = "";
		
	}
}
