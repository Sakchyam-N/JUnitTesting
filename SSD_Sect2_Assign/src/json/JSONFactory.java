package json;

/**
 * Factory for creating JSON Values.<br/>
 * <br/>
 * 
 * Since some values are immutable, common shared instances are used when possible.
 * 
 * @author mdixon
 */
public abstract class JSONFactory {

	/**
	 * Creates an immutable JSON boolean value.
	 * 
	 * @param value the boolean value
	 * @return the created {@link JSONBoolean} value.
	 */
	public final static JSONBoolean createBoolean(boolean value) {

		// Use one of the two shared instances
		return (value) ? JSONBoolean.JSON_TRUE : JSONBoolean.JSON_FALSE;
	}

	/**
	 * Creates an immutable JSON null value.
	 * 
	 * @return the created {@link JSONNull} value.
	 */
	public final static JSONNull createNull() {

		// Use the single shared instance
		return JSONNull.JSON_NULL;
	}

	/**
	 * Creates an immutable JSON string value.
	 * 
	 * @param value the string value
	 * @return the created {@link JSONString} value.
	 */
	public final static JSONString createString(String value) {

		// Create a new JSONString or use the shared empty string instance
		return (value != null && !value.isEmpty()) ? new JSONString(value) : JSONString.JSON_EMPTY_STRING;
	}

	/**
	 * Creates an immutable JSON number value.
	 * 
	 * @param value the numeric value
	 * @return the created {@link JSONNumber} value.
	 */
	public final static JSONNumber createNumber(long value) {

		// Create a new JSONNumber or use the shared 0 value instance
		return (value != 0) ? new JSONNumber(value) : JSONNumber.JSON_ZERO_NUMBER;
	}

	/**
	 * Creates an immutable JSON number value.
	 * 
	 * @param value the numeric value
	 * @return the created {@link JSONNumber} value.
	 */
	public final static JSONNumber createNumber(float value) {

		// Create a new JSONNumber or use the shared 0 value instance
		return (value != 0) ? new JSONNumber(value) : JSONNumber.JSON_ZERO_FLOAT;
	}

	/**
	 * Creates an immutable JSON number value.
	 * 
	 * @param value the numeric value
	 * @return the created {@link JSONNumber} value.
	 */
	public final static JSONNumber createNumber(double value) {

		// Create a new JSONNumber or use the shared 0 value instance
		return (value != 0) ? new JSONNumber(value) : JSONNumber.JSON_ZERO_FLOAT;
	}

	/**
	 * Creates a mutable JSON object value.
	 * 
	 * @return the created {@link JSONObject} value.
	 */
	public final static JSONObject createObject() {

		return new JSONObject();
	}

	/**
	 * Creates a mutable JSON object value, as a copy of an existing object.<br/>
	 * <br/>
	 * 
	 * A deep copy of the given object contents is performed (for mutable elements).<br/>
	 * <br/>
	 * 
	 * @param original the original object from which to copy the new object contents.
	 * @return the created {@link JSONObject} value.
	 */
	public final static JSONObject createObject(JSONObject original) {

		return new JSONObject(original);
	}

	/**
	 * Creates a mutable JSON array value.
	 * 
	 * @return the created {@link JSONArray} value.
	 */
	public final static JSONArray createArray() {

		return new JSONArray();
	}

	/**
	 * Creates a mutable JSON array value with a specific initial size.
	 * 
	 * @param initialSize the initial size of the array
	 * @return the created {@link JSONArray} value.
	 */
	public final static JSONArray createArray(int initialSize) {

		return new JSONArray(initialSize);
	}

	/**
	 * Creates a mutable JSON array value, as a copy of an existing array.<br/>
	 * <br/>
	 * 
	 * A deep copy of the given array contents is performed (for mutable elements).<br/>
	 * <br/>
	 * 
	 * @param original the original array from which to copy the new array contents.
	 * @return the created {@link JSONArray} value.
	 */
	public final static JSONArray createArray(JSONArray original) {

		return new JSONArray(original);
	}
}
