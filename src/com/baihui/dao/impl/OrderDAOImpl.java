package com.baihui.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.baihui.dao.IOrderDAO;
import com.baihui.domain.Customer;
import com.baihui.domain.Order;

@Repository
public class OrderDAOImpl extends HibernateDaoSupport implements IOrderDAO {

		//	ע��sessionFactory
	@Autowired
	public void setSupperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// ��ѯ���еĶ��������ݿͻ�<�޷�ҳ>
	public List<Order> findByCustomer(Customer customer) {
		return (List<Order>) this.getHibernateTemplate().find("from Order o where o.customer=?", customer);
	}

	// ��ѯ���еĶ��������ݿͻ�<��ҳ>

	public List<Order> findOrderByCustomerPage(Customer c, int pageNum, int currentCount) {
		DetachedCriteria dc = DetachedCriteria.forClass(Order.class);
		dc.add(Restrictions.eq("customer", c));
		return (List<Order>) this.getHibernateTemplate().findByCriteria(dc, (pageNum - 1) * currentCount, currentCount);
	}

	// ��ȡ������

	public int findTotalCount() {
		return ((Long) this.getHibernateTemplate().find("select count(*) from Order").iterator().next()).intValue();
	}

	public void delOrder(Order order) {
		 this.getHibernateTemplate().delete(order);
	}

	public List<Order> findById(Order id) {
		// TODO Auto-generated method stub
		
		return   (List<Order>) this.getHibernateTemplate().find("from Order where orderNum=?", id.getOrderNum());
	}

}
