<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="custom" uri="WEB-INF/custom.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="date" uri="WEB-INF/custom.tld"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Admin page</title>

<style type="text/css">
body {
	margin: 10%;
}
</style>


</head>
<body>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<script>
			function check() {
				return window.confirm("Are you sure?");
			}
		</script>

		<jsp:include page="include/admin_header.jsp" />

		<div align="left">
			<s:form method="Post" action="addEdit">
				<input type="submit" value="Add user" />
			</s:form>
		</div>

		<br>

		<div align="center">
			<table border="1" width="100%">
				<tr>
					<td>Login</td>
					<td>First Name</td>
					<td>Last Name</td>
					<td>Age</td>
					<td>Role</td>
					<td>Edit</td>
					<td>Delete</td>
				</tr>
				<c:forEach var="user_to_edit" items="${userList}">
					<tr>
						<date:User user="${user_to_edit}" />
						<td><s:form method="Post" action="addEdit">
								<input type="hidden" name="login" value="${user_to_edit.login}" />
								<input type="submit" value="Edit user" />
							</s:form></td>
						<td><s:form method="Post" action="delete">
								<input type="hidden" name="login" value="${user_to_edit.login}" />
								<s:submit value="Delete" align="center" onclick="return check()" />
							</s:form></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</security:authorize>
	<security:authorize access="hasRole('ROLE_USER')">


		<div align="center">
			<h1>
				Hello, ${loggedUserFirstName} ${loggedUserLastName}, click <a
					href="<c:url value="/logout"/>">Logout</a> to logout
			</h1>

		</div>
	</security:authorize>
</body>
</html>