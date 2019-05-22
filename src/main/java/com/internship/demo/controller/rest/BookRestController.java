package com.internship.demo.controller.rest;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.internship.demo.dao.BookDao;
import com.internship.demo.dao.BorrowOrderDao;
import com.internship.demo.domain.Book;
import com.internship.demo.domain.BorrowOrder;
import com.internship.demo.model.BorrowBookDto;
import com.internship.demo.model.UserModel;
import com.internship.demo.utils.StringUtils;

// @CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping(path = "/api")
public class BookRestController {

  private static final Long PAGE_SIZE = 15l;

  @Autowired
  BookDao bookDao;

  @Autowired
  BorrowOrderDao borrowOrderDao;

  @GetMapping(path = "/book")
  public ResponseEntity<List<Book>> getAllBook() {
    List<Book> list = bookDao.getListBook();
    if (list.isEmpty() && list.size() <= 0) {
      return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
  }

  @GetMapping(path = "/book/pagination/{pageNumber}")
  public ResponseEntity<List<Book>> getAllBookPagination(@PathVariable Long pageNumber) {
    Long itemStart = pageNumber * PAGE_SIZE - PAGE_SIZE;
    List<Book> list = bookDao.getListBookPagination(itemStart, PAGE_SIZE);
    if (list.isEmpty() && list.size() <= 0) {
      return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
  }

  @GetMapping(path = "/book/seach/{keyword}")
  public ResponseEntity<List<Book>> getAllBookSeach(@PathVariable String keyword) {
    // String keyword = '%' + key + '%';
    List<Book> list = bookDao.getListBookSeach(keyword);
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

  @RequestMapping(value = "/book", method = RequestMethod.POST)
  public ResponseEntity<Void> createBook(@RequestBody Book book) {
    bookDao.insertBook(book);
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

  @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {

    Book currentBook = bookDao.findBookById(id);

    if (currentBook == null) {
      return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }

    currentBook.setTitle(book.getTitle());
    currentBook.setStock(book.getStock());
    currentBook.setContentShort(book.getContentShort());
    currentBook.setAuthor(book.getAuthor());
    currentBook.setYear(book.getYear());
    currentBook.setIdCategory(book.getIdCategory());

    bookDao.updateBook(currentBook);
    return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
  }

  @RequestMapping(value = "/book/borrow", method = RequestMethod.POST)
  public ResponseEntity<Void> borrowBook(@RequestBody BorrowOrder borrowOrder,
      HttpServletRequest request) {
    UserModel user = (UserModel) request.getSession().getAttribute("user");
    if (borrowOrder.getBorrowDate() != null && borrowOrder.getReturnDate() != null) {
      if (StringUtils.daysBetween2Dates(borrowOrder.getBorrowDate(),
          borrowOrder.getReturnDate()) > 30) {
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
      }

      if (borrowOrder.getBorrowDate().after(borrowOrder.getReturnDate())
          || borrowOrder.getBorrowDate().equals(borrowOrder.getReturnDate())) {
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
      }
    } else {
      return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
    Long totalBookBorrow = borrowOrderDao.countBorrowOrderByUser((long) user.getId());
    if (user.getRole() == 0 && totalBookBorrow >= 5) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    } else if (user.getRole() == 1 && totalBookBorrow >= 10) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    } else if (user.getRole() == 1 && totalBookBorrow >= 15) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
    Book book = bookDao.findBookById(borrowOrder.getIdBook());
    if (book.getStock() <= book.getOutStock()) {
      return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
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
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<Void>(HttpStatus.CREATED);
  }

  @GetMapping(path = "/book/borrow/list/pagination/{pageNumber}")
  public ResponseEntity<List<BorrowOrder>> redirectBorrowOrderPagePagination(
      @PathVariable Long pageNumber, HttpServletRequest request) {
    Long itemStart = pageNumber * PAGE_SIZE - PAGE_SIZE;
    List<BorrowOrder> listBorrowOrder = null;
    UserModel user = (UserModel) request.getSession().getAttribute("user");
    if (user.getRole() == 1) {
      listBorrowOrder = borrowOrderDao.getListBorrowBookPagination(itemStart, PAGE_SIZE);
    } else {
      listBorrowOrder = borrowOrderDao.getListBorrowOrderByUser((long) user.getId());
    }
    return new ResponseEntity<List<BorrowOrder>>(listBorrowOrder, HttpStatus.OK);
  }

  @GetMapping(path = "/book/borrow/list/pagination/totalPage")
  public Long getTotalPage(HttpServletRequest request) {
    Long totalPage = 0l;
    List<BorrowOrder> listBorrowOrder = borrowOrderDao.getListBorrowOrder();
    long totalRecord = listBorrowOrder.size();
    totalPage = (long) Math.ceil(totalRecord / (PAGE_SIZE * 1.0));
    return totalPage;
  }

  @GetMapping(path = "/book/borrow/list")
  public ResponseEntity<List<BorrowOrder>> redirectBorrowOrderPage(HttpServletRequest request) {
    List<BorrowOrder> listBorrowOrder = null;
    UserModel user = (UserModel) request.getSession().getAttribute("user");
    if (user.getRole() == 1) {
      listBorrowOrder = borrowOrderDao.getListBorrowOrder();
    } else {
      listBorrowOrder = borrowOrderDao.getListBorrowOrderByUser((long) user.getId());
    }
    return new ResponseEntity<List<BorrowOrder>>(listBorrowOrder, HttpStatus.OK);
  }

  @GetMapping(path = "/book/approve/{id}")
  public ResponseEntity<BorrowBookDto> redirectApproveBorrowOrderPage(@PathVariable Long id) {
    BorrowBookDto borrowOrder = borrowOrderDao.findBorrowOrderBookById(id);
    if (borrowOrder == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<BorrowBookDto>(borrowOrder, HttpStatus.OK);
  }

  @PostMapping(path = "/book/approve")
  public ResponseEntity<Void> approveBorrowOrder(@RequestBody long id, HttpServletRequest request)
      throws ParseException {
    UserModel user = (UserModel) request.getSession().getAttribute("user");
    BorrowOrder borrowOrder = borrowOrderDao.findBorrowOrderById(id);
    Book book = bookDao.findBookById(borrowOrder.getIdBook());
    if (book.getStock() <= book.getOutStock()) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    borrowOrder.setStatus(1);
    borrowOrder.setUpdateUser(user.getUsername());
    borrowOrder.setUpdateTime(StringUtils.getTimestampNow());
    borrowOrderDao.updateStatusBorrowOrder(borrowOrder);
    book.setOutStock(book.getOutStock() + 1);
    bookDao.updateOutStockBook(book);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(path = "/book/cancle")
  public ResponseEntity<Void> cancleBorrowOrder(@RequestBody long id, HttpServletRequest request)
      throws ParseException {
    UserModel user = (UserModel) request.getSession().getAttribute("user");
    BorrowOrder borrowOrder = borrowOrderDao.findBorrowOrderById(id);
    borrowOrder.setStatus(2);
    borrowOrder.setUpdateUser(user.getUsername());
    borrowOrder.setUpdateTime(StringUtils.getTimestampNow());
    borrowOrderDao.updateStatusBorrowOrder(borrowOrder);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(path = "/book/return")
  public ResponseEntity<Void> returnBorrowOrder(@RequestBody long id, HttpServletRequest request)
      throws ParseException {
    UserModel user = (UserModel) request.getSession().getAttribute("user");
    BorrowOrder borrowOrder = borrowOrderDao.findBorrowOrderById(id);
    borrowOrder.setStatus(3);
    borrowOrder.setUpdateUser(user.getUsername());
    borrowOrder.setUpdateTime(StringUtils.getTimestampNow());
    borrowOrderDao.updateStatusBorrowOrder(borrowOrder);
    Book book = bookDao.findBookById(borrowOrder.getIdBook());
    book.setOutStock(book.getOutStock() - 1);
    bookDao.updateOutStockBook(book);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(path = "/book/missing")
  public ResponseEntity<Void> missingBorrowOrder(@RequestBody long id, HttpServletRequest request)
      throws ParseException {
    UserModel user = (UserModel) request.getSession().getAttribute("user");
    BorrowOrder borrowOrder = borrowOrderDao.findBorrowOrderById(id);
    borrowOrder.setStatus(4);
    borrowOrder.setUpdateUser(user.getUsername());
    borrowOrder.setUpdateTime(StringUtils.getTimestampNow());
    borrowOrderDao.updateStatusBorrowOrder(borrowOrder);
    Book book = bookDao.findBookById(borrowOrder.getIdBook());
    book.setStock(book.getStock() - 1);
    book.setOutStock(book.getOutStock() - 1);
    bookDao.updateOutStockBook(book);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(path = "/book/borrow/seach/{keyword}")
  public ResponseEntity<List<BorrowOrder>> getAllBorrowBookSeach(@PathVariable String keyword) {
    // String keyword = '%' + key + '%';
    List<BorrowOrder> list = borrowOrderDao.getListBorowBookSeach(keyword);
    if (list.isEmpty() && list.size() <= 0) {
      return new ResponseEntity<List<BorrowOrder>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<BorrowOrder>>(list, HttpStatus.OK);
  }
}
