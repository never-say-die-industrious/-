package com.baihui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baihui.dao.ICustomerDAO;
import com.baihui.dao.IOrderDAO;
import com.baihui.domain.Customer;
import com.baihui.domain.Order;
import com.baihui.domain.PageBean;
import com.baihui.service.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private ICustomerDAO customerDao;

	@Autowired
	private IOrderDAO orderDao;


	public List<Order> findOrderByCustomer(Integer customerId) {
		// 1.根据customerId查询customer对象
		Customer customer = customerDao.findCustomerById(customerId);

		// 2.根据customer对象查询订单
		return orderDao.findByCustomer(customer);

	}

	
	public PageBean<Order> findOrderByCustomerPage(Integer customerId, int pageNum, int currentCount) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setCurrentCount(currentCount); // 封装每页条数
		pageBean.setPageNum(pageNum);//封装当前页码
		int totalCount = orderDao.findTotalCount();
		pageBean.setTotalCount(totalCount);

		// 鎬婚〉鏁�
		int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);

		pageBean.setTotalPage(totalPage);
		
		//鏌ヨ褰撳墠椤电殑鎵�湁淇℃伅
		Customer c=customerDao.findCustomerById(customerId);
		List<Order> orders=orderDao.findOrderByCustomerPage(c,pageNum,currentCount);
		pageBean.setCurrentContent(orders);

		return pageBean;

	}


	public void delOrder(Order id) {
		// TODO Auto-generated method stub
		List<Order> order=orderDao.findById(id);
		orderDao.delOrder(order.get(0));
		
	}

}
