<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="customtag" prefix="custom" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html>
<head>
    <title><fmt:message key="profile.title" /></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="/resources/images/logo.png" type="image/png">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
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
<input type="hidden" id="editStatus" value="<%=request.getAttribute("editStatus")%>">
<input type="hidden" id="lang" value="<%=request.getSession().getAttribute("lang")%>">
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

<div class="container">
    <div class="main-body">
        <div class="row align-items-center justify-content-center">
            <div class="col-lg-7">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <c:choose>
                                <c:when test="${sessionScope.role_id==1}"> <img src="resources/images/avatars/moderator.jpg" alt="Moderator" class="rounded-circle p-1 bg-primary" width="110"></c:when>
                                <c:when test="${sessionScope.role_id==2}"> <img src="resources/images/avatars/speaker.jpg" alt="Speaker" class="rounded-circle p-1 bg-primary" width="110"></c:when>
                                <c:otherwise> <img src="resources/images/avatars/user.jpg" alt="User" class="rounded-circle p-1 bg-primary" width="110"></c:otherwise>
                            </c:choose>
                            <div class="mt-3">
                                <h4>${sessionScope.name} ${sessionScope.surname}</h4>
                                <c:choose>
                                    <c:when test="${sessionScope.role_id==1}"><p class="text-secondary mb-1"><fmt:message key="profile.moderator" /></p></c:when>
                                    <c:when test="${sessionScope.role_id==2}"><p class="text-secondary mb-1"><fmt:message key="profile.speaker" /></p></c:when>
                                    <c:otherwise><p class="text-secondary mb-1"><fmt:message key="profile.user" /></p></c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <form action="editInfo" method="post">
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.name" /></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" name="name" class="form-control"  value="<c:out value='${name}' />" required="required">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.surname" /></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" name="surname" class="form-control"  value="<c:out value='${surname}' />" required="required">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.password" /></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="password" name="password" class="form-control"  value="">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.phone" /></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" name="phone" class="form-control"  value="<c:out value='${phone}' />" required="required">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.email" /></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="email" name="email" class="form-control"  value="<c:out value='${email}' />" required="required">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.location" /></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" name="location" class="form-control"  value="<c:out value='${location}' />" required="required">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-secondary">
<%--                                <input type="button" class="btn btn-primary px-4" value="Save Changes">--%>
                                    <button type="submit" class="btn btn-primary px-4"><fmt:message key="button.save" /></button>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <c:choose>
        <c:when test="${sessionScope.role_id==1}">

            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center"><fmt:message key="profile.activepreps" /></h3></div>
                <hr>
                <div class="container text-center">
                    <c:choose>
                        <c:when test="${prepositionStatus==null||prepositionStatus=='fromModerator'}"> <a href="showProfile?prepositionStatus=fromModerator" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="profile.frommoderator" /></a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=fromModerator" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.frommoderator" /></a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${prepositionStatus=='fromSpeaker'}"> <a href="showProfile?prepositionStatus=fromSpeaker" class="btn btn-success" style="background-color: #1E93F9"><fmt:message key="profile.fromspeaker" /></a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=fromSpeaker" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.fromspeaker" /></a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${prepositionStatus=='forEvent'}"> <a href="showProfile?prepositionStatus=forEvent" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="profile.reportsforevent" /></a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=forEvent" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.reportsforevent" /></a></c:otherwise>
                    </c:choose>
                </div>
                <br>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="profile.id" /></th>
                        <th scope="col"><fmt:message key="profile.event" /></th>
                        <th scope="col"><fmt:message key="profile.report" /></th>
                        <th scope="col"><fmt:message key="profile.speaker_id" /></th>
                        <th scope="col"><fmt:message key="profile.status" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="pos" value="${0}"/>
                    <c:forEach var="prep" items="${Preps}">
                        <tr>
                            <td>${prep.id}</td>
                            <c:choose>
                                <c:when test="${sessionScope.lang.equals('en')}">
                                    <td>${propEvents.get(pos).event_name_en}</td>
                                    <td>${propReports.get(pos).report_name_en}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${propEvents.get(pos).event_name_ua}</td>
                                    <td>${propReports.get(pos).report_name_ua}</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${prep.speaker_id}</td>
                            <c:choose>
                                <c:when test="${prepositionStatus==null||prepositionStatus=='fromModerator'}">
                                    <td><a href="rejectSpeakerFromModerator?report_id=${propReports.get(pos).getReport_id()}&speaker_id=${prep.speaker_id}" class="btn btn-danger"><fmt:message key="profile.reject" /></a></td>
                                </c:when>
                                <c:when test="${prepositionStatus=='fromSpeaker'}">
                                    <td><a href="acceptSpeakerForReport?report_id=${propReports.get(pos).getReport_id()}&speaker_id=${prep.speaker_id}" class="btn btn-success"><fmt:message key="profile.accept" /></a>
                                        <a href="rejectSpeakerForReport?id=${prep.id}" class="btn btn-danger"><fmt:message key="profile.reject" /></a></td>
                                </c:when>
                                <c:when test="${prepositionStatus=='forEvent'}">
                                    <td><a href="acceptSpeakerReports?id=${prep.id}" class="btn btn-success"><fmt:message key="profile.accept" /></a>
                                        <a href="rejectSpeakerReports?id=${prep.id}" class="btn btn-danger"><fmt:message key="profile.reject" /></a></td>
                                </c:when>

                            </c:choose>
                            <c:set var="pos" value="${pos+1}"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <hr>
            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center"><fmt:message key="profile.users" /></h3></div>
                <hr>
                <div class="container text-center">
                    <c:choose>
                        <c:when test="${userStatus==null||userStatus=='all'}"> <a href="showProfile?userStatus=all" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="profile.allusers" /></a></c:when>
                        <c:otherwise> <a href="showProfile?userStatus=all" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.allusers" /></a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${userStatus=='users'}"> <a href="showProfile?userStatus=users" class="btn btn-success" style="background-color: #1E93F9"><fmt:message key="profile.users" /></a></c:when>
                        <c:otherwise> <a href="showProfile?userStatus=users" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.users" /></a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${userStatus=='speakers'}"> <a href="showProfile?userStatus=speakers" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="profile.speakers" /></a></c:when>
                        <c:otherwise> <a href="showProfile?userStatus=speakers" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.speakers" /></a></c:otherwise>
                    </c:choose>
                </div>
                <br>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="profile.userid" /></th>
                        <th scope="col"><fmt:message key="profile.roleid" /></th>
                        <th scope="col"><fmt:message key="profile.username" /></th>
                        <th scope="col"><fmt:message key="profile.usersurname" /></th>
                        <th scope="col"><fmt:message key="profile.email" /></th>
                        <th scope="col"><fmt:message key="profile.phone" /></th>
                        <th scope="col"><fmt:message key="profile.location" /></th>
                        <th scope="col"><fmt:message key="profile.action" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.role_id}</td>
                            <td>${user.user_name}</td>
                            <td>${user.user_surname}</td>
                            <td>${user.user_email}</td>
                            <td>${user.user_phone}</td>
                            <td>${user.user_address}</td>
                            <td><a href="editUser?user_id=${user.id}" class="btn btn-success"><fmt:message key="button.edit" /></a>
                                <a href="deleteUser?user_id=${user.id}" class="btn btn-danger"><fmt:message key="button.delete" /></a></td>
                            <c:set var="pos" value="${pos+1}"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </c:when>
        <c:when test="${sessionScope.role_id==2}"><p class="text-secondary mb-1">

            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center"><fmt:message key="profile.eventwithmyperf" /></h3></div>
                <hr>
                <div class="container text-center">
                    <c:choose>
                        <c:when test="${eventStatus==null||eventStatus=='all'}"> <a href="showProfile?eventStatus=all" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="profile.allevents" /></a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=all" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.allevents" /></a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${eventStatus=='finished'}"> <a href="showProfile?eventStatus=finished" class="btn btn-success" style="background-color: #1E93F9"><fmt:message key="profile.finishedevents" /></a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=finished" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.finishedevents" /></a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${eventStatus=='upcoming'}"> <a href="showProfile?eventStatus=upcoming" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="profile.upcoming" /></a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=upcoming" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.upcoming" /></a></c:otherwise>
                    </c:choose>
                </div>
                <br>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="profile.id" /></th>
                        <th scope="col"><fmt:message key="profile.name" /></th>
                        <th scope="col"><fmt:message key="profile.place" /></th>
                        <th scope="col"><fmt:message key="profile.date" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="event" items="${events}">
                        <tr>
                            <td>${event.event_id}</td>
                            <c:choose>
                                <c:when test="${sessionScope.lang.equals('en')}">
                                    <td>${event.event_name_en}</td>
                                    <td>${event.event_place_en}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${event.event_name_ua}</td>
                                    <td>${event.event_place_ua}</td>
                                </c:otherwise>
                            </c:choose>
                            <td> <custom:dateFormatter date="${event.event_date}" format="dd-MM-yyyy HH:mm" /></td>
