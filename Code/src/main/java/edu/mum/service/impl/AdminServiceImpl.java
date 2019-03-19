package edu.mum.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.batch.ProductBatch;
import edu.mum.domain.Authority;
import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.domain.User;
import edu.mum.main.TestUsers;
import edu.mum.service.AuthService;
import edu.mum.service.CategoryService;
import edu.mum.service.ItemService;
import edu.mum.service.UserService;

@Component
public class AdminServiceImpl {

	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;

	@Autowired
	AuthService authService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	TestUsers testUsers;
	
	@Autowired
	ProductBatch productBatch;

	public void InitialData(){

		Authority admin = new Authority("Admin");
		Authority customer = new Authority("Customer");
		
		authService.save(admin);
		authService.save(customer);
		
		User user = new User();
		user.setFirstName("Wael");
		user.setLastName("Rezk");
		user.setEmail("wrezk@mum.com");
		user.setUserName("wrezk");
		user.setPassword("wrezk");
		user.setAuthority(admin);
		userService.save(user);

	    user = new User();
		user.setFirstName("Ahmed");
		user.setLastName("Said");
		user.setEmail("Asaid@abc.com");
		user.setUserName("Asaid");
		user.setPassword("Asaid");
		user.setAuthority(customer);
		
		userService.save(user);


		Category categorySports = new Category("Sports");
		categoryService.save(categorySports);
		Category categoryToys = new Category("Toys");
		categoryService.save(categoryToys);
		Category categoryGifts = new Category("Gifts");
		categoryService.save(categoryGifts);

		User buyer = userService.findOne(2L); 
		User seller = userService.findOne(1L);
		System.out.println(buyer.getFirstName());
		System.out.println(seller.getFirstName());

		Product itemSled = new Product();
		itemSled.setName("Sled");
		itemSled.setDescription("Winter time fun");
		itemSled.setPrice(new BigDecimal(28.0));

		itemSled.addCategory(categoryToys);
		itemSled.addCategory(categorySports);
		itemService.update(itemSled);

		// Second item

		Product itemSkates = new Product();
		itemSkates.setName("Skates");
		itemSkates.setDescription("Winter time gliding");
		itemSkates.setPrice(new BigDecimal(22.0));

		// Reload categories from db
		categoryToys = categoryService.findOne(categoryToys.getId());
		categorySports = categoryService.findOne(categorySports.getId());

		itemSkates.addCategory(categoryToys);
		itemSkates.addCategory(categorySports);

		// Need to merge .. to handle detached categories....
		itemSkates = itemService.update(itemSkates);

		// Buyer & Reserve = initial will be found by findItemsBySellOrBuy based on
		Product itemShoes = new Product();
		itemShoes.setName("Shoes");
		itemShoes.setDescription("Snug Fit");
		itemShoes.setPrice(new BigDecimal(18.0));

		itemShoes.addCategory(categoryGifts);

		userService.update(buyer);

		userService.findAll();

	}

	public void AddCategory(String categoryName) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Category Name : ");
		Category newCategory = new Category(sc.next());
		categoryService.save(newCategory);
	}

	public void updateCategoryName() {

		Scanner sc = new Scanner(System.in);
		System.out.println("New Category Name : ");
		Category newCategory = new Category(sc.nextLine());
		categoryService.update(newCategory);
	}

	public void AddItems() {

		Scanner sc = new Scanner(System.in);
		Product newItem = new Product();
		System.out.println("Item Name : ");
		newItem.setName(sc.nextLine());
		System.out.println("Item Description : ");
		newItem.setDescription(sc.nextLine());
		System.out.println("Item Initial Price : ");
		newItem.setPrice(sc.nextBigDecimal());
		System.out.println("Item Reserve Price : ");
		System.out.println("Item Category : ");
//		newItem.addCategory(sc.nextInt());
//		System.out.println("Item Seller : ");
//		newItem.setSeller(sc.nextInt());

		itemService.update(newItem);

	}

	
	public void runBatch() {
		try {
			productBatch.startjob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void listAllItems() {

		List<Product> items = itemService.findAll();
		for (Product item : items) {
			System.out.println(item.toString());
		}
	}
	
	

}
