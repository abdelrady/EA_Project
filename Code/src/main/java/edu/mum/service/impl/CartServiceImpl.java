package edu.mum.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;

import edu.mum.domain.Cart;

public class CartServiceImpl  {
	private Map<String, Cart> usersCarts = new HashMap<>();

	private CartServiceImpl() {

	}

	@PreAuthorize("hasAuthority('Customer')")
	public Cart getUserCart(String username) {
		if (usersCarts.containsKey(username)) {
			return usersCarts.get(username);
		} else {
			return usersCarts.put(username, new Cart());
		}
	}
}
