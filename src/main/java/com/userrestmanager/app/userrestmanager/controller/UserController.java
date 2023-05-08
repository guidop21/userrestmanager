/**
 * 
 */
package com.userrestmanager.app.userrestmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.userrestmanager.app.userrestmanager.common.controller.AbstractController;
import com.userrestmanager.app.userrestmanager.inputBean.CsvDataInputBean;
import com.userrestmanager.app.userrestmanager.model.UserModel;
import com.userrestmanager.app.userrestmanager.response.ResponseList;
import com.userrestmanager.app.userrestmanager.response.SingleResponse;
import com.userrestmanager.app.userrestmanager.service.UserService;

import io.swagger.annotations.ApiOperation;

/**
 * @author PastoreGu
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Find All User", notes = "Retrieve All Users present on DB")
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ResponseList> findAllUser() {
		final String methodName = String.format("findAllUser");
		try {
			startLog(methodName);
			ResponseList response = userService.findAllUser();
			endLog(methodName, response);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@ApiOperation(value = "Find User By Id", notes = "Retrieve the User present on DB by Id")
	@GetMapping("/{id}")
	public ResponseEntity<SingleResponse> findUserById(@PathVariable("id") Integer id) {
		final String methodName = String.format("findUserById , %d", id);
		try {
			startLog(methodName, id);
			SingleResponse response = userService.findById(id);
			endLog(methodName, id, response);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@ApiOperation(value = "Find User By Name", notes = "Retrieve the User present on DB by Name")
	@GetMapping("name/{name}")
	public ResponseEntity<ResponseList> findUserByName(@PathVariable("name") String name) {
		final String methodName = String.format("findUserByName %s", name);
		try {
			startLog(methodName, name);
			ResponseList response = userService.findByName(name);
			endLog(methodName, name, response);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@ApiOperation(value = "Find User By Surname", notes = "Retrieve the User present on DB by Surname")
	@GetMapping("surname/{surname}")
	public ResponseEntity<ResponseList> findUserBySurname(@PathVariable("surname") String surname) {
		final String methodName = String.format("findUserBySurname %s", surname);
		try {
			startLog(methodName, surname);
			ResponseList response = userService.findBySurname(surname);
			endLog(methodName, surname, response);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@ApiOperation(value = "Find User By Name and Surname", notes = "Retrieve the User present on DB by Name and Surname")
	@GetMapping("name/{name}/surname/{surname}")
	public ResponseEntity<ResponseList> findUserByNameAndSurname(@PathVariable("name") String name,
			@PathVariable("surname") String surname) {
		final String methodName = String.format("findUserByNameAndSurname %s, %s", name, surname);
		try {
			startLog(methodName, name, surname);
			ResponseList response = userService.findByNameAndSurname(name, surname);
			endLog(methodName, name, surname, response);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@ApiOperation(value = "Save User", notes = "Added New User on DB")
	@PostMapping
	public ResponseEntity<SingleResponse> saveUser(@RequestBody UserModel userModel) {
		final String methodName = String.format("saveUser %s", userModel);
		try {
			startLog(methodName, userModel);
			SingleResponse response = userService.saveUser(userModel);
			endLog(methodName, userModel, response);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@ApiOperation(value = "Update User", notes = "Update User on DB")
	@PutMapping("/{id}")
	public ResponseEntity<SingleResponse> updateUser(@RequestBody final UserModel userModel,
			@PathVariable("id") final Integer id) {
		final String methodName = String.format("updateUser %s, %d", userModel, id);
		try {
			startLog(methodName, userModel);
			SingleResponse response = userService.updateUser(userModel, id);
			endLog(methodName, userModel, response);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (final Exception e) {
			errorLog(methodName, e);
			throw e;
		}
	}

	@ApiOperation(value = "Delete User", notes = "Delete User from DB")
	@DeleteMapping("/{id}")
	public ResponseEntity<SingleResponse> deleteUser(@PathVariable("id") Integer id) {
		final String methodName = String.format("deleteUser %d", id);
		try {
			startLog(methodName, id);
			SingleResponse response = userService.deleteUserById(id);
			endLog(methodName, id, response);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (final Exception e) {
			errorLog(methodName, e);
			throw e;
		}
	}

	@ApiOperation(value = "Update DB from CSV", notes = "Update DB from Data present on CSV")
	@PostMapping(value = "updateDataFromCSV", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseList> updateDataFromCSV(@RequestBody CsvDataInputBean csvDataInputBean)
			throws Exception {
		final String methodName = String.format("updateDataFromCSV %s", csvDataInputBean);
		try {
			startLog(methodName, csvDataInputBean);
			ResponseList response = userService.updateDataFromCSV(csvDataInputBean);
			endLog(methodName, csvDataInputBean, response);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (final Exception e) {
			errorLog(methodName, e);
			throw e;
		}
	}

}
