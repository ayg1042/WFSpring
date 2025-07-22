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
 <h2>Book_my_branch_1</h2>
 
 <p>1) 전체 조회</p>
  <p>/showAll</p>
  <p>/showAll2</p>

  <p>2) Key로 조회</p>
  <p>/find?key="key_value"</p>

  <p>3) 신규 데이터 추가</p>
  <p>/addBook?bookName="name"&author="author"&type="type"</p>

  <p>4) 데이터 수정</p>
  <p>/update?key="key_value"</p>

  <p>5) 데이터 삭제</p>
  <p>/delete?key="key_value"</p>
  <hr>
 
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