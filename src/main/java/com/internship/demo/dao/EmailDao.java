package com.internship.demo.dao;

import org.springframework.mail.SimpleMailMessage;

public interface EmailDao {

	void sendEmail(SimpleMailMessage email);
}
