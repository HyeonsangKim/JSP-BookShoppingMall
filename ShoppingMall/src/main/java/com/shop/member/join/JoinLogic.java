package com.shop.member.join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.MemberDAO;
import com.shop.dto.MemberDTO;

public class JoinLogic implements Command{
	public void execute(HttpServletRequest request, HttpServletResponse response){
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		String id = request.getParameter("user_id");
		String password= request.getParameter("user_password");
		String nickname = request.getParameter("user_nickname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("user_email1") + "@" + request.getParameter("user_email2");
		String addr1= request.getParameter("postAddr1");
		String addr2= request.getParameter("postAddr2");
		String addr3= request.getParameter("postAddr3");		
		String addr = addr1+" "+addr2+" "+addr3;
		dto.setId(id);
		dto.setPassword(password);
		dto.setNickname(nickname);
		dto.setAddress(addr);
		dto.setPhone(phone);
		dto.setEmail(email);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setAddr3(addr3);
		
		boolean result = dao.insert(dto);
		request.setAttribute("result", result);
		
		
	}
}