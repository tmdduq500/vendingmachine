<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>수익 목록</h1>
	<a href="${pageContext.request.contextPath }/beverageBoard">관리자 모드</a>
	<table border="1">
		<tr>
			<th>revenueNo</th>
			<th>beverageNo</th>
			<th>beverageName</th>
			<th>beverageType</th>
			<th>beveragePrice</th>
			<th>createDate</th>
		</tr>
		<c:forEach var="revenue" items="${revenues }">
			<tr>
				<td>${revenue.revenueNo }</td>
				<td>${revenue.beverageNo }</td>
				<td>${revenue.beverageName }</td>
				<td>${revenue.beverageType }</td>
				<td>${revenue.beveragePrice }</td>
				<td>${revenue.createDate }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3"><b>총 수익</b></td>
			<td colspan="3"><b>${revenues[0].totalBeveragePrice }원</b></td>
		</tr>
	</table>
</body>
</html>