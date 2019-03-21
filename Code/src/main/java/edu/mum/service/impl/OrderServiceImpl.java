package edu.mum.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.OrderDao;
import edu.mum.dao.ProductDao;
import edu.mum.dao.UserDao;
import edu.mum.domain.*;
import edu.mum.rest.service.OrderRestService;
import edu.mum.service.ItemService;
import edu.mum.service.OrderService;

@Service
@Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, isolation = org.springframework.transaction.annotation.Isolation.READ_COMMITTED)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRestService orderRestService;

	@Autowired
	private UserDao userDao;

	@Autowired
	ItemService itemService;

	@Autowired
	OrderDao orderDao;

	@Override
	public List<Order> findAll() {
		return orderRestService.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('Customer')")
	public void checkout(ApplicationContext context) {
		Cart cart = CartServiceImpl.getUserCart(SecurityContextHolder.getContext().getAuthentication().getName());
		Order order = new Order();
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		order.setOwner(userDao.findByName(currentUserName));
		
		Integer index = 1;
		for (OrderItem orderItem : cart.getOrderItems()) {
			order.getOrderItems().add(orderItem);
			order.setTotalPrice(orderItem.getProduct().getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
					.add(order.getTotalPrice()));
			index++;
		}
		
		orderDao.save(order);
		
		CartServiceImpl.clearUserCart(currentUserName);
		
		sendOrderForProcessing(context, order);
		System.out.println("Order is sent for processing .. Thank you!");
	}
	
	public void sendOrderForProcessing(ApplicationContext context, Order order) {
			RabbitTemplate directTemplate = context.getBean("directTemplate", RabbitTemplate.class);
			OrderStats orderStats = new OrderStats();
			orderStats.setTotalAmount(order.getTotalPrice());
			orderStats.setCustomerEmail(order.getOwner().getEmail());
			StringBuilder sb = new StringBuilder();
			for(OrderItem oi : order.getOrderItems()) {
				sb.append(oi.toString());
			}
			//order.getOrderItems().stream().map(Object::toString).collect(Collectors.joining(","))
			orderStats.setItemsSummary(sb.toString());
			this.sendOrder(directTemplate, orderStats);
	}

	public void sendOrder(RabbitTemplate rabbitTemplate, OrderStats orderStats) {
		rabbitTemplate.convertAndSend(orderStats);
	}
}
