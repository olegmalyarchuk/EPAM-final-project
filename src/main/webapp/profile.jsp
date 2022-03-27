<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
                                <h4>John Doe</h4>
                                <c:choose>
                                    <c:when test="${sessionScope.role_id==1}"><p class="text-secondary mb-1">Moderator</p></c:when>
                                    <c:when test="${sessionScope.role_id==2}"><p class="text-secondary mb-1">Speaker</p></c:when>
                                    <c:otherwise><p class="text-secondary mb-1">User</p></c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <form action="editInfo" method="post">
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" name="name" class="form-control"  value="<c:out value='${name}' />" required="required">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Surname</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" name="surname" class="form-control"  value="<c:out value='${surname}' />" required="required">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Password</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="password" name="password" class="form-control"  value="">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Phone</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" name="phone" class="form-control"  value="<c:out value='${phone}' />" required="required">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="email" name="email" class="form-control"  value="<c:out value='${email}' />" required="required">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Location</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" name="location" class="form-control"  value="<c:out value='${location}' />" required="required">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-secondary">
<%--                                <input type="button" class="btn btn-primary px-4" value="Save Changes">--%>
                                    <button type="submit" class="btn btn-primary px-4">Save</button>
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
                <div class="container text-center"><h3 class="text-center">Active prepositions</h3></div>
                <hr>
                <div class="container text-center">
                    <c:choose>
                        <c:when test="${prepositionStatus==null||prepositionStatus=='fromModerator'}"> <a href="showProfile?prepositionStatus=fromModerator" class="btn btn-success" style="background-color: #1E93F9;">From moderator</a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=fromModerator" class="btn btn-success" style="background-color: #0074D9;">From moderator</a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${prepositionStatus=='fromSpeaker'}"> <a href="showProfile?prepositionStatus=fromSpeaker" class="btn btn-success" style="background-color: #1E93F9">From speaker</a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=fromSpeaker" class="btn btn-success" style="background-color: #0074D9;">From speaker</a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${prepositionStatus=='forEvent'}"> <a href="showProfile?prepositionStatus=forEvent" class="btn btn-success" style="background-color: #1E93F9;">Reports for event</a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=forEvent" class="btn btn-success" style="background-color: #0074D9;">Reports for event</a></c:otherwise>
                    </c:choose>
                </div>
                <br>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Event</th>
                        <th scope="col">Report</th>
                        <th scope="col">Speaker_id</th>
                        <th scope="col">Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="pos" value="${0}"/>
                    <c:forEach var="prep" items="${Preps}">
                        <tr>
                            <td>${prep.id}</td>
                            <td>${propEvents.get(pos).event_name_en}</td>
                            <td>${propReports.get(pos).report_name_en}</td>
                            <td>${prep.speaker_id}</td>
                            <c:choose>
                                <c:when test="${prepositionStatus==null||prepositionStatus=='fromModerator'}">
                                    <td><a href="rejectSpeakerFromModerator?report_id=${propReports.get(pos).getReport_id()}&speaker_id=${prep.speaker_id}" class="btn btn-danger">Reject</a></td>
                                </c:when>
                                <c:when test="${prepositionStatus=='fromSpeaker'}">
                                    <td><a href="acceptSpeakerForReport?report_id=${propReports.get(pos).getReport_id()}&speaker_id=${prep.speaker_id}" class="btn btn-success">Accept</a>
                                        <a href="rejectSpeakerForReport?id=${prep.id}" class="btn btn-danger">Reject</a></td>
                                </c:when>
                                <c:when test="${prepositionStatus=='forEvent'}">
                                    <td><a href="acceptSpeakerReports?id=${prep.id}" class="btn btn-success">Accept</a>
                                        <a href="rejectSpeakerReports?id=${prep.id}" class="btn btn-danger">Reject</a></td>
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
                <div class="container text-center"><h3 class="text-center">Users</h3></div>
                <hr>
                <div class="container text-center">
                    <c:choose>
                        <c:when test="${userStatus==null||userStatus=='all'}"> <a href="showProfile?userStatus=all" class="btn btn-success" style="background-color: #1E93F9;">All users</a></c:when>
                        <c:otherwise> <a href="showProfile?userStatus=all" class="btn btn-success" style="background-color: #0074D9;">All users</a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${userStatus=='users'}"> <a href="showProfile?userStatus=users" class="btn btn-success" style="background-color: #1E93F9">Users</a></c:when>
                        <c:otherwise> <a href="showProfile?userStatus=users" class="btn btn-success" style="background-color: #0074D9;">Users</a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${userStatus=='speakers'}"> <a href="showProfile?userStatus=speakers" class="btn btn-success" style="background-color: #1E93F9;">Speakers</a></c:when>
                        <c:otherwise> <a href="showProfile?userStatus=speakers" class="btn btn-success" style="background-color: #0074D9;">Speaker</a></c:otherwise>
                    </c:choose>
                </div>
                <br>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">User_id</th>
                        <th scope="col">Role_id</th>
                        <th scope="col">User_name</th>
                        <th scope="col">User_surname</th>
                        <th scope="col">User_email</th>
                        <th scope="col">User_phone</th>
                        <th scope="col">User_address</th>
                        <th scope="col">Action</th>
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
                            <td><a href="editUser?user_id=${user.id}" class="btn btn-success">Edit</a>
                                <a href="deleteUser?user_id=${user.id}" class="btn btn-danger">Delete</a></td>
                            <c:set var="pos" value="${pos+1}"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </c:when>
        <c:when test="${sessionScope.role_id==2}"><p class="text-secondary mb-1">

            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center">Events with my performance</h3></div>
                <hr>
                <div class="container text-center">
                    <c:choose>
                        <c:when test="${eventStatus==null||eventStatus=='all'}"> <a href="showProfile?eventStatus=all" class="btn btn-success" style="background-color: #1E93F9;">All events</a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=all" class="btn btn-success" style="background-color: #0074D9;">All events</a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${eventStatus=='finished'}"> <a href="showProfile?eventStatus=finished" class="btn btn-success" style="background-color: #1E93F9">Finished events</a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=finished" class="btn btn-success" style="background-color: #0074D9;">Finished events</a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${eventStatus=='upcoming'}"> <a href="showProfile?eventStatus=upcoming" class="btn btn-success" style="background-color: #1E93F9;">Upcoming events</a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=upcoming" class="btn btn-success" style="background-color: #0074D9;">Upcoming events</a></c:otherwise>
                    </c:choose>
                </div>
                <br>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Place</th>
                        <th scope="col">Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="event" items="${events}">
                        <tr>
                            <td>${event.event_id}</td>
                            <td>${event.event_name_en}</td>
                            <td>${event.event_place_en}</td>
                            <td>${event.event_date}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <hr>
            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center">Active prepositions</h3></div>
                <hr>
                <div class="container text-center">
                    <c:choose>
                        <c:when test="${prepositionStatus==null||prepositionStatus=='fromModerator'}"> <a href="showProfile?prepositionStatus=fromModerator" class="btn btn-success" style="background-color: #1E93F9;">From moderator</a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=fromModerator" class="btn btn-success" style="background-color: #0074D9;">From moderator</a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${prepositionStatus=='fromSpeaker'}"> <a href="showProfile?prepositionStatus=fromSpeaker" class="btn btn-success" style="background-color: #1E93F9">For report</a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=fromSpeaker" class="btn btn-success" style="background-color: #0074D9;">For report</a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${prepositionStatus=='forEvent'}"> <a href="showProfile?prepositionStatus=forEvent" class="btn btn-success" style="background-color: #1E93F9;">Reports for event</a></c:when>
                        <c:otherwise> <a href="showProfile?prepositionStatus=forEvent" class="btn btn-success" style="background-color: #0074D9;">Reports for event</a></c:otherwise>
                    </c:choose>
                </div>
                <br>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Event</th>
                        <th scope="col">Report</th>
                        <th scope="col">Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="pos" value="${0}"/>
                    <c:forEach var="prepositions" items="${propEvents}">
                        <tr>
                            <td>${prepositions.event_id}</td>
                            <td>${prepositions.event_name_en}</td>
                            <td>${propReports.get(pos).report_name_en}</td>
                            <c:choose>
                                <c:when test="${prepositionStatus==null||prepositionStatus=='fromModerator'}">
                                    <td><a href="acceptSpeakerForReport?report_id=${propReports.get(pos).getReport_id()}&speaker_id=${moderatorPreps.get(pos).speaker_id}" class="btn btn-success">Accept</a>
                                        <a href="rejectSpeakerFromModerator?report_id=${propReports.get(pos).report_id}&speaker_id=${speaker_id}" class="btn btn-danger">Reject</a></td>
                                </c:when>
                                <c:when test="${prepositionStatus=='fromSpeaker'}">
                                    <td><a href="rejectSpeakerForReport?id=${speakerPreps.get(pos).getId()}" class="btn btn-danger">Reject</a></td>
                                </c:when>
                                <c:when test="${prepositionStatus=='forEvent'}">
                                    <td><a href="rejectSpeakerReports?id=${propReports.get(pos).getId()}&speaker_id=${speaker_id}" class="btn btn-danger">Reject</a></td>
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
                <div class="container text-center"><h3 class="text-center">My Events</h3></div>
                <hr>
                <div class="container text-center">
                    <c:choose>
                        <c:when test="${eventStatus==null||eventStatus=='all'}"> <a href="showProfile?eventStatus=all" class="btn btn-success" style="background-color: #1E93F9;">All events</a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=all" class="btn btn-success" style="background-color: #0074D9;">All events</a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${eventStatus=='finished'}"> <a href="showProfile?eventStatus=finished" class="btn btn-success" style="background-color: #1E93F9">Finished events</a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=finished" class="btn btn-success" style="background-color: #0074D9;">Finished events</a></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${eventStatus=='upcoming'}"> <a href="showProfile?eventStatus=upcoming" class="btn btn-success" style="background-color: #1E93F9;">Upcoming events</a></c:when>
                        <c:otherwise> <a href="showProfile?eventStatus=upcoming" class="btn btn-success" style="background-color: #0074D9;">Upcoming events</a></c:otherwise>
                    </c:choose>
                </div>
                <br>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Place</th>
                        <th scope="col">Date</th>
                        <th scope="col">Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="cntRows" value="${events.size()}"/>
                    <c:set var="pos" value="${0}"/>
                    <c:forEach var="event" items="${events}">
                    <tr>
                        <td>${event.event_id}</td>
                        <td>${event.event_name_en}</td>
                        <td>${event.event_place_en}</td>
                        <td>${event.event_date}</td>
                        <td>${presense.get(pos)}</td>
                        <c:set var="pos" value="${pos+1}"/>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </p></c:otherwise>
    </c:choose>
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
    if(editStatus=="emptyFirstname") {
        swal("Error", "First name should not be empty", "error");
        //alert("First name should not be empty");
    }
    if(editStatus=="emptyLastname") {
        swal("Error", "Last name should not be empty", "error");
        //alert("Last name should not be empty");
    }
    if(editStatus=="emptyPassword") {
        swal("Error", "Password should not be empty", "error");
        //alert("Password should not be empty");
    }
    if(editStatus=="emptyPhone") {
        swal("Error", "Phone should not be empty", "error");
        //alert("Phone should not be empty");
    }
    if(editStatus=="emptyEmail") {
        swal("Error", "Email should not be empty", "error");
        //alert("Email should not be empty");
    }
    if(editStatus=="emptyAddress") {
        swal("Error", "Location should not be empty", "error");
        //alert("Location should not be empty");
    }
    if(editStatus=="invalidConfirmpassword") {
        swal("Error", "Password not match", "error");
        //alert("Password not match");
    }
    if(editStatus=="invalidFirstname") {
        swal("Error", "First name should starts with a capital letter and no more than 25 letters long", "error");
        //alert("First name should starts with a capital letter and no more than 25 letters long")
    }
    if(editStatus=="invalidLastname") {
        swal("Error", "Last name should starts with a capital letter and no more than 25 letters long", "error");
        //alert("Last name should starts with a capital letter and no more than 25 letters long")
    }
    if(editStatus=="invalidPassword") {
        swal("Error", "Password must consist of at least 4 characters and not more than 16 characters", "error");
        // alert("Password must consist of at least 4 characters and not more than 16 characters")
    }
    if(editStatus=="invalidPhone") {
        swal("Error", "Phone must starts with + and has 9 digits.", "error");
        //  alert("Phone must starts with + and has 9 digits.")
    }
    if(editStatus=="invalidEmail") {
        swal("Error", "Email must has no less than 1 letter before '@', 2 letters after and domain is no longer than 4 characters", "error");
        // alert("Email must has no less than 1 letter before '@', 2 letters after and domain is no longer than 4 characters")
    }
    if(editStatus=="dublicateEmail") {
        swal("Error", "Current email is already registered. Try to log in.", "error");
        // alert("Current email is already registered. Try to log in.")
    }
    if(editStatus=="dublicatePhone") {
        swal("Error", "Current phone is already registered. Try to log in.", "error");
        // alert("Current phone is already registered. Try to log in.")
    }
    if(editStatus=="success") {
        swal("Congrats", "You have successfully updated information", "success");
        // alert("You have successfully registered.")
    }
    if(editStatus=="servererror") {
        swal("Error", "It is technical work on the site. Please try again.", "error");
        //alert("It is technical work on the site. Please try again.")
    }
</script>
</body>
</html>
