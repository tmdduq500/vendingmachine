<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>가격 수정</h1>
	<c:if test="${errMsg != null }">
		<div>
			${errMsg }
		</div>
	</c:if>
	<form action="" method="post">
		<table border="1">
			<tr>
				<th>음료 번호</th>
				<td>
					${beverage.beverageNo}
					<input type="hidden" name="beverageNo" value="${beverage.beverageNo}">
				</td>
			</tr>
			<tr>
				<th>음료 이름</th>
				<td>${beverage.beverageName}</td>
			</tr>
			<tr>
				<th>음료 타입</th>
				<td>${beverage.beverageType}</td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" name="newBeveragePrice" min="0" value="${beverage.beveragePrice}"> </td>
			</tr>
			<tr>
				<th>재고</th>
				<td>${beverage.beverageStock}</td>
			</tr>

		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>