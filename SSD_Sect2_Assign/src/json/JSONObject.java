package json;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Represents a mutable JSON object. Each JSON object contains members as a set of {name:value} pairs.
 * 
 * @see http://json.org/
 * @see JSONArray
 * 
 * @author mdixon
 */
public class JSONObject extends JSONValue implements Iterable<Entry<String, JSONValue>> {

	/**
	 * The map between contained member names and their associated values.
	 */
	private final Map<String, JSONValue> members;

	///////////////////////////////////////////////////////////////////////////////////////

	@Override
	void appendTo(StringBuilder builder) {

		// Output the object into a string builder, as
		// { "<memberName_1>":<memberValue_1>, "<memberName_n>":<memberValue_n>, ... }
		builder.append("{");

		boolean comma = false;

		for (Entry<String, JSONValue> entry : members.entrySet()) {

			JSONValue value = entry.getValue();

			if (comma)
				builder.append(",");
			else
				comma = true;

			// output the (JSON encoded) property name.
			JSONString.encode(entry.getKey(), builder);

			builder.append(':');

			// append the (JSON encoded) value
			value.appendTo(builder);
		}

		builder.append("}");
	}

	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean equals(Object obj) {

		if (obj == this)
			return true;

		if (obj == null || obj.getClass() != getClass())
			return false;

		return members.equals(((JSONObject) obj).members);
	}

	@Override
	public int hashCode() {

		return members.hashCode();
	}

	@Override
	public JSONObject copy() {
		// since an object is mutable, need to create a complete new object
		return new JSONObject(this);
	}

	@Override
	public Iterator<Entry<String, JSONValue>> iterator() {

		return members.entrySet().iterator();
	}

	@Override
	public boolean isObject() {

		return true;
	}

	/**
	 * Gets the number of members contained by the JSON Object.
	 * 
	 * @return the number of members contained by the JSON Object.
	 */
	public int size() {

		return members.size();
	}

	/**
	 * @return the collection of all JSON values stored within the object.
	 */
	public Collection<JSONValue> values() {

		return members.values();
	}
	
	/**
	 * @return the set of all member names stored within the object.
	 */
	public Set<String> members() {

		return members.keySet();
	}
	
	/**
	 * Gets the named member contained by the JSON Object.
	 * 
	 * @param name the name of the member to be returned.
	 * @return the named member, null if no such member exists.
	 */
	public JSONValue getMember(String name) {

		return members.get(name);
	}
	
	/**
	 * Checks if the named member is contained by the JSON Object.
	 * 
	 * @param name the name of the member to be checked.
	 * @return true if the JSON object contains the named member, else false.
	 */
	public boolean hasMember(String name) {

		return members.containsKey(name);
	}

	/**
	 * Gets the value of a boolean member within the JSON Object.<br/><br/>
	 * 
	 * If the member does not exist specifically as a boolean member, then returns false.<br/><br/>
	 * 
	 * If the member does exist as a boolean member, then returns the value assigned to that boolean member.<br/><br/>
	 * 
	 * @param name the name of the boolean member value to be returned.
	 * @return true if the member exists as a boolean value, and it is set to true, false otherwise.
	 */
	public boolean hasBooleanMember(String name) {
		
		JSONValue member = members.get(name);
		
		if ( member != null && member.isBoolean() )
			try {
				return member.asBoolean();
			} catch (JSONException e) {
				return false;
			}
		
		return false;
	}
	
	/**
	 * Adds a {@link JSONObject} type member to the JSON Object.
	 * 
	 * @param name  the name of the member to be added.
	 * @param value the value of the member.
	 */
	public void addMember(String name, JSONObject value) {

		members.put(name, value);
	}

	/**
	 * Adds a {@link JSONArray} type member to the JSON Object.
	 * 
	 * @param name  the name of the member to be added.
	 * @param value the value of the member.
	 */
	public void addMember(String name, JSONArray value) {

		members.put(name, value);
	}

	/**
	 * Adds a {@link String} type member to the JSON Object.
	 * 
	 * @param name  the name of the member to be added.
	 * @param value the value of the member.
	 */
	public void addMember(String name, String value) {

		members.put(name, JSONFactory.createString(value));
	}

	/**
	 * Adds a double type member to the JSON Object.
	 * 
	 * @param name  the name of the member to be added.
	 * @param value the value of the member.
	 */
	public void addMember(String name, double value) {

		members.put(name, JSONFactory.createNumber(value));
	}

	/**
	 * Adds a float type member to the JSON Object.
	 * 
	 * @param name  the name of the member to be added.
	 * @param value the value of the member.
	 */
	public void addMember(String name, float value) {

		members.put(name, JSONFactory.createNumber(value));
	}

	/**
	 * Adds a long type member to the JSON Object.
	 * 
	 * @param name  the name of the member to be added.
	 * @param value the value of the member.
	 */
	public void addMember(String name, long value) {

		members.put(name, JSONFactory.createNumber(value));
	}

	/**
	 * Adds a boolean type member to the JSON Object.
	 * 
	 * @param name  the name of the member to be added.
	 * @param value the value of the member.
	 */
	public void addMember(String name, boolean value) {

		members.put(name, (value) ? JSONBoolean.JSON_TRUE : JSONBoolean.JSON_FALSE);
	}

	/**
	 * Adds a null type member to the JSON Object.
	 * 
	 * @param name the name of the member to be added.
	 */
	public void addMember(String name) {

		members.put(name, JSONNull.JSON_NULL);
	}

	/**
	 * Adds an {@link JSONValue} type member to the JSON Object.
	 * 
	 * The provided object will be output using its {@link Object#toString()} method.
	 * 
	 * @param name  the name of the member to be added.
	 * @param value the value of the member.
	 */
	public void addMember(String name, JSONValue value) {

		members.put(name, value);
	}

	@Override
	public String toString() {

		// Output the object into a string builder, as
		// { "<memberName_1>":<memberValue_1>, "<memberName_n>":<memberValue_n>, ... }
		StringBuilder builder = new StringBuilder();

		appendTo(builder);

		return builder.toString();
	}

	@Override
	public JSONObject asObject() throws JSONException {

		return this;
	}

	/**
	 * non-public constructor so can only instantiate via factory.
	 */
	JSONObject() {

		members = new LinkedHashMap<String, JSONValue>();
	}

	/**
	 * non-public constructor so can only instantiate via factory.
	 * 
	 * Creates a mutable JSON object value, as a copy of an existing object.<br/>
	 * <br/>
	 * 
	 * A deep copy of the given object contents is performed (for mutable elements).<br/>
	 * <br/>
	 * 
	 * @param original the original object from which to copy the new object contents.
	 */
	JSONObject(JSONObject original) {

		members = new LinkedHashMap<String, JSONValue>(original.members.size());

		for (Entry<String, JSONValue> entry : original.members.entrySet()) {

			// make a copy of any mutable values
			members.put(entry.getKey(), entry.getValue().copy());
		}

	}

}