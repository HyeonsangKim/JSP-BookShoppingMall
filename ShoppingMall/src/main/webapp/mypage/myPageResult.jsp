<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp" >
	<jsp:param name = "title" value = "mypage"/>
</jsp:include>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
     
            document.getElementById("sample4_engAddress").value = data.addressEnglish;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
</script>

<c:set var = "dto" value = "${requestScope.dto }" scope= "page"/>
<c:remove var="dto" scope= "request"/>


<c:if test = "${ dto == null }">
	<script>
		alert("잘못된 ID 혹은 비밀번호 입니다.");
		history.back();
	</script>
</c:if>
<h1 align="center">마이페이지</h1>
<div class="container" id="main" align="center">
<form action = "ModifyLogic.do" method = "post">
	<table border = "1" class="table">
		<tr>
			<th>ID</th>
			<td>${dto.id}</td>
			<input type = "hidden" name="user_id" value="${dto.id }">
		</tr>
		<tr>
		<th rowspan = "2">
			Password
		</th>
			<td>
				<input type = "password" name = "user_password" value = "${dto.password }" required>
			</td>
		</tr>
		<tr>
			<td>
				<input type = "password" name = "user_repassword" value = "${dto.password }" required>
			</td>
		</tr>
			
			
		<tr>
			<th>
				Nickname
			</th>
			<td>
				<input type = "text" name = "user_nickname" value = "${dto.nickname }" required>
			</td>
		</tr>
		<tr>
			<th>
				Email
			</th>
			<td>
				<input type = "email" name = "user_email" value = "${dto.email }" required>
			</td>
		</tr>
		<tr>
			<th>
				Phone
			</th>
			<td>
				<input type = "text" name = "user_phone" value = "${dto.phone }" required>
			</td>
		</tr>
		<tr>
			<th>
				가입일
			</th>
			<td>
				<h4>${dto.regdate }</h4>
			</td>
			
		</tr>
		<tr>
			<th>
				Address
			</th>
			<td>
				<input type="text" id="sample4_postcode" value="${dto.addr1 }" name="postAddr1">
				<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample4_roadAddress" value="${dto.addr2 }" size="60" name="postAddr2"><br>
				<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소"  size="60">
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="sample4_detailAddress" value="${dto.addr3 }"  size="60" name="postAddr3"><br>
				<input type="hidden" id="sample4_extraAddress" placeholder="참고항목"  size="60">
				<input type="hidden" id="sample4_engAddress" placeholder="영문주소"  size="60" ><br>
			</td>
		</tr>

		<tr>
			<td colspan = "2" align = "center">
				<input type = "button" value = "확인" onclick = "location.href='/ShoppingMall/index.do'">
				<input type = "submit" value = "수정">
				<input type="button" value="회원탈퇴" onclick ="location.href='/ShoppingMall/signout/SingOutPage.do'">
				<c:if test = "${ dto.id == 'admin' }"> <%-- 현재 유저가 admin인 경우 --%>
					<input type = "button" value = "전회원 정보" onclick = "location.href='AdminPage.do'"> <%-- 모든 회원 보기 버튼을 생성 --%> 
				</c:if>
			</td>
		</tr>
	</table>
</form>
</div>
<jsp:include page = "/layout/footer.jsp" />