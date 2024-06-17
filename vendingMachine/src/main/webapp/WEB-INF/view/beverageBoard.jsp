<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>음료 관리</title>
</head>
<body>
	<a href="${pageContext.request.contextPath }/revenues">수익 현황</a>
	<a href="${pageContext.request.contextPath }/beverages">유저 모드</a>
    <a href="${pageContext.request.contextPath }/beverageBoard/add">음료 추가</a>
    <table border="1">
        <tr>
            <th>음료 번호</th>
            <th>음료 이름</th>
            <th>음료 타입</th>
            <th>가격</th>
            <th>재고</th>
            <th>음료 등록일</th>
            <th>음료 정보 수정일</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="beverage" items="${beverages}">
            <tr>
                <td>${beverage.beverageNo}</td>
                <td>
                	<a href="${pageContext.request.contextPath }/beverageBoard/edit/${beverage.beverageNo}">
                		${beverage.beverageName}
                	</a>
               	</td>
                <td>${beverage.beverageType}</td>
                <td>${beverage.beveragePrice}</td>
                <td>${beverage.beverageStock}</td>
                <td>${beverage.createDate}</td>
                <td>${beverage.updateDate}</td>
                <td>
                	<form action="${pageContext.request.contextPath }/beverageBoard/remove" method="post">
                		<input type="hidden" name="beverageNo" value="${beverage.beverageNo }">
                		<button type="submit">삭제</button>
                	</form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
