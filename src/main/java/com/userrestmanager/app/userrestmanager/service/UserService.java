/**
 * 
 */
package com.userrestmanager.app.userrestmanager.service;

import java.io.FileNotFoundException;

import com.userrestmanager.app.userrestmanager.inputBean.CsvDataInputBean;
import com.userrestmanager.app.userrestmanager.model.UserModel;
import com.userrestmanager.app.userrestmanager.response.ResponseList;
import com.userrestmanager.app.userrestmanager.response.SingleResponse;

/**
 * @author PastoreGu
 *
 */
public interface UserService {

	ResponseList findAllUser();

	SingleResponse findById(Integer id);

	SingleResponse saveUser(UserModel user);

	SingleResponse updateUser(UserModel user, Integer id);

	SingleResponse deleteUserById(Integer id);

	ResponseList findByName(String name);

	ResponseList findBySurname(String surname);

	ResponseList findByNameAndSurname(String name, String surname);

	ResponseList updateDataFromCSV(CsvDataInputBean csvDataInputBean)
			throws IllegalStateException, FileNotFoundException;

}