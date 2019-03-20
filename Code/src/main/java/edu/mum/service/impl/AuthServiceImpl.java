package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.AuthorityDao;
import edu.mum.domain.Authority;

@Service
@Transactional 
public class AuthServiceImpl implements edu.mum.service.AuthService {
	
 	@Autowired
	private AuthorityDao authorityDao;
 	
 	@Autowired
	AuthenticationManager authenticationManager;

 	
     @Override
	public void save(Authority authority) {
    	 authorityDao.save(authority);
 	}
  	
  	
	@Override
	public List<Authority> findAll(){
		return authorityDao.findAll();
	}

	@Override
	public Authority update(Authority authority) {
		 return authorityDao.update(authority);
	}

	
	@Override
	public Authority findOne(Long id) {
		 
		return authorityDao.findOne(id);
	}


	@Override
	public Authentication Login(String username, String password) {
		// TODO Auto-generated method stub
		 try {
			
    	Authentication request = new UsernamePasswordAuthenticationToken(username, password);
    	Authentication result =authenticationManager.authenticate(request);
    	SecurityContextHolder.getContext().setAuthentication(result);
    	return result;
		 }
		 catch(AccessDeniedException  e) {
	      	 System.out.println("Authentication failed: " + e.getMessage()) ;
	      }
		 catch(BadCredentialsException e) {
			 System.out.println("Authentication failed: " + e.getMessage()) ;
	          } catch (Exception e) {
				// TODO Auto-generated catch block
	        	  System.out.println("Authentication failed: " + e.getMessage()) ;
				e.printStackTrace();
			}
	
		 return null;
	}
 

}
