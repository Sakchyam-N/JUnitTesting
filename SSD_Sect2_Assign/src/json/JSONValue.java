package json;

/**
 * Base class for JSON values.<br/>
 * <br/>
 * 
 * @author mdixon
 */
abstract public class JSONValue {

	/**
	 * Appends the JSONValue as a string to the given builder
	 * 
	 * @param builder the string builder to which the string version of the value is to be appended.
	 */
	abstract void appendTo(StringBuilder builder);

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * @return a deep copy of the value
	 */
	public JSONValue copy() {
		// by default assume the value is immutable, and return this value itself (mutable sub-classes must override).
		return this;
	}

	/**
	 * 
	 * @return true if the value is a {@link JSONObject}
	 */
	public boolean isObject() {

		return false;
	}

	/**
	 * 
	 * @return true if the value is a {@link JSONArray}
	 */
	public boolean isArray() {

		return false;
	}

	/**
	 * 
	 * @return true if the value is a {@link JSONBoolean}
	 */
	public boolean isBoolean() {

		return false;
	}

	/**
	 * 
	 * @return true if the value is a {@link JSONNumber}
	 */
	public boolean isNumber() {

		return false;
	}

	/**
	 * 
	 * @return true if the value is a {@link JSONString}
	 */
	public boolean isString() {

		return false;
	}

	/**
	 * 
	 * @return true if the value is a {@link JSONNull}
	 */
	public boolean isNull() {

		return false;
	}

	/**
	 * 
	 * @return the value as a JSON Object
	 * @throws JSONException if the value is not a valid object
	 */
	public JSONObject asObject() throws JSONException {

		throw new JSONException("Value is not a valid object");
	}

	/**
	 * 
	 * @return the value as a JSON Array
	 * @throws JSONException if the value is not a valid array
	 */
	public JSONArray asArray() throws JSONException {

		throw new JSONException("Value is not a valid array");
	}

	/**
	 * Gets the stored string value.<br/>
	 * <br/>
	 * 
	 * @return the value as a string
	 * @throws JSONException if the value is not a valid string
	 */
	public String asString() throws JSONException {

		throw new JSONException("Value is not a valid string");
	}

	/**
	 * 
	 * @return the value as a boolean
	 * @throws JSONException if the value is not a valid boolean
	 */
	public boolean asBoolean() throws JSONException {

		throw new JSONException("Value is not a valid boolean");
	}

	/**
	 * 
	 * @return the value as a double value
	 * @throws JSONException if the value is not a valid double
	 */
	public double asDouble() throws JSONException {

		throw new JSONException("Value is not a valid double");
	}

	/**
	 * note: this may lose precision since if the number was stored as a double then narrowing conversion may apply.
	 * 
	 * @return the value as a float value
	 * @throws JSONException if the value is not a valid float
	 */
	public float asFloat() throws JSONException {

		throw new JSONException("Value is not a valid float");
	}

	/**
	 * 
	 * @return the value as a long value
	 * @throws JSONException if the value is not a valid long
	 */
	public long asLong() throws JSONException {

		throw new JSONException("Value is not a valid long");
	}

	/**
	 * 
	 * @return the value as an integer value
	 * @throws JSONException if the value is not a valid integer
	 */
	public int asInteger() throws JSONException {

		throw new JSONException("Value is not a valid integer");
	}

	/**
	 * 
	 * @return the value as a short value
	 * @throws JSONException if the value is not a valid short
	 */
	public short asShort() throws JSONException {

		throw new JSONException("Value is not a valid short");
	}

	/**
	 * 
	 * @return the value as a byte value
	 * @throws JSONException if the value is not a valid byte
	 */
	public byte asByte() throws JSONException {

		throw new JSONException("Value is not a valid byte");
	}
}
