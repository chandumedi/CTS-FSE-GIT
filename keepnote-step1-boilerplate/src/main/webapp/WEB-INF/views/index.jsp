
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KeepNote</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->
	<form:form modelAttribute="noteform" action="saveNote" method="post">
		<div class="form-group">
			<form:label path="noteId">Node Id:</form:label>
			<form:input class="form-control" path="noteId" 
				placeholder="Enter noteId"/>
		</div>
		<div class="form-group">
			<form:label path="noteTitle">Node Id:</form:label>
			<form:input class="form-control" path="noteTitle" 
				placeholder="Enter note title"/>
			
		</div>
		<div class="form-group">
			<form:label path="noteContent">Node Id:</form:label>
			<form:input class="form-control" path="noteContent"
				 placeholder="Enter note content"/>
			
		</div>
		<div class="form-group">
			<form:label path="noteStatus">Node Id:</form:label>
			<form:input class="form-control" path="noteStatus" 
				placeholder="Enter note status"/>
			
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
	</form:form>
	<%-- <form  action="saveNote" method="post">
		<div class="form-group">
			<label>Node Id:<label> <input class="form-control"
					name="noteId" placeholder="Enter noteId"> </input>
		</div>
		<div class="form-group">
			<label>Node title:<label> <input class="form-control"
					name="noteTitle" placeholder="Enter note title"> </input>
		</div>
		<div class="form-group">
			<label>Node content:<label> <input
					class="form-control" name="noteContent"
					placeholder="Enter note content"> </input>
		</div>
		<div class="form-group">
			<label>Node status:<label> <input
					class="form-control" name="noteStatus"
					placeholder="Enter note status"> </input>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form> --%>
	<!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->

	<div class="notes-container">
		<c:forEach items="${noteList}" var="note">
			<div class="card" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">${note.noteTitle}</h5>
					<p class="card-text">${note.noteContent}</p>
					<br>
					<p class="card-text">${note.noteStatus}</p>
					<br> <a href="${contextPath}/deleteNote?noteId=${note.noteId}"
						class="card-link">Delete</a>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>