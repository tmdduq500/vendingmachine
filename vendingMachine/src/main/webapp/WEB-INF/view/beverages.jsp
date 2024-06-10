<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>음료 자판기</h1>
	<!-- 금액 부족시  -->
	<div>
		 <h2>${errMsg }</h2>
	</div>
	
	<a href="${pageContext.request.contextPath }/beverageBoard">관리자 모드</a>
	<form action="${pageContext.request.contextPath }/buy" method="post">
		<table border="1">
			<tr>
		    <c:forEach var="beverage" items="${beverages}" varStatus="status">
		    	
	    		<td width="150">
	    			<div>${beverage.beverageName }</div>
	    			<div>${beverage.beveragePrice }원</div>
	    			<c:if test="${beverage.beverageStock != 0}">
	    				<input type="radio" name="inputBeverageNo" value=" ${beverage.beverageNo }" required="required">
	    			</c:if>
	    			<c:if test="${beverage.beverageStock == 0}">
	    				<input type="radio" name="inputBeverageNo" value=" ${beverage.beverageNo }" disabled="disabled">x
	    			</c:if>
	    		</td>
		        <c:if test="${status.index % 5 == 4}">
	            	</tr><tr>
		        </c:if>
		    </c:forEach>
	        </tr>
		</table>
		<br>
		
		<div>
			투입 가능 금액은 100원, 500원, 1000원, 5000원, 10000원입니다.
		</div>
		<br>
		
		<div>
			투입 금액
			<br>
			<div>
				100원 개수 : <input type="number" name="money100Cnt" value="0" min="0" >개
			</div>
			<div>
				500원 개수 : <input type="number" name="money500Cnt" value="0" min="0" >개
			</div>
			<div>
				1000원 개수 : <input type="number" name="money1000Cnt" value="0" min="0" >개
			</div>
			<div>
				5000원 개수 : <input type="number" name="money5000Cnt" value="0" min="0" >개
			</div>
			<div>
				10000원 개수 : <input type="number" name="money10000Cnt" value="0" min="0" >개
			</div>
		</div>
		
		
		<button type="submit">구매하기</button>
	</form>
	
	<c:if test="${changeMoney.getTotalMoney() != 0}">
		<div>
			거스름돈 
			<div>
				100원: ${changeMoney.money100Cnt}개 - ${changeMoney.money100Cnt * 100}원
			</div>
			<div>
				500원: ${changeMoney.money500Cnt}개 - ${changeMoney.money500Cnt * 500}원
			</div>
			<div>
				1000원: ${changeMoney.money1000Cnt}개 - ${changeMoney.money1000Cnt * 1000}원
			</div>
			<div>
				5000원: ${changeMoney.money5000Cnt}개 - ${changeMoney.money5000Cnt * 5000}원
			</div>
			<div>
				10000원: ${changeMoney.money10000Cnt}개 - ${changeMoney.money10000Cnt * 10000}원
			</div>
			총 ${changeMoney.getTotalMoney() }원이 나왔습니다.
		</div>
	</c:if>
</body>
</html>