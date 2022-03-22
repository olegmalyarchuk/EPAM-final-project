<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 18.03.2022
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Events page</title>
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
<h1>List of all events</h1>
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
<br>
<a href="events?action=add">Add new event</a>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name UA</th>
            <th>Name EN</th>
            <th>Place UA</th>
            <th>Place EN</th>
            <th>Description UA</th>
            <th>Description EN</th>
            <th>Date</th>
            <th>Photo</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
    <!-- for(Todo todo: todos) { -->
    <c:forEach var="event" items="${eventsList}">
        <tr>
            <td>
                <c:out value="${event.event_id}"/>
            </td>
            <td>
                <c:out value="${event.event_name_ua}"/>
            </td>
            <td>
                <c:out value="${event.event_name_en}"/>
            </td>
            <td>
                <c:out value="${event.event_place_ua}"/>
            </td>
            <td>
                <c:out value="${event.event_place_en}"/>
            </td>
            <td>
                <c:out value="${event.event_description_ua}"/>
            </td>
            <td>
                <c:out value="${event.event_description_en}"/>
            </td>
            <td>
                <c:out value="${event.event_date}"/>
            </td>
            <td>
                <c:out value="${event.event_photo_url}"/>
            </td>
            <td><a href="events?action=edit&id=<c:out value='${event.event_id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="events?action=delete&id=<c:out value='${event.event_id}' />">Delete</a></td>
        </tr>
    </c:forEach>
    <!-- } -->
    </tbody>
</table>
<!--- JS -->
<script src="vendor/jquery/jguery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweatalert.css ">
<script type="text/javascript">
    var status = document.getElementById("status").value;
    if(status=="successDelete") {
        swal("Success", "Event was successfully deleted", "success");
        //alert("First name should not be empty");
    }
    if(status=="errorDelete") {
        swal("Error", "Event was not deleted", "error");
        //alert("Last name should not be empty");
    }
</script>
</body>
</html>

