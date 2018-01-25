package com.baihui.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.baihui.domain.Customer;
import com.baihui.service.ICustomerService;
import com.opensymphony.xwork2.ActionSupport;


@Controller
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerMultiAction extends ActionSupport {

	@Autowired
	private ICustomerService customerService;

	private File cusImg; // ���ڽ����ϴ����ļ�
	private String cusImgFileName;// ���������ϴ��ļ�����
	private String cusName;
	private String cusPhone;

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public File getCusImg() {
		return cusImg;
	}

	public void setCusImg(File cusImg) {
		this.cusImg = cusImg;
	}

	public String getCusImgFileName() {
		return cusImgFileName;
	}

	public void setCusImgFileName(String cusImgFileName) {
		this.cusImgFileName = cusImgFileName;
	}

	// ��ӿͻ���Ϣ
	@Action(value = "addCustomer", results = {
			@Result(name = "success", location = "findAllCustomer", type = "redirectAction"),
			@Result(name = "input", location = "/error.jsp") })
	public String addCustomer() {

		// �����ϴ��ļ������浽��������
		File destFile = new File(ServletActionContext.getServletContext().getRealPath("/upload"), cusImgFileName);
		try {
			FileUtils.copyFile(cusImg, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// �����ӿͻ�����
		Customer customer = new Customer();
		customer.setCusName(cusName);
		customer.setCusPhone(cusPhone);
		String cusImgsrc = ServletActionContext.getRequest().getContextPath() + "/upload/" + cusImgFileName; // /ssh-exam/upload/1.jpg
		customer.setCusImgsrc(cusImgsrc);

		customerService.addCustomer(customer);

		return SUCCESS;

	}
}
