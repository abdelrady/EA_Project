package edu.mum.domain;

import javax.persistence.*;

@Table(name="orders_items")
@Entity
public class OrderItem {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

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
