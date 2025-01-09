<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="/Forum/css/general.css" />
</head>
<body>

	<jsp:include page="header.jsp" />
	
	<form action="/Forum/poruke/getRazmenjenePoruke" method = "post">
	
		<select name = "korisnik">
			<c:forEach items = "${posiljaoci }" var = "k">
				<option value = "${k.idkorisnik }"> ${k.username }</option>
			</c:forEach>		
		</select>
		
		<input type = "submit" value = "Prikazi poruke">

	</form>
	
	<br>
	
	<c:if test="${!empty razmenjenePoruke }">
	
		<table>
		
			<tr>
				<th>Korisnik</th>
				<th>Sadrzaj</th>
				<th>Datum</th>
				<th>Vreme</th>
			</tr>
			<c:forEach items = "${razmenjenePoruke }" var = "p">
				<tr>
					<td>${p.korisnik1.username }</td>
					<td>${p.sadrzaj }</td>
					<td><fmt:formatDate value="${p.datum}" pattern="yyyy-MM-dd" /></td>
					<td>${p.vreme }</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<br>	
		
		<form action="/Forum/poruke/posaljiPoruku" method = "post">
	
			Sadrzaj : <input type = "text" name = "sadrzaj"> 
		
			<input type = "submit" value = "Posalji poruku">
		
		</form>
	
	
	</c:if>
	
	<br>
	
	<button><a href="/Forum/profil/getProfil">Nazad</a></button>
	

</body>
</html>