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

	<form action="/Forum/objava/posaljiObjavaPoruku" method="post">
	
		Sadrzaj: <input type = "text" name = "sadrzaj">
		
		<input type = "submit" value = "Posalji poruku">
	
	</form>

</body>
</html>