package edu.mum;

import edu.mum.domain.Order;

public interface OrderService {
    Order getOrder(String stateCode);
}
