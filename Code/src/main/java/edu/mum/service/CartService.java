package edu.mum.service;

import org.springframework.security.core.context.SecurityContextHolder;

import edu.mum.domain.Cart;
import edu.mum.domain.Product;
import edu.mum.service.impl.CartServiceImpl;

public interface CartService {
	public void showCart();
	public void removeItemFromCart(int productIndex);
	public void addItemToCart(Product product, int quantity);
	
}
