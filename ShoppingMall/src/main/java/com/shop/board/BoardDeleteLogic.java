package com.shop.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.BoardDAO;

public class BoardDeleteLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("p"));
		BoardDAO dao = BoardDAO.getInstance();
		boolean result = dao.deleteNotice(id);
		
		request.setAttribute("state", "delete");
		request.setAttribute("result", result);
	}
}
