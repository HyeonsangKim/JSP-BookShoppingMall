package com.shop.member.join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.MemberDAO;

public class SignOut implements Command{
	public void execute(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");
		HttpSession session = request.getSession();
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.delete(id, password);
		
		request.setAttribute("result", result);
		
		session.setAttribute("currentNickname", null);
		session.setAttribute("currentId",null);
		
		
	}
}
