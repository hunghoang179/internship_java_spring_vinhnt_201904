package com.internship.demo.dao;

import java.util.List;
import com.internship.demo.domain.BorrowOrder;
import com.internship.demo.form.BorrowBookSearchForm;
import com.internship.demo.model.BorrowBookDto;

public interface BorrowOrderDao {

  void insertBorrowOrder(BorrowOrder borrowOrder);

  List<BorrowOrder> getListBorrowOrder();

  List<BorrowOrder> getListBorrowOrderByUser(Long id);

  BorrowBookDto findBorrowOrderBookById(Long id);

  void updateStatusBorrowOrder(BorrowOrder borrowOrder);

  BorrowOrder findBorrowOrderById(Long id);

  Long countBorrowOrderByUser(Long id);

  List<BorrowOrder> getListBorrowBookPagination(BorrowBookSearchForm borrowBookSearchForm,
      Long recordStart, Long pageSize);

  Long countListBorrowBookPagination(BorrowBookSearchForm borrowBookSearchForm);

  List<BorrowOrder> getListBorowBookSeach(String keyword);
}
