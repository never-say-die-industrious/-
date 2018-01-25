package com.baihui.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baihui.domain.Customer;
import com.baihui.domain.Order;
import com.baihui.domain.PageBean;
import com.baihui.service.IOrderService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@Namespace("/order")
@ParentPackage("struts-default")
public class OrderAction extends ActionSupport implements ModelDriven<Order>  {

	@Autowired
	private IOrderService orderService;
	 private Order order = new Order();

	    public Order getModel() {
	        return order;
	    }
	@Action("findOrder")
	public void findOrder() {
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		// 1.得到customerId
		Integer customerId = Integer.parseInt((ServletActionContext.getRequest().getParameter("customerId")));
		System.out.printf("*******************************customerId*************************************8");
		// 2.得到当前页码
		int pageNum = Integer.parseInt((ServletActionContext.getRequest().getParameter("pageNum")));
		// 3.每页显示条数
		int currentCount = Integer.parseInt((ServletActionContext.getRequest().getParameter("currentCount")));

		// 2.查询客户对应的订单
		// List<Order> orders = orderService.findOrderByCustomer(customerId);

		PageBean<Order> pageBean = orderService.findOrderByCustomerPage(customerId, pageNum, currentCount);

		// 3.将orders转换成json
				// 对json数据进行过滤，并且取消循环引用
//		String json = JSONArray.toJSONString(json);
//		System.out.println(json);
//		// 4.将json响应到浏览器
//			ServletActionContext.getResponse().getWriter().write(json);
		PropertyFilter filter = new PropertyFilter() {

//			@Override
			public boolean apply(Object arg0, String fieldName, Object arg2) {
				if ("cusPhone".equalsIgnoreCase(fieldName)) {
					return false;
				}
				if ("id".equalsIgnoreCase(fieldName)) {
					return false;
				}
				if ("orders".equalsIgnoreCase(fieldName)) {
					return false;
				}

				return true;
			}
		};
		String json = JSONArray.toJSONString(pageBean, filter, SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(json);
		// 4.将json响应到浏览器
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
//	// 删除客户操作
//	@Action(value = "delOrder")
//	public void delOrder() {
//
////		int orderNum = Integer.parseInt((ServletActionContext.getRequest().getParameter("orderNum")));
//		String orderNum=ServletActionContext.getRequest().getParameter("orderNum");
//		Integer customerId = Integer.parseInt((ServletActionContext.getRequest().getParameter("customerId")));
//		System.out.println("******************"+orderNum+"************************");
//		orderService.delOrder(orderNum);
////		this.findOrder();
//
//	}

	
	 //删除客户
//  @Action(value = "delOrder",results = {@Result(name=SUCCESS,location = "findOrder",type = "redirectAction")})
  @Action(value = "delOrder")
  //跳到用户list @Action(value = "delOrder",results = {@Result(name=SUCCESS,location = "findAllCustomer",type = "redirectAction")})
  public void delOrder(){
      //ServletActionContext.getRequest().getParameter("id")
      orderService.delOrder(order);
  }

	
}
