package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.shop.dto.CategoryDTO;
import com.shop.dto.MemberDTO;

public class CategoryDAO {
	private static CategoryDAO dao;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
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
	private CategoryDAO() {}
	public static CategoryDAO getInstance() {
		if (dao == null) {
			dao = new CategoryDAO();
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
	
	public ArrayList<CategoryDTO> categoryList(){
		ArrayList<CategoryDTO> cdtos = new ArrayList<CategoryDTO>();
		String sql = "select * from category order by cnum desc";
		
		try {
			con = ds.getConnection();
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
				
			while(rs.next()) {
				String cnum = rs.getString("cnum");
				String code = rs.getString("code");
				String cname = rs.getString("cname");
				
				CategoryDTO cdto = new CategoryDTO(cnum, code, cname);
				cdtos.add(cdto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		return cdtos;
	}
	
	public boolean insertCat(String code,String cname) {
		boolean result = false;
		
		String sql = "insert into category values(category_seq.nextval,?,?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, cname);
			
			result = ps.executeUpdate() == 1;  
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		return result;
	}
	
	public boolean deleteCat(String id) {
		boolean result = false;
		MemberDTO dto = null;
		String sql = "DELETE FROM category WHERE cnum = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			result = 1 == ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		
		return result;
	}
	
	
}