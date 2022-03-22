package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.shop.dto.MemberDTO;

public class MemberDAO {
	private static MemberDAO dao;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	private static DataSource ds;
	static {
		try {
			System.out.println("start DBCP!");
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	// 싱글톤 패턴
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if (dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	
	private static void close(Connection con,PreparedStatement ps, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(con != null) {
				con.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void close(Connection con,PreparedStatement ps) {
		close(con,ps,null);
	}
	
	// 회원정보 삽입
	public boolean insert(MemberDTO dto) {
		boolean result = false;
		sql = "INSERT INTO member VALUES(?, ?, ?, ?,?, SYSDATE,?,?,?,?)";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPassword());
			ps.setString(3, dto.getNickname());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getEmail());
			ps.setString(6, dto.getAddress());
			ps.setString(7, dto.getAddr1());
			ps.setString(8, dto.getAddr2());
			ps.setString(9, dto.getAddr3());
			result = ps.executeUpdate() == 1;
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		return result;
		}
	
	//아이디 중복여부 확인
	public boolean isExistId(String id) {
		boolean exist = false;
		String sql = "SELECT * FROM MEMBER WHERE ID = ? ";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			exist = rs.next();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(con,ps,rs);
		}
		return exist;
	}
	//로그인
	public MemberDTO select(String id,String password) {
		MemberDTO dto = null;
		String sql="SELECT * FROM MEMBER WHERE ID=? AND PWD=?";
		try {
			con = ds.getConnection();
			ps= con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto= new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("pwd"));
				dto.setNickname(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setAddress(rs.getString("address"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setAddr3(rs.getString("addr3"));
			}
		}catch(Exception e) {
			close(con,ps,rs);
		}
		return dto;
	}
	public MemberDTO MemberSelect(String id) {
		MemberDTO dto = null;
		String sql="SELECT * FROM MEMBER WHERE ID=?";
		try {
			con = ds.getConnection();
			ps= con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto= new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("pwd"));
				dto.setNickname(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setAddress(rs.getString("address"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setAddr3(rs.getString("addr3"));
			}
		}catch(Exception e) {
			close(con,ps,rs);
		}
		return dto;
	}
	//회원 탈퇴
	public boolean delete(String id,String password) {
		boolean result = false;
		MemberDTO dto = null;
		String sql = "DELETE FROM MEMBER WHERE ID = ? AND PWD = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			result = 1 == ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		
		return result;
	}
	// 회원 정보 수정
	public boolean modify(MemberDTO dto) {
		int result = 0;
		String sql = "UPDATE MEMBER SET PWD= ?,name=?,email=?,phone=?,addr1=?,addr2=?,addr3=? WHERE id =?";
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, dto.getPassword());
			ps.setString(2,dto.getNickname());
			ps.setString(3, dto.getEmail());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getAddr1());
			ps.setString(6, dto.getAddr2());
			ps.setString(7, dto.getAddr3());
			ps.setString(8, dto.getId());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		
		return result == 1;
	}
	
	//전체 회원 정보 (어드민 페이지)
	public ArrayList<MemberDTO> getMemberList(){
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		MemberDTO dto = null;
		String tmpPassword;
		int passwordIndex = 0;
		String sql = "SELECT * FROM MEMBER";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new MemberDTO();
				tmpPassword= rs.getString("pwd");
				passwordIndex = tmpPassword.length() - 1;
				tmpPassword = tmpPassword.substring(0,1);
				while(passwordIndex>0) {
					tmpPassword += "*";
					--passwordIndex;
				}
				dto.setId(rs.getString("id"));
				dto.setPassword(tmpPassword);
				dto.setNickname(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setAddress(rs.getString("address"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setAddr3(rs.getString("addr3"));
				
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps,rs);
		}
		return list.isEmpty() ? null : list;
	}
	
	//회원 삭제
	public void Memberdelete(String id) {
		MemberDTO dto = null;
		String sql = "DELETE FROM MEMBER WHERE id = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			int n = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
	}
	
}
