package json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a mutable JSON Array. Each JSON Array contains an ordered list of values.
 * 
 * @see http://json.org/
 * @see JSONObject
 * 
 * @author mdixon
 */
public class JSONArray extends JSONValue implements Iterable<JSONValue> {

	/**
	 * The list or values within the array.
	 */
	private final List<JSONValue> values;

	///////////////////////////////////////////////////////////////////////////////////////

	@Override
	void appendTo(StringBuilder builder) {

		// Output the array into a string builder, as [ <propertyValue_1>, <propertyValue_n> ... ]
		builder.append("[");

		boolean comma = false;

		for (JSONValue value : values) {

			if (comma)
				builder.append(",");
			else
				comma = true;

			// append the (JSON encoded) value
			value.appendTo(builder);
		}

		builder.append("]");
	}

	///////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean equals(Object obj) {

		if (obj == this)
			return true;

		if (obj == null || obj.getClass() != getClass())
			return false;

		return values.equals(((JSONArray) obj).values);
	}

	@Override
	public int hashCode() {

		return values.hashCode();
	}

	@Override
	public JSONArray copy() {
		// since an array is mutable, need to create a complete new array
		return new JSONArray(this);
	}

	@Override
	public JSONArray asArray() throws JSONException {

		return this;
	}

	@Override
	public boolean isArray() {

		return true;
	}

	/**
	 * Gets the number of values within the JSON Array.
	 * 
	 * @return the number of values within the JSON Array.
	 */
	public int size() {

		return values.size();
	}

	/**
	 * Gets the value at the given index.
	 * 
	 * @param index the index of the required value
	 * @return the value at the given index
	 */
	public JSONValue get(int index) {

		return values.get(index);
	}

	/**
	 * Adds a {@link JSONObject} type value to the JSON Array.
	 * 
	 * @param value the value to be added.
	 */
	public void addValue(JSONObject value) {

		values.add(value);
	}

	/**
	 * Adds a {@link JSONArray} type value to the JSON Array.
	 * 
	 * @param value the value to be added.
	 */
	public void addValue(JSONArray value) {

		values.add(value);
	}

	/**
	 * Adds a {@link String} type value to the JSON Array.
	 * 
	 * @param value the value to be added.
	 * @return the JSON array
	 */
	public JSONArray addValue(String value) {

		values.add(JSONFactory.createString(value));

		return this;
	}

	/**
	 * Adds a double type value to the JSON Array.
	 * 
	 * @param value the value to be added.
	 */
	public void addValue(double value) {

		values.add(JSONFactory.createNumber(value));
	}

	/**
	 * Adds a float type value to the JSON Array.
	 * 
	 * @param value the value to be added.
	 */
	public void addValue(float value) {

		values.add(JSONFactory.createNumber(value));
	}

	/**
	 * Adds a long type value to the JSON Array.
	 * 
	 * @param value the value to be added.
	 */
	public void addValue(long value) {

		values.add(JSONFactory.createNumber(value));
	}

	/**
	 * Adds a boolean type value to the JSON Array.
	 * 
	 * @param value the value to be added.
	 */
	public void addValue(boolean value) {

		values.add((value) ? JSONBoolean.JSON_TRUE : JSONBoolean.JSON_FALSE);
	}

	/**
	 * Adds an {@link JSONValue} type value to the JSON Array.
	 * 
	 * The provided object will be output using its {@link Object#toString()} method.
	 * 
	 * @param value the value of the member
	 */
	public void addValue(JSONValue value) {

		values.add(value);
	}

	/**
	 * Adds a null type value to the JSON Array.
	 */
	public void addValue() {

		values.add(JSONNull.JSON_NULL);
	}

	@Override
	public String toString() {

		// Output the array into a string builder, as [ <propertyValue_1>, <propertyValue_n> ... ]
		StringBuilder builder = new StringBuilder();

		appendTo(builder);
		
		return builder.toString();
	}

	@Override
	public Iterator<JSONValue> iterator() {

		return values.iterator();
	}

	/**
	 * non-public constructor so can only instantiate via factory.
	 */
	JSONArray() {

		values = new ArrayList<JSONValue>();
	}

	/**
	 * non-public constructor so can only instantiate via factory.
	 * 
	 * @param initialSize the initial size of the array
	 */
	JSONArray(int initialSize) {

		values = new ArrayList<JSONValue>(initialSize);
	}

	/**
	 * non-public constructor so can only instantiate via factory.
	 * 
	 * Creates a mutable JSON array value, as a copy of an existing array.<br/>
	 * <br/>
	 * 
	 * A deep copy of the given array contents is performed (for mutable elements).<br/>
	 * <br/>
	 * 
	 * @param original the original array from which to copy the new array contents.
	 */
	JSONArray(JSONArray original) {

		values = new ArrayList<JSONValue>(original.size());

		for (JSONValue value : original.values) {

			// make a copy of any mutable values
			values.add(value.copy());
		}
	}

}
