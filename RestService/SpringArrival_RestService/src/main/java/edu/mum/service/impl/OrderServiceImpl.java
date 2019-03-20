package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.dao.OrderDao;
import edu.mum.domain.Order;
import edu.mum.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
 	@Autowired
	private OrderDao orderDao;

    public void save(Order order) {  		
		orderDao.save(order);
	}
	
	
    public Order update( Order order) {  		
		return orderDao.update(order);
	}
	
	
	public List<Order> findAll() {
		return (List<Order>)orderDao.findAll();
	}

 	public Order findOne(Long id) {
		return orderDao.findOne(id);
	}


}
