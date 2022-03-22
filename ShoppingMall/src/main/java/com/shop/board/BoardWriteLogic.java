package com.shop.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.BoardDAO;
import com.shop.dto.BoardDTO;

public class BoardWriteLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		String writerId=null;
		String nickname= null;
		BoardDTO dto = null;
		BoardDAO dao = BoardDAO.getInstance();
		boolean result = false;
		HttpSession session = request.getSession();
		
		writerId = (String)session.getAttribute("currentId");
		nickname=(String)session.getAttribute("currentNickname");
		
		if(writerId!=null) {
			dto= new BoardDTO();
			dto.setTitle(request.getParameter("board_title"));
			dto.setContent(request.getParameter("board_content"));
			dto.setWriterId(writerId);
			dto.setNickname(nickname);
			
			result = dao.insertNotice(dto);
		}
		request.setAttribute("state", "write");
		request.setAttribute("result", result);
		
	}

}
