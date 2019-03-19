package edu.mum.service;

import java.util.List;

import edu.mum.domain.Authority;
import edu.mum.domain.User;
 
public interface AuthService {

	public void save(Authority authority);
	public List<Authority> findAll();
	public Authority findOne(Long id);
	public Authority update(Authority user);
	public boolean Login(String username,String password);
}
