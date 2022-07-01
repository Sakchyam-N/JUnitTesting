package manager;

/**
 * Defines an interface used to check security codes.
 * 
 * Used for Part 2.
 * 
 * @author mdixon
 */
public interface SecuredAccess {

	/**
	 * Check if the given code matches the stored code.
	 * 
	 * @param code the candidate code
	 * @return true of the codes match, false otherwise
	 */
	boolean checkCode(String code);
	
	/**
	 * Sets the currently stored code.
	 * 
	 * @param code the stored code
	 */
	void setCode(String code);
	
	/**
	 * Resets the stored code to the default value.
	 */
	void resetToDefault();
	
	/**
	 * Checks if access has been locked out due to too many incorrect attempts.
	 * 
	 * @return true if locked out due to too many attempts
	 */
	boolean isLockedOut();
	
	/**
	 * Gets the number of recorded incorrect access attempts made by calling {@link #checkCode(String)}
	 * 
	 * @return the number of incorrect access attempts
	 */
	int getIncorrectAttempts();
}
