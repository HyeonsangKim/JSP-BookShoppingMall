package com.shop.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.MemberDAO;

public class MemberDelete implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		MemberDAO dao = MemberDAO.getInstance();
		dao.Memberdelete(id);
	}
}
