<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Product" />
</jsp:include>
<h1 align="center">상품 등록</h1>
<div class="container" id="main" align="center">
	<form action="ProductWrite.do" method="post" enctype="multipart/form-data">
		<table border="1" class="table">

			<tr>
				<th>상품이름</th>
				<td ><input type="text" name="pname"
					size="100" placeholder="글 제목을 입력하세요." required></td>
			</tr>
			<tr>
				<th >작가</th>
				<td><input type="text" name="pcompany"></td>
			</tr>
			<tr>
				<th >상품수량</th>
				<td><input type="text" name="pqty" maxlength="8"></td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td><input type="text" name="price" maxlength="8"></td>
			</tr>
			<tr>
				<th>상품</th>
				<td><select name="pspec">
						<option value="none" selected>일반</option>
						<option value="hit">인기</option>
						<option value="new">최신</option>
						<option value="recommand">추천</option>
				</select></td>
			</tr>

			<tr>
				<th>카테고리</th>
				<td><select name="pcategory_fk">
				<option ${(param.f == "가구")?"selected":"" } value="가구">중국사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">일본사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">한국사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">유럽고대사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">유럽중세사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">유럽근대사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">아시아고대사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">아시아중세사</option>
				<option ${(param.f == "가전제품")?"selected":"" } value="가전제품">아시아근대사</option>
				</select></td>
			</tr>
			<tr>
				<th>상품포인트</th>
				<td><input type="text" name="point"></td>
			</tr>
			<tr>
				<th>사진</th>
				<td colspan="3" class="text-align-left text-indent"><input
					type="file" name="pimage" /></td>
			</tr>

			<tr>
			<tr>
				<td colspan="2" align="center"><textarea name="pcontents"
						rows="20" cols="120"></textarea></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글쓰기">
					<input type="reset" value="초기화"></td>
			</tr>
		</table>
	</form>
</div>
<jsp:include page="/layout/footer.jsp" />