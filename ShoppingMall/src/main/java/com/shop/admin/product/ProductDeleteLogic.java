package com.shop.admin.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.ProductDAO;

public class ProductDeleteLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDAO dao = ProductDAO.getInstance();
		boolean result = dao.deleteProduct(id);
		
		request.setAttribute("state", "delete");
		request.setAttribute("result", result);
	}

}
