<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="/Forum/css/general.css" />
<link rel="stylesheet" href="/Forum/css/pocetna.css" />
</head>
<body>
	
	<jsp:include page="header.jsp" />
	
	<!-- ovde ide lista topika iz baze -->
	
	<h2>Lista topika</h2>
	
	<c:if test = "${!empty ulogovan and ulogovan.uloga.iduloga == 2}">
		<button><a href = "/Forum/UnosTopika.jsp">Dodaj novi topik</a></button> <br>
	</c:if> <br>
	
	<c:if test ="${!empty topiks }"> 
		<table>
			<tr>
				<th>Naziv topika</th>
				<th>Datum kreiranja</th>
				<th>Vreme kreiranja</th>
				<th>Kreiran od strane</th>
				<th></th>
			</tr>
			<c:forEach items = "${topiks }" var = "t">
				<tr>
					<td><a href ="/Forum/objava/getStranica?idTopik=${t.idtopik}">${t.naziv } </a></td>
					<td>${t.datum }</td>
					<td>${t.vreme } </td>
					<td>${t.korisnik.username}</td>
					<td>
						<c:if test = "${!empty ulogovan and ulogovan.uloga.iduloga == 2}">
							<form action="/Forum/izvestaj/izvestajZaTopik">
								
								<input type = "hidden" name = "idtopik" value = "${t.idtopik}">
								<input type = "submit" value = "Generisi izvestaj">
							</form>
						</c:if>		
					</td>
				</tr>
			</c:forEach>
		
		</table>
	
	</c:if> <br>
	
	<h4>
	
		Trenutni datum: ${danasnjiDatum }<br>
		Najaktivniji danasnjeg dana: 
		<c:if test = "${!empty najaktivnijiDanas }">
			${najaktivnijiDanas.username }
		</c:if>
	
	</h4>
	
	
</body>
</html>