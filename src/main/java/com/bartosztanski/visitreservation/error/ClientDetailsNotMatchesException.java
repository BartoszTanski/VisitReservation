package com.bartosztanski.visitreservation.error;

public class ClientDetailsNotMatchesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8363296284078335551L;

	public ClientDetailsNotMatchesException() {
		super();
	}
	
	public ClientDetailsNotMatchesException(String message) {
		super(message);
	}
	
	public ClientDetailsNotMatchesException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ClientDetailsNotMatchesException(Throwable cause) {
		super(cause);
	}
	
	public ClientDetailsNotMatchesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
