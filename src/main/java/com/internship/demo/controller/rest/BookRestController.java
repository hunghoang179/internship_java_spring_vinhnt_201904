package com.internship.demo.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.internship.demo.dao.BookDao;
import com.internship.demo.domain.Book;

@RestController
@RequestMapping(path = "/api")
public class BookRestController {

  @Autowired
  BookDao bookDao;

  @GetMapping(path = "/book")
  public ResponseEntity<List<Book>> getAllBook() {
    List<Book> list = bookDao.getListBook();
    if (list.isEmpty() && list.size() <= 0) {
      return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
  }

  @GetMapping(path = "/book/{id}")
  public ResponseEntity<Book> findBookById(@PathVariable Long id) {
    Book book = bookDao.findBookById(id);
    if (book == null) {
      return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Book>(book, HttpStatus.OK);
  }

  @RequestMapping(value = "/book",method = RequestMethod.POST)
  public ResponseEntity<Void> createBook(@RequestBody Book book) {
    System.out.println(book.getAuthor());
    bookDao.insertBook(book);
    HttpHeaders headers = new HttpHeaders();
   // headers.setLocation(builder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }
}
