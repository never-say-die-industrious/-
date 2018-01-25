package com.baihui.service;

import java.util.List;

import com.baihui.domain.Customer;


public interface ICustomerService {

	List<Customer> findAllCustomer();

	void addCustomer(Customer customer);

	void delCustomer(Customer customer);

}
