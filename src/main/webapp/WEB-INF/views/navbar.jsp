<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<nav class="navbar bg-body-tertiary">
    <div class="container-fluid justify-content-between mt-1" style="justify-content: flex-start;">
        <div class="d-flex">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/rss/feeds">Feeds reader</a>
            <form class="d-flex me-2" method="get" action="${pageContext.request.contextPath}/rss/feeds">
                <input class="form-control me-2" type="search" placeholder="Search by category" name="filter"
                       aria-label="Search" value="${filter}">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <a href="${pageContext.request.contextPath}/rss/sources" class="btn btn-primary me-2">
                Settings
            </a>
            <security:authorize access="isAuthenticated()">
                <a href="${pageContext.request.contextPath}/rss/saved-feeds" class="btn btn-warning position-relative">
                    Saved feeds
                    <c:if test="${savedFeedsCount != 0}">
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                            ${savedFeedsCount}<span class="visually-hidden">saved feeds count</span>
                    </span>
                    </c:if>
                </a>
            </security:authorize>
        </div>
        <security:authorize access="!isAuthenticated()">
            <div class="d-flex">
                <a href="${pageContext.request.contextPath}/login" class="btn btn-primary me-2">
                    Login
                </a>
                <a href="${pageContext.request.contextPath}/signup" class="btn btn-primary me-2">
                    Sign up
                </a>
            </div>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <div class="d-flex">
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary me-2">
                    Log out
                </a>
            </div>
        </security:authorize>
    </div>
</nav>