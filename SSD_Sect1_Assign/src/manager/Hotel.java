package manager;

import java.util.ArrayList;
import java.util.List;

/**
 * A Hotel containing many {@link Room} instances.
 * 
 * @author mdixon
 *
 */
public class Hotel extends Property {
	
	List<Room> roomsList;
	
	//////////////////////////////////
	
	/**
	 * 
	 * @return the number of rooms within the hotel.
	 */
	public int getRooms() {
		return roomsList.size();
	}
	
	/**
	 * Set the specified room to be occupied by the given guest.
	 * 
	 * @param roomNo  the room number
	 * @param guest the guest
	 */
	public void occupyRoom(int roomNo, Guest guest) {
		
		roomsList.get(roomNo).setOccupant(guest);
		
	}
	
	/**
	 * Ensures the specified room is unoccupied.
	 * 
	 * @param roomNo the room number
	 */
	public void freeRoom(int roomNo) {
		roomsList.get(roomNo).removeOccupant();
	}
	
	/**
	 * Gets the count of number of occupied rooms
	 * 
	 * @return the occupied room count
	 */
	public int getOccupiedRoomCount() {
		int count = 0;
		//check each room (using a loop) to see if it is occupied. Add to count if so.
		for(int index=0;index<roomsList.size();index++) {
			if(roomsList.get(index).hasOccupant()) {
				count++;
			}
		}
		return count;
	}
	
	//////////////////////////////////////////////
	
	/**
	 * Constructor
	 * 
	 * @param addr the address of the hotel
	 * @param roomCount the number of rooms in the hotel
	 */
	public Hotel(String addr, int roomCount) {
		super(addr);
		//create array/collection along with Room stored instances
		roomsList = new ArrayList<Room>();
		for(int i = 0; i < roomCount; i++) {
			roomsList.add(new Room(i));
		}
	}
	
	public Hotel() {
		super("");
		roomsList = new ArrayList<Room>();
	}
}
