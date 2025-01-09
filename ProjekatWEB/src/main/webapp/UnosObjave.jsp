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

	<form action = "/Forum/objava/postaviObjavu" method = "post">
	
		Naslov: <input type = "text" name = "naslov"> <br>
		Sadrzaj: <input type = "text" name = "sadrzaj"> <br>
		
		<input type="submit" value="Objavi">	
	
	</form>

</body>
</html>