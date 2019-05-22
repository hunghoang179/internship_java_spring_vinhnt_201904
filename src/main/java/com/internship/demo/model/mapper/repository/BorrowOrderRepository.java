package com.internship.demo.model.mapper.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.internship.demo.domain.BorrowOrder;
import com.internship.demo.model.BorrowBookDto;
import com.internship.demo.model.mapper.BorrowOrderModelMapper;
import com.internship.demo.mybaties.activemodel.Repository;

@org.springframework.stereotype.Repository
public class BorrowOrderRepository extends Repository<BorrowOrderModelMapper> {

  @Override
  protected BorrowOrderModelMapper repositoryMapper(SqlSession session) {
    return session.getMapper(BorrowOrderModelMapper.class);
  }

  public void insertBorrowOrder(BorrowOrder borrowOrder) {
    execute(mapper -> {
      mapper.insertBorrowOrder(borrowOrder);
    });
  }

  public List<BorrowOrder> getListBorrowOrder() {
    return execute(mapper -> {
      return mapper.getListBorrowOrder();
    });
  }

  public List<BorrowOrder> getListBorrowOrderByUser(Long id) {
    return execute(mapper -> {
      return mapper.getListBorrowOrderByUser(id);
    });
  }

  public BorrowBookDto findBorrowOrderBookById(Long id) {
    return execute(mapper -> {
      return mapper.findBorrowOrderBookById(id);
    });
  }

  public void updateStatusBorrowOrder(BorrowOrder borrowOrder) {
    execute(mapper -> {
      mapper.updateStatusBorrowOrder(borrowOrder);
    });
  }

  public BorrowOrder findBorrowOrderById(Long id) {
    return execute(mapper -> {
      return mapper.findBorrowOrderById(id);
    });
  }

  public Long countBorrowOrderByUser(Long id) {
    return execute(mapper -> {
      return mapper.countBorrowOrderByUser(id);
    });
  }

  public List<BorrowOrder> getListBorrowBookPagination(Long recordStart, Long pageSize) {
    return execute(mapper -> {
      return mapper.getListBorrowBookPagination(recordStart, pageSize);
    });
  }

  public List<BorrowOrder> getListBorowBookSeach(String keyword) {
    return execute(mapper -> {
      return mapper.getListBorowBookSeach(keyword);
    });
  }

}
