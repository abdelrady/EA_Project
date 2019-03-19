package edu.mum.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import edu.mum.domain.*;
import edu.mum.service.impl.AdminServiceImpl;
import edu.mum.service.impl.AuthServiceImpl;

@Component
public class Main {

	private Map<String, Cart> usersCarts = new HashMap<>();
	
	@Autowired
	TestUsers testUsers;
	
	@Autowired
	TestItems testItems;
	
	@Autowired	
	Admin admin;
	
	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	AuthServiceImpl authService;

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context/applicationContext.xml");
		applicationContext.getBean(Main.class).mainInternal(applicationContext);
	}

	private void mainInternal(ApplicationContext applicationContext)  {
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
		
		System.out.print(username+password);
		
		if(authService.Login(username,password))
				{
					
				}
		
		//admin.adminActions();
	}

}