package edu.mum.main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.User;
import edu.mum.service.UserService;

@Component
public class TestUsers {

	@Autowired
	UserService userService;
	
	

 public void setupUsers() {
	 
	//Add 2 users for JPQL testing 
	 
   User user = new User();
   user.setFirstName("John");
   user.setLastName("Doe");
   user.setEmail("john@Doe.com");
   //user.setUserName("JohnDoe");
   //user.setPassword("DoeNuts");
   
   userService.save(user);
   
   //UserCredentials userCredentials = new UserCredentials();
   //userCredentials.setVerifyPassword("DoeNuts");

   // Set both sides
   //userCredentials.setUser(user);
   //user.setUserCredentials(userCredentials);
   
   //userCredentialsService.update(userCredentials);
   
    
   // Add second for JPQL testing
    user = new User();
   user.setFirstName("Jane");
   user.setLastName("Doe");
   user.setEmail("jane@Doe.com");
   userService.save(user);
 
   
}
}
