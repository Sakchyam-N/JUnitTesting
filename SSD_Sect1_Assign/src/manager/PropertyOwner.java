package manager;

import java.util.ArrayList;
import java.util.List;

/**
 * A person that owns {@link Property} instances.
 * 
 * @author mdixon
 *
 */
public class PropertyOwner extends Person {

	private List<Property> propertyList = new ArrayList<Property>();
	
	/**
	 * Adds a property to the set of owned properties.
	 * 
	 * @param prop the property to be added
	 * @return true if the property added, false if already owned.
	 */
	public boolean addProperty(Property prop) {
		if(propertyList.contains(prop)) {
			return false;
		}
		else {
			propertyList.add(prop);
			return true;
		}
	}
	
	/**
	 * Removes a property from the set of owned properties.
	 * 
	 * @param prop the property to be removed
	 * @return true if the property was removed, false if it was not owned
	 */
	public boolean removeProperty(Property prop) {
		if(this.propertyList.remove(prop)) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Clears all properties from the set of owned properties.
	 */
	public void clearOwnedProperties() {
		this.propertyList.removeAll(propertyList);
	}
	
	/**
	 * Gets the number of owned properties
	 * 
	 * @return the number of owned properties
	 */
	public int getPropertyCount() {
		
		int noOfPropOwned = this.propertyList.size();
		return noOfPropOwned;
	}
	
	/////////////////////////////////////////////////////////
	
	/**
	 * Constructor
	 * 
	 * @param name the property owner's name
	 */
	public PropertyOwner(String name) {
		
		super(name);
	}
}
