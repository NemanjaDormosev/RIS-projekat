<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="/Forum/css/general.css" />
<link rel="stylesheet" href="/Forum/css/loginpage.css" />
</head>
<body>

	<!-- action = "/Forum/korisnik/logovanje" -->
	<!-- action = "${pageContext.request.contextPath}/login" -->

	<div class="form-input">

		<form action="${pageContext.request.contextPath}/login" method="post">

			Username: <input type="text" name="username"><br>
			Password: <input type="password" name="password"><br> <input
				type="submit" value="Uloguj se"><br>

		</form>

		<c:if test="${!empty message }">
			${message }
		</c:if>
		
	</div>

</body>
</html>