package edu.mum.main;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.service.AdminService;

@Component
public class Main {

	@Autowired
	TestUsers testUsers;
	
	@Autowired
	TestItems testItems;
	
	@Autowired	
	Admin admin;
	
	@Autowired
	AdminService adminService;

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context/applicationContext.xml");
		applicationContext.getBean(Main.class).mainInternal(applicationContext);
	}

	private void mainInternal(ApplicationContext applicationContext) {
		int key = 0;

		testUsers.setupUsers();
		admin.adminActions();
	}

}