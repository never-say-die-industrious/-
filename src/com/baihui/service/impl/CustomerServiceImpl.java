package com.baihui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baihui.dao.ICustomerDAO;
import com.baihui.domain.Customer;
import com.baihui.service.ICustomerService;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDAO customerDao;

	
	public List<Customer> findAllCustomer() {
		return customerDao.findAllCustomer();
	}

	
	public void addCustomer(Customer customer) {
		customerDao.addCustomer(customer);
	}

	
	public void delCustomer(Customer customer) {
		customer = customerDao.findCustomerById(customer.getId());
		// if (c != null) {

		customerDao.delCustomer(customer);
		// }
	}
}
