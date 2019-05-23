package com.internship.demo.dao;

import java.util.List;
import com.internship.demo.domain.Book;
import com.internship.demo.form.BookSearchForm;

public interface BookDao {

  List<Book> getListBook();

  Book findBookById(long id);

  void updateBook(Book book);

  void insertBook(Book book);

  void updateOutStockBook(Book book);

  List<Book> getListBookPagination(BookSearchForm bookSearchForm, Long recordStart, Long pageSize);

  Long countTotalRecord();

  Long countTotalRecordBookSearch(BookSearchForm bookSearchForm);

  List<Book> getListBookSeach(String title, String author, String year);
}
