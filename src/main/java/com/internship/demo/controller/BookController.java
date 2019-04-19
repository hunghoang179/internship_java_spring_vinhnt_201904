package com.internship.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.internship.demo.dao.BookDao;
import com.internship.demo.dao.CategoryDao;
import com.internship.demo.domain.Book;
import com.internship.demo.domain.Category;
import com.internship.demo.model.UserModel;
import com.internship.demo.utils.StringUtils;

@Controller
@RequestMapping(path = "/book")
@SessionAttributes("user")
public class BookController {
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@GetMapping(path = "/xem-noi-dung/{id}")
	public String redirectDetailBook(@PathVariable long id, Model model) {
		Book book = bookDao.findBookById(id);
		model.addAttribute("book", book);
		return "/book/detail-book";
	}
	
	@GetMapping(path = "/admin/cap-nhat-sach/{id}")
	public String redirectUpdateBookPage(@PathVariable long id, Model model) {
		Book book = bookDao.findBookById(id);
		List<Category> listCategory = categoryDao.getListCategory();
		model.addAttribute("book", book);
		model.addAttribute("listCategory", listCategory);
		return "/book/update-book";
	}
	
	@PostMapping(path = "/admin/cap-nhat-sach")
	public String redirectUpdateBookPage(@SessionAttribute UserModel user, @ModelAttribute Book book, Model model) {
		try {
			book.setUpdateTime(StringUtils.getTimestampNow());
			book.setUpdateUser(user.getUsername());
			bookDao.updateBook(book);
		} catch (Exception e) {
			return "redirect:/403";
		}
		return "redirect:/home";
	}
	
	@PostMapping(path = "/admin/them-moi-sach")
	public String handleInsertBook(@SessionAttribute UserModel user, @ModelAttribute Book book, Model model) {
		try {
			book.setCreateTime(StringUtils.getTimestampNow());
			book.setCreateUser(user.getUsername());
			book.setUpdateTime(StringUtils.getTimestampNow());
			book.setUpdateUser(user.getUsername());
			bookDao.insertBook(book);
		} catch (Exception e) {
			return "redirect:/403";
		}
		return "redirect:/home";
	}

}
