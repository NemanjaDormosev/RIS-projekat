<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:if test = "${empty ulogovan }">
		<a href = "/Forum/UnosKorisnika.jsp">Registracija</a>
		<a href = "/Forum/Logovanje.jsp">Logovanje</a><br>
	</c:if>
	
	<c:if test = "${!empty ulogovan }"> Prijavljeni ste kao: <a href = "/Forum/profil/getProfil" > ${ulogovan.username } </a><br>
		<a href = "/Forum/korisnik/odjavljivanje">Odjavi se</a>
	</c:if><br>
	
	<br>
	
	<!-- ovde ide lista topika iz baze -->
	
	<h3>Lista topika</h3>
	
	<c:if test = "${!empty ulogovan and ulogovan.uloga.iduloga == 2}">
		<a href = "">Dodaj novi topik</a>
	</c:if> <br>
	
	<c:if test ="${!empty topiks }"> 
		<table>
			<tr>
				<th>Naziv topika</th>
				<th>Datum kreiranja</th>
				<th>Vreme kreiranja</th>
				<th>Kreiran od strane</th>
			</tr>
			<c:forEach items = "${topiks }" var = "t">
				<tr>
					<td><a href ="">${t.naziv } </a></td>
					<td>${t.datum }</td>
					<td>${t.vreme } </td>
					<td>${t.korisnik.username}</td>
				</tr>
			</c:forEach>
		
		</table>
	
	</c:if> <br>
	
	
	Trenutni datum: [date]<br>
	Najaktivniji danasnjeg dana: [korisnik]
	
	
</body>
</html>