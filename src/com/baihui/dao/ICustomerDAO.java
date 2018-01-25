package com.baihui.dao;

import java.util.List;

import com.baihui.domain.Customer;


public interface ICustomerDAO {

	List<Customer> findAllCustomer();

	void addCustomer(Customer customer);

	void delCustomer(Customer customer);

	Customer findCustomerById(Integer id);

	

}
