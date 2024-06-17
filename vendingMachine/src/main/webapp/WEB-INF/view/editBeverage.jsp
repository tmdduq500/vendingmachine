<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>음료 수정</h1>
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
				<td>
					${beverage.beverageName}
					<input type="hidden" name="beverageName" value="${beverage.beverageName}">
				</td>
			</tr>
			<tr>
				<th>음료 타입</th>
				<td>
					${beverage.beverageType}
					<input type="hidden" name="beverageType" value="${beverage.beverageType}">
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" name="BeveragePrice" min="0" value="${beverage.beveragePrice}" placeholder="100원단위 이상으로 설정해주세요"></td>
			</tr>
			<tr>
				<th>재고</th>
				<td><input type="number" name="BeverageStock" min="0" value="${beverage.beverageStock}"></td>
			</tr>

		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>