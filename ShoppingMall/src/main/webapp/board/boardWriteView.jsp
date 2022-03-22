<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "Board List" />
</jsp:include>
<h1 align="center">글 쓰기</h1>
<div class="container" id="main" align="center">
<form action = "BoardWrite.do" method = "post">
	<table border="1" class="table">
		
		<tr>
			<th>제목</th>
			<td align = "center">
				<input type ="text" name="board_title" size="100" placeholder = "글 제목을 입력하세요." required>
			</td>
		</tr>
		<tr>
			<td colspan="2" align = "center">
				<textarea name= "board_content" rows="20" cols="120"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan ="2" align="center">
				<input type = "submit" value="글쓰기">
				<input type = "reset" value="초기화">
			</td>
		</tr>
	</table>
</form>
</div>
<jsp:include page="/layout/footer.jsp"/>