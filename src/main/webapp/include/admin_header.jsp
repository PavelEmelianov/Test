<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<security:authorize access="isAuthenticated()">
	<div align="right">
		${loggedUserFirstName} ${loggedUserLastName} <a
			href="<c:url value="/logout"/>">Logout</a>
	</div>
</security:authorize>