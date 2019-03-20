package edu.mum.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.mum.domain.Order;
import edu.mum.rest.service.OrderRestService;
import edu.mum.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRestService orderRestService;
	public List<Order> findAll(){
		return orderRestService.findAll();
	}
}
