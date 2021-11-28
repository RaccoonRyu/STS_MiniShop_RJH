<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- top 부분 -->
<c:if test="${empty login }">
	<a href="loginForm">로그인</a>&nbsp; <!-- 주소는 xml에 지정 -->
	<a href="memberForm">회원가입</a>&nbsp; <!-- 주소는 xml에 지정 -->
</c:if>
<c:if test="${!empty login }">
안녕하세요? ${login.username } 님 !<br>
	<a href="loginCheck/logout">로그아웃</a>&nbsp;
	<a href="loignCheck/myPage">mypage</a>&nbsp;
	<a href="loginCheck/cartList">장바구니</a>&nbsp;
</c:if>