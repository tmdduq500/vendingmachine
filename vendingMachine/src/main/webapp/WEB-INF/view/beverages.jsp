<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<title>Insert title here</title>
</head>
<body>
	<h1>음료 자판기</h1>
	
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
			<div>
				투입 금액 : <input type="text" id="totalMoney" value="0" readonly="readonly">
			</div>
			<br>
			<div>
				<input type="hidden" id="money100Cnt" name="money100Cnt" value="0">
				<button type="button" id="money100">100원</button>


				<input type="hidden" id="money500Cnt" name="money500Cnt" value="0">
				<button type="button" id="money500">500원</button>

				<input type="hidden" id="money1000Cnt" name="money1000Cnt" value="0">
				<button type="button" id="money1000">1000원</button>

				<input type="hidden" id="money5000Cnt" name="money5000Cnt" value="0">
				<button type="button" id="money5000">5000원</button>

				<input type="hidden" id="money10000Cnt" name="money10000Cnt" value="0">
				<button type="button" id="money10000">10000원</button>
			</div>
			
		</div>
		<br>
		<button type="submit">구매하기</button>
		<button type="button" id="reset">반환하기</button>
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
	
	<script type="text/javascript">
		let errMsg = '${errMsg}';
		let buyBeverageName = '${buyBeverageName}';
		
		console.log(errMsg);
		console.log(buyBeverageName);
		// errMsg가 있을 때 alert 발생
		if(errMsg != '') {
			alert(errMsg);
		}
		
		// 
		if(buyBeverageName != '') {
			alert(buyBeverageName + '가 나왔습니다');
		}
		
		$('#money100').click(function() {
		    let currentCount = parseInt($('#money100Cnt').val()) || 0;
		    currentCount += 1;
		    $('#money100Cnt').val(currentCount);
		    
		    let totalMoney = parseInt($('#totalMoney').val()) || 0;
		    totalMoney += 100;
		    $('#totalMoney').val(totalMoney);
		});
	
		$('#money500').click(function() {
		    let currentCount = parseInt($('#money500Cnt').val()) || 0;
		    currentCount += 1;
		    $('#money500Cnt').val(currentCount);
		    
		    let totalMoney = parseInt($('#totalMoney').val()) || 0;
		    totalMoney += 500;
		    $('#totalMoney').val(totalMoney);
		});
	
		$('#money1000').click(function() {
		    let currentCount = parseInt($('#money1000Cnt').val()) || 0;
		    currentCount += 1;
		    $('#money1000Cnt').val(currentCount);
		    
		    let totalMoney = parseInt($('#totalMoney').val()) || 0;
		    totalMoney += 1000;
		    $('#totalMoney').val(totalMoney);
		});
	
		$('#money5000').click(function() {
		    let currentCount = parseInt($('#money5000Cnt').val()) || 0;
		    currentCount += 1;
		    $('#money5000Cnt').val(currentCount);
		    
		    let totalMoney = parseInt($('#totalMoney').val()) || 0;
		    totalMoney += 5000;
		    $('#totalMoney').val(totalMoney);
		});
	
		$('#money10000').click(function() {
		    let currentCount = parseInt($('#money10000Cnt').val()) || 0;
		    currentCount += 1;
		    $('#money10000Cnt').val(currentCount);
		    
		    let totalMoney = parseInt($('#totalMoney').val()) || 0;
		    totalMoney += 10000;
		    $('#totalMoney').val(totalMoney);
		});
		
		$('#reset').click(function() {
			$('#money100Cnt').val(0);
			$('#money500Cnt').val(0);
			$('#money1000Cnt').val(0);
			$('#money5000Cnt').val(0);
			$('#money10000Cnt').val(0);
			$('#totalMoney').val(0);
		})
	</script>
</body>
</html>