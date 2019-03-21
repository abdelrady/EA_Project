package edu.mum.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import edu.mum.batch.ProductBatch;
import edu.mum.domain.Authority;
import edu.mum.domain.Category;
import edu.mum.domain.Order;
import edu.mum.domain.Product;
import edu.mum.domain.User;
import edu.mum.main.TestUsers;
import edu.mum.service.AuthService;
import edu.mum.service.CategoryService;
import edu.mum.service.ItemService;
import edu.mum.service.OrderService;
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

	@Autowired
	OrderService orderService;

	public void addUser() {

		User user = new User();
		Authority role;

		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Select Role [1] Admin :" + "[2] customer ");
			if (sc.nextInt() == 1)
				role = new Authority("Admin");
			else
				role = new Authority("Customer");

			authService.save(role);
			System.out.println("First Name : ");
			user.setFirstName(sc.next());
			System.out.println("last Name : ");
			user.setLastName(sc.next());
			System.out.println("Email : ");
			user.setEmail(sc.next());
			System.out.println("Username : ");
			user.setUserName(sc.next());
			System.out.println("Password : ");
			user.setPassword(sc.next());

			user.setAuthority(role);
			userService.save(user);

		} catch (Exception e) {
			Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

			Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

			System.out.println("Not added successfully");
			for (ConstraintViolation<?> violation : constraintViolations) {
				System.out.format("%10s | %35s | value is %10s%n", violation.getPropertyPath(), violation.getMessage(),
						violation.getInvalidValue());
			}

		}

	}

	public void listAllUser() {

		List<User> users = userService.findAll();
		for (User user : users) {
			System.out.println(user.toString());
		}

	}

	public void listAllCategories() {

		List<Category> categories = categoryService.findAll();
		for (Category category : categories) {
			System.out.println(category.toString());
		}

	}

	@PreAuthorize("hasAuthority('Admin')")
	public void AddCategory() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Category Name : ");
		Category newCategory = new Category(sc.next());
		try {
			categoryService.save(newCategory);

		} catch (Exception e) {
			Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

			Set<ConstraintViolation<Category>> constraintViolations = validator.validate(newCategory);

			System.out.println("Not added successfully");
			for (ConstraintViolation<?> violation : constraintViolations) {
				System.out.format("%10s | %35s | value is %10s%n", violation.getPropertyPath(), violation.getMessage(),
						violation.getInvalidValue());
			}
		}
	}

	@PreAuthorize("hasAuthority('Admin')")
	public void updateCategoryName() {

		listAllCategories();

		System.out.println("Enter ID of Category to update : ");

		Scanner sc = new Scanner(System.in);

		Category newCategory = categoryService.findOne(sc.nextLong());

		
		try {
			System.out.println("New Category Name : ");
			newCategory.setName(sc.next());
			categoryService.update(newCategory);

		} catch (Exception e) {
			Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

			Set<ConstraintViolation<Category>> constraintViolations = validator.validate(newCategory);

			System.out.println("Not added successfully");
			for (ConstraintViolation<?> violation : constraintViolations) {
				System.out.format("%10s | %35s | value is %10s%n", violation.getPropertyPath(), violation.getMessage(),
						violation.getInvalidValue());
			}
		}

	}

	@PreAuthorize("hasAuthority('Admin')")
	public void addItems() {

		Scanner sc = new Scanner(System.in);
		Product newItem = new Product();

//		newItem.addCategory(sc.nextInt());

		try {
			System.out.println("Enter ID of Category of Item : ");

			listAllCategories();

			Category newCategory = categoryService.findOne(sc.nextLong());
//			newItem.addCategory(newCategory);
			System.out.println("Item Name : ");
			newItem.setName(sc.next());
			System.out.println("Item Description : ");
			newItem.setDescription(sc.next());
			System.out.println("Item  Price : ");
			newItem.setPrice(sc.nextBigDecimal());

			itemService.save(newItem);

		} catch (Exception e) {
			System.out.println(e.toString());
			Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

			Set<ConstraintViolation<Product>> constraintViolations = validator.validate(newItem);

			System.out.println("Not added successfully");
			for (ConstraintViolation<?> violation : constraintViolations) {
				System.out.format("%10s | %35s | value is %10s%n", violation.getPropertyPath(), violation.getMessage(),
						violation.getInvalidValue());
			}
		}
	}

	public void updateItemPrice() {

		listAllItems();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ID of item to update : ");
		Product item = itemService.findOne(sc.nextLong());
		try {

			System.out.println("New Price : ");
			item.setPrice(sc.nextBigDecimal());

			itemService.update(item);

		} catch (Exception e) {
			Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

			Set<ConstraintViolation<Product>> constraintViolations = validator.validate(item);

			System.out.println("Not added successfully");
			for (ConstraintViolation<?> violation : constraintViolations) {
				System.out.format("%10s | %35s | value is %10s%n", violation.getPropertyPath(), violation.getMessage(),
						violation.getInvalidValue());
			}
		}
	}

	public void updateItemName() {

		listAllItems();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ID of item to update : ");
		Product item = itemService.findOne(sc.nextLong());

		try {

			System.out.println("New Item Name : ");
			item.setName(sc.next());

			itemService.update(item);

		} catch (Exception e) {
			Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

			Set<ConstraintViolation<Product>> constraintViolations = validator.validate(item);

			System.out.println("Not added successfully");
			for (ConstraintViolation<?> violation : constraintViolations) {
				System.out.format("%10s | %35s | value is %10s%n", violation.getPropertyPath(), violation.getMessage(),
						violation.getInvalidValue());
			}
		}
	}

	public void runBatch() {
		try {
			productBatch.startjob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listOrders() {
		List<Order> orders = orderService.findAll();
		System.out.println("Numbers of orders: " + orders.size());
		if (CollectionUtils.isNotEmpty(orders)) {
			for (Order o : orders) {
				System.out.println(o.toString());
			}
		}
	}

	@PreAuthorize("hasAuthority('Admin')")
	public void listAllItems() {

		List<Product> items = itemService.findAll();
		for (Product item : items) {
			System.out.println(item.toString());
		}
	}

}
