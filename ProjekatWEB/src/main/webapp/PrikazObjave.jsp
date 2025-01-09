<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="/Forum/css/general.css" />
</head>
<body>

	<jsp:include page="header.jsp" />

	<h2>Detalji o objavi: ${objava.naslov }</h2>
	
	<br>

	<p>
		<strong>Sadrzaj:</strong> ${objava.sadrzaj}
	</p>
	<p>
		<strong>Datum:</strong> ${objava.datum}
	</p>
	<p>
		<strong>Vreme:</strong> ${objava.vreme}
	</p>
	<p>
		<strong>Od strane:</strong> ${objava.korisnik.username}, <strong>status:</strong>
		${objava.korisnik.status}
	</p>
	
	<c:if test="${!empty ulogovan }">

		<button><a href="/Forum/objava/getObjavaPoruka">Posalji poruku</a></button>

		<button><a href="/Forum/objava/getDodavanjeKomentara"> Dodaj komentar </a></button>
		<br>

	</c:if>

	<c:if test="${!empty komentari }">

		<h3>Lista komentara
	</h2>

	<table border="1">
		<tr>
			<th>Od strane</th>
			<th>Sadrzaj</th>
			<th>Datum</th>
			<th>Vreme</th>
		</tr>
		<c:forEach items="${komentari }" var="k">
			<tr>
				<td>${k.korisnik.username }<c:if test="${!empty ulogovan }">
						<br>
						<a
							href="/Forum/objava/getKomentarPoruka?idkorisnik=${k.korisnik.idkorisnik }">Posalji
							poruku</a>
					</c:if>
				</td>
				<td>${k.sadrzaj }</td>
				<td><fmt:formatDate value="${k.datum}" pattern="yyyy-MM-dd" /></td>
				<td>${k.vreme }</td>
			</tr>
		</c:forEach>

	</table>
	</c:if>

	<br>

	<c:choose>

		<c:when test="${!empty topik }">
			<button><a href="/Forum/objava/getStranica?idTopik=${topik.idtopik }">Nazad</a></button>
		</c:when>

		<c:otherwise>
			<button><a href="/Forum/profil/getObjaveZaKategoriju?kategorija=${kategorija.idkategorija }">Nazad</a></button>
		</c:otherwise>

	</c:choose>

</body>
</html>