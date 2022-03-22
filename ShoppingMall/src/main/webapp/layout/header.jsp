<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kim's: ${param.title != null ? param.title : "My WebPage!"}</title>


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
	crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="/ShoppingMall/index.do">홈으로</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="/ShoppingMall/board/BoardList.do?page=1">게시판
			</a></li>
			<li class="nav-item"><a class="nav-link" href="/ShoppingMall/product/ProductList.do?p=1">상품목록</a></li>
			<c:choose>
				<c:when test="${sessionScope.currentNickname == null }">
				<li class="nav-item"><a class="nav-link" href="/ShoppingMall/login/LoginPage.do">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="/ShoppingMall/join/joinView.do">회원가입</a></li>
				</c:when>
			<c:otherwise>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-expanded="false">
					${sessionScope.currentNickname }님</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="/ShoppingMall/login/LogoutLogic.do">로그아웃</a> <a
						class="dropdown-item" href="/ShoppingMall/mypage/mypageView.do">마이페이지</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/ShoppingMall/product/cart/CartList.do?user=${sessionScope.currentId }">장바구니</a>
					<c:if test = "${ sessionScope.currentId == 'admin' }"> <%-- 현재 유저가 admin인 경우 --%>
						<a class="dropdown-item" href="/ShoppingMall/admin/CategoryList.do">카테고리</a> 
					</c:if>
				</div></li>
			</c:otherwise>
			</c:choose>
			
		</ul>
	</div>
</nav>

