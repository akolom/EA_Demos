 
  
package edu.mum.integration;

import org.springframework.integration.annotation.Gateway;

import edu.mum.domain.BookOrder;
import edu.mum.domain.Order;

/**
 * The entry point for the 
 * Bus Flow.
 * 
 */
public interface OrderGateway {

    /**
     * Process a book order.
     */
	@Gateway(requestChannel="processOrder")
	public void process(BookOrder order);

}
