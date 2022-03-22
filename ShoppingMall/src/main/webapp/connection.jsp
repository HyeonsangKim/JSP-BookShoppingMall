<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.InitialContext, javax.naming.Context" %>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	Context context = new InitialContext();
	DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet rset = stmt.executeQuery("SELECT * FROM USER_PRACTICE");
	while(rset.next()){
		//out.println("VERSION"+rset.getString("version()"));
		
	}
	rset.close();
	stmt.close();
	conn.close();
	context.close();
%>
</body>
</html>