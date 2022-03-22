package com.shop.admin.category;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.CategoryDAO;
import com.shop.dto.CategoryDTO;

public class CategoryListLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		CategoryDAO dao = CategoryDAO.getInstance();
		ArrayList<CategoryDTO> list = dao.categoryList();
		
		request.setAttribute("categoryList",list);
		
		
	}

}
