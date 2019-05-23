package com.internship.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.internship.demo.dao.BookDao;
import com.internship.demo.domain.Book;
import com.internship.demo.form.BookSearchForm;
import com.internship.demo.model.mapper.repository.BookRepository;

@Service
public class BookService implements BookDao {

  @Autowired
  BookRepository bookRepository;

  @Override
  public List<Book> getListBook() {
    return bookRepository.getListBook();
  }

  @Override
  public Book findBookById(long id) {
    return bookRepository.findBookById(id);
  }

  @Override
  public void updateBook(Book book) {
    bookRepository.updateBook(book);
  }

  @Override
  public void insertBook(Book book) {
    bookRepository.insertBook(book);
  }

  @Override
  public void updateOutStockBook(Book book) {
    bookRepository.updateOutStockBook(book);
  }

  @Override
  public List<Book> getListBookPagination(BookSearchForm bookSearchForm, Long recordStart,
      Long pageSize) {
    return bookRepository.getListBookPagination(bookSearchForm, recordStart, pageSize);
  }

  @Override
  public Long countTotalRecord() {
    return bookRepository.countTotalRecord();
  }

  // @Override
  // public List<Book> getListBookSeach(String keyword) {
  // return bookRepository.getListBookSeach(keyword);
  // }

  @Override
  public List<Book> getListBookSeach(String title, String author, String year) {
    return bookRepository.getListBookSeach(title, author, year);
  }

  @Override
  public Long countTotalRecordBookSearch(BookSearchForm bookSearchForm) {
    return bookRepository.countTotalRecordBookSearch(bookSearchForm);
  }

}
