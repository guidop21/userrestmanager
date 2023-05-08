/**
 * 
 */
package com.userrestmanager.app.userrestmanager.exception;

import java.io.Serializable;

/**
 * @author PastoreGu
 *
 */
public class ErrorDetails<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = -2728086027357883575L;

	private T details;

	public ErrorDetails(final T details) {
		super();
		this.details = details;

	}

	public T getDetails() {
		return details;
	}

	public void setDetails(final T details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return details.toString();
	}
}
