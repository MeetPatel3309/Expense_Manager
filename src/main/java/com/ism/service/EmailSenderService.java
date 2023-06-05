package com.ism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ism.repository.UserRepository;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	UserRepository userRepository;
	
	public void sendMail(String toEmail, String subject, String body)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("mrmeet.mungara@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
				
		System.out.println("Mail Sent Successfully..!");
	}
	
	
}
