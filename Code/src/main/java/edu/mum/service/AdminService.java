package edu.mum.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Category;
import edu.mum.domain.Item;
import edu.mum.domain.User;
import edu.mum.domain.UserCredentials;
import edu.mum.main.TestUsers;

@Component
public class AdminService {

	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	TestUsers testUsers;

	@Autowired
	UserCredentialsService userCredentialsService;

	public void InitialData(){

		User user = new User();
		user.setFirstName("Wael");
		user.setLastName("Rezk");
		user.setEmail("wrezk@mum.com");
		userService.save(user);

		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUserName("wrezk");
		userCredentials.setPassword("wrezk");
		userCredentials.setVerifyPassword("wrezk");

		userCredentials.setUser(user);
		user.setUserCredentials(userCredentials);

	    user = new User();
		user.setFirstName("Ahmed");
		user.setLastName("Said");
		user.setEmail("Asaid@abc.com");
		userService.save(user);

		userCredentials.setUserName("Asaid");
		userCredentials.setPassword("Asaid");
		userCredentials.setVerifyPassword("Asaid");

		userCredentials.setUser(user);
		user.setUserCredentials(userCredentials);


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

		Item itemSled = new Item();
		itemSled.setName("Sled");
		itemSled.setDescription("Winter time fun");
		itemSled.setInitialPrice(new BigDecimal(28.0));
		itemSled.setReservePrice(new BigDecimal(32.0));

		itemSled.addCategory(categoryToys);
		itemSled.addCategory(categorySports);
		itemSled.setSeller(seller);
		itemService.update(itemSled);

		// Second item

		Item itemSkates = new Item();
		itemSkates.setName("Skates");
		itemSkates.setDescription("Winter time gliding");
		itemSkates.setReservePrice(new BigDecimal(26.0));
		itemSkates.setInitialPrice(new BigDecimal(22.0));

		// Reload categories from db
		categoryToys = categoryService.findOne(categoryToys.getId());
		categorySports = categoryService.findOne(categorySports.getId());

		itemSkates.addCategory(categoryToys);
		itemSkates.addCategory(categorySports);

		// Need to merge .. to handle detached categories....
		itemSkates = itemService.update(itemSkates);

		// Buyer & Reserve = initial will be found by findItemsBySellOrBuy based on
		Item itemShoes = new Item();
		itemShoes.setName("Shoes");
		itemShoes.setDescription("Snug Fit");
		itemShoes.setReservePrice(new BigDecimal(18.0));
		itemShoes.setInitialPrice(new BigDecimal(18.0));

		itemShoes.addCategory(categoryGifts);

		buyer.addBoughtItem(itemShoes);
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
		Item newItem = new Item();
		System.out.println("Item Name : ");
		newItem.setName(sc.nextLine());
		System.out.println("Item Description : ");
		newItem.setDescription(sc.nextLine());
		System.out.println("Item Initial Price : ");
		newItem.setInitialPrice(sc.nextBigDecimal());
		System.out.println("Item Reserve Price : ");
		newItem.setReservePrice(sc.nextBigDecimal());
//		System.out.println("Item Category : ");
//		newItem.addCategory(sc.nextInt());
//		System.out.println("Item Seller : ");
//		newItem.setSeller(sc.nextInt());

		itemService.update(newItem);

	}

	public void listAllItems() {

		List<Item> items = itemService.findAll();
		for (Item item : items) {
			System.out.println(item.toString());
		}
	}

}
