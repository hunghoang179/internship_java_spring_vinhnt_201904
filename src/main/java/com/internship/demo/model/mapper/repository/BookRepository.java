package com.internship.demo.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.internship.demo.domain.Book;
import com.internship.demo.model.mapper.BookModelMapper;
import com.internship.demo.mybaties.activemodel.Repository;

@org.springframework.stereotype.Repository
public class BookRepository extends Repository<BookModelMapper> {

	@Override
	protected BookModelMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BookModelMapper.class);
	}

	public List<Book> getListBook() {
		return execute(mapper -> {
			return mapper.getListBook();
		});
	}

	public Book findBookById(long id) {
		return execute(mapper -> {
			return mapper.findBookById(id);
		});
	}

	public void updateBook(Book book) {
		execute(mapper -> {
			mapper.updateBook(book);
		});
	}

	public void insertBook(Book book) {
		execute(mapper -> {
			mapper.insertBook(book);
		});
	}

	public void updateOutStockBook(Book book) {
		execute(mapper -> {
			mapper.updateOutStockBook(book);
		});
	}
}
