<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
</head>
<body>	

	<form action = "/Forum/korisnik/logovanje" method = "post">
	
		Username: <input type = "text" name = "username"><br>
		Password: <input type = "text" name = "password"><br>
		<input type = "submit" value = "Uloguj se"><br>
	
	</form>
	
	<c:if test = "${!empty message }">
		${message }
	 </c:if>
	

</body>
</html>