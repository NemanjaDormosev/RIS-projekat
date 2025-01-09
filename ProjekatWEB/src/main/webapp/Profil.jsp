<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="/Forum/css/general.css" />
<link rel="stylesheet" href="/Forum/css/profil.css" />
</head>
<body>
    <jsp:include page="header.jsp" />
    <div class="profile-container">
        <h2>Ovo su vaši podaci:</h2>
        <p><strong>Ime:</strong> ${ulogovan.ime}</p>
        <p><strong>Prezime:</strong> ${ulogovan.prezime}</p>
        <p><strong>Godine:</strong> ${ulogovan.godine}</p>
        <p><strong>Email:</strong> ${ulogovan.email}</p>
        <p><strong>Username:</strong> ${ulogovan.username}</p>
        <p><strong>Status:</strong> ${ulogovan.status}</p>
        <p><strong>Zabrana:</strong> 
            <c:choose>
                <c:when test="${!empty ulogovan.zabrana}">${ulogovan.zabrana}</c:when>
                <c:otherwise>nema</c:otherwise>
            </c:choose>
        </p>
        <p><strong>Broj objava:</strong> ${brojObjava}</p>
        <p><strong>Broj komentara:</strong> ${brojKomentara}</p>
        <p><strong>Poruke:</strong> 
            <c:choose>
                <c:when test="${brojPoruka != 0}">
                    <a href="/Forum/poruke/getStranica">lista poruka</a>
                </c:when>
                <c:otherwise>nemate nijednu poruku</c:otherwise>
            </c:choose>
        </p>
        <p><strong>Kategorije:</strong> 
            <c:choose>
                <c:when test="${!empty kategorije}">
                    <form action="/Forum/profil/getObjaveZaKategoriju">
                        <select name="kategorija">
                            <c:forEach items="${kategorije}" var="k">
                                <option value="${k.idkategorija}">${k.naziv}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Prikazi objave">
                    </form>
                </c:when>
                <c:otherwise>nemate sacuvanih kategorija</c:otherwise>
            </c:choose>
        </p>
        <c:if test="${ulogovan.uloga.iduloga == 2}">
            <form action="/Forum/statistika/getStranica">
                <input type="submit" value="Statistika foruma">
            </form>
            <form action="/Forum/izvestaj/izvestajZaStatistiku">
                <input type="submit" value="Generisi izvestaj">
            </form>
        </c:if>
        <br>
        <button><a href="/Forum/korisnik/getPocetna">Nazad na pocetnu</a></button>
    </div>
</body>
</html>