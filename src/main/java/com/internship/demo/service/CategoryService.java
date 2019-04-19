package com.internship.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.demo.dao.CategoryDao;
import com.internship.demo.domain.Category;
import com.internship.demo.model.mapper.repository.CategoryRepository;

@Service
public class CategoryService implements CategoryDao{
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> getListCategory() {
		return categoryRepository.getListCategory();
	}

}
