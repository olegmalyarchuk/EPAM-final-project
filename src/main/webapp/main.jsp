<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 18.03.2022
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>

</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxies
    if(session.getAttribute("email")==null) {
        request.setAttribute("status", "unregistered");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
%>
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
<h1>Main page</h1>
<h2>Hello, <c:out value="${sessionScope.name}"/></h2>
<c:choose>
    <c:when test="${sessionScope.role_id=='1'}">
        <h3>You are registered as Moderator</h3>
    </c:when>
    <c:when test="${sessionScope.role_id=='2'}">
        <h3>You are registered as Speaker</h3>
    </c:when>
    <c:otherwise>
        <h3>You are registered as User</h3>
    </c:otherwise>
</c:choose>

<a href="events/list">Show all events</a>

<form action="logout">
    <input type="Submit" value="Logout">
</form>
<!--- JS -->
<script src="vendor/jquery/jguery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweatalert.css ">
<script type="text/javascript">
    var status = document.getElementById("status").value;
    if(status=="registered") {
       swal("Error", "You are already logged in.", "error");
    }
    if(status=="successRegister") {
        swal("Congrats", "You have successfully registered", "success");
    }
    if(status=="successLogin") {
        swal("Congrats", "You have successfully login", "success");
    }
</script>
</body>
</html>
