package manager;

public class Office extends Property implements SecuredAccess {

	/**
	 * The stored security code.
	 */
	private String storedCode="";	
	private int IncorrectAttempts=0;
	
	
	@Override
	public void setCode(String code) {
		this.storedCode = code;
		IncorrectAttempts = 0;
	}

	@Override
	public boolean checkCode(String code) {

		if (isLockedOut() || !code.equals(storedCode)) {
			// is locked out, or codes do not match
			IncorrectAttempts++;
			return false;
		}
		else {
			// not locked, and codes match
			IncorrectAttempts = 0;
			return true;
		}
	}

	@Override
	public void resetToDefault() {
		this.storedCode = "1234";
		IncorrectAttempts = 0;
	}

	@Override
	public boolean isLockedOut() {

		// check attempts again limit, return true if limit exceeded
		if(this.IncorrectAttempts >5) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public int getIncorrectAttempts() {
		return this.IncorrectAttempts;
	}
	
	//////////////////////////////////////////////////////

	public Office(String address) {

		super(address);
		this.storedCode = "1234";
	}
	
	public Office() {
		this.storedCode = "1234";
	}

	
}
