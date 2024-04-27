<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Share</title>
</head>
<body>
<h1>Add a Book to your shelf !</h1>	    

	<a href="/welcome">back to the shelves</a>
	
	<form action="/logout" method="POST">
		<button type="submit">Logout</button>
	</form>

	<form:form action="/books/new" method="post" modelAttribute="newBook">

	    <p>
	        <form:label path="title">Title:</form:label>
	        <form:errors path="title"/>
	        <form:input path="title"/>
	    </p>
	    <p>
	        <form:label path="author">Author:</form:label>
	        <form:errors path="author"/>
	        <form:input path="author"/>
	    </p>
	    <p>
	        <form:label path="thoughts">My Thoughts:</form:label>
	        <form:errors path="thoughts"/>
	        <form:textarea path="thoughts"/>
	    </p>
	    
	    <form:hidden path="user" value="${user.id}" />
	    
	    <input type="submit"/>
	    
	</form:form> 
	

</body>
</html>