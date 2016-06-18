package edu.mum;

import edu.mum.domain.Order;

public class OrderListener {

	public void listen(Order order) {
			
		String name = order.getItems().get(0).getProduct().getName();
		System.out.println("---------- Order for Product: " + name);
	}
}
