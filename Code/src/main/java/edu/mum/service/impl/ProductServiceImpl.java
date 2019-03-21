package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.ProductDao;
import edu.mum.domain.Product;
import edu.mum.domain.User;
import edu.mum.service.ProductService;

@Service
@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED)
public class ProductServiceImpl implements ProductService {
	
	
 	@Autowired
	private ProductDao productDao;

    @Override
	public void save( Product Item) {  		
		productDao.save(Item);
	}
	
	
    @Override
	public Product update( Product Item) {  		
		return productDao.update(Item);
	}
	
	
	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

 	@Override
	public Product findOne(Long id) {
		return productDao.findOne(id);
	}


	@Override
	public List<Product> findBySellerOrBuyer(Integer price, User buyer, User seller) {
		 
		return productDao.findBySellerOrBuyer(price, buyer, seller);
	}

	@Override
	public List<Product> findByCategoryName(String categoryName) {
		return productDao.findByCategoryName(categoryName);
	}
	
	@Override
	public List<Product> findItemCriteria(Integer initialPrice, User buyer, User seller) {
		return productDao.findItemCriteria(initialPrice,buyer,seller);
	}
}
