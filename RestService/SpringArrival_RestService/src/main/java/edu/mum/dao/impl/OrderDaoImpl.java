package edu.mum.dao.impl;

 

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.mum.dao.OrderDao;
import edu.mum.domain.Order;
import edu.mum.domain.User;


@SuppressWarnings("unchecked")
@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

	public OrderDaoImpl() {
		super.setDaoType(Order.class );
		}

	public List<Order> findOrdersByUser(User user){
		return null;
	}

 }