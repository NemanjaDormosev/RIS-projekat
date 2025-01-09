<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="css/general.css" />
<link rel="stylesheet" href="css/index.css" />
</head>
<body>

	<div class="container">
		<div class="header">
			<h1>Dobrodošli na naš forum!</h1>
		</div>
		<div class="rules">
			<p>Pravila za sve članove su sledeća:</p>
			<ul>
				<li>Poštujte jedni druge</li>
				<li>Zabranjeno vređanje</li>
				<li>Zabranjene uvredljive objave i komentari</li>
				<li>I kao najvažnije, uživajte u našoj zajednici!</li>
			</ul>
		</div>
		<div class="button-container">
			<button>
				<a href="/Forum/korisnik/getPocetna">Pristupi forumu</a>
			</button>
		</div>
	</div>

</body>
</html>