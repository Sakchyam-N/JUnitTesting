package json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * A JSON parser<br/><br/>
 * 
 * This is part of the very simple custom JSON package that provides minimal model building and output
 * functionality.<br/><br/>
 * 
 * This could be replaced with javax.json.Json if more complex JSON processing is ever required.<br/><br/>
 * 
 * @see http://json.org/
 * @see JSONException
 * @author mdixon
 */
public class JSONParser {

	private final static String VALID_VALS = " (valid values are { \"<string>\" : <value> , ... }, [ <value> , ... ], \"<string>\", true, false, null, <number>)";

	/**
	 * The reader from which to parse input
	 */
	private final Reader reader;

	/**
	 * The latest character read from the input stream.
	 * 
	 * @see #nextChar()
	 */
	private int next = -1;

	/**
	 * A string builder to use during parsing.
	 */
	private StringBuilder builder = new StringBuilder();
	
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Fetches the next char from the input stream, skipping any whitespace.<br/><br/>
	 * 
	 * The character is stored within the {@link #next} attribute.
	 * 
	 * @throws IOException
	 */
	private void nextChar() throws IOException {

		do {
			next = reader.read();

		} while (Character.isWhitespace(next));
	}

	/**
	 * 
	 * @return an error string showing the last read character or end of input message.
	 */
	private String getErrStr() {

		return (next != -1) ? "'" + Character.valueOf((char) next) + "'" : "end of input";
	}

	/**
	 * Parses the JSON format object currently on the input stream
	 * 
	 * @return the representative {@link JSONObject}
	 * @throws IOException
	 * @throws JSONException
	 */
	private JSONObject parseObject() throws IOException, JSONException {

		JSONObject jsonObject = new JSONObject();

		nextChar(); // consume '{'

		while (next != '}') {

			if (next != '"')
				throw new JSONException("Expected '\"' while parsing object contents. But found " + getErrStr());

			JSONString name = parseString();

			if (next != ':')
				throw new JSONException("Expected ':' while parsing object contents. But found " + getErrStr());

			nextChar(); // consume ':'

			// add the member (extracting the underlying String as the key)
			jsonObject.addMember(name.asString(), parseValue());

			if (next == ',') {
				nextChar(); // consume ','

				if (next == '}' || next == -1)
					throw new JSONException("Expected '\"' while parsing object contents. But found " + getErrStr());

			} else if (next != '}') {

				throw new JSONException("Expected ',' or '}' while parsing object contents. But found " + getErrStr());
			}
		}

		nextChar(); // consume '}'

		return jsonObject;
	}

	/**
	 * Parses the array currently on the input stream
	 * 
	 * @return the representative {@link JSONArray}
	 * @throws IOException
	 * @throws JSONException
	 */
	private JSONArray parseArray() throws IOException, JSONException {

		JSONArray jsonArray = new JSONArray();

		nextChar(); // consume '['

		while (next != ']') {

			jsonArray.addValue(parseValue());

			if (next == ',') {
				nextChar(); // consume ','

				if (next == ']' || next == -1)
					throw new JSONException("Expected 'value' while parsing array contents. But found " + getErrStr());
			} else if (next != ']') {
				throw new JSONException("Expected ',' or ']' while parsing array contents. But found " + getErrStr());
			}
		}

		nextChar(); // consume ']'

		return jsonArray;
	}

	/**
	 * Parses the 'true' currently on the input stream
	 * 
	 * @return the representative true {@link JSONBoolean} value
	 * @throws IOException
	 * @throws JSONException
	 */
	private JSONBoolean parseTrue() throws IOException, JSONException {

		char[] buffer = new char[3];

		if (reader.read(buffer) == 3 && buffer[0] == 'r' && buffer[1] == 'u' && buffer[2] == 'e') {

			nextChar();

			return JSONBoolean.JSON_TRUE;
		}
		throw new JSONException("Unexpected value " + getErrStr() + VALID_VALS);
	}

	/**
	 * Parses the 'false' currently on the input stream
	 * 
	 * @return the representative false {@link JSONBoolean} value
	 * @throws IOException
	 * @throws JSONException
	 */
	private JSONBoolean parseFalse() throws IOException, JSONException {

		char[] buffer = new char[4];

		if (reader.read(buffer) == 4 && buffer[0] == 'a' && buffer[1] == 'l' && buffer[2] == 's' && buffer[3] == 'e') {

			nextChar();

			return JSONBoolean.JSON_FALSE;
		}
		throw new JSONException("Unexpected value " + getErrStr() + VALID_VALS);
	}

	/**
	 * Parses the 'null' currently on the input stream
	 * 
	 * @return the representative {@link JSONNull} value
	 * @throws IOException
	 * @throws JSONException
	 */
	private JSONNull parseNull() throws IOException, JSONException {

		char[] buffer = new char[3];

		if (reader.read(buffer) == 3 && buffer[0] == 'u' && buffer[1] == 'l' && buffer[2] == 'l') {

			nextChar();

			return JSONNull.JSON_NULL;
		}
		throw new JSONException("Unexpected value " + getErrStr() + VALID_VALS);
	}

	/**
	 * Parses the number currently on the input stream.<br/><br/>
	 * 
	 * note: At the moment this is slightly non-standard since it will allow a 'f' or 'd' suffix to be used to
	 * explicitly identify float or double values.
	 * 
	 * @return the representative {@link JSONNumber} value
	 * @throws IOException
	 * @throws JSONException
	 */
	private JSONNumber parseNumber() throws IOException, JSONException {

		builder.setLength(0);	// re-init the shared string builder
		
		// scan up until the next terminating character normally expected after a value
		while (next != ',' && next != '}' && next != ']' && next != -1 && !Character.isWhitespace(next)) {

			builder.append(Character.valueOf((char) next));
			next = reader.read();
		}

		String str = builder.toString();

		// ensure any trailing whitespace consumed
		if (Character.isWhitespace(next))
			nextChar();

		// Attempt to determine the type for the value.

		try {
			// attempt to parse as a long first
			return JSONFactory.createNumber(Long.parseLong(str));

		} catch (NumberFormatException e) {

			// cannot parse as a long, so attempt to parse as a double
			try {
				return JSONFactory.createNumber(Double.parseDouble(str));

			} catch (NumberFormatException e2) {

				throw new JSONException("Invalid character while parsing number '" + str + "'");
			}
		}
	}

	/**
	 * Helper that parses the &bsol;u<code>xxxx</code> encoded character currently on the input stream
	 * 
	 * @return the representative {@link String} containing the decoded character
	 * @throws IOException
	 * @throws JSONException
	 */
	private String parseUnicodeChars() throws IOException, JSONException {

		char[] buffer = new char[4];

		if (reader.read(buffer) != 4)
			throw new JSONException("Expected four hex characters following \\u while parsing string");

		String str = new String(buffer);

		try {

			return Character.toString((char) Integer.parseInt(str, 16));

		} catch (NumberFormatException e) {

			throw new JSONException("Invalid hex characters following \\u while parsing string '" + str + "'");
		}
	}

	/**
	 * Parses the string currently on the input stream
	 * 
	 * @return the representative {@link JSONString} value
	 * @throws IOException
	 * @throws JSONException
	 */
	private JSONString parseString() throws IOException, JSONException {

		builder.setLength(0);	// re-init the shared string builder

		next = reader.read(); // consume delimiter (not skipping any subsequent white-space)

		while (next != '"') {

			if (next == '\\') {
				// encoded character
				nextChar(); // consume '\'

				switch (next) {

				case '\"':
					builder.append('\"');
					break;
				case '\\':
					builder.append('\\');
					break;
				case '/':
					builder.append('/');
					break;
				case 'b':
					builder.append('\b');
					break;
				case 'f':
					builder.append('\f');
					break;
				case 'n':
					builder.append('\n');
					break;
				case 'r':
					builder.append('\r');
					break;
				case 't':
					builder.append('\t');
					break;
				case 'u':
					builder.append(parseUnicodeChars());
					break;

				default:
					throw new JSONException("Unexpected escape sequence found : '\\" + Character.valueOf((char) next) + "' (valid ones are  \\b  \\t  \\n  \\f  \\r  \\\"  \\\\ \\/ \\uxxxx)");

				}
			} else if (Character.isISOControl(next)) {

				throw new JSONException("Illegal control character found while parsing string value");

			} else if (next == -1) {

				throw new JSONException("Unexpected end of input while parsing string value");

			} else {
				// a normal character
				builder.append(Character.valueOf((char) next));
			}

			next = reader.read(); // read next char (not skipping any white-space), since within a string.
		}

		nextChar(); // consume closing '"'

		return JSONFactory.createString(builder.toString());
	}

	/////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Gets the last character read from the input.
	 * 
	 * @return the last character read from the input.
	 */
	int lastChar() {

		return next;
	}

	/**
	 * Parses the value currently on the input stream
	 * 
	 * @return the parsed value
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONValue parseValue() throws IOException, JSONException {

		JSONValue result;

		switch (next) {

		case '{':
			result = parseObject();
			break;

		case '"':
			result = parseString();
			break;

		case '[':
			result = parseArray();
			break;

		case 't':
			result = parseTrue();
			break;

		case 'f':
			result = parseFalse();
			break;

		case 'n':
			result = parseNull();
			break;

		case -1:
			throw new JSONException("Unexpected end of input");

		default:
			// should be a number, which must start with a digit or a '-'
			if (Character.isDigit(next) || next == '-')
				result = parseNumber();
			else
				throw new JSONException("Unexpected value " + getErrStr() + VALID_VALS);
		}

		return result;
	}

	//////////////////////////////////////////////////////////////////////////

	/**
	 * Constructor
	 * 
	 * @param reader the reader from which to parse input
	 * @throws IOException
	 */
	private JSONParser(Reader reader) throws IOException {

		this.reader = reader;

		nextChar(); // move to first character.
	}

	/**
	 * Parses text into a representative JSON object
	 * 
	 * @param reader the reader from which to parse input
	 * @return the representative JSON object
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONValue parse(Reader reader) throws IOException, JSONException {

		JSONParser parser = new JSONParser(reader);

		JSONValue result = parser.parseValue();

		if (parser.lastChar() != -1)
			throw new JSONException("Unexpected characters following valid input");

		return result;
	}

	/**
	 * Parses text into a representative JSON object
	 * 
	 * @param str the string from which to parse input
	 * @return the representative JSON object
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONValue parse(String str) throws IOException, JSONException {

		return parse(new StringReader(str));
	}
	
	/**
	 * Parses text loaded from a file into a representative JSON object
	 * 
	 * @param fileName the name of the file containing the JSON to be parsed
	 * @return the representative JSON object
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONValue parseFile(String fileName) throws FileNotFoundException, IOException, JSONException {
		
		return parse(new BufferedReader(new FileReader(fileName)));
	}
}
