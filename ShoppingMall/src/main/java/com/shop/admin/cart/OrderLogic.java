package com.shop.admin.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;

public class OrderLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		int price = Integer.parseInt(request.getParameter("fullprice"));

		
		request.setAttribute("username", username);
		request.setAttribute("email", email);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("price", price);
	}
}
