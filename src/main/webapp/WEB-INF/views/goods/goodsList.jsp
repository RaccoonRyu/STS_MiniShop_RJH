<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table width="100%" cellspacing="0" cellpadding="0">
	<tr><!-- tr하나당 큰 td 4개, 큰 td(상품1개의 틀)안에 작은 td(상품1개의 정보) 4개씩 있다. -->
		<td>
			<table align="center" width="710" cellspacing="0" cellpadding="0"
				border="0">
				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td height="1" colspan="8" bgcolor="CECECE"></td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<tr>

<!-- 반복시작 -->
<c:forEach var="dto" items="${goodsList }" varStatus="status">
						<td>
							<table style='padding: 15px'>
								<tr>
									<td><a href="goodsRetrieve?gCode=${dto.gCode }"> <img
											src="images/items/${dto.gImage }.gif" border="0"
											align="center" width="200">
									</a></td>
								</tr>
								<tr>

									<td height="10">
								</tr>
								<tr>
									<td class="td_default" align="center"><a class="a_black"
										href="goodsRetrieve?gCode=${dto.gCode }"> ${dto.gName }<br>
									</a> <font color="gray"> -------------------- </font></td>

								</tr>
								<tr>
									<td height="10">
								</tr>
								<tr>
									<td class="td_gray" align="center">${dto.gContent }</td>
								</tr>
								<tr>
									<td height="10">
								</tr>
								<tr>
									<td class="td_red" align="center"><font color="red"><strong>
												${dto.gPrice }</strong></font></td>
								</tr>
							</table>
						</td>
						<!-- 한 줄에 4개씩 보여주기 -->
					<c:if test="${status.count%4 ==0 }">
						<tr> <!-- 줄바꿈용 tr -->
							<td height="10"></td>
						</tr>
					</c:if>
<!-- 반복 끝 -->
</c:forEach>				
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10">
	</tr>
</table>
