package com.internship.demo.model.mapper;

import java.util.List;
import com.internship.demo.domain.Category;

public interface CategoryModelMapper {

  List<Category> getListCategory();

  void insertCategory(Category category);

  Category findCategoryById(long id);

  void updateCategory(Category category);

  void deleteCategory(long id);

  Long checkCategoryValid(Long id);
}
