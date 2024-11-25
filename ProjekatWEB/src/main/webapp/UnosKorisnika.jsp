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
	
	<!-- 
		<form action = "/Forum/korisnik/novaUloga" method = "post">
			
			<input type = "submit" value = "Testiraj bazu">
		
		</form> <br>
		
		<c:if test = "${!empty test }">${test } </c:if> <br><br>
		
		<form action = "/Forum/korisnik/uloga-name">
			
			Naziv uloge<input type = "text" name = "naziv">
			<input type = "submit" value = "Dobavi ulogu">
		
		</form>
		
		<c:if test = "${!empty uloga }">${uloga } </c:if> <br><br>

	 -->

   	<form action = "/Forum/korisnik/registracija" method = "post">
	
		Ime: <input type ="text" name = "ime"><br>
		Prezime: <input type ="text" name = "prezime"><br>
		Godine: <input type ="text" name = "godine"><br>
		Email: <input type = "text" name = "email"><br>
		Username: <input type ="text" name = "username"><br>
		Password: <input type = "text" name = "password"><br>
		
		<input type="submit" value="Registruj se">	
	
	</form>
	
	<c:if test = "${!empty message }">${message } </c:if>


</body>

</body>
</html>