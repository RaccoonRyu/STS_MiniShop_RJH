<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartList.jsp</title>
</head>
<body>
<h1>장바구니 목록 화면입니다</h1>
<jsp:include page="common/top.jsp" flush="true" /><br>
<jsp:include page="common/menu.jsp" flush="true" />
<hr>
<jsp:include page="cart/cartList.jsp" flush="true" />
<!-- flush="true"는 include시 데이터에 변화가 있으면 cash에 데이터를 넣지 않고 화면상에 바로 변경시켜주는 속성 -->
</body>
</html>


