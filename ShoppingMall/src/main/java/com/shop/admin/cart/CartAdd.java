package com.shop.admin.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.CartDAO;

public class CartAdd implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		String pnum = request.getParameter("pnum");
		String pqty = request.getParameter("pqty");
		String pname = request.getParameter("pname");
		String pimage = request.getParameter("pimage");
		String userId = request.getParameter("userId");
		String price = request.getParameter("price");
		String point = request.getParameter("point");
		
		pnum=pnum.trim();
		pqty=pqty.trim();
		CartDAO pdao = CartDAO.getInstance(); 
		
		pdao.insertCart(userId,Integer.parseInt(pnum),Integer.parseInt(pqty),pname,pimage,Integer.parseInt(price),Integer.parseInt(point));

	}
}
