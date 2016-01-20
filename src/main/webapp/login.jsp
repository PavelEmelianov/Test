<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Login page</title>
<style type="text/css">
body {
	margin: 10%;
}
</style>
</head>

<body>
	<div align="center" style="color: red">
		<c:if test="${not empty param.error}">
    Unable to log in.
Please check that you have entered your login and password correctly.
    </c:if>
	</div>
	<div align="center">
		<s:property value="message" />
		<h3>${error}</h3>
		<table>
			<tr>
				<td>
					<form name="loginForm" action="login" method="POST">
						<fieldset>
							<legend>Login</legend>
							<input id="login " name="login" />
						</fieldset>
						<fieldset>
							<legend>Password</legend>
							<input id="password" name="password" type="password" />
						</fieldset>
						<input type="hidden" name="command" value="login" />
						<div align="center">
							<input type="submit" value="Enter"> <input type="hidden"
								name="${_csrf.parameterName}" value="${_csrf.token}" />
						</div>
					</form> <br>
					<div align="center">
						<form action="registration.jsp">
							<input type="submit" value="Registration">
						</form>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>

</html>