/**
 * 
 */
package com.userrestmanager.app.userrestmanager.exception;

import org.springframework.http.HttpStatus;

/**
 * @author PastoreGu
 *
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -6506630054000021139L;

	private final HttpStatus code;
	private final ErrorDetails<?> error;

	public BusinessException(final HttpStatus code, final ErrorDetails<?> error, final Throwable cause) {
		super(error.toString(), cause);
		this.code = code;
		this.error = error;
	}

	public BusinessException(final HttpStatus code, final ErrorDetails<?> error) {
		super(error.toString());
		this.code = code;
		this.error = error;
	}

	public HttpStatus getCode() {
		return code;
	}

	@SuppressWarnings("rawtypes")
	public ErrorDetails getError() {
		return error;
	}

}
