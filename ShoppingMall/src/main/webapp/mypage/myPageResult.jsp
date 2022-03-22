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
            // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.

            // ���θ� �ּ��� ���� ��Ģ�� ���� �ּҸ� ǥ���Ѵ�.
            // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
            var roadAddr = data.roadAddress; // ���θ� �ּ� ����
            var extraRoadAddr = ''; // ���� �׸� ����

            // ���������� ���� ��� �߰��Ѵ�. (�������� ����)
            // �������� ��� ������ ���ڰ� "��/��/��"�� ������.
            if(data.bname !== '' && /[��|��|��]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // ǥ���� �����׸��� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
     
            document.getElementById("sample4_engAddress").value = data.addressEnglish;
            
            // �����׸� ���ڿ��� ���� ��� �ش� �ʵ忡 �ִ´�.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // ����ڰ� '���� ����'�� Ŭ���� ���, ���� �ּҶ�� ǥ�ø� ���ش�.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(���� ���θ� �ּ� : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(���� ���� �ּ� : ' + expJibunAddr + ')';
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
		alert("�߸��� ID Ȥ�� ��й�ȣ �Դϴ�.");
		history.back();
	</script>
</c:if>
<h1 align="center">����������</h1>
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
				������
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
				<input type="button" onclick="sample4_execDaumPostcode()" value="�����ȣ ã��"><br>
				<input type="text" id="sample4_roadAddress" value="${dto.addr2 }" size="60" name="postAddr2"><br>
				<input type="hidden" id="sample4_jibunAddress" placeholder="�����ּ�"  size="60">
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="sample4_detailAddress" value="${dto.addr3 }"  size="60" name="postAddr3"><br>
				<input type="hidden" id="sample4_extraAddress" placeholder="�����׸�"  size="60">
				<input type="hidden" id="sample4_engAddress" placeholder="�����ּ�"  size="60" ><br>
			</td>
		</tr>

		<tr>
			<td colspan = "2" align = "center">
				<input type = "button" value = "Ȯ��" onclick = "location.href='/ShoppingMall/index.do'">
				<input type = "submit" value = "����">
				<input type="button" value="ȸ��Ż��" onclick ="location.href='/ShoppingMall/signout/SingOutPage.do'">
				<c:if test = "${ dto.id == 'admin' }"> <%-- ���� ������ admin�� ��� --%>
					<input type = "button" value = "��ȸ�� ����" onclick = "location.href='AdminPage.do'"> <%-- ��� ȸ�� ���� ��ư�� ���� --%> 
				</c:if>
			</td>
		</tr>
	</table>
</form>
</div>
<jsp:include page = "/layout/footer.jsp" />