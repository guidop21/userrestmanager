/**
 * 
 */
package com.userrestmanager.app.userrestmanager.model;

import java.io.Serializable;

/**
 * @author PastoreGu
 *
 */
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = -1515462107279993420L;

	private String key;
	private String message;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(final String key, final String message) {
		super();
		this.key = key;
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return key + " - " + message;
	}

}
