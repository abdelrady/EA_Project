package edu.mum.amqp;

import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.domain.*;
import edu.mum.helpers.EmailService;

public class ItemListener {

	//@Autowired
	//EmailService emailService;
	
	public void listen(OrderStats orderStats) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("------------------------- New Order received -------------------");
		sb.append("\nOrder Total Price is : " + orderStats.getTotalAmount());
		sb.append("\nOrder Owner Email : " + orderStats.getCustomerEmail());
		sb.append("\nOrder Summary : " + orderStats.getItemsSummary());
		sb.append("\n----------------------------------------------------------------");
		
		String content = sb.toString();
		System.out.println(content);
		
		try {
			//emailService.sendNotificationMail("Test", orderStats.getCustomerEmail(), content);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
}
