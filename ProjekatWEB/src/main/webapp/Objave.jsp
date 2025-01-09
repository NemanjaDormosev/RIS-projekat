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

	<h2>Ovo je topik: ${topik.naziv}</h2>
	
	<h3>Lista objava</h3>
	
	<form action = "/Forum/objava/pretragaObjava" method = "post">
		Pretraga po kljucnoj reci ili datumu <br>
		<input type = "text" name = "pretraga"> <input type = "date" name = "datum"> 
		<input type = "submit" value = "Pretrazi">
		
	</form>
	
	<form action = "/Forum/objava/sortiranjeObjava" method = "post">
		Sortiranje <br>
		<select name = "kriterijum">
			<option>naslov</option>
			<option>datum</option>
		</select>
		<input type = "submit" value = "Sortiraj">
	
	</form> <br>
	
	<c:if test="${!empty ulogovan }">
	
		<a href ="/Forum/objava/getObjavljivanje">Dodaj novu objavu</a> 
		<br><br>	
	
	</c:if>
	
	
	<c:if test ="${!empty objave }"> 
	
		<table>
			<tr>
				<th>Naslov objave</th>
				<th>Datum kreiranja</th>
				<th>Vreme kreiranja</th>
				<th>Kreiran od strane</th>
				<th></th>
			</tr>
			<c:forEach items = "${objave }" var = "o">
				<tr>
					<td><a href ="/Forum/objava/getDetaljiObjave?idobjava=${o.idobjava}">${o.naslov } </a></td>
					<td><fmt:formatDate value="${o.datum}" pattern="yyyy-MM-dd" /></td>
					<td>${o.vreme }</td>
					<td>${o.korisnik.username } </td>
					<td>
				
						<c:if test="${!empty ulogovan }">
						
							<c:choose>
							
								<c:when test="${!empty kategorije }">
								
									<form action = "/Forum/kategorija/dodajObjavu" method = "post">
										<select name = "kategorija">
											<c:forEach items = "${kategorije }" var = "k">
												<option value = "${k.idkategorija }"> ${k.naziv }</option>
											</c:forEach>
										
										</select>
										
										<input type = "hidden" name = "idobjava" value = "${o.idobjava }">
										<input type = "submit" value = "Dodaj u kategoriju"> 
									</form>
								
								
								</c:when>
								
								<c:otherwise>
									
									<form action="/Forum/kategorija/getStranica">
										
										<input type = "hidden" name = "idobjava" value = "${o.idobjava }">
										<input type = "submit" value = "Napravi kategoriju">	
									</form>
								
								</c:otherwise>
							
							</c:choose>
						
						
							<c:if test = "${ulogovan.uloga.iduloga == 2}">
								<form action = "/Forum/objava/getBrisanje"> 
									<input type = "hidden" name = "idobjava" value = "${o.idobjava }">
									<input type = "hidden" name = "idkorisnik" value = "${o.korisnik.idkorisnik }">
									<input type = "submit" value = "Izbrisi objavu">
								</form>
							</c:if>
						
						</c:if>
						
					</td>
				</tr>
			</c:forEach>
		
		</table>
	
	</c:if> <br>
	
	<form action="/Forum/korisnik/getPocetna">
	
		<input type = "submit" value = "Nazad">	
	</form>
	
	
	

</body>
</html>