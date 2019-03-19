package edu.mum.dao;

import java.util.List;

import edu.mum.domain.Product;
import edu.mum.domain.User;

public interface ItemDao extends GenericDao<Product> {
      
	public List<Product> findBySellerOrBuyer(Integer price, User buyer, User seller);
	public List<Product> findByCategoryName(String categoryName);
	public List<Product> findItemCriteria(Integer initialPrice, User buyer, User seller);
}
