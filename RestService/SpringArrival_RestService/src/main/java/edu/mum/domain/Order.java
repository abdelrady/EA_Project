package edu.mum.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Table(name="orders")
@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ITEM_ID")
	private Long id = null;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User owner;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	@Column
	private BigDecimal totalPrice;
	
}

