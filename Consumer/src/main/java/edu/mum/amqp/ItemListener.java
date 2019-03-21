package edu.mum.amqp;

import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.domain.*;
import edu.mum.helpers.EmailService;

public class ItemListener {

	@Autowired
	EmailService emailService;
	
	public void listen(OrderStats orderStats) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("------------------------- New Order received -------------------");
		sb.append("Order Total Price is : " + orderStats.getTotalAmount());
		sb.append("Order Owner Email : " + orderStats.getCustomerEmail());
		sb.append("Order Summary : " + orderStats.getItemsSummary());
		sb.append("----------------------------------------------------------------");
		
		String content = sb.toString();
		System.out.println(content);
		
		try {
			emailService.sendNotificationMail("Test", orderStats.getCustomerEmail(), content);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
}
