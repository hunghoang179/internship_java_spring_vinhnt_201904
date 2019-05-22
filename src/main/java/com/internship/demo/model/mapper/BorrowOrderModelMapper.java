package com.internship.demo.model.mapper;

import java.util.List;
import com.internship.demo.domain.BorrowOrder;
import com.internship.demo.model.BorrowBookDto;

public interface BorrowOrderModelMapper {

  void insertBorrowOrder(BorrowOrder borrowOrder);

  List<BorrowOrder> getListBorrowOrder();

  List<BorrowOrder> getListBorrowOrderByUser(Long id);

  BorrowBookDto findBorrowOrderBookById(Long id);

  void updateStatusBorrowOrder(BorrowOrder borrowOrder);

  BorrowOrder findBorrowOrderById(Long id);

  Long countBorrowOrderByUser(Long id);

  List<BorrowOrder> getListBorrowBookPagination(Long recordStart, Long pageSize);
  
  List<BorrowOrder> getListBorowBookSeach(String keyword);
}
