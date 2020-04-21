<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/sample.css">
<meta charset="UTF-8">
<title>Voeg een student toe</title>
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

	<c:if test="${not empty errors}">
		<div class="alert alert-danger">
			<ul>
				<c:forEach items="${errros}" var="error">
					<li>${error}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>

	<main id="container">
		<c:if test="${not empty errors}">
			<div class="alert alert-danger">
				<ul>
					<c:forEach items="${errors}" var="error">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	<article>
		<h2>Voeg een student toe</h2>
		<form method="POST" action="StudentInfo?command=voegStudentToe" novalidate>
			<fieldset>
				<legend>Student informatie</legend>
				<p class="form-group" ${naamClass}>
					<label class="control-label" for="naam">Naam: * </label>
					<input id="naam" name="naam" type="text" value="${naamPreviousValue}" required>
				</p>
				<p class="form-group" ${voornaamClass}>
					<label class="control-label" for="voornaam">Voornaam: * </label>
					<input id="voornaam" name="voornaam" type="text" value="${voornaamPreviousValue}">
				</p>
				<p class="form-group" ${leeftijdClass}>
					<label class="control-label" for="leeftijd">Leeftijd: * </label>
					<input id="leeftijd" name="leeftijd" type="text" value="${leeftijdPreviousValue}">
				</p>
				<p class="form-group" ${studierichtingClass}>
					<label class="control-label" for="studierichting">Studierichting: * </label>
					<input id="studierichting" name="studierichting" type="text" value="${studierichtingPreviousValue}">
				</p>
			</fieldset>
			<p>
				<label for="bewaar">&nbsp;</label> <input id="bewaar" type="submit"
					value="Voeg Student Toe">
			</p>

		</form>
	</article>
	</main>
</body>
</html>