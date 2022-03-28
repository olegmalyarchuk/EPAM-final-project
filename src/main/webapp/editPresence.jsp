<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html>
<head>
    <title> <fmt:message key="editPresence.title" /></title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="shortcut icon" href="/resources/images/logo.png" type="image/png">
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxies
    if(session.getAttribute("email")==null) {
        request.setAttribute("status", "unregistered");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
%>
<c:choose>
    <c:when test="${sessionScope.role_id!=1}">
        <% RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);%>
    </c:when>
</c:choose>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #0074D9">
        <ul class="navbar-nav">
            <li><a href="listEvent"
                   class="nav-link"><fmt:message key="header.events" /></a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="showProfile"
                   class="nav-link"> <fmt:message key="header.profile" /></a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="/logout"
                   class="nav-link"><fmt:message key="header.logout" /></a></li>

        </ul>
        <ul class="nav justify-content-end">
            <li><a href="lang?lang=ua"
                   class="nav-link"><img src="/resources/images/lang/ukr.png" width="24" /></a></li>
        </ul>
        <ul class="nav justify-content-end">
            <li><a href="lang?lang=en"
                   class="nav-link"><img src="/resources/images/lang/eng.png" width="24" /></a></li>
        </ul>
    </nav>
</header>
<br>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="editPresence.userid" /></th>
        <th scope="col"><fmt:message key="editPresence.status" /></th>
        <th scope="col"><fmt:message key="editPresence.action" /></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="event_user" items="${event_users}">
        <tr>
            <td>${event_user.user_id}</td>
            <td>
                <c:choose>
                    <c:when test="${event_user.present==true}"><fmt:message key="editPresence.present" /></c:when>
                    <c:otherwise><fmt:message key="editPresence.registered" /></c:otherwise>
                </c:choose>

            </td>
            <td>
                <a href="setPresence?user_id=${event_user.user_id}&event_id=${event_id}&presence=yes" class="btn btn-success"><fmt:message key="editPresence.present" /></a>
                <a href="setPresence?user_id=${event_user.user_id}&event_id=${event_id}&presence=no" class="btn btn-success"><fmt:message key="editPresence.registered" /></a>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
     <c:choose>
         <c:when test="${prevDisabled}">
         <li class="page-item disabled">
             <a class="page-link" href="#" tabindex="-1"><fmt:message key="editPresence.previous" /></a>
         </li>
       </c:when>
         <c:otherwise> <li class="page-item"><a class="page-link" href="editPresence?page=${page-1}&event_id=${vent_id}"><fmt:message key="editPresence.previous" /></a></li></c:otherwise>
     </c:choose>
        <li class="page-item"><a class="page-link" href="editPresence?page=1&event_id=${event_id}">1</a></li>
        <li class="page-item"><a class="page-link" href="editPresence?page=2&event_id=${event_id}">2</a></li>
        <li class="page-item"><a class="page-link" href="editPresence?page=3&event_id=${event_id}">3</a></li>
        <c:choose>
            <c:when test="${nextDisabled}">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1"><fmt:message key="editPresence.next" /></a>
                </li>
            </c:when>
            <c:otherwise> <li class="page-item"><a class="page-link" href="editPresence?page=${page+1}&event_id=${event_id}"><fmt:message key="editPresence.next" /></a></li></c:otherwise>
        </c:choose>
    </ul>
</nav>
</body>
</html>
