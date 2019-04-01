package Exceptions;

public class InvalidTermException extends Exception {

	private String term;
	
	/**
	 * Gets the term which caused the exception
	 * @return The term
	 */
	public String getTerm() {
		return term;
	}
	
	/**
	 * Gets the message delivered by the exception raiser
	 * @return The message
	 */
	public String getMessage() {
		return message;
	}

	private String message;
	
	/**
	 * Constructs a new exception of this type
	 * @param trm The term which caused this exception
	 * @param msg A message describing what is wrong
	 */
	public InvalidTermException(String trm, String msg) {
		term = trm;
		message = msg;
	}
	
	

}
