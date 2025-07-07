<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<form id="update" action="/update" method="post">
		<input type="hidden" name="key" id="key" value="${book.key}">
		<label name="bookName">책 이름</label>
		<input type="text" name="bookName" id="bookName" value="${book.book_name }"></br>	
		<label name="author">저자</label>
		<input type="text" name="author" id="author" value="${book.author }"></br>	
		<label name="type">타입</label>
		<input type="text" name="type" id="type" value="${book.type }"></br>
		<button id="form_sumit" type="button">수정하기</button>
	</form>
	
	<script>
		$(function(){
			
			if(${notFind} == 0){
				alert("없는 key값 입니다.");
				location.href = "/";
			}
			
			$("#form_sumit").click(function(){
				
				if(confirm("수정 하십니까?")){					
					$("#update").submit();
				}
			})
		})
		
		
	</script>
	
</body>
</html>