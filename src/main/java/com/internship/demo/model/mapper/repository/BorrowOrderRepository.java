package com.internship.demo.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.internship.demo.domain.BorrowOrder;
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

}
