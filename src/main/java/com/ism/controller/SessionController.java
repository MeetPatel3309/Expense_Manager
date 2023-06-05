package com.ism.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ism.bean.ResponseBean;
import com.ism.entity.UserEntity;
import com.ism.repository.UserRepository;
import com.ism.service.EmailSenderService;
import com.ism.service.TokenGenerator;

@CrossOrigin
@RestController
@RequestMapping("/public")
public class SessionController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenGenerator tokenGenerator;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	@PostMapping("/signup")
	public ResponseEntity<ResponseBean<UserEntity>> addUser(@RequestBody UserEntity userEntity)
	{
		UserEntity existingUser = userRepository.findByEmail(userEntity.getEmail());
		
		ResponseBean<UserEntity> resp = new ResponseBean<>();
		
		if(existingUser == null && userEntity.getEmail() != null)
		{
		

		userRepository.save(userEntity);
		
		resp.setData(userEntity);
		resp.setMsg("User Registered Successful..!");
		
		return ResponseEntity.ok(resp);
		}
		else
		{
			resp.setData(userEntity);
			resp.setMsg("Please Enter Unique Email..!");
			return new ResponseEntity<ResponseBean<UserEntity>>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<ResponseBean<UserEntity>> loginUser(@RequestBody UserEntity userEntity)
	{
		
		System.out.println("performing login.....");
		UserEntity userExist = userRepository.findByEmailAndPassword(userEntity.getEmail(), userEntity.getPassword() );
		
		ResponseBean<UserEntity> resp = new ResponseBean<>();
		
		if(userExist != null)
		{
			String token = tokenGenerator.generateToken(16);
			
			userExist.setToken(token);
			userRepository.save(userExist);
			
			resp.setData(userExist);
			resp.setMsg("Login Success..!!");
			
			return ResponseEntity.ok(resp);
		}
		else
		{
			resp.setData(userEntity);
			resp.setMsg("Please enter valid credentials..!");
			
			return new ResponseEntity<ResponseBean<UserEntity>>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	
	@GetMapping("/forgetPassword")
	public ResponseEntity<ResponseBean<UserEntity>> forgetPassword(@RequestParam("email") String email) 
	{
		UserEntity userExist = userRepository.findByEmail(email);
	
		ResponseBean<UserEntity> resp = new ResponseBean<>();
		
		if (userExist == null) 
		{
			resp.setData(userExist);
			resp.setMsg("Otp will send to your mailId, if email is exists..!!!");
			
			return ResponseEntity.unprocessableEntity().body(resp);
			
		} else 
		{
			System.out.println("Email : "+email);
			Integer randomOtp = (int) (Math.random() * 1500000);

			
			String subject = "OTP from Expense-Manager-Project By Meet Mungara";
			String toEmail = email;
			String body  = "<h1> OTP = "+randomOtp+"</h1>";
			
			emailSenderService.sendMail(toEmail, subject, body);
			
			System.out.println("OTP sent on "+email+" ");
			
			System.out.println("-----------------"+randomOtp+"-----------------");
			
			userExist.setOtp(randomOtp);
			
			userRepository.save(userExist);
			
			resp.setData(userExist);
			resp.setMsg("Otp will send to your mailId, if email is exists (OTP set)!!");
			
			return ResponseEntity.ok(resp);
		}
	}

	@GetMapping("/resetPassword")
	public ResponseEntity<ResponseBean<UserEntity>> resetPassword(@RequestParam("otp") Integer otp, 
																  @RequestParam("password") String password, 
																  @RequestParam("confirmPassword") String confirmPassword) 
	{
		UserEntity userExist = userRepository.findByOtp(otp);
	
		ResponseBean<UserEntity> resp = new ResponseBean<>();
				
		if ((password.equals(confirmPassword)) && !(userExist.getPassword().equals(password))) 
		{
		
			userExist.setPassword(password);
			
			userRepository.save(userExist);
			
			resp.setData(userExist);
			resp.setMsg("Password Reset Sucessfully..!!");
			
			return ResponseEntity.ok(resp);
			
		} 
		else 
		{
			resp.setData(userExist);
			resp.setMsg("Please Enter Same Password And ConfirmPassword !!");

			return ResponseEntity.unprocessableEntity().body(resp);
		}
	}
}
