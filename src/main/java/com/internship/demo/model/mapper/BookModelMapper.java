package com.internship.demo.model.mapper;

import java.util.List;

import com.internship.demo.domain.Book;

public interface BookModelMapper {

	List<Book> getListBook();

	Book findBookById(long id);

	void updateBook(Book book);

	void insertBook(Book book);

	void updateOutStockBook(Book book);
}
