package edu.mum.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.mum.domain.Product;
import edu.mum.service.AuthService;
import edu.mum.service.ItemService;
import edu.mum.service.UserService;
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
	
	@Autowired
	UserService userService;
	
	@Autowired
	ItemService itemService;
	
	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context/applicationContext.xml");
		applicationContext.getBean(Main.class).mainInternal(applicationContext);
	}

	private void mainInternal(ApplicationContext applicationContext)  {
		
//		adminService.InitialData();
		
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
		 System.out.println("Successfully authenticated. Security context contains: " +
	             SecurityContextHolder.getContext().getAuthentication().getName());
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
			admin.adminActions(applicationContext);
			else if(isCustomer)
			{
				customerActions();
			}
				
				}
		
		
		
	}
	
	public void customerActions() {

		int key = 0;
		while (key != -1) {
			System.out.println("Please write the no of your option or -1 to exit");
			System.out.println("[1] List all products");
			System.out.println("[2] Show cart");
			System.out.println("[3] Add product to cart");
			System.out.println("[4] Remove product from cart");
			System.out.println("[5] Checkout");
			List<Product> products=new ArrayList<Product>();
			products=itemService.findAll();
			Scanner sc = new Scanner(System.in);
			key = sc.nextInt();
			switch (key) {
			case 1:
				products=userService.listItems();
				break;

			case 2:
				userService.showCart();
				break;

			case 3:
				System.out.println("Enter product number");

				 sc = new Scanner(System.in);
				key = sc.nextInt();
				int quantity=0;
				System.out.println("Enter quantity");
				 sc = new Scanner(System.in);
				 quantity = sc.nextInt();
				 
				Product product=products.get(key-1);
				userService.addItemToCart(product, quantity);
				break;
			case 4:
				System.out.println("Enter product number");
				 sc = new Scanner(System.in);
				 key = sc.nextInt();
				 product=products.get(key-1);
				userService.removeItemFromCart(key-1);
				break;
			case 5:
				

				break;
			default:
				break;
			}
		}

	}

}