package com.internship.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.demo.dao.BorrowOrderDao;
import com.internship.demo.domain.BorrowOrder;
import com.internship.demo.model.mapper.repository.BorrowOrderRepository;

@Service
public class BorrowOrderService implements BorrowOrderDao{
	
	@Autowired
	BorrowOrderRepository borrowOrderRepository;

	@Override
	public void insertBorrowOrder(BorrowOrder borrowOrder) {
		borrowOrderRepository.insertBorrowOrder(borrowOrder);
	}

	@Override
	public List<BorrowOrder> getListBorrowOrder() {
		return borrowOrderRepository.getListBorrowOrder();
	}

	@Override
	public List<BorrowOrder> getListBorrowOrderByUser(Long id) {
		return borrowOrderRepository.getListBorrowOrderByUser(id);
	}

}
