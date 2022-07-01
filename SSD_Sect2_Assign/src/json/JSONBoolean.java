package json;

/**
 * Represents an immutable JSON boolean value.<br/>
 * <br/>
 * 
 * Only two instances of this class ever exist. Saving space and ensuring {@link #equals(Object)} and {@link #hashCode()} work efficiently.
 * 
 * @author mdixon
 *
 */
public class JSONBoolean extends JSONValue {

	/**
	 * A true JSONBoolean instance
	 */
	public final static JSONBoolean JSON_TRUE = new JSONBoolean(true);

	/**
	 * A false JSONBoolean instance
	 */
	public final static JSONBoolean JSON_FALSE = new JSONBoolean(false);

	/**
	 * The boolean value.
	 */
	private final boolean value;

	///////////////////////////////////////////////////////////////////////////////////////

	@Override
	void appendTo(StringBuilder builder) {

		builder.append((value == true) ? "true" : "false");
	}

	///////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean equals(Object obj) {

		// only two instances, so can do direct comparison
		return (obj == this);
	}

	@Override
	public int hashCode() {

		return (value) ? 1231 : 1237;
	}

	@Override
	public boolean isBoolean() {

		return true;
	}

	@Override
	public boolean asBoolean() {

		return value;
	}

	@Override
	public String toString() {

		return (value == true) ? "true" : "false";
	}

	/**
	 * Private constructor to ensure no other instances created.
	 * 
	 * @param value the boolean value to be represented.
	 */
	private JSONBoolean(boolean value) {

		this.value = value;
	}
}
