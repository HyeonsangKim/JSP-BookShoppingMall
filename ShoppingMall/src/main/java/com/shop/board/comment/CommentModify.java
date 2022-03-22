package com.shop.board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.CommentDAO;
import com.shop.dto.CommentDTO;

public class CommentModify implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		CommentDAO dao = CommentDAO.getInstance();
		
		int noticeId = Integer.parseInt(request.getParameter("parent_id"));
		int Id = Integer.parseInt(request.getParameter("comment_id"));
		String content = request.getParameter("comment_reply");
		
		boolean result = dao.modify(Id,content);
		request.setAttribute("result", result);
		request.setAttribute("state", "write");
		request.setAttribute("parentNum", noticeId);
	}

}
