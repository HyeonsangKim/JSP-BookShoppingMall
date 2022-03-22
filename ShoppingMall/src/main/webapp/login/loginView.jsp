<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "Login"/>
</jsp:include>

<h1 align="center">로그인</h1>
<div class="container" id="main" align="center">
   <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-default content-main">
          <form action = "Login.do" method = "post" name="question">
              <div class="form-group">
                  <label for="userId">사용자 아이디</label>
                  <c:choose>
				<c:when test ="${cookie.rememberId.value == null }">
					<input class="form-control" type ="text" name="user_id" placeholder= "ID를 입력하세요." required>
				</c:when>
				<c:otherwise>
					<input class="form-control" type ="text" name="user_id" value="${cookie.rememberId.value }" required>
				</c:otherwise>
			</c:choose>
              </div>
              <div class="form-group">
                  <label for="password">비밀번호</label>
              	  <Input class="form-control" type ="password" name="user_password" placeholder= "PASSWORD를 입력하세요." required>
              </div>
              <div class="form-group">
                  <input type = 'checkbox' name = "remember_me" value = "true">아이디 기억하기
              </div>
              <button type="submit" class="btn btn-success clearfix pull-right">로그인</button>
              <div class="clearfix" />
              <div class="form-group">
                  <a href="/ShoppingMall/login/findId.do">아이디 찾기</a>
              </div>
          </form>
        </div>
    </div>
</div>

<jsp:include page="/layout/footer.jsp"/>