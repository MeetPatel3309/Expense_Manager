package com.ism.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ism.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{

	UserEntity findByEmail(String email);

	UserEntity findByEmailAndPassword(String email, String password);
	
	List<UserEntity> findAll();
	
	Optional<UserEntity> findByToken(String token);

	UserEntity findByOtp(Integer otp);
}
