package edu.mum.amqp;

import edu.mum.domain.*;

public class ItemListener {

	public void listen(Order order) {
		System.out.println("---------- DIRECT consumer for order: " + order.getTotalPrice());
	}

}
