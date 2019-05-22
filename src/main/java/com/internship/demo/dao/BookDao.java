package com.internship.demo.dao;

import java.util.List;
import com.internship.demo.domain.Book;

public interface BookDao {

  List<Book> getListBook();

  Book findBookById(long id);

  void updateBook(Book book);

  void insertBook(Book book);

  void updateOutStockBook(Book book);

  List<Book> getListBookPagination(Long recordStart, Long pageSize);

  Long countTotalRecord();

  List<Book> getListBookSeach(String title, String author, String year);
}
