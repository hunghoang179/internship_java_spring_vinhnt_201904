package com.internship.demo.model.mapper.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.internship.demo.domain.Book;
import com.internship.demo.form.BookSearchForm;
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

  public List<Book> getListBookPagination(BookSearchForm bookSearchForm, Long recordStart,
      Long pageSize) {
    return execute(mapper -> {
      return mapper.getListBookPagination(bookSearchForm.getTitle(), bookSearchForm.getAuthor(),
          bookSearchForm.getYear(), recordStart, pageSize);
    });
  }

  public Long countTotalRecord() {
    return execute(mapper -> {
      return mapper.countTotalRecord();
    });
  }

  public Long countTotalRecordBookSearch(BookSearchForm bookSearchForm) {
    return execute(mapper -> {
      return mapper.countTotalRecordBookSearch(bookSearchForm.getTitle(),
          bookSearchForm.getAuthor(), bookSearchForm.getYear());
    });
  }

  // public List<Book> getListBookSeach(String keyword) {
  // return execute(mapper -> {
  // return mapper.getListBookSeach(keyword);
  // });
  // }

  public List<Book> getListBookSeach(String title, String author, String year) {
    return execute(mapper -> {
      return mapper.getListBookSeach(title, author, year);
    });
  }

}
