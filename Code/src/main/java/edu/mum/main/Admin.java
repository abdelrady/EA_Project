package edu.mum.main;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;
import edu.mum.service.UserService;
import edu.mum.service.impl.AdminServiceImpl;

@Component
public class Admin {

	@Autowired
	ProductService itemService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AdminServiceImpl adminService;

	public void adminActions(ApplicationContext context) {

		int key = 0;
		while (key != -1) {
			System.out.println("====================================");
			System.out.println("Please write option no or -1 to exit");
			System.out.println("[1] List All items");
			System.out.println("[2] List All Users");
			System.out.println("[3] List All Categories");
			System.out.println("[4] Add user");
			System.out.println("[5] Add Category");
			System.out.println("[6] Update Category Name");
			System.out.println("[7] Add Item");
			System.out.println("[8] Update Item Price");
			System.out.println("[9] Update Item Name");
			System.out.println("[10] Import Product from CSV file");
			System.out.println("[11] List orders");
			Scanner sc = new Scanner(System.in);
			key = sc.nextInt();
			switch (key) {

			case 1:

				adminService.listAllItems();
				break;

			case 2:

				adminService.listAllUser();

				break;

			case 3:
				adminService.listAllCategories();
				break;

			case 4:

				adminService.addUser();

				break;

			case 5:

				adminService.AddCategory();
				break;

			case 6:

				adminService.updateCategoryName();

				break;
			case 7:

				adminService.addItems();

				break;

			case 8:

				adminService.updateItemPrice();

				break;

			case 9:

				adminService.updateItemName();

				break;

			case 10:// run batch
				adminService.runBatch();
				break;

			case 11:// list Orders
				adminService.listOrders();
				break;

			case 12:// checkout order mock
				adminService.sendOrder(context);
				break;

			default:
				break;
			}
		}
	}
}
