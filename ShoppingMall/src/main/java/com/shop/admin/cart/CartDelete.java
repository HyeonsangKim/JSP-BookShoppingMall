package com.shop.admin.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.CartDAO;

public class CartDelete implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("pnum"));
		
		CartDAO cdao = CartDAO.getInstance();
		
		boolean result = cdao.delete(id);
		request.setAttribute("result", result);
		
	}
}
