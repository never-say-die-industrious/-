package com.baihui.service;

import java.util.List;

import com.baihui.domain.Order;
import com.baihui.domain.PageBean;


public interface IOrderService {

	List<Order> findOrderByCustomer(Integer customerId);

	PageBean<Order> findOrderByCustomerPage(Integer customerId, int pageNum, int currentCount);

	void delOrder(Order id);

}