<%--                            <td>${event.event_date}</td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <hr>
            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center"><fmt:message key="profile.activepreps" /></h3></div>
                <hr>
                <div class="container text-center">
                    <c:choose>
                        <c:when test="${prepositionStatus==null||prepositionStatus=='fromModerator'}"> <a href="showProfile?prepositionStatus=fromModerator" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="profile.frommoderator" /></a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=fromModerator" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.frommoderator" /></a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${prepositionStatus=='fromSpeaker'}"> <a href="showProfile?prepositionStatus=fromSpeaker" class="btn btn-success" style="background-color: #1E93F9"><fmt:message key="profile.forreport" /></a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=fromSpeaker" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.forreport" /></a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${prepositionStatus=='forEvent'}"> <a href="showProfile?prepositionStatus=forEvent" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="profile.reportsforevent" /></a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=forEvent" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.reportsforevent" /></a></c:otherwise>
                    </c:choose>
                </div>
                <br>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="profile.id" /></th>
                        <th scope="col"><fmt:message key="profile.event" /></th>
                        <th scope="col"><fmt:message key="profile.report" /></th>
                        <th scope="col"><fmt:message key="profile.status" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="pos" value="${0}"/>
                    <c:forEach var="prepositions" items="${propEvents}">
                        <tr>
                            <td>${prepositions.event_id}</td>
                            <c:choose>
                                <c:when test="${sessionScope.lang.equals('en')}">
                                    <td>${prepositions.event_name_en}</td>
                                    <td>${propReports.get(pos).report_name_en}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${prepositions.event_name_ua}</td>
                                    <td>${propReports.get(pos).report_name_ua}</td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${prepositionStatus==null||prepositionStatus=='fromModerator'}">
                                    <td><a href="acceptSpeakerForReport?report_id=${propReports.get(pos).getReport_id()}&speaker_id=${moderatorPreps.get(pos).speaker_id}" class="btn btn-success"><fmt:message key="profile.accept" /></a>
                                        <a href="rejectSpeakerFromModerator?report_id=${propReports.get(pos).report_id}&speaker_id=${speaker_id}" class="btn btn-danger"><fmt:message key="profile.reject" /></a></td>
                                </c:when>
                                <c:when test="${prepositionStatus=='fromSpeaker'}">
                                    <td><a href="rejectSpeakerForReport?id=${speakerPreps.get(pos).getId()}" class="btn btn-danger"><fmt:message key="profile.reject" /></a></td>
                                </c:when>
                                <c:when test="${prepositionStatus=='forEvent'}">
                                    <td><a href="rejectSpeakerReports?id=${propReports.get(pos).getId()}&speaker_id=${speaker_id}" class="btn btn-danger"><fmt:message key="profile.reject" /></a></td>
                                </c:when>
                            </c:choose>
                            <c:set var="pos" value="${pos+1}"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </p></c:when>
        <c:otherwise><p class="text-secondary mb-1">

            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center"><fmt:message key="profile.myevents" /></h3></div>
                <hr>
                <div class="container text-center">
                    <c:choose>
                        <c:when test="${eventStatus==null||eventStatus=='all'}"> <a href="showProfile?eventStatus=all" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="profile.allevents" /></a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=all" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.allevents" /></a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${eventStatus=='finished'}"> <a href="showProfile?eventStatus=finished" class="btn btn-success" style="background-color: #1E93F9"><fmt:message key="profile.finishedevents" /></a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=finished" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.finishedevents" /></a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${eventStatus=='upcoming'}"> <a href="showProfile?eventStatus=upcoming" class="btn btn-success" style="background-color: #1E93F9;"><fmt:message key="profile.upcoming" /></a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=upcoming" class="btn btn-success" style="background-color: #0074D9;"><fmt:message key="profile.upcoming" /></a></c:otherwise>
                    </c:choose>
                </div>
                <br>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="profile.id" /></th>
                        <th scope="col"><fmt:message key="profile.name" /></th>
                        <th scope="col"><fmt:message key="profile.place" /></th>
                        <th scope="col"><fmt:message key="profile.date" /></th>
                        <th scope="col"><fmt:message key="profile.status" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="cntRows" value="${events.size()}"/>
                    <c:set var="pos" value="${0}"/>
                    <c:forEach var="event" items="${events}">
                    <tr>
                        <td>${event.event_id}</td>
                        <c:choose>
                            <c:when test="${sessionScope.lang.equals('en')}">
                                <td>${event.event_name_en}</td>
                                <td>${event.event_place_en}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${event.event_name_ua}</td>
                                <td>${event.event_place_ua}</td>
                            </c:otherwise>
                        </c:choose>
                        <td> <custom:dateFormatter date="${event.event_date}" format="dd-MM-yyyy HH:mm" /></td>
                        <td>${presense.get(pos)}</td>
                        <c:set var="pos" value="${pos+1}"/>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </p></c:otherwise>
    </c:choose>
    <custom:copyright/>
