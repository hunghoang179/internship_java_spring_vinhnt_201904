package com.internship.demo.dao;

import java.util.List;

import com.internship.demo.domain.BorrowOrder;

public interface BorrowOrderDao {
	
	void insertBorrowOrder(BorrowOrder borrowOrder);
	
	List<BorrowOrder> getListBorrowOrder();
	
	List<BorrowOrder> getListBorrowOrderByUser(Long id);
}
