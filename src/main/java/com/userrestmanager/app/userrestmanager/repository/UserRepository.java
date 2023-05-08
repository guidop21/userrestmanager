/**
 * 
 */
package com.userrestmanager.app.userrestmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.userrestmanager.app.userrestmanager.entity.UserEntity;

/**
 * @author PastoreGu
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	// @Query(value = "SELECT user FROM UserEntity user WHERE user.name = ?1")
	List<UserEntity> findByNameIgnoreCase(String name);

	// @Query(value = "SELECT user FROM UserEntity user WHERE user.surname = ?1")
	List<UserEntity> findBySurnameIgnoreCase(String surname);

	@Query(value = "SELECT user FROM UserEntity user WHERE user.name = ?1 and user.surname = ?2")
	List<UserEntity> findByNameAndSurname(String name, String surname);

	@Query(value = "SELECT user FROM UserEntity user WHERE user.cf = ?1")
	Optional<UserEntity> findByCf(String cf);
}
