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

	<form action="/Forum/objava/izbrisiObjavu" method="post">
		
		Opciona zabrana: <input type = "date" name = "datumZabrane"> <br>
		
		<input type = "hidden" name = "idobjava" value = "${idobjava }">
		<input type = "hidden" name = "idkorisnik" value = "${idkorisnik }">
		<input type = "submit" value = "Obrisi objavu">
	
	</form>

</body>
</html>