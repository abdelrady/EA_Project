package edu.mum.main;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.domain.User;
import edu.mum.service.UserService;

@Component
public class Main {

	@Autowired
	TestItemCategory testItemCategory;

	@Autowired
	TestUsers testUsers;

	@Autowired
	TestItems testItems;

	@Autowired
	TestFindItemsBySellOrBuy testFindItemsBySellOrBuy;

	@Autowired
	UserService userService;

	public static void main(String[] args) throws BeansException, Exception {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context/applicationContext.xml");
		applicationContext.getBean(Main.class).mainInternal(applicationContext);
	}

	private void mainInternal(ApplicationContext applicationContext) throws Exception {

		testUsers.setupUsers();
		testItems.setupItems();

		// Named Query
		testItemCategory.testItemCategory();

		// JPQL - Criteria Query
		testFindItemsBySellOrBuy.test();

	}

}