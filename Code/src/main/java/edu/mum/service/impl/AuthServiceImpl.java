package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.AuthorityDao;
import edu.mum.dao.GenericDao;
import edu.mum.dao.UserDao;
import edu.mum.domain.Authority;
import edu.mum.domain.User;

@Service
@Transactional 
public class AuthServiceImpl implements edu.mum.service.AuthService {
	
 	@Autowired
	private AuthorityDao authorityDao;

 	
     public void save(Authority authority) {
    	 authorityDao.save(authority);
 	}
  	
  	
	public List<Authority> findAll(){
		return (List<Authority>)authorityDao.findAll();
	}

	public Authority update(Authority authority) {
		 return authorityDao.update(authority);
	}

	
	@Override
	public Authority findOne(Long id) {
		 
		return authorityDao.findOne(id);
	}
 

}
