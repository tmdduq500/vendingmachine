<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>음료 추가</h1>	
	<a href="${pageContext.request.contextPath}/beverageBoard">돌아가기</a>
	<c:if test="${errMsg != null }">
		<div>
			${errMsg }
		</div>
	</c:if>
	<form action="" method="post">
		<table border="1">
			<tr>
				<th>beverageName</th>
				<td><input type="text" name="beverageName" required="required"></td>
			</tr>
			<tr>
				<th>beverageType</th>
				<td><input type="text" name="beverageType" required="required"></td>
			</tr>
			<tr>
				<th>beveragePrice</th>
				<td><input type="number" name="beveragePrice" min="0" required="required" placeholder="100원 단위이상 금액만 입력해주세요"></td>
			</tr>
			<tr>
				<th>beverageStock</th>
				<td><input type="number" name="beverageStock" min="0" required="required"></td>
			</tr>

		</table>
		
		<button type="submit">추가</button>
	</form>
</body>
</html>