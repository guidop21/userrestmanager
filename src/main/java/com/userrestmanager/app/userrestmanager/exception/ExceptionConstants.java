/**
 * 
 */
package com.userrestmanager.app.userrestmanager.exception;

/**
 * @author PastoreGu
 *
 */
public enum ExceptionConstants {
	INVALID_INPUT("Input not valid"), USER_NOT_FOUND("User not found");

	private String message;

	private ExceptionConstants(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
