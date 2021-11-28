<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>goodsRetrieve.jsp</title>
</head>
<body>
<h1>상품자세히보기화면입니다.</h1>
<jsp:include page="common/top.jsp" flush="true"></jsp:include><BR>
<jsp:include page="common/menu.jsp" flush="true"></jsp:include>
<hr>
<jsp:include page="goods/goodsRetrieve.jsp" flush="true"></jsp:include>
<!-- flush="true"는 include시 데이터에 변화가 있으면 cash에 데이터를 넣지 않고 화면상에 바로 변경시켜주는 속성 -->
</body>
</html>