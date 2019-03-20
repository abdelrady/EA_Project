package edu.mum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.service.OrderService;

//@Component
public class AmqpConsumerMain {

	//@Autowired
	//OrderService itemService;

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"classpath:META-INF/spring/item-app-context.xml", "classpath*:context/applicationContext.xml"});
		new AmqpConsumerMain().mainInternal(applicationContext);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void mainInternal(ApplicationContext applicationContext) {
		System.out.println("Main Internal....");
		//System.out.println(itemService.findAll().size());
	}
}
