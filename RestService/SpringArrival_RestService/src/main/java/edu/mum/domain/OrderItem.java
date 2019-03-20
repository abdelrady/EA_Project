package edu.mum.domain;

import javax.persistence.*;

@Table(name="orders_items")
@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	@Column
	private int quantity;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Product product;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Order order;
	
}
