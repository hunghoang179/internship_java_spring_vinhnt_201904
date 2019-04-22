package com.internship.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.demo.dao.CategoryDao;
import com.internship.demo.domain.Category;
import com.internship.demo.model.mapper.repository.CategoryRepository;

@Service
public class CategoryService implements CategoryDao {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> getListCategory() {
		return categoryRepository.getListCategory();
	}

	@Override
	public void insertCategory(Category category) {
		categoryRepository.insertCategory(category);
	}

	@Override
	public Category findCategoryById(long id) {
		return categoryRepository.findCategoryById(id);
	}

	@Override
	public void updateCategory(Category category) {
		categoryRepository.updateCategory(category);
	}

	@Override
	public void deleteCategory(long id) {
		categoryRepository.deleteCategory(id);
	}

	@Override
	public boolean checkCategoryValid(Long id) {
		boolean isCheck = false;
		Long result = categoryRepository.checkCategoryValid(id);
		if (result != 0) {
			isCheck = true;
			return isCheck;
		}
		return isCheck;
	}

}
