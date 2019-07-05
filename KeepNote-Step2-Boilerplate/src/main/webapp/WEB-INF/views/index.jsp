
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KeepNote</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style type="text/css">
.notes-container {
	display: flex;
	flex-flow: row;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Sen button. Handle errors like empty fields -->
	<form:form modelAttribute="noteform" action="add" method="post">

		<div class="form-group">
			<form:label path="noteTitle">Note Title:</form:label>
			<form:input class="form-control" path="noteTitle"
				placeholder="Enter note title" />

		</div>
		<div class="form-group">
			<form:label path="noteContent">Note Content:</form:label>
			<form:input class="form-control" path="noteContent"
				placeholder="Enter note content" />

		</div>
		<div class="form-group">
			<form:label path="noteStatus">Note Status:</form:label>
			<form:select class="form-control" path="noteStatus">
				<option>Started</option>
				<option>Not Started</option>
				<option>Completed</option>
			</form:select>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
	</form:form>

	<div class="notes-container">
		<c:forEach items="${noteList}" var="note">
			<div class="card" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">Title: ${note.noteTitle}</h5>
					<form:form modelAttribute="noteform" action="update" method="post">
						<div class="form-group">
							<form:label path="noteContent">Note Content:</form:label>
							<form:input class="form-control" path="noteContent"
								value="${note.noteContent}" placeholder="Enter note content" />

						</div>
						<div class="form-group">
							<form:label path="noteStatus">Note Status:</form:label>
							<form:select class="form-control" path="noteStatus"
								value="${note.noteStatus}">
								<option>Started</option>
								<option>Not Started</option>
								<option>Completed</option>
							</form:select>
						</div>
						<div class="form-group">
						
							<form:hidden class="form-control" path="noteUpdateId"  value="${note.noteId}" />
						</div>
						<button type="submit" class="btn btn-primary">Update</button>
					</form:form>
					<br> <a href="${contextPath}/delete?noteId=${note.noteId}"
						class="card-link">Delete</a>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>