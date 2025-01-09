<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="/Forum/css/general.css" />
<link rel="stylesheet" href="/Forum/css/loginpage.css" />
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

	<div class="form-input">

		<form action="/Forum/korisnik/registracija" method="post">
			<table>
				<tr>
					<td>Ime:</td>
					<td><input type="text" name="ime"></td>
				</tr>
				<tr>
					<td>Prezime:</td>
					<td><input type="text" name="prezime"></td>
				</tr>
				<tr>
					<td>Godine:</td>
					<td><input type="text" name="godine"></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"></td>
				</tr>

			</table>

			<input type="submit" value="Registruj se">
		</form>

		<c:if test="${!empty message }">${message } </c:if>
	</div>

</body>
</html>