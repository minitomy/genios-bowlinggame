<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="/playgame">
	Pins :
		<input type="text" name="pins">
		<input type="submit" value="Submit">
	</form>
	<br>
	<form method="POST" action="/restart">
		<input type="submit" value="Restart">
	</form>
	<br>
	<h3>${message}</h3>
	<br>	
	Frames and their Scores
	<br>
	<c:if test="${not empty scoreboard}">
		<table border=1>
				<tr>
			      <td>Frame Id</td>
			      <td>Hits in Frame</td>
			      <td>Score</td>
			      <td>Strikes</td>
			      <td>Spares</td>
			      <td>Misses</td>
			   </tr>
			<c:forEach items="${scoreboard}" var="score">
			   <tr>
			      <td>${score.id}</td>
			      <td>${score.hits}</td>
			      <td>${score.score}</td>
			      <td>${score.strike}</td>
			      <td>${score.spare}</td>
			      <td>${score.misses}</td>
			   </tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>