package edu.mum.main;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import edu.mum.service.AuthService;
import edu.mum.service.impl.AdminServiceImpl;

@Component
public class Main {

	
	
	@Autowired
	TestUsers testUsers;
	
	@Autowired
	TestItems testItems;
	
	@Autowired	
	Admin admin;
	
	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	AuthService authService;
	

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context/applicationContext.xml");
		applicationContext.getBean(Main.class).mainInternal(applicationContext);
	}

	private void mainInternal(ApplicationContext applicationContext)  {
		
		adminService.InitialData();
		
		int key = 0;
		String username="";
		String password="";
		System.out.println("Welcome to SpringArrival!");
		System.out.println("Please enter you credentials");
		System.out.print("User name:");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		username = sc.next();
		System.out.print("Password:");
		 sc = new Scanner(System.in);
		password = sc.next();
		boolean isAdmin=false;
		boolean isCustomer=false;
		Authentication result=authService.Login(username,password);
		// System.out.println("Successfully authenticated. Security context contains: " +
	       //       SecurityContextHolder.getContext().getAuthentication());
		if(result!=null && result.isAuthenticated()==true)
				{
			for(GrantedAuthority a : result.getAuthorities())
			{
				System.out.println(a.getAuthority());
				if(a.getAuthority().equals("Admin"))
					isAdmin=true;
				if(a.getAuthority().equals("Customer"))
					isCustomer=true;
			}
			
			if(isAdmin)
			admin.adminActions();
			else if(isCustomer)
			{
				//customer actions here
			}
				
				}
		
		
		
	}

}