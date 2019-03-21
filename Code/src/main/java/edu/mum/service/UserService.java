package edu.mum.service;

import java.util.List;

import edu.mum.domain.Product;
import edu.mum.domain.User;
 
public interface UserService {

	public void save(User user);
	public List<User> findAll();
	public User findOne(Long id);
	public User findByEmail(String email);
	public User update(User user);
	public User testRefresh(User user);
	
	public List<Product>  listItems();
	public void addItemToCart(Product product,int quantity);
	public void removeItemFromCart(int productIndex);
	public void showCart();
	public void checkout();
	
}
