/**
 * 
 */
package com.userrestmanager.app.userrestmanager.response;

import com.userrestmanager.app.userrestmanager.model.UserModel;

/**
 * @author PastoreGu
 *
 */
public class SingleResponse {

	private String message;
	private UserModel user;

	public SingleResponse() {
		super();
	}

	public SingleResponse(String message) {
		super();
		this.message = message;
	}

	public SingleResponse(String message, UserModel user) {
		super();
		this.message = message;
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SingleResponse [message=" + message + ", user=" + user + "]";
	}

}
