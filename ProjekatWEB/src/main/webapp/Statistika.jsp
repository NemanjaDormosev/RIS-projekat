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

	<c:if test="${!empty sviAktivniKorisnici }">
	
		<h3>Najaktivniji clanovi foruma</h2>
		
		
		
		<table border = "1">
			<tr>
				<th>Username</th>
				<th>Status</th>
				<th></th>
			</tr>
			<c:forEach items = "${sviAktivniKorisnici }" var = "k">
				<tr>
					<td>${k.username }<br></td>
					<td>${k.status}</td>
					<td><a href ="/Forum/statistika/prikaziAktivnost?idkorisnik=${k.idkorisnik }">Prikazi broj</a></td>
				</tr>
			</c:forEach>
		
		</table>
		
		<c:choose>
							
			<c:when test="${!empty brojObjava }">
			
				<p><strong>Broj objava: </strong> ${brojObjava }</p>
				
			</c:when>
		
		</c:choose> 
	
		<c:choose>
							
			<c:when test="${!empty brojKomentara}">
			
				<p><strong>Broj komentara: </strong> ${brojKomentara }</p> <br>
				
			</c:when>
			
		</c:choose>
		
		 
		 
 	</c:if>
 	
	<button><a href = "/Forum/profil/getProfil">Nazad</a></button>

</body>
</html>