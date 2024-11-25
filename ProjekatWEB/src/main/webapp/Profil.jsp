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


	Ovo su vasi podaci:<br>
	<br>
	Ime : ${ulogovan.ime }<br>
	Prezime : ${ulogovan.prezime }<br>
	Godine : ${ulogovan.godine }<br>
	Email : ${ulogovan.email }<br>
	Username : ${ulogovan.username }<br>
	Status : ${ulogovan.status }<br>
	Zabrana : 
	
	<c:choose>
	
		<c:when test = "${!empty ulogovan.zabrana }">
			${ulogovan.zabrana }
		</c:when>
		
		<c:otherwise>
			nema
		</c:otherwise>
		
	</c:choose> <br>
	
	Broj objava: ${brojObjava }<br>
	Broj komentara: ${brojKomentara }<br>
	
	Poruke : 
	
	<c:choose>
	
		<c:when test = "${brojPoruka != 0 }">
			<a href = "">lista poruka</a>
		</c:when>
		
		<c:otherwise>
			nemate nijednu poruku
		</c:otherwise>
		
	</c:choose><br>
				
	Kategorije : 

	
	<c:choose>
	
		<c:when test ="${!empty kategorije}">
			<select>
				<c:forEach items = "${kategorije }" var = "k">
					<option value = "${k.naziv }"> ${k.naziv }</option>
				</c:forEach>		
			</select>
			
		</c:when>
		
		<c:otherwise>
			nemate sacuvanih kategorija
		</c:otherwise>
		
	</c:choose><br>
	
	
	<c:if test="${ulogovan.uloga.idkorisnik == 2 }">
		<form action="">
		
			<input type = "submit" value = "Statistika foruma">
		</form>
	</c:if>
	
	<br>
	
	<a href = "/Forum/korisnik/getPocetna"> Nazad na pocetnu</a>
	
	

</body>
</html>