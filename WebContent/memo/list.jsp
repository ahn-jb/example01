<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
</head>
<body>

	<table border="1" width="800">
	<tr>
		<td>No</td>
		<td>이름</td>
		<td>메모</td>
		<td>날짜</td>
	</tr>	
	<c:forEach var="row" items="${list }">
	<tr>
		<td>${row.no}</td>
		<td width="150">${row.writer}</td>
		<td width="330"><a href="#" onclick="GoPage('memo_write','','${dto.getNo()}');">${row.content}</a></td>
		<td width="200">${row.regi_date}</td>
	</tr>
	</c:forEach>
	<c:if test="${totalRecord > 0 }">
		<tr>
			<td colspan="4" align="center"> 
				<a href="#" onclick="GoPage('memo_list','1','');">[첫페이지]</a>&nbsp;&nbsp;
				<c:if test="${startPage > blockSize }">
					<a href="#" onclick="GoPage('memo_list','${startPage -blockSize}','');">[이전 10개]</a>
				</c:if>
				<c:if test="${startPage <=blockSize }"> [이전10개] </c:if>&nbsp;&nbsp;
				<c:forEach var="i" begin="${startPage}" end="${lastPage}" step="1">
				<c:if test="${i == pageNumber}"> [${i}]</c:if>
				<c:if test="${i != pageNumber}">
					<a href="#" onclick="GoPage('memo_list','${i}','')">${i}</a>
				</c:if>
				</c:forEach>&nbsp;&nbsp;
				<c:if test="${lastPage < totalPage }">
					<a href="#" onclick="GoPage('memo_list','${startPage + blockSize}','');">[다음 10개]</a>
				</c:if>
				<c:if test="${lastPage >= totalPage }"> [다음10개] </c:if>&nbsp;&nbsp;
				<a href="#" onclick="GoPage('memo_list','${totalPage}','');">[끝페이지]</a>
			</td>
		</tr>
	</c:if>
	<c:if test="${totalRecord == 0 }">
	<tr>
		<td colspan="4" align ="center">  
			비어 있음..
		</td>
	</tr>
	</c:if>
</table>
<script type="text/javascript">
function GoPage(value1,value2,value3){
	if(value1 =="memo_list"){
		location.href="${path}/memo_servlet/write.do?pageNumber="+value2+"&no="+value3;
	}
}	
</script>
</body>
</html>