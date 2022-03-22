package com.shop.mypage;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.MemberDAO;
import com.shop.dto.MemberDTO;

public class AdminPage implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		MemberDAO dao = MemberDAO.getInstance();
		ArrayList<MemberDTO> list = dao.getMemberList();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("currentId");
		request.setAttribute("memberList",list);
		request.setAttribute("admin", id);
	}
}
