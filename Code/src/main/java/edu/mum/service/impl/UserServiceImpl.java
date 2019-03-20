package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.UserDao;
import edu.mum.domain.Cart;
import edu.mum.domain.Product;
import edu.mum.domain.User;
import edu.mum.service.AuthService;
import edu.mum.service.CategoryService;
import edu.mum.service.ItemService;
import edu.mum.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements edu.mum.service.UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	ItemService itemService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	CartServiceImpl cartService;

	public void save(User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userDao.save(user);
	}

	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User update(User user) {
		return userDao.update(user);

	}

	public User testRefresh(User user) {
		user.setEmail("Lotta@Doe.com");
		userDao.save(user);

		return user;
	}

	@Override
	public User findOne(Long id) {

		return userDao.findOne(id);
	}

	@Override
	@PreAuthorize("hasAuthority('Customer')")
	public void listItems() {
		List<Product> items = itemService.findAll();
		for (Product item : items) {
			System.out.println(item.toString());
		}

	}

	@Override
	@PreAuthorize("hasAuthority('Customer')")
	public void addItemToCart(Product product, int quantity) {
		Cart cart = cartService.getUserCart(SecurityContextHolder.getContext().getAuthentication().getName());
		cart.addItem(product, quantity);
	}

	@Override
	@PreAuthorize("hasAuthority('Customer')")
	public void removeItemFromCart(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	@PreAuthorize("hasAuthority('Customer')")
	public void showCart() {
		// TODO Auto-generated method stub

	}

	@Override
	@PreAuthorize("hasAuthority('Customer')")
	public void checkout() {
		// TODO Auto-generated method stub

	}

}
