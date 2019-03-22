package edu.mum.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.mum.domain.Cart;
import edu.mum.domain.OrderItem;
import edu.mum.domain.Product;
import edu.mum.service.CartService;

public class CartServiceImpl implements CartService {
	private static Map<String, Cart> usersCarts = new HashMap<>();

	private CartServiceImpl() {

	}

	@PreAuthorize("hasAuthority('Customer')")
	public static Cart getUserCart(String username) {
		if (usersCarts.containsKey(username)) {
			return usersCarts.get(username);
		} else {
			Cart cart = new Cart();

			usersCarts.put(username, cart);
			return cart;
		}
	}

	@PreAuthorize("hasAuthority('Customer')")
	public static void clearUserCart(String username) {
		usersCarts.put(username, new Cart());
	}

	@Override
	@PreAuthorize("hasAuthority('Customer')")
	public void showCart() {
		// TODO Auto-generated method stub
		Cart cart = CartServiceImpl.getUserCart(SecurityContextHolder.getContext().getAuthentication().getName());
		Integer index = 1;
		for (OrderItem orderItem : cart.getOrderItems()) {
			System.out.println(index.toString() + "-" + orderItem.toString());
			index++;
		}
	}

	@Override
	@PreAuthorize("hasAuthority('Customer')")
	public void removeItemFromCart(int productIndex) {
		// TODO Auto-generated method stub
		Cart cart = CartServiceImpl.getUserCart(SecurityContextHolder.getContext().getAuthentication().getName());
		cart.removeItem(productIndex - 1);
	}

	@Override
	@PreAuthorize("hasAuthority('Customer')")
	public void addItemToCart(Product product, int quantity) {
		// TODO Auto-generated method stub
		Cart cart = CartServiceImpl.getUserCart(SecurityContextHolder.getContext().getAuthentication().getName());
		cart.addItem(product, quantity);
		System.out.println("Item is added!");
	}
}
