package com.baihui.dao;

import java.util.List;

import com.baihui.domain.Customer;
import com.baihui.domain.Order;

public interface IOrderDAO {

	List<Order> findByCustomer(Customer customer);

	List<Order> findOrderByCustomerPage(Customer c, int pageNum, int currentCount);

	public int findTotalCount();

	void delOrder(Order order);

	List<Order> findById(Order id);

}
