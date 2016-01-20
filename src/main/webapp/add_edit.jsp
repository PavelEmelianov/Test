<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Edit user page</title>
<style type="text/css">
body {
	margin: 10%;
}
.errorMessage {
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="/include/admin_header.jsp" />

	<s:if test="%{operation == 'edit'}">
		<h1>Edit user</h1>
	</s:if>

	<s:if test="%{operation != 'edit'}">
		<h1>Add user</h1>
	</s:if>

	<s:form method="Post" action="addEditUser">

		<s:if test="%{operation == 'edit'}">
			<input type=hidden name=login value="${login}">
			<input type=hidden name=operation value="edit">
		</s:if>

		<s:if test="%{operation != 'edit'}">
			<input type=hidden name=operation value="add">
		</s:if>

		<s:if test="%{operation != 'edit'}">
			<s:textfield label="Login" name="login" />
		</s:if>
		
		<s:password value="%{password}" showPassword="true" label="Password"
			name="password" />
		<s:password value="%{password}" showPassword="true"
			label="Confirm password" name="confirmPassword" />
		<s:textfield label="Email" name="email" />
		<s:textfield label="First name" name="firstName" />
		<s:textfield label="Last name" name="lastName" />
		<s:textfield label="Birthday" name="birthday" />
		
		<td><select name="role">
				<s:if test="%{role != 'user'}">
					<option value="admin" selected="selected">Admin</option>
					<option value="user">User</option>
				</s:if>
				<s:elseif test="%{role == 'user'}">
					<option value="admin">Admin</option>
					<option value="user" selected="selected">User</option>
				</s:elseif>
		</select></td>
		
		<s:submit value="Ok" align="left"></s:submit>
	</s:form>
	<br>

	<form action="homepage">
		<input type="submit" value="Cancel">
	</form>
</body>
</html>