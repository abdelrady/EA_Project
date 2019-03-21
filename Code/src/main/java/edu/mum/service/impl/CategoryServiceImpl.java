package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.CategoryDao;
import edu.mum.domain.Category;

@Service
@Transactional 
public class CategoryServiceImpl implements edu.mum.service.CategoryService {
	
 	@Autowired
	private CategoryDao categoryDao;

    @Override
	public void save( Category category) {  		
  		categoryDao.save(category);
 	}
  	
  	
    @Override
	public void update( Category category) {  		
  		categoryDao.update(category);
 	}
  	
  	
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category findByCategoryName(String name) {
		return categoryDao.findByCategoryName(name);
	}
 
	@Override
	public Category findOne(Long id) {
		return categoryDao.findOne(id);
	}
	
 
}
