package com.shop.admin.cart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.CartDAO;
import com.shop.dao.ProductDAO;
import com.shop.dto.CartDTO;
import com.shop.dto.ProductDTO;

public class CartListLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		String user = request.getParameter("user");
		ProductDAO pdao = ProductDAO.getInstance();
		CartDAO cdao = CartDAO.getInstance();

		cartList = cdao.cartList(user);

		request.setAttribute("cartList",cartList);
	}
}
