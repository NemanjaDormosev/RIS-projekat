<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="/Forum/css/general.css" />
</head>
<body>

	<jsp:include page="header.jsp" />

	<h2>Kategorija: ${kategorija.naziv }</h2>
	
	<c:if test="${!empty objave }">
		
		<table>
			<tr>
				<th>Naslov objave</th>
				<th>Datum kreiranja</th>
				<th>Vreme kreiranja</th>
				<th>Kreiran od strane</th>
			</tr>
			<c:forEach items = "${objave }" var = "o">
				<tr>
					<td><a href ="/Forum/objava/getDetaljiObjave?idobjava=${o.idobjava}">${o.naslov } </a></td>
					<td>${o.datum } </td>
					<td>${o.vreme }</td>
					<td>${o.korisnik.username } </td>
				</tr>
			</c:forEach>
	
		</table>
		
		<br>
	
	</c:if>
	
	<c:if test="${empty objave }">
		<p>Nemate sacuvanih objava</p>
	</c:if>
	
	<button><a href="/Forum/profil/getProfil">Nazad</a></button>
	

</body>
</html>