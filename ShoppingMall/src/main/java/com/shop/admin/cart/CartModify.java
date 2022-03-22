package com.shop.admin.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.CartDAO;

public class CartModify implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("pnum"));
		int pqty = Integer.parseInt(request.getParameter("pqty"));
		String user = request.getParameter("user");
		CartDAO cdao = CartDAO.getInstance();
		
		boolean result = cdao.modify(id,pqty);
		request.setAttribute("result", result);
		request.setAttribute("user", user);
	}

}
