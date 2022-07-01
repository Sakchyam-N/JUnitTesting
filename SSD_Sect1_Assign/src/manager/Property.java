package manager;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that represents all types of properties.
 * 
 * @author mdixon
 *
 */
abstract class Property {

	private String address;
	private List<Tenant> tenantList = new ArrayList<Tenant>();
	
	/**
	 * 
	 * @return the property address.
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the tenant of the property.
	 * 
	 * @param tenant the tenant of the property
	 */
	public void setTenant(Tenant tenant) {
		this.tenantList.add(tenant);
	}
	
	/**
	 * Removes any tenant from the property.
	 */
	public void removeTenant() {
		int tenantLastIndex = tenantList.size()-1;
		this.tenantList.remove(tenantLastIndex);
	}
	
	/**
	 * 
	 * @return true if the property has a tenant
	 */
	public boolean hasTenant() {
		if(this.tenantList.size()>0)
			return true;
		return false;
	}
	
	/**
	 * Constructor
	 * 
	 * @param address the property address.
	 */
	Property(String address) {
		this.address = address;
	}
	
	public Property() {
		address = "";
	}
	
}
