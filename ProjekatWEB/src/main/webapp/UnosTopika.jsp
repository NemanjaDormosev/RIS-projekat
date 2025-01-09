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

	<form action = "/Forum/topik/dodajTopik" method = "post">
	
		Unesite naslov: <input type = "text" name = "naziv">
		
		<input type="submit" value="Dodaj topik">	
	
	</form>
	

</body>
</html>