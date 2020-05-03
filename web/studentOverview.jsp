<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="domain.model.Student"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/sample.css">
<meta charset="UTF-8">
<title>Overzicht Studenten</title>
</head>
<body>
	<header>
		<div>
			<h1>Studentenregistratie</h1>
			<nav>
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="zoekForm.jsp">Zoek een student</a></li>
					<li><a href="studentForm.jsp">Voeg een student toe</a></li>
					<li><a href="StudentInfo?command=overzicht">Bekijk alle studenten</a></li>
				</ul>
			</nav>
		</div>
		<img alt="Toscane" src="images/student.jpg">

	</header>

	<main id="container">
	<article>
		<h2>Overzicht studenten</h2>
		<table id="overview">
			<tr>
				<th>Naam</th>
				<th>Voornaam</th>
				<th class="getal">Leeftijd</th>
				<th>Studierichting</th>
				<th>Verwijder</th>
			</tr>
            <c:choose>
                <c:when test="${not empty studenten}">
                    <c:forEach var="student" items="${studenten}">

			<tr id="${student.naam}">
				<td>${student.naam}</td>
				<td>${student.voornaam}</td>
				<td class="getal">${student.leeftijd}</td>
				<td>${student.studierichting}</td>
				<td><a href="StudentInfo?command=verwijder&naam=${student.naam}&voornaam=${student.voornaam}&leeftijd=${student.leeftijd}&studierichting=${student.studierichting}">Verwijder</a></td>
			</tr>
                    </c:forEach>
		</table>
            </c:when>
                <c:otherwise>
                    <p>Er zijn nog geen studenten toegevoegd.</p>
                </c:otherwise>
        </c:choose>


	</article>
	</main>
</body>
</html>
