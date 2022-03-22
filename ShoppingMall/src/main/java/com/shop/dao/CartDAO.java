package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.shop.dto.CartDTO;
import com.shop.dto.ProductDTO;

public class CartDAO {
	private static CartDAO dao;
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
	private CartDAO() {}
	public static CartDAO getInstance() {
		if (dao == null) {
			dao = new CartDAO();
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
	
	public List<CartDTO> cartList(String user){
		ArrayList<CartDTO> pdtos = new ArrayList<>();
			
		String sql = "select * from cart where user_id =? order by amount desc";
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery(); 
			
			while(rs.next()){
				
				int cart_id = rs.getInt("cart_id");
				String user_id = rs.getString("user_id");
				int product_id = rs.getInt("product_id");
				int amount = rs.getInt("amount");
				String pname = rs.getString("product_name");
				String pimage = rs.getString("product_image");
				int price = rs.getInt("price");
				int point = rs.getInt("point");
				
				CartDTO pdto = new CartDTO(cart_id,user_id,product_id,amount,pname,pimage,price,point);
				pdtos.add(pdto);
			}
			return pdtos;
	
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return pdtos;
		
	}
	public int prodNum(String user) {
		int prodNum = 0;
		String sql ="select product_id from cart where user_id = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			
			if(rs.next())
				prodNum = rs.getInt("product_id");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps,rs);
		}
		return prodNum;
	}
	
	public boolean insertCart(String userId,int pnum,int amount,String pname,String pimage,int price,int point) {
		boolean result = false;
		
		try {
			con = ds.getConnection();
			String sql = "insert into cart values(cart_seq.nextval,?,?,?,?,?,?,?)";
			
			ps = con.prepareStatement(sql);
			
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setInt(2, pnum);
			ps.setInt(3, amount);
			ps.setString(4,pname);
			ps.setString(5,pimage);
			ps.setInt(6, price);
			ps.setInt(7, point);
			
			result = ps.executeUpdate() == 1; 
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		
		return result;
	}
	
	public boolean modify(int id, int pqty) {
		boolean result = false;
		
		try {
			con = ds.getConnection();
			String sql = "UPDATE cart SET amount = ? WHERE cart_id = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, pqty);
			ps.setInt(2, id);
			
			result = ps.executeUpdate() == 1;
		}catch(Exception e) {
			
		}finally {
			close(con,ps);
		}
		return result;
	}
	
	public boolean delete(int id) {
		boolean result = false;
		
		try {
			con = ds.getConnection();
			String sql = "DELETE FROM cart WHERE cart_id = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			result = ps.executeUpdate() == 1;
		}catch(Exception e) {
			
		}finally {
			close(con,ps);
		}
		return result;
	}
}
