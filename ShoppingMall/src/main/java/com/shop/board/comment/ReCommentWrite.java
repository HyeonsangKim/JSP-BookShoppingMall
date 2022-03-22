package com.shop.board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.CommentDAO;
import com.shop.dto.CommentDTO;

public class ReCommentWrite implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		CommentDAO dao = CommentDAO.getInstance();
		CommentDTO dto = new CommentDTO();
		
		int noticeId = Integer.parseInt(request.getParameter("parent_id"));
		String content = request.getParameter("comment_reply");
		String writerId = (String)session.getAttribute("currentNickname");
		int gGroup = Integer.parseInt(request.getParameter("parent_comment"));
		int bStep = dao.getStep(noticeId);
		int bIndent= dao.getIndent(noticeId);
		
		boolean result = dao.reply(content,writerId,noticeId,gGroup,bStep,bIndent);
		request.setAttribute("result", result);
		request.setAttribute("state", "write");
		request.setAttribute("parentNum", noticeId);
	}
}
