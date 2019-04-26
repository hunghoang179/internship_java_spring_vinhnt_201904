package com.internship.demo.custom.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.internship.demo.custom.annotation.Phone;
import com.internship.demo.utils.StringUtils;

public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {

  @Override
  public boolean isValid(String phoneField, ConstraintValidatorContext context) {
    if (phoneField == null) {
      return false;
    }
    return StringUtils.isPhone(phoneField);
  }

}
