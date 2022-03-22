package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.shop.dto.BoardDTO;
import com.shop.dto.BoardViewDTO;

public class BoardDAO {
	private static BoardDAO dao;
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
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		if (dao == null) {
			dao = new BoardDAO();
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
	
	// 글작성
	public boolean insertNotice(BoardDTO dto){
		boolean result = false;	
		String sql = "INSERT INTO NOTICE(TITLE,CONTENT,WRITER_ID,NICKNAME,PUB,FILES) VALUES(?,?,?,?,?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getWriterId());
			ps.setString(4, dto.getNickname());
			ps.setString(5, dto.getFiles());
			
			result = ps.executeUpdate() == 1;               		

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps);
		}
		
		return result;
	}
	
	public boolean updateNotice(int id,String newTitle, String newContent) {
		boolean result = false;
		String sql = "UPDATE notice SET title = ? ,content = ? WHERE id = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, newTitle);
			ps.setString(2, newContent);
			ps.setInt(3, id);
			result = ps.executeUpdate() == 1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps,rs);
		}
		return result;
	}
	
	public boolean deleteNotice(int id) {
		boolean result = false;
		String sql = "DELETE FROM notice WHERE id = ?";
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
	public List<BoardViewDTO> getNoticeList(){
		
		return getNoticeList("title","",1);
	}
	public List<BoardViewDTO> getNoticeList(int page){
		
		return getNoticeList("title","",page);
	}
	//리스트 불러오기
	public List<BoardViewDTO> getNoticeList(String field/*title,writer_id*/,String query/*문자열*/,int page){
		
		List<BoardViewDTO> list = new ArrayList<>();
		
		String sql = "select * from (" + 
				"select rownum RN, N.* " + 
				"from (select * from BOARD_VIEW where " + field + " like ? order by regdate desc) N" + 
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
				
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				String nickname = rs.getString("NICKNAME");
				Date regdate = rs.getDate("REGDATE");	
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				//String content = rs.getString("CONTENT");
				int cmtCount = rs.getInt("CMT_COUNT");
				
				BoardViewDTO notice = new BoardViewDTO(
						id,
						title,
						writerId,
						nickname,
						regdate,
						hit,
						files,
						cmtCount
					);
				list.add(notice);
			}

		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		
		return list;
	}
	
	public int getNoticeCount() {
		return getNoticeCount("title","");
		
		
	}
	
	public int getNoticeCount(String field,String query) {
		
		int count = 0;
		
		String sql = "select count(ID) COUNT from (" + 
				"select rownum NUM, N.* " + 
				"from (select * from notice where " + field + " like ? order by regdate desc) N" + 
				") ";
	
		
		try {
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+query+"%");

			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				count = rs.getInt("count");
			
		 	rs.close();
		 	ps.close();
		    con.close();                		

		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		
		return count;
	}
	
	//글 읽기
	public BoardDTO getNotice(int id) {
		BoardDTO dto = null;
		String sql = "SELECT * FROM NOTICE WHERE ID =?";
		
		try {
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				String nickname = rs.getString("NICKNAME");
				Date regdate = rs.getDate("REGDATE");	
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
			
				dto = new BoardDTO(
						nid,
						title,
						writerId,
						nickname,
						regdate,
						hit,
						files,
						content
					);			
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps,rs);
		}
		return dto;
	}
	
	public int getTotalPages() {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM NOTICE";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				total = rs.getInt("COUNT(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps,rs);
		}
		return (total-1)/10 +1;
	}
	
	public boolean updateHit(int id) {
		boolean result = false;
		String sql = "UPDATE notice SET hit = hit + 1 WHERE id = ?";
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
