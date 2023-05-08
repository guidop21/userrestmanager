/**
 * 
 */
package com.userrestmanager.app.userrestmanager.response;

import java.util.List;

import com.userrestmanager.app.userrestmanager.model.UserModel;

/**
 * @author PastoreGu
 *
 */
public class ResponseList {

	private Integer totalResult;
	private String message;
	private List<UserModel> users;

	public ResponseList() {
	}

	public ResponseList(Integer totalResult, String message, List<UserModel> users) {
		super();
		this.totalResult = totalResult;
		this.message = message;
		this.users = users;
	}

	public Integer getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "ResponseList [totalResult=" + totalResult + ", message=" + message + ", users=" + users + "]";
	}

}
