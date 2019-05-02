package com.internship.demo.utils;

import org.springframework.context.MessageSource;


public class MessageUltils {

  public static final String ERROR_USER = getMessage("users.exits");
  public static final String ERROR_RE_PASSWORD = getMessage("re.password.not.valid");

  public static String getMessage(String idmsg) {
    try {
      String msg = BeanUtils.getBean(MessageSource.class).getMessage(idmsg, null, null);
      return msg;
    } catch (Exception e) {
      return idmsg;
    }
  }

}
