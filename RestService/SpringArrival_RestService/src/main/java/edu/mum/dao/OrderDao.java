package edu.mum.dao;

import java.util.List;

import edu.mum.domain.Order;
import edu.mum.domain.User;

public interface OrderDao extends GenericDao<Order> {
      
	public List<Order> findOrdersByUser(User user);
}
