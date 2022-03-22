package com.shop.board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.CommentDAO;
import com.shop.dto.CommentDTO;

public class CommentWrite implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		CommentDAO dao = CommentDAO.getInstance();
		
		int noticeId = Integer.parseInt(request.getParameter("parent_id"));
		String content = request.getParameter("user_comment");

		String writerId = (String)session.getAttribute("currentNickname");
		
		boolean result = dao.reply(content,writerId,noticeId);
		request.setAttribute("result", result);
		request.setAttribute("state", "write");
		request.setAttribute("parentNum", noticeId);
	}
}
