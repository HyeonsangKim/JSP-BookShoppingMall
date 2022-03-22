package com.shop.admin.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.CartDAO;
import com.shop.dao.MemberDAO;
import com.shop.dto.MemberDTO;

public class OrderPage implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		MemberDTO dto = null;
		String user = request.getParameter("user");
		int price = Integer.parseInt(request.getParameter("price"));
		MemberDAO mdao = MemberDAO.getInstance();
		dto = mdao.MemberSelect(user);
		request.setAttribute("dto", dto);
		request.setAttribute("price", price);
	}
}
