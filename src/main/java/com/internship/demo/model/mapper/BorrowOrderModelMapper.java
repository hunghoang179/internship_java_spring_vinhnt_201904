package com.internship.demo.model.mapper;

import java.util.List;

import com.internship.demo.domain.BorrowOrder;

public interface BorrowOrderModelMapper {

	void insertBorrowOrder(BorrowOrder borrowOrder);

	List<BorrowOrder> getListBorrowOrder();

	List<BorrowOrder> getListBorrowOrderByUser(Long id);
}
