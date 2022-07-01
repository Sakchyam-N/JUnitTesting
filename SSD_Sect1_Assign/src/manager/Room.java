package manager;

/**
 * Represents a Room within a {@link Hotel}.
 * 
 * @author mdixon
 *
 */
public class Room implements SecuredAccess {

	/**
	 * The room number within the hotel.
	 */
	private int roomNum;
	private String storedCode="";
	
	
	// TODO:Part3 add attribute to store current Guest (occupant)
	private Guest occupant;
	
	////////////////////////////////
	
	@Override
	public void setCode(String code) {
		this.storedCode = code;
	}

	@Override
	public boolean checkCode(String code) {
		if(code.equals(storedCode)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void resetToDefault() {
		this.storedCode = "9999";
	}

	@Override
	public boolean isLockedOut() {
		return false;
	}

	@Override
	public int getIncorrectAttempts() {
		return 0;
	}
	
	/**
	 * @return the roomNum
	 */
	public int getRoomNum() {
		return roomNum;
	}

	/**
	 * @param roomNum the roomNum to set
	 */
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	/**
	 * Sets the occupant of the room.
	 * 
	 * @param guest the guest which is to occupy the room
	 */
	public void setOccupant(Guest guest) {
		this.occupant = guest;	
	}
	
	/**
	 * Removes any occupant from the room.
	 */
	public void removeOccupant() {
		this.occupant = null;
	}
	
	/**
	 * 
	 * @return true if the room has an occupant
	 */
	public boolean hasOccupant() {
		if(occupant == null) {
			return false;
		}
		return true;
	}

	
	/**
	 * Constructor.
	 * 
	 * @param roomNum the room number
	 */
	public Room(int roomNum) {
		this.roomNum = roomNum;
		this.storedCode = "9999";
	}
	
	public Room() {
		this.storedCode = "9999";
	}


}
