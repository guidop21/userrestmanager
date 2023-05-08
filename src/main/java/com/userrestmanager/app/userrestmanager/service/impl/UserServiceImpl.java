/**
 * 
 */
package com.userrestmanager.app.userrestmanager.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.opencsv.bean.CsvToBeanBuilder;
import com.userrestmanager.app.userrestmanager.common.log.LogConstant;
import com.userrestmanager.app.userrestmanager.common.service.AbstractService;
import com.userrestmanager.app.userrestmanager.entity.UserEntity;
import com.userrestmanager.app.userrestmanager.inputBean.CsvDataInputBean;
import com.userrestmanager.app.userrestmanager.model.CsvUserPojo;
import com.userrestmanager.app.userrestmanager.model.UserModel;
import com.userrestmanager.app.userrestmanager.repository.UserRepository;
import com.userrestmanager.app.userrestmanager.response.ResponseList;
import com.userrestmanager.app.userrestmanager.response.SingleResponse;
import com.userrestmanager.app.userrestmanager.service.UserService;
import com.userrestmanager.app.userrestmanager.util.ConvertersUtils;

/**
 * @author PastoreGu
 *
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseList findAllUser() {
		final String methodName = "findAllUser";
		try {
			startLog(methodName);
			ResponseList response = new ResponseList();
			List<UserEntity> userList = userRepository.findAll();
			List<UserModel> users = new ArrayList<>();
			if ((userList != null) && !userList.isEmpty()) {
				users = userList.parallelStream().map(ConvertersUtils::createUserModelFromUserEntity)
						.collect(Collectors.toList());
			}

			response.setTotalResult(users.size());
			response.setUsers(users);
			response.setMessage(LogConstant.LOG_SUCCESS);
			endLog(methodName, users, LogConstant.LOG_SUCCESS);
			return response;
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = true)
	public SingleResponse findById(Integer id) {
		final String methodName = String.format("findById %d", id);
		try {
			startLog(methodName, id);
			SingleResponse response = new SingleResponse();
			Optional<UserEntity> userEntity = userRepository.findById(id);
			UserModel user = new UserModel();
			if (userEntity.isPresent()) {
				user = ConvertersUtils.createUserModelFromUserEntity(userEntity.get());
				response.setUser(user);
				response.setMessage(LogConstant.LOG_SUCCESS);
				endLog(methodName, user, LogConstant.LOG_SUCCESS);
				return response;
			} else {
				response.setMessage(LogConstant.LOG_GENERIC_ERROR + " The user with id: " + id + " is not present");
				endLog(methodName, user, LogConstant.LOG_GENERIC_ERROR);
				return response;
			}
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@Override
	public ResponseList findByName(String name) {
		final String methodName = String.format("findByName %s", name);
		try {
			startLog(methodName, name);
			ResponseList response = new ResponseList();
			List<UserEntity> userEntityList = userRepository.findByNameIgnoreCase(name);
			List<UserModel> userModelList = new ArrayList<>();
			if ((userEntityList != null) && !userEntityList.isEmpty()) {
				userModelList = userEntityList.parallelStream().map(ConvertersUtils::createUserModelFromUserEntity)
						.collect(Collectors.toList());
				response.setTotalResult(userModelList.size());
				response.setUsers(userModelList);
				response.setMessage(LogConstant.LOG_SUCCESS);
				endLog(methodName, userModelList, LogConstant.LOG_SUCCESS);
				return response;
			} else {
				response.setMessage(LogConstant.LOG_GENERIC_ERROR + " Users with name: " + name + " are not present");
				response.setTotalResult(userModelList.size());
				endLog(methodName, userModelList, LogConstant.LOG_GENERIC_ERROR);
				return response;
			}

		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@Override
	public ResponseList findBySurname(String surname) {
		final String methodName = String.format("findBySurname %s", surname);
		try {
			startLog(methodName, surname);
			ResponseList response = new ResponseList();
			List<UserEntity> userEntityList = userRepository.findBySurnameIgnoreCase(surname);
			List<UserModel> userModelList = new ArrayList<>();
			if ((userEntityList != null) && !userEntityList.isEmpty()) {
				userModelList = userEntityList.parallelStream().map(ConvertersUtils::createUserModelFromUserEntity)
						.collect(Collectors.toList());
				response.setTotalResult(userModelList.size());
				response.setUsers(userModelList);
				response.setMessage(LogConstant.LOG_SUCCESS);
				endLog(methodName, userModelList, LogConstant.LOG_SUCCESS);
				return response;
			} else {
				response.setMessage(
						LogConstant.LOG_GENERIC_ERROR + " Users with surname: " + surname + " are not present");
				response.setTotalResult(userModelList.size());
				endLog(methodName, userModelList, LogConstant.LOG_GENERIC_ERROR);
				return response;
			}
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseList findByNameAndSurname(String name, String surname) {
		final String methodName = String.format("findByNameAndSurname %s, %s", name, surname);
		try {
			startLog(methodName, name, surname);
			ResponseList response = new ResponseList();
			List<UserEntity> userEntityList = userRepository.findByNameAndSurname(
					Arrays.stream(name.split(" "))
							.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
							.collect(Collectors.joining(" ")),
					Arrays.stream(surname.split(" "))
							.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
							.collect(Collectors.joining(" ")));
			List<UserModel> userModelList = new ArrayList<>();
			if ((userEntityList != null) && !userEntityList.isEmpty()) {
				userModelList = userEntityList.parallelStream().map(ConvertersUtils::createUserModelFromUserEntity)
						.collect(Collectors.toList());
				response.setTotalResult(userModelList.size());
				response.setUsers(userModelList);
				response.setMessage(LogConstant.LOG_SUCCESS);
				endLog(methodName, userModelList, LogConstant.LOG_SUCCESS);
				return response;
			} else {
				response.setMessage(LogConstant.LOG_GENERIC_ERROR + " The user with name: " + name + " and surname: "
						+ surname + " is not present");
				endLog(methodName, name, LogConstant.LOG_GENERIC_ERROR);
				return response;
			}
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@Override
	public SingleResponse saveUser(UserModel userModel) {
		final String methodName = String.format("saveUser %s", userModel);
		try {
			startLog(methodName, userModel);
			SingleResponse response = new SingleResponse();
			UserEntity userEntity = ConvertersUtils.createUserEntityFromUserModel(userModel);
			Optional<UserEntity> userEntityCheck = userRepository.findByCf(userModel.getCf().toUpperCase());
			if (userEntityCheck.isPresent()) {
				endLog(methodName, userEntityCheck.get().getCf(), LogConstant.LOG_GENERIC_ERROR);
				response.setMessage(LogConstant.LOG_GENERIC_ERROR + " User with cf: "
						+ userEntityCheck.get().getCf().toUpperCase() + " is already present");
				return response;
			}
			UserEntity userEntitySaved = userRepository.save(userEntity);
			endLog(methodName, userEntitySaved, LogConstant.LOG_SUCCESS);
			response.setMessage(
					LogConstant.LOG_SUCCESS + " The user with id: " + userEntitySaved.getId() + " has been added");
			response.setUser(userModel);
			return response;
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		}
	}

	@Override
	public SingleResponse updateUser(UserModel userModel, Integer id) {
		final String methodName = String.format("updateUser %s, %d", userModel, id);
		try {
			startLog(methodName, userModel);
			SingleResponse response = new SingleResponse();
			Optional<UserEntity> queryResultById = userRepository.findById(id);
			if (!queryResultById.isPresent()) {
				endLog(methodName, id, LogConstant.LOG_GENERIC_ERROR);
				response.setMessage(LogConstant.LOG_GENERIC_ERROR + " The user with id: " + id + " is not present");
				return response;
			}
			UserEntity userEntity = queryResultById.get();

			userEntity.setName(Arrays.stream(userModel.getName().split(" "))
					.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
					.collect(Collectors.joining(" ")));

			userEntity.setSurname(Arrays.stream(userModel.getSurname().split(" "))
					.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
					.collect(Collectors.joining(" ")));

			userEntity.setGender(StringUtils.capitalize(userModel.getGender()));

			userEntity.setAge(userModel.getAge());

			userEntity.setCf(userModel.getCf().toUpperCase());

			userEntity.setMail(userModel.getMail());

			userEntity.setAddress(Arrays.stream(userModel.getAddress().split(" "))
					.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
					.collect(Collectors.joining(" ")));

			userRepository.save(userEntity);
			endLog(methodName, userEntity, LogConstant.LOG_SUCCESS);
			response.setMessage(
					LogConstant.LOG_SUCCESS + " The user with id: " + userEntity.getId() + " has been updated");
			response.setUser(userModel);
			return response;
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;

		}
	}

	@Override
	public SingleResponse deleteUserById(Integer id) {
		final String methodName = String.format("deleteUserById %d", id);
		try {
			startLog(methodName, id);
			SingleResponse response = new SingleResponse();
			Optional<UserEntity> queryResult = userRepository.findById(id);
			if (!queryResult.isPresent()) {
				endLog(methodName, id, LogConstant.LOG_GENERIC_ERROR);
				response.setMessage(LogConstant.LOG_GENERIC_ERROR + " The user with id: " + id + " is not present");
				return response;
			}
			userRepository.deleteById(id);
			endLog(methodName, id, LogConstant.LOG_SUCCESS);
			response.setMessage(LogConstant.LOG_SUCCESS + " The user with id: " + id + " has been deleted");
			return response;
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

	@Override
	public ResponseList updateDataFromCSV(CsvDataInputBean csvDataInputBean)
			throws IllegalStateException, FileNotFoundException {
		final String methodName = String.format("updateDataFromCSV %s", csvDataInputBean);
		try {
			startLog(methodName, csvDataInputBean);
			ResponseList response = new ResponseList();
			log.info("Input path " + csvDataInputBean.getPath().toString());
			List<CsvUserPojo> csvUserFile = new CsvToBeanBuilder<CsvUserPojo>(
					new FileReader(csvDataInputBean.getPath())).withType(CsvUserPojo.class).withSeparator(';').build()
							.parse();
			List<UserEntity> listToSave = new LinkedList<>();
			List<UserModel> listOfModels = new LinkedList<>();
			for (CsvUserPojo csvUser : csvUserFile) {
				log.info("User present in CSV file: " + csvUser);
				UserEntity userEntity = new UserEntity();
				Optional<UserEntity> userByCF = userRepository.findByCf(csvUser.getCf().toUpperCase());

				if (!userByCF.isPresent()) {
					userEntity.setName(Arrays.stream(csvUser.getName().split(" "))
							.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
							.collect(Collectors.joining(" ")));

					userEntity.setSurname(Arrays.stream(csvUser.getSurname().split(" "))
							.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
							.collect(Collectors.joining(" ")));

					userEntity.setGender(StringUtils.capitalize(csvUser.getGender()));

					userEntity.setAge(csvUser.getAge());

					userEntity.setCf(csvUser.getCf().toUpperCase());

					userEntity.setAddress(Arrays.stream(csvUser.getAddress().split(" "))
							.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
							.collect(Collectors.joining(" ")));

					userEntity.setMail(csvUser.getMail());

					listToSave.add(userEntity);

					UserModel userModel = ConvertersUtils.createUserModelFromUserEntity(userEntity);
					listOfModels.add(userModel);

					log.info("User saved on DB: " + userEntity.getCf().toUpperCase());
				} else {
					log.info("User with CF: " + userByCF.get().getCf().toUpperCase() + " already present on DB");
				}
			}
			if (listToSave.isEmpty()) {
				endLog(methodName, listToSave, LogConstant.LOG_GENERIC_ERROR);
				response.setMessage(LogConstant.LOG_GENERIC_ERROR + " No new Users have been added");
				response.setTotalResult(listOfModels.size());
				response.setUsers(listOfModels);
				return response;
			} else {
				userRepository.saveAll(listToSave);
				endLog(methodName, listToSave, LogConstant.LOG_SUCCESS);
				response.setMessage(LogConstant.LOG_SUCCESS + " " + listToSave.size() + " Users have been added");
				response.setTotalResult(listOfModels.size());
				response.setUsers(listOfModels);
				return response;
			}
		} catch (final Exception e) {
			this.errorLog(methodName, e);
			throw e;
		}
	}

}
