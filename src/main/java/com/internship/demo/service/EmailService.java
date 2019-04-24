package com.internship.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.internship.demo.dao.EmailDao;

@Service
public class EmailService implements EmailDao {

	@Autowired
	private JavaMailSender mailSender;

	@Async
	@Override
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}

}
