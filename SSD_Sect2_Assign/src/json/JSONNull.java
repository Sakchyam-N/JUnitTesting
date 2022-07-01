package json;

/**
 * Represents an immutable JSON null value.<br/><br/>
 * 
 * Only one instance of this class ever exists. Saving space and ensuring {@link #equals(Object)} and
 * {@link #hashCode()} work efficiently.
 * 
 * @author mdixon
 *
 */
public class JSONNull extends JSONValue {

	/**
	 * A single shared NULL value. Only one copy needs to exist since null values have no state.
	 */
	public final static JSONNull JSON_NULL = new JSONNull();

	///////////////////////////////////////////////////////////////////////////////////////

	@Override
	void appendTo(StringBuilder builder) {

		builder.append("null");
	}

	///////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean equals(Object obj) {

		// only one instance, so can do direct comparison
		return (obj == this);
	}

	@Override
	public int hashCode() {
		
		return 953;
	}
	
	@Override
	public boolean isNull() {
		
		return true;
	}
	
	@Override
	public String toString() {

		return "null";
	}

	/**
	 * Private constructor to ensure no other instances created.
	 */
	private JSONNull() {
	}
}
