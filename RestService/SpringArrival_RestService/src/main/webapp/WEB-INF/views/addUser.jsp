<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Items</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Users</h1>
				<p>Add Users</p>
			</div>
 		</div>
	</section>
	<section class="container">
		<form:form  modelAttribute="newUser" class="form-horizontal" >
			<fieldset>
				<legend>Add new user</legend>

				<form:errors path="*" cssClass="alert alert-danger" element="div"/>
 
				<div class="form-group">
					<label class="control-label col-lg-2" for="name"><spring:message code="addUser.form.firstName.label"/></label>
					<div class="col-lg-10">
						<form:input id="name" path="firstName" type="text" class="form:input-large"/>
						<form:errors path="firstName" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="lastName"><spring:message code="addUser.form.lastName.label"/></label>
					<div class="col-lg-10">
						<form:input id="lastName" path="lastName" type="text" class="form:input-large"/>
						<form:errors path="lastName" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="email"><spring:message code="addUser.form.email.label"/></label>
					<div class="col-lg-10">
						<form:input id="email" path="email" type="text" class="form:input-large"/>
						<form:errors path="email" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="userName"><spring:message code="addUser.form.userName.label"/></label>
					<div class="col-lg-10">
						<form:input id="userName" path="userCredentials.userName" type="text" class="form:input-large"/>
						<form:errors path="userCredentials.userName" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="password"><spring:message code="addUser.form.password.label"/></label>
					<div class="col-lg-10">
						<form:input id="password" path="userCredentials.password" type="text" class="form:input-large"/>
						<form:errors path="userCredentials.password" cssClass="text-danger"/>
					</div>
				</div>


				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/>
					</div>
				</div>
				
			</fieldset>
		</form:form>
	</section>
</body>
</html>
