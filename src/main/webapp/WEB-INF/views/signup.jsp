<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Feeds reader</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container mt-4">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <form:form method="POST"
                               action="${pageContext.request.contextPath}/signup" modelAttribute="user">
                        <div class="mb-3">
                            <form:label path="email" for="email" class="form-label">Email address</form:label>
                            <form:input path="email" type="email" class="form-control" id="email"
                                        aria-describedby="email"/>
                            <div id="emailText" class="form-text">We'll never share your email with anyone else.</div>
                        </div>
                        <div class="mb-3">
                            <form:label path="username" for="username" class="form-label">Username</form:label>
                            <form:input path="username" type="text" class="form-control" id="username"
                                        aria-describedby="username" required="required"/>
                        </div>
                        <div class="mb-3">
                            <form:label path="password" for="password" class="form-label">Password</form:label>
                            <form:input path="password" type="password" class="form-control" id="password"
                                        required="required"/>
                        </div>
                        <div class="mb-3">
                            <form:label path="confirmPassword" for="confirmPassword"
                                        class="form-label">Confirm password</form:label>
                            <form:input path="confirmPassword" type="password" class="form-control"
                                        id="confirmPassword" required="required"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form:form>
                </div>
            </div>
            <c:if test="${errorMessage!=null}">
                <div class="alert alert-danger mt-3" role="alert">
                        ${errorMessage}
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>