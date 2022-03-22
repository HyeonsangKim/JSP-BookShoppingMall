package com.shop.admin.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.CategoryDAO;

public class CategoryDeleteLogic implements Command{
	public void execute(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		CategoryDAO cdao = CategoryDAO.getInstance();
		
		boolean result = cdao.deleteCat(id);
		
		request.setAttribute("result", result);
		request.setAttribute("state", "delete");
	}

}
