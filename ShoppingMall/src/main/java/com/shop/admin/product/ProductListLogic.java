package com.shop.admin.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.ProductDAO;
import com.shop.dto.ProductDTO;

public class ProductListLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		
		String field = "전체";
		if(field_!=null && !field_.equals("") && field != "전체") {
			field = field_;
		}
		
		String query = "";
		if(query_!=null && !query_.equals(""))
			query = query_;
		
		int page = 1;
		if(page_!=null && !page_.equals(""))
			page = Integer.parseInt(page_);
	
		
		ProductDAO dao = ProductDAO.getInstance();
		
		list = dao.productAll(field,query,page);
		
		
		request.setAttribute("productList",list);
		request.setAttribute("currentPage", page);
	}

}
