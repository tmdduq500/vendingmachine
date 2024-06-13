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
				<th>음료 이름</th>
				<td><input type="text" name="beverageName" required="required"></td>
			</tr>
			<tr>
				<th>음료 타입</th>
				<td><input type="text" name="beverageType" required="required" placeholder="ex) 탄산음료,과일주스 ..."></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" name="beveragePrice" min="0" required="required" placeholder="100원 단위이상 금액만 입력해주세요"></td>
			</tr>
			<tr>
				<th>재고</th>
				<td><input type="number" name="beverageStock" min="0" required="required"></td>
			</tr>

		</table>
		
		<button type="submit">추가</button>
	</form>
</body>
</html>