package com.bartosztanski.visitreservation.error;

public class VisitNotAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3706262616960534362L;
	
	public VisitNotAvailableException() {
		super();
	}
	
	public VisitNotAvailableException(String message) {
		super(message);
	}
	
	public VisitNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public VisitNotAvailableException(Throwable cause) {
		super(cause);
	}
	
	public VisitNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
