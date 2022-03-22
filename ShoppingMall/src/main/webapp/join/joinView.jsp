<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "Join"/>
</jsp:include>
<script type = "text/javascript" src= "script.js"></script>
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
<div class="container" id="main">
      
<h1 align="center">회원가입</h1>
<form name = "joinForm" method = "post" action = "JoinLogic.do">
  <div class="form-group">
    <div class="form-group">
      <label for="id"> ID</label>
      <input class="form-control" type = "text" name = "user_id" placeholder="아이디를 입력하세요" required>
      <input class="btn btn-primary" type = "button" value = "중복확인" onclick = "checkId()">
    </div>
</div>
    <div class="form-group">
      <label for="inputPassword4"> Password</label>
      <input class="form-control" type = "password" name = "user_password" placeholder = "비밀번호를 입력하세요." required>
    </div>
    <div class="form-group">
      <label for="inputPassword4"> Re Password</label>
      <input class="form-control" type = "password" name = "user_repassword" placeholder = "비밀번호를 다시 입력하세요." required>
    </div>
  <div class="form-group">
    <label for="inputAddress">Nick Name</label>
    <input class="form-control" type ="text" name = "user_nickname" placeholder = "닉네임을 입력하세요." required>
  </div>
   <div class="form-group">
    <div class="form-group">
      <label for="inputCity">우편번호</label>
      <input class="form-control" type="text" id="sample4_postcode" placeholder="우편번호" name="postAddr1">
      <input class="btn btn-primary" type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
    </div>

   </div>
   <div class="form-row">
    <div class="form-group col-md-5">
      <label for="inputCity">도로명주소</label>
      <input class="form-control" type="text" id="sample4_roadAddress" placeholder="도로명주소" size="60" name="postAddr2">
      <input type="hidden" id="sample4_jibunAddress" placeholder="지번주소"  size="60">
      <span id="guide" style="color:#999;display:none"></span>
    </div>
     <div class="form-group col-md-5">
      <label for="inputCity">상세주소</label>
      <input class="form-control" type="text" id="sample4_detailAddress" placeholder="상세주소"  size="60" name="postAddr3">
      <input type="hidden" id="sample4_extraAddress" placeholder="참고항목"  size="60">
		<input type="hidden" id="sample4_engAddress" placeholder="영문주소"  size="60" >
    </div>
    </div>
   
  <div class="form-group">
    <label for="inputAddress">전화번호</label>
    <input  class="form-control" type ="text" name = "user_phone" placeholder = "전화번호를 입력하세요." required>
  </div>
  <div class="form-row">
    <div class="col-md-8 mb-3">
      <label for="validationCustom03">이메일</label>
      <input type="text" name="user_email1" class="form-control" required>
      <div class="invalid-feedback">
        Please provide a valid city.
      </div>
    </div>
    <div class="col-md-4 mb-3">
      <label for="validationCustom04">주소</label>
      <select class="custom-select" name="user_email2" required>
        <option value = "naver.com">naver.com</option>
		<option value = "gmail.com">gmail.com</option>
		<option value = "daum.net">daum.net</option>
      </select>
      <div class="invalid-feedback">
        Please select a valid state.
      </div>
    </div>
    </div>
 
  <button type="submit" class="btn btn-primary">가입하기</button>
</form>

</div>

<jsp:include page="/layout/footer.jsp"/>