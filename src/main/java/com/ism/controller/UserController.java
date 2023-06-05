package com.ism.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ism.bean.ResponseBean;
import com.ism.entity.UserEntity;
import com.ism.repository.UserRepository;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
	public ResponseEntity<ResponseBean<List<UserEntity>>> getAllUser()
	{
		List<UserEntity> users = userRepository.findAll();
		
		ResponseBean<List<UserEntity>> resp = new ResponseBean<>();
		
		if(users != null)
		{
		resp.setData(users);
		resp.setMsg("Users Fetched Successful..!");
		
		return ResponseEntity.ok(resp);
		}
		else
		{
			resp.setData(users);
			resp.setMsg("Users not fetched..");
			
			return ResponseEntity.unprocessableEntity().body(resp);
		}
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<ResponseBean<UserEntity>> deleteUser(@PathVariable("userId") Integer userId)
	{
		UserEntity userEntity = userRepository.findById(userId).orElse(null);

		ResponseBean<UserEntity> resp = new ResponseBean<>();

		if(userEntity != null)
		{
			userRepository.deleteById(userId);
			resp.setData(userEntity);
			resp.setMsg("User Deleted Successful..!");
			
			return ResponseEntity.ok(resp);
		}
		else
		{
			resp.setData(userEntity);
			resp.setMsg("User not deleted..");
			return ResponseEntity.unprocessableEntity().body(resp);
		}
		
	}
}
