/**
 * 
 */
package com.userrestmanager.app.userrestmanager.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.userrestmanager.app.userrestmanager.entity.UserEntity;
import com.userrestmanager.app.userrestmanager.model.UserModel;

/**
 * @author PastoreGu
 *
 */
public final class ConvertersUtils {

	private ConvertersUtils() {
		throw new IllegalStateException("Converter Utility class");
	}

	public static String convertDate(final String date) {
		return date.replaceAll("/", "_").replaceAll(" ", "_");
	}

	public static UserModel createUserModelFromUserEntity(final UserEntity userEntity) {

		final UserModel userModel = new UserModel();

		userModel.setName(Arrays.stream(userEntity.getName().split(" "))
				.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
				.collect(Collectors.joining(" ")));

		userModel.setSurname(Arrays.stream(userEntity.getSurname().split(" "))
				.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
				.collect(Collectors.joining(" ")));

		userModel.setGender(StringUtils.capitalize(userEntity.getGender()));

		userModel.setAge(userEntity.getAge());

		userModel.setCf(userEntity.getCf().toUpperCase());

		userModel.setMail(userEntity.getMail());

		userModel.setAddress(Arrays.stream(userEntity.getAddress().split(" "))
				.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
				.collect(Collectors.joining(" ")));

		return userModel;
	}

	public static UserEntity createUserEntityFromUserModel(final UserModel userModel) {

		final UserEntity userEntity = new UserEntity();

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

		return userEntity;
	}
}
