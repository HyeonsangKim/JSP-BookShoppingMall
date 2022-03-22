package com.shop.board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.CommentDAO;
import com.shop.dto.CommentDTO;

public class ReComment implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
	int id = Integer.parseInt(request.getParameter("parent_comment"));
	CommentDAO dao = CommentDAO.getInstance();
	CommentDTO dto = dao.replyView(id);
	
	request.setAttribute("replyView", dto);
	
	//request.setAttribute("commentList", dto);
	}
}
