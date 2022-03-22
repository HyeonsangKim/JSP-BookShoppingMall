package com.shop.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.BoardDAO;

public class BoardUpdateLogic implements Command {
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		String newTitle = null;
		String newContent = null;
		BoardDAO dao = BoardDAO.getInstance();
		int id = 0;
		boolean result = false;
		
		newTitle = request.getParameter("board_title");
		newContent = request.getParameter("board_content");
		id = Integer.parseInt(request.getParameter("board_id"));
		result = dao.updateNotice(id, newTitle, newContent);
		
		request.setAttribute("state", "update");
		request.setAttribute("result", result);
		
	}
}
