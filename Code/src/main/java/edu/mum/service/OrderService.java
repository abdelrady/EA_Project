package edu.mum.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;

import edu.mum.domain.*;

public interface OrderService {
	public List<Order> findAll();
	public void sendOrder(RabbitTemplate rabbitTemplate, OrderStats orderStats);
	public void checkout(ApplicationContext context);
}
