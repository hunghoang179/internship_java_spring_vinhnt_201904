package com.internship.demo.dao;

import java.util.List;
import com.internship.demo.domain.Category;

public interface CategoryDao {

  List<Category> getListCategory(Long recordStart, Long pageSize);

  List<Category> getListCategoryPagination(String name, Long recordStart, Long pageSize);

  Long countTotalCategory();

  void insertCategory(Category category);

  Category findCategoryById(long id);

  void updateCategory(Category category);

  void deleteCategory(long id);

  boolean checkCategoryValid(Long id);
}
