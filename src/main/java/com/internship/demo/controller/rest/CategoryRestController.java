package com.internship.demo.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.internship.demo.dao.CategoryDao;
import com.internship.demo.domain.Category;

@RestController
@RequestMapping(path = "/api")
public class CategoryRestController {

  @Autowired
  CategoryDao categoryDao;

  @GetMapping(path = "/categories")
  public ResponseEntity<List<Category>> getAllCategory() {
    List<Category> list = categoryDao.getListCategory();
    if (list.isEmpty() && list.size() <= 0) {
      return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
  }

}
