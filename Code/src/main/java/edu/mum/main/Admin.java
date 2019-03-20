package edu.mum.main;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.service.CategoryService;
import edu.mum.service.ItemService;
import edu.mum.service.UserService;
import edu.mum.service.impl.AdminServiceImpl;

@Component
public class Admin {

	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AdminServiceImpl adminService;

	public void adminActions() {

		int key = 0;
		while (key != -1) {
			System.out.println("Please write the no of your option or -1 to exit");
			System.out.println("[0] Initial Data");
			System.out.println("[1] List All items");
			System.out.println("[2] List All Users");
			System.out.println("[3] Add user");
			System.out.println("[4] Add Category");
			System.out.println("[5] Update Category Name");
			System.out.println("[6] Add Item");
			System.out.println("[7] Update Item Price");
			System.out.println("[8] Update Item Name");
			System.out.println("[9] Import Product from CSV file");
			System.out.println("[10] List orders");
			Scanner sc = new Scanner(System.in);
			key = sc.nextInt();
			switch (key) {
			case 0:

				adminService.InitialData();
				break;

			case 1:

				adminService.listAllItems();
				break;
				
			case 2:

				adminService.listAllUser();

				break;

			case 3:

				adminService.addUser();

				break;

			case 4:

				adminService.AddCategory("Winter");
				break;

			case 5:

				adminService.updateCategoryName();

				break;
			case 6:

				adminService.addItems();

				break;

			case 7:

				adminService.updateItemPrice();

				break;
				
			case 8:

				adminService.updateItemName();

				break;
				
			case 9://run batch
				adminService.runBatch();
				break;
				
			case 10://list Orders
				adminService.listOrders();
				break;

			default:
				break;
			}
		}
	}
}
