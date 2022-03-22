<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp" >
	<jsp:param name = "title" value = "mypage"/>
</jsp:include>


<h1 align="center">카테고리 리스트</h1>
<div class="container" id="main" align="center">
<script type="text/javascript">
	function inputCheck(){
		if(!cat_inputForm.code.value){
			alert("카테고리 코드를 입력하세요!!!");
			cat_inputForm.code.focus();
			return;
		}
		if(!cat_inputForm.cname.value){
			alert("카테고리 이름을 입력하세요!!!");
			cat_inputForm.cname.focus();
			return;
		}
		document.cat_inputForm.submit();
	}
</script>
	
	<hr width=400>
	<h3>카테고리 등록</h3>
	<hr width=400>
	<br><br>
	<form action="CategoryInputLogic.do" method="post" name="cat_inputForm">
		<table border=1 width=500 class="table">
			<tr>
				<td>코드</td>
				<td><input type="text" name="code" maxlength=10></td>
			</tr>
			<tr>
				<td>카테고리 이름</td>
				<td><input type="text" name="cname"></td>
			</tr>
			<tr>
				<td colspan=2 align="center">
					<input type="button" value="등록" onclick="inputCheck();">
					<input type="reset" value ="취소">
				</td>
			</tr>
		</table>
	</form>

</div>
<jsp:include page = "/layout/footer.jsp" />