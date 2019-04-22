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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.internship.demo.dao.BookDao;
import com.internship.demo.dao.BorrowOrderDao;
import com.internship.demo.dao.CategoryDao;
import com.internship.demo.domain.Book;
import com.internship.demo.domain.BorrowOrder;
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

	@Autowired
	BorrowOrderDao borrowOrderDao;

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

	@GetMapping(path = "/the-loai-sach")
	public String redirectCategoryPage(Model model) {
		model.addAttribute("listCategory", categoryDao.getListCategory());
		return "/category/category-list";
	}

	@PostMapping(path = "/admin/them-the-loai-sach")
	public String handleInsertCategory(@SessionAttribute UserModel user, @ModelAttribute Category category,
			Model model) {
		if (category.getName().equals("") || category.getName() == null) {
			model.addAttribute("error", "Lỗi tên thể loại không được để trống");
			return "/category/category-list";
		}
		try {
			category.setCreateTime(StringUtils.getTimestampNow());
			category.setCreateUser(user.getUsername());
			category.setUpdateTime(StringUtils.getTimestampNow());
			category.setUpdateUser(user.getUsername());
			categoryDao.insertCategory(category);
		} catch (Exception e) {
			return "redirect:/403";
		}
		return "redirect:/book/the-loai-sach";
	}

	@GetMapping(path = "/admin/{id}/cap-nhat-the-loai-sach")
	public String redirectUpdateCategoryPage(@PathVariable long id, Model model) {
		Category category = categoryDao.findCategoryById(id);
		model.addAttribute("category", category);
		return "/category/update-category";
	}

	@PostMapping(path = "/admin/cap-nhat-the-loai-sach")
	public String handleUpdateCategory(@SessionAttribute UserModel user, @ModelAttribute Category category,
			Model model) {
		try {
			category.setUpdateTime(StringUtils.getTimestampNow());
			category.setUpdateUser(user.getUsername());
			categoryDao.updateCategory(category);
		} catch (Exception e) {
			return "redirect:/403";
		}
		return "redirect:/book/the-loai-sach";
	}

	@PostMapping(path = "/admin/delete")
	public String handleDeleteCategory(@RequestParam long id, Model model) {
		boolean isCheck = categoryDao.checkCategoryValid(id);
		try {
			if (!isCheck) {
				categoryDao.deleteCategory(id);
			} else {
				model.addAttribute("error", "Không thể xóa thể loại sách đang có sách");
				model.addAttribute("listCategory", categoryDao.getListCategory());
				return "/category/category-list";
			}
		} catch (Exception e) {
			return "redirect:/403";
		}
		return "redirect:/book/the-loai-sach";
	}

	@PostMapping(path = "/borrow")
	public String handleBorrowOrderBook(@SessionAttribute UserModel user, @ModelAttribute BorrowOrder borrowOrder,
			Model model) {
		Book book = bookDao.findBookById(borrowOrder.getIdBook());
		if (book.getStock() <= book.getOutStock()) {
			model.addAttribute("error", "Số lượng sách không đủ để mượn");
			List<Book> listBook = bookDao.getListBook();
			List<Category> listCategory = categoryDao.getListCategory();
			model.addAttribute("listBook", listBook);
			model.addAttribute("listCategory", listCategory);
			return "home";
		}
		try {
			borrowOrder.setIdUser((long) user.getId());
			borrowOrder.setCreateTime(StringUtils.getTimestampNow());
			borrowOrder.setCreateUser(user.getUsername());
			borrowOrder.setUpdateTime(StringUtils.getTimestampNow());
			borrowOrder.setUpdateUser(user.getUsername());
			borrowOrder.setStatus(0);
			if (user.getRole() == 1) {
				borrowOrder.setStatus(1);
				book.setOutStock(book.getOutStock() + 1);
				bookDao.updateOutStockBook(book);
			}
			borrowOrderDao.insertBorrowOrder(borrowOrder);
		} catch (Exception e) {
			return "redirect:/403";
		}
		return "redirect:/home";
	}

	@GetMapping(path = "/danh-sach-muon-sach")
	public String redirectBorrowOrderPage(@SessionAttribute UserModel user, Model model) {
		List<BorrowOrder> listBorrowOrder = null;
		if (user.getRole() == 1) {
			listBorrowOrder = borrowOrderDao.getListBorrowOrder();
		} else {
			listBorrowOrder = borrowOrderDao.getListBorrowOrderByUser((long) user.getId());
		}
		model.addAttribute("listBorrowOrder", listBorrowOrder);
		return "/borrow-order/borrow-order";
	}

}
