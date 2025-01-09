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

	<form action="/Forum/kategorija/napraviKategoriju" method="post">
	
		Naziv: <input type = "text" name = "naziv"> <br>
		
		<input type = "submit" value = "Napravi kategoriju">
	
	</form>

	

</body>
</html>