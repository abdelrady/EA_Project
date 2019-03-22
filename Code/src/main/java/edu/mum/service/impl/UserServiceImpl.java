package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.OrderDao;
import edu.mum.dao.UserDao;
import edu.mum.domain.Cart;
import edu.mum.domain.OrderItem;
import edu.mum.domain.Product;
import edu.mum.domain.User;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;

@Service
@Transactional
public class UserServiceImpl implements edu.mum.service.UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	ProductService itemService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	OrderDao orderDao;

	

	@Override
	public void save(User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userDao.save(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public User update(User user) {
		return userDao.update(user);

	}

	@Override
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
	public List<Product> listItems() {
		List<Product> items = itemService.findAll();
		for (Product item : items) {
			System.out.println(item.toString());
		}
		return items;

	}

	
}
