<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모</title>

<script>
$(document).ready(function(){
	list();
	$('#btnSave').click(function(){
		insert();
	});

});
function insert(){
	var writer = $("#writer").val();
	var content = $("#content").val();
	var param = "writer="+writer + "&content="+content;//쿼리스트링
	
	$.ajax({
		type:"post",
		data: param,
		url: "${path}/memo_servlet/writeProc.do",
		success: function(){//콜백함수
			list();
			$("#writer").val("");
			$("#content").val("");
		}
	});
}
function list(){
	var param = "search_gubun=&sdata="
	$.ajax({
		type: "post",
		data: " ",
		url: "${path}/memo_servlet/list.do?pageNumber=${pageNumber}",
		success: function(result){
			$("#result").html(result);
		}
	});
}
</script>
</head>
<body>
	<form name="memoForm">
		<h2>메모장</h2>
		<table width="400">
			<tr>
				<td>
					이름 : <input type="text" id="writer" size="20"><br> 
					내용 : <input type="text" id ="content" size="40"><br>
				</td>
			</tr>
			<tr>
				<td align="center">
					<br><input type="button"  id="btnSave" value="확인" >
				</td>
			</tr>
		</table>
	</form>
<!-- 	결과가 출력되는 영역 -->
	<div id="result"></div>
</body>
</html>