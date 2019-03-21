package edu.mum.amqp;

import edu.mum.domain.*;

public class ItemListener {

	public void listen(OrderStats orderStats) {
		System.out.println("---------- Price is : " + orderStats.getTotalAmount());
	}
}
