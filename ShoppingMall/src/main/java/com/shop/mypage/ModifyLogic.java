package com.shop.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.MemberDAO;
import com.shop.dto.MemberDTO;

public class ModifyLogic implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		String password;
		String repassword;
		String name;
		String email;
		String phone;
		String id;
		String addr1;
		String addr2;
		String addr3;
		MemberDTO dto= null;
		MemberDAO dao = MemberDAO.getInstance();
		HttpSession session = null;
		
		password=request.getParameter("user_password");
		repassword = request.getParameter("user_repassword");
		
		if(!password.equals(repassword)) {
			request.setAttribute("result",false);
		}else {
			session = request.getSession();
			id = request.getParameter("user_id");
			name = request.getParameter("user_nickname");
			email=request.getParameter("user_email");
			phone = request.getParameter("user_phone");
			addr1= request.getParameter("postAddr1");
			addr2= request.getParameter("postAddr2");
			addr3= request.getParameter("postAddr3");
			dto = new MemberDTO();
			dto.setPassword(password);
			dto.setNickname(name);
			dto.setEmail(email);
			dto.setPhone(phone);
			dto.setAddr1(addr1);
			dto.setAddr2(addr2);
			dto.setAddr3(addr3);
			dto.setId(id);
			if(dao.modify(dto)) {
				request.setAttribute("result", true);
				session.setAttribute("currentNickname", dto.getNickname());
			}else {
				request.setAttribute("result", false);
			}
		}
	}
}
