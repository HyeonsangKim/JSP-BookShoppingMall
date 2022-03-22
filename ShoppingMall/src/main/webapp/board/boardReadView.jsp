<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:set var="dto" value = "${ requestScope.brdDto }" scope = "page"/>
<c:remove var = "brdDto" scope = "request" />

<% request.setCharacterEncoding("UTF-8"); %>
<script type="text/javascript"></script>


<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "${dto.title }" />
</jsp:include>

<h1 align="center">본문 보기</h1>
<div class="container" id="main" align="center">
<table border="1" width = "70%" class="table">
	<thead class="thead-dark">
	<tr>
		<th width = "15%">제목</th>
		<th width = "55%">
			${dto.title }
		</th>
		<th width="15%">조회수</th>
		<th>${dto.hit }</th>
	</tr>
	</thead>
	<tr>
		<td colspan="4" height="200" valign ="top">
			${dto.content }
		</td>
	</tr>
	<tr>
		<td colspan = "4" align = "center">
			<input type = "button" value= "목록으로" onclick = "location.href='/ShoppingMall/board/BoardList.do?p=1'">
			<c:if test = "${sessionScope.currentId == dto.writerId }">
				<input type ="button" value= "수정" onclick = "location.href='/ShoppingMall/board/boardUpdateView.do?p=${dto.id}'">
				<input type ="button" value= "삭제" onclick = "location.href='/ShoppingMall/board/BoardDelete.do?p=${dto.id}'">
			</c:if>
		</td>
	</tr>
</table>
<c:if test="${currentId != null }">	
<form action = "CommentWrite.do" method = "post">
<table border = "1" class="table">
<input type = "hidden" name= "parent_id" value = "${dto.id }">
	<tr>
		<th align="center">${sessionScope.currentNickname }</th>
		<td>
			<textarea name = "user_comment" rows="4" cols="100" placeholder = "댓글을 남겨주세요!" required></textarea>
		</td>
		<td><input type = "submit" value = "댓글 달기"></td>
	</tr>
	</table>
</form> 
</c:if>

<!-- 대댓글 달기 -->
<c:forEach var = "commentDto" items = "${requestScope.commentList }">
	<table class="table" id="Comment.${commentDto.id }" style="display:block;">
		<tr>
			
			<td width="20%" ><c:forEach begin="1" end="${commentDto.indent }">ㄴ</c:forEach> ${commentDto.writerId }</td>
			<td width="70%" >
				
				${commentDto.content }
				<c:if test="${currentId != null }">	
				<c:if test="${commentDto.step == 0}">
				&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="doDisplay(${commentDto.id})">[답글]</a>
				&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test = "${sessionScope.currentNickname == commentDto.writerId }">
					<a href="CommentDelete.do?p=${dto.id }&c=${commentDto.id}">삭제</a>
					<a href="#" onclick="doModify(${commentDto.id})">수정</a>
				</c:if>
				</c:if>
			</td>
			<td width="10%">${commentDto.regdate }</td>
		</tr>
		</table>
		
		<div id="myDIV.${commentDto.id }" style="display:none;">
			<form action = "ReComment.do" method = "post">
			<input type = "hidden" name= "parent_id" value = "${dto.id }">
			<input type = "hidden" name= "parent_comment" value = "${commentDto.id }">
			<input type="hidden" name="gGroup" value="${replyView.gGroup }">
			<input type="hidden" name="bStep" value="${replyView.bStep }">
			<input type="hidden" name="bIndent" value="${replyView.bIndent }">
			<table border = "1" class="table" >
				<tr>
					<th>${sessionScope.currentNickname }</th>
					<td>
						<textarea name = "comment_reply" rows="4" cols="100" placeholder = "댓글을 남겨주세요!" required></textarea>
					</td>
					<td><input type = "submit" value = "댓글 달기"></td>
				</tr>
				</table>
			</form> 
	</div>
	
	<div id="Modify.${commentDto.id }" style="display:none;">
			<form action = "CommentModify.do" method = "post">
			<input type = "hidden" name= "parent_id" value = "${dto.id }">
			<input type = "hidden" name= "comment_id" value = "${commentDto.id }">
			<table border = "1" class="table">
				<tr>
					<th >${sessionScope.currentNickname }</th>
					<td >
						<textarea name = "comment_reply" rows="4" cols="100" required>${commentDto.content }</textarea>
					</td>
					<td><input type = "submit" value = "수정하기"></td>
				</tr>
				</table>
			</form> 
	</div>

	</c:forEach>
</div>
<script>
function doDisplay(id){
	var con = document.getElementById("myDIV." + id);
	if(con.style.display=='none'){
		con.style.display = 'block';
	}else{
		con.style.display = 'none';
	}
}
</script>

<script>
function doModify(id){
	var con = document.getElementById("Modify." + id);
	var con2 = document.getElementById("Comment."+id);
	if(con.style.display=='none'){
		con.style.display = 'block';
	}else{
		con.style.display = 'none';
	}
	if(con2.style.display=='none'){
		con.style.display = 'block';
	}else{
		con2.style.display = 'none';
	}
}
</script>

<jsp:include page="/layout/footer.jsp"/>