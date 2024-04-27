<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read Share</title>
</head>
<body>
<h1>${book.title}</h1>

<form action="/logout" method="POST">
	<button type="submit">Logout</button>
</form>

<a href="/welcome">back to the shelves</a>


<c:if test="${book.user.id.equals(user.id)}"> 
	<p>	You read ${book.title} by ${book.author}</p>
	<p>Here are your thoughts:</p>
	<p>${book.thoughts}</p>
	<a href="/books/${book.id}/edit"><button>Edit</button></a>
	<form action="/books/${book.id}/delete" method="post">
	    <input type="hidden" name="_method" value="delete">
	    <input type="submit" value="Delete">
	</form>
	
</c:if>

<c:if test="${!book.user.id.equals(user.id)}">
    <p>	${book.user.userName} read ${book.title} by ${book.author}</p>
	<p>Here are ${book.user.userName}'s thoughts:</p>
	<p>${book.thoughts}</p>
	<a href="/books/${book.id}/edit"><button disabled>Edit</button></a>
	<a href="/books/${book.id}/delete"><button disabled>Delete</button></a>
</c:if>

</body>
</html>