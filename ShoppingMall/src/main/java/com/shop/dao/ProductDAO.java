package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.shop.dto.ProductDTO;

public class ProductDAO {
	private static ProductDAO dao;
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
	private ProductDAO() {}
	public static ProductDAO getInstance() {
		if (dao == null) {
			dao = new ProductDAO();
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
	public List<ProductDTO> product(int product){
		ArrayList<ProductDTO> pdtos = new ArrayList<>();
			
		String sql = "select * from product where pnum =?";
		try{
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, product);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				String pnum = rs.getString("pnum");
				String pname = rs.getString("pname");
				String pcategory_fk = rs.getString("pcategory_fk");
				String pcompany = rs.getString("pcompany");
				String pimage = rs.getString("pimage");
				int pqty = rs.getInt("pqty");
				int price = rs.getInt("price");
				String pspec = rs.getString("pspec");
				String pcontents = rs.getString("pcontents");
				int point = rs.getInt("point");
				String d = rs.getString("pinputdate");
				String pinputDate = d.toString();
				
				ProductDTO pdto = new ProductDTO(pnum,pname,pcategory_fk,pcompany,pimage,pqty,price,pspec,pcontents,point,pinputDate, 0,0,0);
				pdtos.add(pdto);
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return pdtos;
		
	}
	//리스트 불러오기
	public List<ProductDTO> productAll(String field,String query/*문자열*/,int page){
		
		ArrayList<ProductDTO> pdtos = new ArrayList<>();
		
		if(field=="전체") {
			
			String sql = "select * from (" + 
					"select rownum RN, N.* " + 
					"from (select C.* from " +
					"(select * from product)C " +
					"where pname like ? order by pinputdate desc) N" + 
					") " + 
					"WHERE RN BETWEEN ? AND ?";
			
			try {
				con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, "%"+query+"%");
				ps.setInt(2, 1+(page-1)*10);
				ps.setInt(3,page*10);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					
					String pnum = rs.getString("pnum");
					String pname = rs.getString("pname");
					String pcategory_fk = rs.getString("pcategory_fk");
					String pcompany = rs.getString("pcompany");
					String pimage = rs.getString("pimage");
					int pqty = rs.getInt("pqty");
					int price = rs.getInt("price");
					String pspec = rs.getString("pspec");
					String pcontents = rs.getString("pcontents");
					int point = rs.getInt("point");
					String d = rs.getString("pinputdate");
					String pinputDate = d.toString();
					
					ProductDTO pdto = new ProductDTO(pnum,pname,pcategory_fk,pcompany,pimage,pqty,price,pspec,pcontents,point,pinputDate, 0,0,0);
					pdtos.add(pdto);
				}
	
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				close(con,ps,rs);
			}
			return pdtos;
		}else {
			String sql = "select * from (" + 
					"select rownum RN, N.* " + 
					"from (select C.* from " +
					"(select * from product where pcategory_fk = ?)C " +
					"where pname like ? order by pinputdate desc) N" + 
					") " + 
					"WHERE RN BETWEEN ? AND ?";
		
			try {
				con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, field);
				ps.setString(2, "%"+query+"%");
				ps.setInt(3, 1+(page-1)*10);
				ps.setInt(4,page*10);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					
					String pnum = rs.getString(1);
					String pname = rs.getString(2);
					String pcategory_fk = rs.getString(3);
					String pcompany = rs.getString(4);
					String pimage = rs.getString(5);
					int pqty = rs.getInt(6);
					int price = rs.getInt(7);
					String pspec = rs.getString(8);
					String pcontents = rs.getString(9);
					int point = rs.getInt(10);
					String d = rs.getString(11);
					String pinputDate = d.toString();
					
					ProductDTO pdto = new ProductDTO(pnum,pname,pcategory_fk,pcompany,pimage,pqty,price,pspec,pcontents,point,pinputDate, 0,0,0);
					pdtos.add(pdto);
				}
	
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				close(con,ps,rs);
			}
			return pdtos;
		}
		
	}
	
	public List<ProductDTO> productAll() {
		String sql= "select * from product order by pnum desc";
		ArrayList<ProductDTO> pdtos = new ArrayList<>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			pdtos = makeArrayList(rs);
			return pdtos;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		return pdtos;
	}
	
	public ArrayList<ProductDTO> makeArrayList(ResultSet rs) throws SQLException{
		ArrayList<ProductDTO> pdtos = new ArrayList<ProductDTO>();
		
		while(rs.next()) {
			String pnum = rs.getString(1);
			String pname = rs.getString(2);
			String pcategory_fk = rs.getString(3);
			String pcompany = rs.getString(4);
			String pimage = rs.getString(5);
			int pqty = rs.getInt(6);
			int price = rs.getInt(7);
			String pspec = rs.getString(8);
			String pcontents = rs.getString(9);
			int point = rs.getInt(10);
			String d = rs.getString(11);
			String pinputDate = d.toString();
			
			ProductDTO pdto = new ProductDTO(pnum,pname,pcategory_fk,pcompany,pimage,pqty,price,pspec,pcontents,point,pinputDate, 0,0,0);
			pdtos.add(pdto);
		}//while End
		
		return pdtos;
	}

	public boolean registerProduct(MultipartRequest multi) {
		boolean result = false;
		
		try {
			con = ds.getConnection();
			String sql = "insert into product values(product_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate)";
			
			ps = con.prepareStatement(sql);
			
			String pname = multi.getParameter("pname");
			String pcategory_fk = multi.getParameter("pcategory_fk");
			String pcompany = multi.getParameter("pcompany");
			String pimage = multi.getFilesystemName("pimage");
			String pqty = multi.getParameter("pqty");
			String price = multi.getParameter("price");
			String pspec = multi.getParameter("pspec");
			String pcontents = multi.getParameter("pcontents");
			String point = multi.getParameter("point");
			
			ps.setString(1, pname);
			ps.setString(2, pcategory_fk);
			ps.setString(3, pcompany);
			ps.setString(4, pimage);
			ps.setString(5, pqty);
			ps.setString(6, price);
			ps.setString(7, pspec);
			ps.setString(8, pcontents);
			ps.setString(9, point);
			
			result = 1 == ps.executeUpdate();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		
		return result;
	}
	
	public boolean deleteProduct(int id) {
		boolean result = false;
		String sql = "DELETE FROM product WHERE pnum = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate() == 1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps,rs);
		}
		return result;
	}
	
	
}
