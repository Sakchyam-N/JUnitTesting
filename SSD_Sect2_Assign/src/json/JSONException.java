package json;

/**
 * Thrown to indicate JSON Parse or number conversion issues.
 * 
 * @author mdixon
 */
@SuppressWarnings("serial")
public class JSONException extends Exception {

	/**
	 * Constructor.
	 * 
	 * @param msg
	 */
	public JSONException(String msg) {
		super(msg);
	}
}
