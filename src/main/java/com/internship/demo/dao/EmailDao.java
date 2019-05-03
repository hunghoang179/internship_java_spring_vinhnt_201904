package com.internship.demo.dao;

import com.internship.demo.model.MailDto;

public interface EmailDao {

  void sendEmail(MailDto email);
}
