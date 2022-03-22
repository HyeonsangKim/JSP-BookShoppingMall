package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.shop.dto.CommentDTO;

public class CommentDAO {
	private static CommentDAO dao;
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
	private CommentDAO() {}
	public static CommentDAO getInstance() {
		if (dao == null) {
			dao = new CommentDAO();
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

	
	public CommentDTO replyView(int noticeId) {	
		CommentDTO dto = null;
		try {
			con = ds.getConnection();
			String sql = "select * from comments where NOTICE_ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, noticeId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
		
				int id =rs.getInt("id");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				String writerId = rs.getString("WRITER_ID");

				int ggroup = rs.getInt("ggroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");
				
				dto = new CommentDTO(id,content,regdate,writerId,noticeId,ggroup,bstep,bindent);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		return dto;
	} // replyView
	
	public boolean reply(String content,String writerId,int noticeId) {
		boolean result = false;
		
		try {
			con = ds.getConnection();
			String sql = "insert into comments(id,content,WRITER_ID,NOTICE_ID,GGroup,BStep,BIndent)"
					+ "values(comment_seq.nextval,?,?,?,comment_seq.currval,0,0)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, content);
			ps.setString(2, writerId);
			ps.setInt(3, noticeId);
			
			result = ps.executeUpdate() == 1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		return result;
	}
	
	public int getGroup(int noticeId) {
		int result = 0;
		
		try {
			con = ds.getConnection();
			String sql = "select gGroup from comments where NOTICE_ID= ?";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, noticeId);
			rs = ps.executeQuery();
			
			if(rs.next())
				result = rs.getInt("gGroup");
						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		return result;
	}
	
	public int getStep(int noticeId) {
		int result = 0;
		
		try {
			con = ds.getConnection();
			String sql = "select BSTEP from comments where NOTICE_ID= ?";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, noticeId);
			rs = ps.executeQuery();
			
			if(rs.next())
				result = rs.getInt("BSTEP");
						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		return result;
	}
	
	public int getIndent(int noticeId) {
		int result = 0;
		
		try {
			con = ds.getConnection();
			String sql = "select BINDENT from comments where NOTICE_ID= ?";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, noticeId);
			rs = ps.executeQuery();
			
			if(rs.next())
				result = rs.getInt("BINDENT");
						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		return result;
	}
	//대댓글
	public boolean reply(String content,String writerId,int noticeId,int gGroup,int bStep,int bIndent) {
		replyForm(gGroup, bStep);
		boolean result = false;
		
		try {
			con = ds.getConnection();
			String sql = "insert into comments(content,WRITER_ID,NOTICE_ID,GGroup,BStep,BIndent)"
					+ "values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, content);
			ps.setString(2, writerId);
			ps.setInt(3, noticeId);
			ps.setInt(4, gGroup);
			ps.setInt(5, bStep + 1);
			ps.setInt(6, bIndent+1);
			
			result = ps.executeUpdate() == 1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		return result;
	} //reply
	
	//대댓글 구현
	private void replyForm(int gGroup,int bStep) {
		
		try {
			con = ds.getConnection();
			String sql = "update comments set BStep = BStep+1 where GGroup = ? and BStep > ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,gGroup);
			ps.setInt(2,bStep);
			
			int n = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
	}//replyform


	// 댓글 목록
	public ArrayList<CommentDTO> getList(int parentNum) {
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		String sql = "SELECT * FROM COMMENTS WHERE NOTICE_ID = ? ORDER BY GGROUP asc, regdate asc" ;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, parentNum);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				String writerId = rs.getString("WRITER_ID");
				int noticeId = rs.getInt("NOTICE_ID");
				int nGroup = rs.getInt("GGroup");
				int step = rs.getInt("BStep");
				int indent = rs.getInt("BIndent");
				
				CommentDTO dto = new CommentDTO(id,content,regdate,writerId,noticeId,nGroup,step,indent);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list.isEmpty() ? null : list;
	}

	//댓글 삭제
	public boolean delete(int id) {
		boolean result = false;
		try {
			con = ds.getConnection();
			String sql = "delete from comments where id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			result = ps.executeUpdate() == 1;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps);
		}
		return result;
	}//delete
	
	//수정
	public boolean modify(int id,String Content) {
		boolean result = false;
		
		try {
			con = ds.getConnection();
			String sql = "update comments set content =? where id =?";
			ps = con.prepareStatement(sql);
			ps.setString(1, Content);
			ps.setInt(2, id);
			
			result = ps.executeUpdate() == 1;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps);
		}
		return result;
	}//modift
}
