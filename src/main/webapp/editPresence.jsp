<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Report update form</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
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
                   class="nav-link">Events</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="showProfile"
                   class="nav-link">Profile</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>
<br>
<table class="table">
    <thead>
    <tr>
        <th scope="col">User_id</th>
        <th scope="col">Status</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="event_user" items="${event_users}">
        <tr>
            <td>${event_user.user_id}</td>
            <td>${event_user.present}</td>
            <td>
                <a href="setPresence?user_id=${event_user.user_id}&event_id=${event_id}&presence=yes" class="btn btn-success">Present</a>
                <a href="setPresence?user_id=${event_user.user_id}&event_id=${event_id}&presence=no" class="btn btn-success">Registered</a>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
