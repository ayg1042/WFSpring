<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
 <h2>Book</h2>
 <c:if test="${showList != null}">
 	<c:forEach items="${showList}" var="dto">
 		<p>키값 : ${dto.key}</p>
 		<p>책이름 : ${dto.book_name}</p>
 		<p>저자 : ${dto.author}</p>
 		<p>타입 : ${dto.type}</p>
 		<hr>
 	</c:forEach>
 </c:if>
 
 <script>
 	$(function(){
 		if(${result} == true){
 			alert("성공했습니다.");
 		}else{
 			alert("실패하였습니다.");
 		}
 	})
 </script>
 
</body>
</html>