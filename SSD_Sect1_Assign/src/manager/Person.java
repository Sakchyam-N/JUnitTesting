package manager;

/**
 * Abstract class to represent a person.
 * 
 * @author mdixon
 *
 */
abstract class Person {

	private String name;
	private String addr;
	private String phone;
	private String email;

	/**
	 * @return the home address of the person
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr the home address of the person
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the phone number of the person
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone number of the person
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email addr of the person
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email addr of the person
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the name of the person
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param the new name of the person
	 */
	public void setName(String name) {
		this.name = name;
	}

	///////////////////////////////////////////////

	/**
	 * Constructor
	 * 
	 * @param name the name of the person
	 */
	Person(String name) {
		this.name = name;
	}
}
