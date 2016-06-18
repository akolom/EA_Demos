package edu.mum.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

  
public class Order implements Serializable{
 	   private Long id = null;
 	   private int version = 0;

 	   private String orderNumber;

 	   private Set<OrderItem> items = new HashSet<OrderItem>();

 	   private OrderPayment payment = new OrderPayment();

 	   public Order() {}
 	   public Order (String orderNumber, Set<OrderItem> items,OrderPayment payment ) {
 		   this.orderNumber = orderNumber;
 		   this.items = items;
 		   this.payment = payment;
 	   }
 	   
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	
	public OrderPayment getPayment() {
		return payment;
	}

	public void setPayment(OrderPayment payments) {
		this.payment= payments;
	}

	public void addOrderItem(OrderItem orderItem) {
		this.items.add(orderItem);
		orderItem.setOrder(this);
	}

 
}
