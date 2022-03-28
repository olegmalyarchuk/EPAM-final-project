<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html>
<head>
    <title><fmt:message key="moderatorpreposition.title" /></title>
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
        <th scope="col"><fmt:message key="moderatorpreposition.userid"/></th>
        <th scope="col"><fmt:message key="moderatorpreposition.username"/></th>
        <th scope="col"><fmt:message key="moderatorpreposition.usersurname"/></th>
        <th scope="col"><fmt:message key="moderatorpreposition.action"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="speaker" items="${speakers}">
        <tr>
            <td>${speaker.id}</td>
            <td>${speaker.user_name}</td>
            <td>${speaker.user_surname}</td>
            <td>
                <a href="setPreposition?report_id=${report_id}&speaker_id=${speaker.id}" class="btn btn-success"><fmt:message key="button.propose"/></a>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
