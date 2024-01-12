<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
            <div class="card w-50 m-auto">
                <div class="card-header">
                    Manage sources
                </div>
                <div class="card-body">
                    <form id="sourceList" method="post" action="${pageContext.request.contextPath}/rss/sources/enable">
                        <ul class="list-group">
                            <c:forEach items="${sourceList}" var="source">
                                <li class="list-group-item d-flex justify-content-between">
                                    <div class="d-flex align-items-center">
                                        <input class="form-check-input me-1" type="checkbox" value="${source.id}"
                                               id="checkbox_${source.id}" name="enabled_group"
                                            ${source.enabled ? 'checked' : ''}>
                                        <label class="form-check-label"
                                               for="checkbox_${source.id}">${source.name}</label>
                                    </div>
                                    <c:if test="${!source.defaultValue}">
                                        <spring:url var="delete"
                                                    value="${pageContext.request.contextPath}/rss/sources/delete/${source.id}"/>
                                        <a href="${delete}" class="btn btn-danger">Delete</a>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </form>
                    <security:authorize access="isAuthenticated()">
                        <form id="newSource" method="post"
                              action="${pageContext.request.contextPath}/rss/sources">
                            <div class="d-flex mt-2">
                                <input class="form-control me-2" type="text" placeholder="New source" name="name"
                                       aria-label="New source">
                                <button class="btn btn-outline-success" type="button" id="newSourceSubmit">Add</button>
                            </div>
                        </form>
                    </security:authorize>
                </div>
                <div class="card-footer">
                    <a href="${pageContext.request.contextPath}/rss/feeds" class="btn btn-secondary">Close</a>
                    <button type="button" class="btn btn-primary" id="sourceListSubmit">Save changes</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>

<script>
    let formSourceList = document.getElementById("sourceList");
    let formNewSource = document.getElementById("newSource");

    document.getElementById("sourceListSubmit").addEventListener("click", function () {
        formSourceList.submit();
    });

    document.getElementById("newSourceSubmit").addEventListener("click", function () {
        formNewSource.submit();
    });
</script>
</body>

</html>