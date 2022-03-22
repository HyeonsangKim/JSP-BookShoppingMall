package com.shop.member.join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.MemberDAO;



/**
 * Servlet implementation class JoinCheckId
 */
public class JoinCheckId implements Command{
	public void execute(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");

		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.isExistId(id);

		request.setAttribute("id", id);
		request.setAttribute("result", result);
	
	}

}
