<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="/Forum/css/header.css" />
</head>
<body>

	<header>

		<div class="content">

			<div class = "welcome-text">
				Dobrodošli na gaming forum!
			</div>

			<div class="opcije">
				<c:if test="${empty ulogovan }">
					<a href="/Forum/UnosKorisnika.jsp">Registracija</a>
					<a href="/Forum/Logovanje.jsp">Logovanje</a>
					<br>
				</c:if>
			</div>

			<div class="status">
			
				<!-- /Forum/korisnik/odjavljivanje -->
			
				<c:if test="${!empty ulogovan }"> Prijavljeni ste kao: <a
						href="/Forum/profil/getProfil"> ${ulogovan.username } </a>
					<br>
					<a href="/Forum/logout">Odjavi se</a>
				</c:if>
				<br> <br>
				
			</div>

		</div>

	</header>

</body>
</html>