</div>

<style type="text/css">
    body{
        background: #f7f7ff;
        margin-top:0px;
    }
    .card {
        position: relative;
        display: flex;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 0 solid transparent;
        border-radius: .25rem;
        margin-bottom: 1.5rem;
        box-shadow: 0 2px 6px 0 rgb(218, 218, 253 / 65%), 0 2px 6px 0 rgb(206 206 238 / 54%);
    }
    .me-2 {
        margin-right: .5rem!important;
    }
</style>


<!--- JS -->
<script src="vendor/jquery/jguery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweatalert.css ">
<script type="text/javascript">
    var editStatus = document.getElementById("editStatus").value;
    var lang = document.getElementById("lang").value;
    if(lang=="en") {
        if (editStatus == "emptyFirstname") {
            swal("Error", "First name should not be empty", "error");
            //alert("First name should not be empty");
        }
        if (editStatus == "emptyLastname") {
            swal("Error", "Last name should not be empty", "error");
            //alert("Last name should not be empty");
        }
        if (editStatus == "emptyPassword") {
            swal("Error", "Password should not be empty", "error");
            //alert("Password should not be empty");
        }
        if (editStatus == "emptyPhone") {
            swal("Error", "Phone should not be empty", "error");
            //alert("Phone should not be empty");
        }
        if (editStatus == "emptyEmail") {
            swal("Error", "Email should not be empty", "error");
            //alert("Email should not be empty");
        }
        if (editStatus == "emptyAddress") {
            swal("Error", "Location should not be empty", "error");
            //alert("Location should not be empty");
        }
        if (editStatus == "invalidConfirmpassword") {
            swal("Error", "Password not match", "error");
            //alert("Password not match");
        }
        if (editStatus == "invalidFirstname") {
            swal("Error", "First name should starts with a capital letter and no more than 25 letters long", "error");
            //alert("First name should starts with a capital letter and no more than 25 letters long")
        }
        if (editStatus == "invalidLastname") {
            swal("Error", "Last name should starts with a capital letter and no more than 25 letters long", "error");
            //alert("Last name should starts with a capital letter and no more than 25 letters long")
        }
        if (editStatus == "invalidPassword") {
            swal("Error", "Password must consist of at least 4 characters and not more than 16 characters", "error");
            // alert("Password must consist of at least 4 characters and not more than 16 characters")
        }
        if (editStatus == "invalidPhone") {
            swal("Error", "Phone must starts with + and has 9 digits.", "error");
            //  alert("Phone must starts with + and has 9 digits.")
        }
        if (editStatus == "invalidEmail") {
            swal("Error", "Email must has no less than 1 letter before '@', 2 letters after and domain is no longer than 4 characters", "error");
            // alert("Email must has no less than 1 letter before '@', 2 letters after and domain is no longer than 4 characters")
        }
        if (editStatus == "dublicateEmail") {
            swal("Error", "Current email is already registered. Try to log in.", "error");
            // alert("Current email is already registered. Try to log in.")
        }
        if (editStatus == "dublicatePhone") {
            swal("Error", "Current phone is already registered. Try to log in.", "error");
            // alert("Current phone is already registered. Try to log in.")
        }
        if (editStatus == "success") {
            swal("Congrats", "You have successfully updated information", "success");
            // alert("You have successfully registered.")
        }
        if (editStatus == "servererror") {
            swal("Error", "It is technical work on the site. Please try again.", "error");
            //alert("It is technical work on the site. Please try again.")
        }
    } else {
        if (editStatus == "emptyFirstname") {
            swal("Помилка", "Ім'я не повинно бути порожнє", "error");
            //alert("First name should not be empty");
        }
        if (editStatus == "emptyLastname") {
            swal("Помилка", "Прізвище не повинно бути порожнє", "error");
            //alert("Last name should not be empty");
        }
        if (editStatus == "emptyPassword") {
            swal("Помилка", "Пароль не  повинен бути порожнім", "error");
            //alert("Password should not be empty");
        }
        if (editStatus == "emptyPhone") {
            swal("Помилка", "Телефон не  повинен бути порожнім", "error");
            //alert("Phone should not be empty");
        }
        if (editStatus == "emptyEmail") {
            swal("Помилка", "Емейл не  повинен бути порожнім", "error");
            //alert("Email should not be empty");
        }
        if (editStatus == "emptyAddress") {
            swal("Помилка", "Адрес не  повинен бути порожнім", "error");
            //alert("Location should not be empty");
        }
        if (editStatus == "invalidConfirmpassword") {
            swal("Помилка", "Паролі не співпадають", "error");
            //alert("Password not match");
        }
        if (editStatus == "invalidFirstname") {
            swal("Помилка", "Ім'я повинно починатися з великої літери, та мати довжину не більше ніж 25 символів", "error");
            //alert("First name should starts with a capital letter and no more than 25 letters long")
        }
        if (editStatus == "invalidLastname") {
            swal("Помилка", "Прізвище повинно починатися з великої літери, та мати довжину не більше ніж 25 символів", "error");
            //alert("Last name should starts with a capital letter and no more than 25 letters long")
        }
        if (editStatus == "invalidPassword") {
            swal("Помилка", "Пароль має мати довджину від 4 до 16 символів", "error");
            // alert("Password must consist of at least 4 characters and not more than 16 characters")
        }
        if (editStatus == "invalidPhone") {
            swal("Помилка", "Телефон має починатися з + і мати 9 знаків", "error");
            //  alert("Phone must starts with + and has 9 digits.")
        }
        if (editStatus == "invalidEmail") {
            swal("Помилка", "Емейл має мати не менше, ніж 1 літеру до @, дві після і, щонайменше, 4 в домені", "error");
            // alert("Email must has no less than 1 letter before '@', 2 letters after and domain is no longer than 4 characters")
        }
        if (editStatus == "dublicateEmail") {
            swal("Помилка", "Такий емейл вже зареєстрований. Попробуйте увійти", "error");
            // alert("Current email is already registered. Try to log in.")
        }
        if (editStatus == "dublicatePhone") {
            swal("Помилка", "Такий телефон вже зареєстрований. Попробуйте увійти", "error");
            // alert("Current phone is already registered. Try to log in.")
        }
        if (editStatus == "success") {
            swal("Успіх", "Ви успішно оновили інформацію", "success");
            // alert("You have successfully registered.")
        }
        if (editStatus == "servererror") {
            swal("Помилка", "На сайті технічні роботи, повторіть спробу пізніше", "error");
            //alert("It is technical work on the site. Please try again.")
        }
    }

</script>
</body>
</html>
