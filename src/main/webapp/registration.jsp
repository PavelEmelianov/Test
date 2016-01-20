<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration page</title>
<style type="text/css">
body {
	margin: 10%;
}

.errorMessage {
	color: red;
}
</style>
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>

	<s:form method="Post" action="register">
		<h3>Registration page</h3>
		<s:textfield label="Login" name="login" />
		<s:password label="Password" name="password" />
		<s:password label="Confirm password" name="confirmPassword" />
		<s:textfield label="Email" name="email" />
		<s:textfield label="First name" name="firstName" />
		<s:textfield label="Last name" name="lastName" />
		<s:textfield label="Birthday" name="birthday" />
		<td>
			<div class="g-recaptcha"
				data-sitekey="6LdNHxMTAAAAAIAyFKLcHXRGJvOowNDQHVfeu8sg"></div>
		</td>
		<s:submit value="Enter" align="left" />
	</s:form>
	<br>
	<form action="login.jsp">
		<input type="submit" value="Cancel" />
	</form>
</body>
</html>