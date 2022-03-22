package com.shop.board;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.BoardDAO;
import com.shop.dao.MemberDAO;
import com.shop.dto.BoardViewDTO;
import com.shop.dto.MemberDTO;

public class BoardLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_!=null && !field_.equals(""))
			field = field_;
		
		String query = "";
		if(query_!=null && !query_.equals(""))
			query = query_;
		
		int page = 1;
		if(page_!=null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardViewDTO> list = dao.getNoticeList(field,query,page);
		int count = dao.getNoticeCount(field,query);
		int totalpages = dao.getTotalPages();
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("totalPages", totalpages);
		request.setAttribute("currentPage", page);
	}
}