package com.shop.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.BoardDAO;
import com.shop.dao.CommentDAO;
import com.shop.dto.BoardDTO;
import com.shop.dto.CommentDTO;

public class BoardReadLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = null;
		int boardNum = Integer.parseInt(request.getParameter("p"));
		
		
		CommentDAO commentDao = CommentDAO.getInstance();
		ArrayList<CommentDTO> commentList = commentDao.getList(boardNum);
		
		
		String attName = "brd_" + boardNum; // 새로고침시 조회수 증가를 막기위해 세션에 정보저장
		HttpSession session = request.getSession();
		
		
		
		if(session.getAttribute(attName) == null) {//햔재 세션이 이글을 본적이 없다면
			dao.updateHit(boardNum);
			session.setAttribute(attName, "Y");
		}
		dto = dao.getNotice(boardNum);
	
		request.setAttribute("brdDto",dto);
		request.setAttribute("commentList", commentList);
		
	}
}
