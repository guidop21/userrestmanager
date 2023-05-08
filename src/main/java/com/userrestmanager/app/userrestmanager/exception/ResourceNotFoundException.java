/**
 * 
 */
package com.userrestmanager.app.userrestmanager.exception;

import org.springframework.http.HttpStatus;

/**
 * @author PastoreGu
 *
 */
public class ResourceNotFoundException extends BusinessException {

	private static final long serialVersionUID = -5451896190999970024L;

	public ResourceNotFoundException(final ErrorDetails<?> error, final Throwable cause) {
		super(HttpStatus.NOT_FOUND, error, cause);
	}

	public ResourceNotFoundException(final ErrorDetails<?> error) {
		super(HttpStatus.NOT_FOUND, error);
	}

}