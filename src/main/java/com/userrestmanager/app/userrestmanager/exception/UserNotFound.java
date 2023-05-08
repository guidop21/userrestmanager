/**
 * 
 */
package com.userrestmanager.app.userrestmanager.exception;

import com.userrestmanager.app.userrestmanager.model.ErrorResponse;

/**
 * @author PastoreGu
 *
 */
public class UserNotFound extends ResourceNotFoundException {

	private static final long serialVersionUID = -5826157041224804235L;

	public UserNotFound() {
		super(new ErrorDetails<>(new ErrorResponse(ExceptionConstants.USER_NOT_FOUND.name(),
				ExceptionConstants.USER_NOT_FOUND.getMessage())));
	}

	public UserNotFound(final Throwable cause) {
		super(new ErrorDetails<>(new ErrorResponse(ExceptionConstants.USER_NOT_FOUND.name(),
				ExceptionConstants.USER_NOT_FOUND.getMessage())), cause);
	}

}
