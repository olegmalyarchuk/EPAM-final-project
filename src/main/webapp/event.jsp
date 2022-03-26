<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Event</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
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
<p class="fw-bolder text-center fs-3">${event.event_name_en}</p>
<div class="row">
    <div class="mx-auto d-block">
        <img src="/resources/images/events/${event.event_photo_url}" style="height: 300px;" class="mx-auto d-block" alt="Responsive image">
    </div>
</div>
<p class="fw-bold fs-6">About what ?</p>
<p class="fw-normal fs-5">${event.event_description_en}</p>
<p class="fw-bold fs-6">Where ?</p>
<p class="fw-normal fs-5">${event.event_place_en}</p>
<p class="fw-bold fs-6">When ?</p>
<p class="fw-normal fs-5">${event.event_date}</p>
<c:set var="cntRows" value="${reports.size()}"/>
<c:set var="pos" value="${0}"/>
<c:choose>
    <c:when test="${event.isFinished()}">
        <hr>
        <p class="fw-normal fs-5">Registered users: ${registered}&nbsp&nbsp&nbspPresent: ${present}</p>
    </c:when>
    <c:otherwise>
        <hr>
        <p class="fw-normal fs-5">Registered users: ${registered}</p>
    </c:otherwise>
</c:choose>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Report name</th>
        <th scope="col">Speaker</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="report" items="${reports}">
    <tr>
        <td>${report.report_name_en}</td>
        <c:choose>
            <c:when test="${speakers.get(pos).user_name=='null'}">
                <td>No speaker has been appointed &nbsp&nbsp&nbsp&nbsp&nbsp
            <c:choose>
                <c:when test="${sessionScope.role_id==2 && !event.isFinished()}">
                <td><a href="#" class="btn btn-success">Propose me</a></td>
                </td>
                </c:when>
                 <c:when test="${sessionScope.role_id==1 && !event.isFinished()}">
                    <td><a href="#" class="btn btn-success">Propose speaker</a>
                        <a href="deleteReport?report_id=${report.report_id}" class="btn btn-danger">Delete</a>
                        <a href="editReport?report_id=${report.report_id}&event_id=${event.event_id}" class="btn btn-success">Edit</a>
                     </td>
                 </c:when>
                <c:otherwise><td></td></c:otherwise>
              </c:choose>
            </c:when>
            <c:otherwise>
                <td>${speakers.get(pos).user_name} ${speakers.get(pos).user_surname}</td>
                <c:choose>
                    <c:when test="${sessionScope.role_id==1 && !event.isFinished()}">
                        <td><a href="editReport?report_id=${report.report_id}&event_id=${event.event_id}" class="btn btn-success">Edit</a>
                            <a href="deleteReport?report_id=${report.report_id}" class="btn btn-danger">Delete</a></td>
                    </c:when>
                    <c:otherwise><td></td></c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
        <c:set var="pos" value="${pos+1}"/>
    </tr>
    </c:forEach>
    </tbody>
</table>

<c:choose>
    <c:when test="${!event.isFinished()}">
        <c:choose>
            <c:when test="${sessionScope.role_id==1}">
                <a href="addReport?event_id=${event.event_id}" class="btn btn-success">Add report for this event</a>
            </c:when>
            <c:when test="${sessionScope.role_id==2}">
                <a href="proposeReport?event_id=${event.event_id}" class="btn btn-success">Propose report for this event</a>
            </c:when>
            <c:when test="${sessionScope.role_id==3 && isRegister=='no'}">
                <a href="registerUser?email=${sessionScope.email}&event_id=${event.event_id}" class="btn btn-success">Register</a>
            </c:when>
            <c:when test="${sessionScope.role_id==3 && isRegister=='yes'}">
                <a href="excludeUser?email=${sessionScope.email}&event_id=${event.event_id}" class="btn btn-danger">Exclude me</a>
            </c:when>
        </c:choose>
    </c:when>
</c:choose>

<!--- JS -->
<script src="vendor/jquery/jguery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweatalert.css ">
<script type="text/javascript">
</script>
</body>
</html>
