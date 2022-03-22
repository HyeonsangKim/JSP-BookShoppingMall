package com.shop.admin.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.CategoryDAO;

public class CategoryInputLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		String code = request.getParameter("code");
		String cname = request.getParameter("cname");
		CategoryDAO cdao = CategoryDAO.getInstance();
		
		boolean result = cdao.insertCat(code, cname);
		request.setAttribute("result", result);
		request.setAttribute("state", "insert");
	}
}
