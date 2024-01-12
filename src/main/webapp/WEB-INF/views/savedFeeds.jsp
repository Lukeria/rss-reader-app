<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%@ include file="navbar.jsp" %>
<div class="container mt-2">
    <div class="row">
        <div class="col">
            <h2 class="my-4">Saved Feeds</h2>
            <c:if test="${page != 1}">
                <a href="${pageContext.request.contextPath}/rss/saved-feeds?page=${page-1}"
                   class="btn btn-outline-primary my-4">Load previous</a>
            </c:if>
            <c:forEach items="${feedItems}" var="feedItem">
                <div class="card">
                    <div class="card-header d-flex justify-content-between">
                        <div class="d-flex">
                            <span class="badge text-bg-secondary me-2">${feedItem.source}</span>
                            <span class="badge text-bg-danger me-2">${feedItem.category}</span>
                        </div>
                        <small>${feedItem.publishDate}</small>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title my-4">${feedItem.title}</h5>
                        <p class="card-text mt-4">${feedItem.author}</p>
                        <div class="mt-3">
                            <a href="${feedItem.link}" class="btn btn-primary" target="_blank">Read more</a>
                            <a href="/rss/saved-feeds/delete/${feedItem.id}" class="btn btn-danger">Remove</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${feedItems.isEmpty()}">
                <p>No more feeds</p>
            </c:if>
            <c:if test="${!feedItems.isEmpty()}">
                <a href="${pageContext.request.contextPath}/rss/saved-feeds?page=${page+1}"
                   class="btn btn-outline-primary my-4">Load next</a>
            </c:if>
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
</body>

</html>