package com.shop.board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.CommentDAO;

public class CommentDelete implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		int c = Integer.parseInt(request.getParameter("c"));
		int p = Integer.parseInt(request.getParameter("p"));
		
		CommentDAO dao = CommentDAO.getInstance();
		boolean result = dao.delete(c);
		
		request.setAttribute("state", "delete");
		request.setAttribute("result", result);
		request.setAttribute("parentNum", p);
		}
}
