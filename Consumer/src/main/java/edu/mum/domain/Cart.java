package edu.mum.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Cart {
	private Long id = null;
	
	private User owner;
	
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	private BigDecimal totalPrice;
	
	public void addItem(Product product,int quantity)
	{
		OrderItem orderItem=new OrderItem();
		orderItem.setProduct(product);
		orderItem.setQuantity(quantity);
		orderItems.add(orderItem);
	}
	
	public void removeItem(int productIndex)
	{
		orderItems.remove(productIndex);
	}
	
	public List<OrderItem> getOrderItems()
	{
		return orderItems;
	}
	
}
