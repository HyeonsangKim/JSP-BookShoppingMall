package com.shop.member.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.MemberDAO;
import com.shop.dto.MemberDTO;

public class Login implements Command{
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("user_id");
		String password=  request.getParameter("user_password");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = dao.select(id, password);
		
		boolean rememberMe = Boolean.parseBoolean(request.getParameter("remember_me"));
		if(rememberMe) {
			Cookie cookie = new Cookie("rememberId",id);
			cookie.setMaxAge(60*60*24*365); // 1년
			cookie.setPath("/");
			response.addCookie(cookie);
		}else {
			Cookie cookie = new Cookie("rememberId",null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		if(dto!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("currentNickname", dto.getNickname());
			session.setAttribute("currentId",id);
		}
		
	}
}