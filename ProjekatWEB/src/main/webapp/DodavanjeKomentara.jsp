<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="/Forum/css/general.css" />
</head>
<body>

	<jsp:include page="header.jsp" />

	<form action="/Forum/objava/dodajKomentar" method="post">
	
		Sadrzaj komentara: <input type = "text" name = "sadrzaj">
		
		<input type = "submit" value = "Dodaj komentar"> 
	
	</form>

</body>
</html>