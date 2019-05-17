package com.internship.demo.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.internship.demo.dao.CategoryDao;
import com.internship.demo.domain.Category;
import com.internship.demo.utils.MessageUltils;

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

  @GetMapping(path = "/category/{id}")
  public ResponseEntity<Category> findCategoryById(@PathVariable int id) {
    Category category = categoryDao.findCategoryById(id);
    if (category == null) {
      return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Category>(category, HttpStatus.OK);
  }

  @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Category> updateBook(@PathVariable("id") long id,
      @RequestBody Category category) {

    Category currentCategory = categoryDao.findCategoryById(id);
    if (currentCategory == null) {
      return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
    }
    currentCategory.setName(category.getName());
    categoryDao.updateCategory(currentCategory);
    return new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
  }

  @RequestMapping(value = "/category", method = RequestMethod.POST)
  public ResponseEntity<Void> createCategory(@RequestBody Category category) {
    categoryDao.insertCategory(category);
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

  @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
    boolean isCheck = categoryDao.checkCategoryValid(id);
    try {
      if (!isCheck) {
        categoryDao.deleteCategory(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
      } else {
        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
      }
    } catch (Exception e) {

    }
    return new ResponseEntity<Void>(HttpStatus.FOUND);
  }
}
