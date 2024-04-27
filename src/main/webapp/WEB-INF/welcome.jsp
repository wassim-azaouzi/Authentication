<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
<h1> Welcome <c:out value="${user.userName}"/> !</h1>
<h3>Book from everyone's shelves:</h3>

<form action="/logout" method="POST">
	<button type="submit">Logout</button>
</form>

<a href="/books/new">Add to my shelf</a>

<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author Name</th>
			<th>Posted By</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="book" items="${books}">
			<tr>
				<th>${book.id}</th>
				<th><a href="/books/${book.id}">${book.title}</a></th>
				<th>${book.author}</th>
				<th>${book.user.userName}</th>
			</tr>
		 </c:forEach>
	</tbody>
</table>

</body>
</html>