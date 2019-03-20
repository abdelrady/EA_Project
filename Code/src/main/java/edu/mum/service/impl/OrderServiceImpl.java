package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Order;
import edu.mum.rest.service.OrderRestService;
import edu.mum.service.OrderService;

@Service
@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED)
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRestService orderRestService;
	
	public List<Order> findAll(){
		return orderRestService.findAll();
	}
}
