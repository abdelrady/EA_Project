package edu.mum.service;

import java.util.List;

import edu.mum.domain.Order;

public interface OrderService {

	public void save(Order Item);

	public Order update(Order order);

	public List<Order> findAll();

	public Order findOne(Long id);

}
