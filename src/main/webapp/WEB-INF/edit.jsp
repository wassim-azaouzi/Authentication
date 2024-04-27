<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>      
<%@ page isErrorPage="true" %>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 
<h1>Change your entry:</h1>
<a href="/welcome">back to the shelves</a>

<form:form action="/books/${book.id}/edit" method="post" modelAttribute="book">
    <input type="hidden" name="_method" value="put">
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
	    
    <input type="submit" value="Update"/>
</form:form>  


<form action="/books/${book.id}/delete" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="Delete">
</form>

</body>
</html>