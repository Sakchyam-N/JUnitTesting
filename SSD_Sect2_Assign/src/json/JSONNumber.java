package json;

/**
 * Represents an immutable JSON number value.<br/>
 * <br/>
 * 
 * The actual type of the stored value is determined by which version of the constructor is called.<br/>
 * <br/>
 * 
 * Rather than store everything as double, the appropriate type is used, to save space and to also ensure the value is displayed correctly when output as a string, i.e. integer
 * type values do not include the fractional part.
 * 
 * @author mdixon
 *
 */
public class JSONNumber extends JSONValue {

	/**
	 * A single shared '0' integer value.
	 */
	public final static JSONNumber JSON_ZERO_NUMBER = new JSONNumber(0);

	/**
	 * A single shared '0.0' float value.
	 */
	public final static JSONNumber JSON_ZERO_FLOAT = new JSONNumber(0.0f);

	/**
	 * The stored numerical value.
	 */
	private final Object value;

	///////////////////////////////////////////////////////////////////////////////////////

	@Override
	void appendTo(StringBuilder builder) {

		builder.append(value.toString());
	}

	///////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean equals(Object obj) {

		if (obj == this)
			return true;

		if (obj == null || obj.getClass() != getClass())
			return false;

		return value.equals(((JSONNumber) obj).value);
	}

	@Override
	public int hashCode() {

		return value.hashCode();
	}

	@Override
	public String toString() {

		return value.toString();
	}

	/**
	 * @return the numeric value.
	 */
	public Object getNumber() {

		return value;
	}

	@Override
	public boolean isNumber() {

		return true;
	}

	@Override
	public double asDouble() {

		if (value instanceof Integer)
			return (Integer) value;

		if (value instanceof Long)
			return (Long) value;

		if (value instanceof Float)
			return (Float) value;

		return (Double) value;
	}

	@Override
	public float asFloat() throws JSONException {

		if (value instanceof Integer)
			return (Integer) value;

		if (value instanceof Long)
			return (Long) value;

		if (value instanceof Float)
			return (Float) value;

		return ((Double) value).floatValue();
	}

	@Override
	public long asLong() throws JSONException {

		if (value instanceof Integer)
			return (Integer) value;

		if (value instanceof Long)
			return (Long) value;

		throw new JSONException("Number is not a valid long");
	}

	@Override
	public int asInteger() throws JSONException {

		if (value instanceof Integer)
			return (Integer) value;

		throw new JSONException("Number is not a valid integer");
	}

	@Override
	public short asShort() throws JSONException {

		if (value instanceof Integer) {

			int tmp = (Integer) value;

			if (tmp >= Short.MIN_VALUE && tmp <= Short.MAX_VALUE)
				return (short) tmp;
		}

		throw new JSONException("Number is not a valid short");
	}

	@Override
	public byte asByte() throws JSONException {

		if (value instanceof Integer) {

			int tmp = (Integer) value;

			if (tmp >= Byte.MIN_VALUE && tmp <= Byte.MAX_VALUE)
				return (byte) tmp;
		}

		throw new JSONException("Number is not a valid byte");
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * non-public constructor so can only instantiate via factory.
	 * 
	 * @param value the double value to be represented.
	 */
	JSONNumber(double value) {

		// check if range and precision actually allows storage as float
		if (((float) value) == value)
			this.value = Float.valueOf((float) value);
		else
			this.value = Double.valueOf(value);
	}

	/**
	 * non-public constructor so can only instantiate via factory.
	 * 
	 * @param value the float value to be represented.
	 */
	JSONNumber(float value) {

		this.value = Float.valueOf(value);
	}

	/**
	 * non-public constructor so can only instantiate via factory.
	 * 
	 * @param value the long value to be represented.
	 */
	JSONNumber(long value) {

		// Attempt to determine the best possible type for the value. Although this could always just be stored as
		// a double this is done to allow later detection of the actual range of the value. It also ensures integer
		// type values are output cleanly, and do not include the fractional part.

		if (value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE) {
			// in range of an integer value
			this.value = Integer.valueOf((int) value);

		} else// in range of long value
			this.value = Long.valueOf(value);
	}

}
