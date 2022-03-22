
function checkPassword(){
	var pw1 = joinForm.user_password.value;
	var pw2 = joinForm.user_repassword.value;
	if(pw1.length<6 || pw1.length > 14){
		alert("비밀번호를 6자이상 14자이하로 입력하세요.");
		joinForm.user_password.value="";
		joinFrom.user_repasword.value="";
	}
	else if (pw1 != pw2){
		alert('두 비밀번호가 일치 하지 않습니다.');
		joinForm.user_password.value="";
		joinFrom.user_repasword.value="";
	}else{
		joinForm.submit();
	}
}

function checkId(){
	var id = joinForm.user_id.value;
	if (id == ""){
		alert("아이디를 먼저 입력하세요.");
	}else if(id.indexOf(" ") >= 0){
		alert("아이디에 공백을 사용 할 수 없습니다.");
	}
	else{
		window.open("JoinCheckId.do?id="+ id,"","width=350 height=100 left=400 top=350");
	}
}

function checkIdFormClose(sId){
	opener.joinForm.user_id.value = sId;
	window.close();
}
