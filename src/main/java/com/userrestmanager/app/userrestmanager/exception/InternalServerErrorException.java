/**
 * 
 */
package com.userrestmanager.app.userrestmanager.exception;

import org.springframework.http.HttpStatus;

/**
 * @author PastoreGu
 *
 */
public class InternalServerErrorException extends BusinessException {

	private static final long serialVersionUID = -5451896190999970024L;

	public InternalServerErrorException(final ErrorDetails<?> error, final Throwable cause) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, error, cause);
	}

	public InternalServerErrorException(final ErrorDetails<?> error) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, error);
	}

}