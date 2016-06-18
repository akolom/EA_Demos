  

package edu.mum.domain;


import java.io.Serializable;

public class BookOrder  implements Serializable {

    public enum BookOrderType { DELIVERY, PICKUP }

    private final String title;
    private final int quantity;
    private final int orderNumber;
    private final BookOrderType orderType;

    /**
     * Constructor
     */
    public BookOrder(String title, int quantity, BookOrderType orderType, int orderNumber) {
        this.title = title;
        this.quantity = quantity;
        this.orderType = orderType;
        this.orderNumber = orderNumber;
    }

    /**
     * Gets title.
     */
 
    public String getTitle() {
        return title;
    }

    /**
     * Gets quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets order type.
     */
    public BookOrderType getBookOrderType() {
        return orderType;
    }

	public int getOrderNumber() {
		return orderNumber;
	}

	public BookOrderType getOrderType() {
		return orderType;
	}

}
