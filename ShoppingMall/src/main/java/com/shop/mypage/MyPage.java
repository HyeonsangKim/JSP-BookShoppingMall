package com.shop.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.MemberDAO;
import com.shop.dto.MemberDTO;

public class MyPage implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("currentId");
		String password = request.getParameter("user_password");
		dto = dao.select(id,password);
		request.setAttribute("dto", dto);
	}
}
