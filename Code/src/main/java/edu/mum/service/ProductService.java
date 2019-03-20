package edu.mum.service;

import java.util.List;

import edu.mum.domain.Product;
import edu.mum.domain.User;
 
public interface ProductService {

	public void save(Product Item);
	public Product update(Product Item);
	public List<Product> findAll();
	 
	public List<Product> findBySellerOrBuyer(Integer price, User buyer, User seller);
	public List<Product> findByCategoryName(String categoryName);	 
	public List<Product> findItemCriteria(Integer initialPrice, User buyer, User seller);
	public Product findOne(Long id);

}
