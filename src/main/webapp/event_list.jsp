<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Event list</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
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
<input type="hidden" id="status" value="<%=session.getAttribute("status")%>">
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #0074D9">
        <ul class="navbar-nav">
            <li><a href="listEvent"
                   class="nav-link">Events</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Events</h3>
        <hr>
        <div class="container text-center">

            <a href="newEvent" class="btn btn-success" style="background-color: #0074D9;">Add
                New Event</a>
            <c:choose>
                <c:when test="${eventStatus==null||eventStatus=='all'}"> <a href="listEvent?eventStatus=all" class="btn btn-success" style="background-color: #1E93F9;">All events</a></c:when>
                <c:otherwise> <a href="listEvent?eventStatus=all" class="btn btn-success" style="background-color: #0074D9;">All events</a></c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${eventStatus=='finished'}"> <a href="listEvent?eventStatus=finished" class="btn btn-success" style="background-color: #1E93F9">Finished events</a></c:when>
                <c:otherwise> <a href="listEvent?eventStatus=finished" class="btn btn-success" style="background-color: #0074D9;">Finished events</a></c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${eventStatus=='upcoming'}"> <a href="listEvent?eventStatus=upcoming" class="btn btn-success" style="background-color: #1E93F9;">Upcoming events</a></c:when>
                <c:otherwise> <a href="listEvent?eventStatus=upcoming" class="btn btn-success" style="background-color: #0074D9;">Upcoming events</a></c:otherwise>
            </c:choose>
            <button class="btn btn-secondary dropdown-toggle" style="background-color: #1E93F9" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Order by
            </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="listEvent?orderBy=date">Date</a>
                    <a class="dropdown-item" href="listEvent?orderBy=reports">Reports count</a>
                    <a class="dropdown-item" href="listEvent?orderBy=users">Users count</a>
                    <a class="dropdown-item" href="listEvent">Default</a>
                </div>
        </div>
        <br>
<%--        <table class="table table-bordered">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>ID</th>--%>
<%--                <th>Name UA</th>--%>
<%--                <th>Name EN</th>--%>
<%--                <th>Place UA</th>--%>
<%--                <th>Place EN</th>--%>
<%--                <th>Description UA</th>--%>
<%--                <th>Description EN</th>--%>
<%--                <th>Date</th>--%>
<%--                <th>Photo</th>--%>
<%--                <th>Actions</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <!-- for(Todo todo: todos) { -->--%>
<%--            <c:forEach var="event" items="${eventsList}">--%>
<%--                <c:choose>--%>
<%--                   <c:when test="${(eventStatus=='finished' && event.isFinished()==true) || (eventStatus=='upcoming' && event.isFinished()==false) || eventStatus=='all' || eventStatus==null}">--%>
<%--                        <tr>--%>
<%--                            <td>--%>
<%--                                <c:out value="${event.event_id}"/>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:out value="${event.event_name_ua}"/>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:out value="${event.event_name_en}"/>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:out value="${event.event_place_ua}"/>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:out value="${event.event_place_en}"/>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:out value="${event.event_description_ua}"/>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:out value="${event.event_description_en}"/>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:out value="${event.event_date}"/>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:out value="${event.event_photo_url}"/>--%>
<%--                            </td>--%>
<%--                            <td><a href="edit?id=<c:out value='${event.event_id}' />">Edit</a>--%>
<%--                                &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${event.event_id}' />">Delete</a>--%>
<%--                               <a href="event?id=<c:out value='${event.event_id}' />">View more</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--            </c:forEach>--%>
<%--            <!-- } -->--%>
<%--            </tbody>--%>

<%--        </table>--%>
        <div class="card-columns">
    <c:forEach var="event" items="${eventsList}">
       <c:choose><c:when test="${(eventStatus=='finished' && event.isFinished()==true) || (eventStatus=='upcoming' && event.isFinished()==false) || eventStatus=='all' || eventStatus==null}">
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="/resources/images/events/${event.event_photo_url}" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">${event.event_name_en}</h5>
                <p class="card-text">${event.event_description_en}</p>
                <a href="eventEvent?id=<c:out value='${event.event_id}' />" class="btn btn-primary">View more</a>
                <a href="editEvent?id=<c:out value='${event.event_id}' />" class="btn btn-success">Edit</a>
                <a href="deleteEvent?id=<c:out value='${event.event_id}' />" class="btn btn-danger">Delete</a>
            </div>
        </div>
       </c:when>
           <c:otherwise>
           </c:otherwise>
       </c:choose>
    </c:forEach>
    </div>
</div>
</div>
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
            <% request.getSession().removeAttribute("status");%>
            swal("Congrats", "You have successfully registered", "success");
    }
    if(status=="successLogin") {
             <% request.getSession().removeAttribute("status");%>
            swal("Congrats", "You have successfully login", "success");
    }
</script>
</body>
</html>
