<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html>
<head>
    <title><fmt:message key="eventlist.title" /></title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="shortcut icon" href="/resources/images/logo.png" type="image/png">
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
<input type="hidden" id="lang" value="${sessionScope.lang}">
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

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center"><fmt:message key="eventlist.listofevent" /></h3>
        <hr>
        <div class="container text-center">

            <c:choose><c:when test="${sessionScope.role_id==1}">
                <a href="newEvent" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="eventlist.addnewevent" /></a>
            </c:when></c:choose>
            <c:choose>
                <c:when test="${eventStatus==null||eventStatus=='all'}"> <a href="listEvent?eventStatus=all" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="eventlist.allevents" /></a></c:when>
                <c:otherwise> <a href="listEvent?eventStatus=all" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="eventlist.allevents" /></a></c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${eventStatus=='finished'}"> <a href="listEvent?eventStatus=finished" class="btn btn-success" style="background-color: #1E93F9"><fmt:message key="eventlist.finishedevents" /></a></c:when>
                <c:otherwise> <a href="listEvent?eventStatus=finished" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="eventlist.finishedevents" /></a></c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${eventStatus=='upcoming'}"> <a href="listEvent?eventStatus=upcoming" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="eventlist.upcomingevents" /></a></c:when>
                <c:otherwise> <a href="listEvent?eventStatus=upcoming" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="eventlist.upcomingevents" /></a></c:otherwise>
            </c:choose>
            <button class="btn btn-secondary dropdown-toggle" style="background-color: #1E93F9" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <fmt:message key="eventlist.orderby" />
            </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="listEvent?orderBy=date"><fmt:message key="eventlist.date" /></a>
                    <a class="dropdown-item" href="listEvent?orderBy=reports"><fmt:message key="eventlist.reportscount" /></a>
                    <a class="dropdown-item" href="listEvent?orderBy=users"><fmt:message key="eventlist.userscount" /></a>
                    <a class="dropdown-item" href="listEvent"><fmt:message key="eventlist.default" /></a>
                </div>
        </div>
        <br>
        <div class="card-columns">
    <c:forEach var="event" items="${eventsList}">
       <c:choose><c:when test="${(eventStatus=='finished' && event.isFinished()==true) || (eventStatus=='upcoming' && event.isFinished()==false) || eventStatus=='all' || eventStatus==null}">
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="/resources/images/events/${event.event_photo_url}" alt="Card image cap">
            <div class="card-body">
                <c:choose>
                    <c:when test="${sessionScope.lang.equals('en')}">
                        <h5 class="card-title">${event.event_name_en}</h5>
                        <p class="card-text">${event.event_description_en}</p>
                    </c:when>
                    <c:otherwise>
                        <h5 class="card-title">${event.event_name_ua}</h5>
                        <p class="card-text">${event.event_description_ua}</p>
                    </c:otherwise>
                </c:choose>
                <a href="eventEvent?id=<c:out value='${event.event_id}' />" class="btn btn-primary"><fmt:message key="eventlist.viewmore" /></a>
                <c:choose><c:when test="${sessionScope.role_id==1}">
                    <a href="editEvent?id=<c:out value='${event.event_id}' />" class="btn btn-success"><fmt:message key="button.edit" /></a>
                    <a href="deleteEvent?id=<c:out value='${event.event_id}' />" class="btn btn-danger"><fmt:message key="button.delete" /></a>
                </c:when></c:choose>

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
    var lang = document.getElementById("lang").value;
    if(lang=="en") {
        if (status == "registered") {
            swal("Error", "You are already logged in.", "error");
        }
        if (status == "successRegister") {
            <% request.getSession().removeAttribute("status");%>
            swal("Congrats", "You have successfully registered", "success");
        }
        if (status == "successLogin") {
            <% request.getSession().removeAttribute("status");%>
            swal("Congrats", "You have successfully login", "success");
        }
    } else {
        if (status == "registered") {
            swal("Помилка", "Ви вже увійшли", "error");
        }
        if (status == "successRegister") {
            <% request.getSession().removeAttribute("status");%>
            swal("Успіх", "Ви успішно зареєструвалися", "success");
        }
        if (status == "successLogin") {
            <% request.getSession().removeAttribute("status");%>
            swal("Успіх", "Ви успішно увійшли", "success");
        }
    }
</script>
</body>
</html>
