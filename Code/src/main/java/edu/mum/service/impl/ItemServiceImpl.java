package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.ItemDao;
import edu.mum.domain.Product;
import edu.mum.domain.User;
import edu.mum.service.ItemService;

@Service
@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED)
public class ItemServiceImpl implements ItemService {
	
	
 	@Autowired
	private ItemDao itemDao;

    public void save( Product Item) {  		
		itemDao.save(Item);
	}
	
	
    public Product update( Product Item) {  		
		return itemDao.update(Item);
	}
	
	public List<Product> findAll() {
		return (List<Product>)itemDao.findAll();
	}

 	public Product findOne(Long id) {
		return itemDao.findOne(id);
	}


	@Override
	public List<Product> findBySellerOrBuyer(Integer price, User buyer, User seller) {
		 
		return itemDao.findBySellerOrBuyer(price, buyer, seller);
	}

	public List<Product> findByCategoryName(String categoryName) {
		return itemDao.findByCategoryName(categoryName);
	}
	
	public List<Product> findItemCriteria(Integer initialPrice, User buyer, User seller) {
		return itemDao.findItemCriteria(initialPrice,buyer,seller);
	}
}
