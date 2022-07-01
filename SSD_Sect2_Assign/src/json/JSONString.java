package json;

/**
 * Represents an immutable JSON string value.
 * 
 * @author mdixon
 *
 */
public class JSONString extends JSONValue {

	/**
	 * A single shared empty string value. This instance is always used when an empty string is required.
	 */
	public final static JSONString JSON_EMPTY_STRING = new JSONString("");

	/**
	 * The String value. This is always stored in raw (un-encoded form).
	 */
	private final String value;

	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	void appendTo(StringBuilder builder) {

		if (this == JSON_EMPTY_STRING)
			builder.append("\"\"");

		// Since the value is a String, place within double quotes and encode special characters (such as " and \)
		encode(value, builder);
	}

	////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Encodes the given String into a JSON compatible value.<br/>
	 * <br/>
	 * 
	 * Since the value is a String, it is placed within double quotes and special characters (such as " and \) are encoded.
	 * 
	 * @param value   the String to be encoded.
	 * @param builder the {@link StringBuilder} to which the encoded value is to be appended.
	 * @return the provided {@link StringBuilder} builder.
	 * @See http://json.org/
	 */
	public static StringBuilder encode(String value, StringBuilder builder) {

		// The value is a String type, so place within quotes and encode special characters.
		builder.append('\"');

		String str = (String) value;

		int len = str.length();

		for (int i = 0; i < len; i++) {

			char c = str.charAt(i);

			// Check for any characters which require encoding.
			switch (c) {

			case '"':
				builder.append("\\\"");
				break;

			case '\\':
				builder.append("\\\\");
				break;

			case '/':
				builder.append("\\/");
				break;

			case '\b':
				builder.append("\\b");
				break;

			case '\f':
				builder.append("\\f");
				break;

			case '\n':
				builder.append("\\n");
				break;

			case '\r':
				builder.append("\\r");
				break;

			case '\t':
				builder.append("\\t");
				break;

			default:

				if (Character.isISOControl(c)) {
					// is a control character, so output as four hex digits.					
					if (c < 0x100) {
						builder.append((c < 0x10) ? "\\u000" : "\\u00");
					} else {
						builder.append((c < 0x1000) ? "\\u0" : "\\u");
					}
					builder.append(Integer.toHexString(c));
				} else
					// regular character.
					builder.append(c);
			}
		}

		builder.append('\"');

		return builder;
	}

	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean equals(Object obj) {

		if (obj == this)
			return true;

		if (obj == null || obj.getClass() != getClass())
			return false;

		return value.equals(((JSONString) obj).value);
	}

	@Override
	public int hashCode() {

		return value.hashCode();
	}

	@Override
	public boolean isString() {

		return true;
	}

	@Override
	public String toString() {

		if (this == JSON_EMPTY_STRING)
			return "\"\"";

		// Since the value is a String, place within double quotes and encode special characters (such as " and \)
		return encode(value, new StringBuilder()).toString();
	}
	
	@Override
	public String asString() throws JSONException {

		// unlike toString() this returns the raw string value, not enclosed within double quotes and without encoding of special characters.
		return value;
	}

	/** 
	 * @return the raw string value (not enclosed within double quotes and without encoding of special characters.)
	 */
	public String getString() {
		
		return value;
	}
	
	/**
	 * non-public constructor so can only instantiate via factory.
	 * 
	 * @param value the string value to be represented.
	 */
	JSONString(String value) {

		this.value = value;
	}
}
