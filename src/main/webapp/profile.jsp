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
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #0074D9">
        <ul class="navbar-nav">
            <li><a href="listEvent"
                   class="nav-link">Events</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="profile.jsp"
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
                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt="Admin" class="rounded-circle p-1 bg-primary" width="110">
                            <div class="mt-3">
                                <h4>John Doe</h4>
                                <c:choose>
                                    <c:when test="${sessionScope.role_id==1}"><p class="text-secondary mb-1">Moderator</p></c:when>
                                    <c:when test="${sessionScope.role_id==2}"><p class="text-secondary mb-1">Speaker</p></c:when>
                                    <c:otherwise><p class="text-secondary mb-1">User</p></c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" value="John">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Surname</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" value="Doe">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Password</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="password" class="form-control" value="password">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Phone</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" value="+123456789">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="email" class="form-control" value="user@example.com">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Location</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" value="Lviv">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-secondary">
                                <input type="button" class="btn btn-primary px-4" value="Save Changes">
                            </div>
                        </div>
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
                    <a href="listEvent?eventStatus=all" class="btn btn-success" style="background-color: #1E93F9;">From me</a>
                    <a href="listEvent?eventStatus=all" class="btn btn-success" style="background-color: #1E93F9;">From speaker</a>
                    <a href="listEvent?eventStatus=all" class="btn btn-success" style="background-color: #1E93F9;">Reports for event</a>
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
                    <tr>
                        <th scope="row">1</th>
                        <td>Collections</td>
                        <td>Deep dive into collection</td>
                        <td>4</td>
                        <td><a href="#" class="btn btn-danger">Reject</a></td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Testing and Logging</td>
                        <td>What is it</td>
                        <td>6</td>
                        <td><a href="#" class="btn btn-success">Accept</a>
                            <a href="#" class="btn btn-danger">Reject</a></td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>Filters and Sessions</td>
                        <td>Master-class of filters</td>
                        <td>5</td>
                        <td><a href="#" class="btn btn-success">Accept</a>
                            <a href="#" class="btn btn-danger">Reject</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <hr>
            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center">Users</h3></div>
                <hr>
                <div class="container text-center">
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">All users</a>
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">Users</a>
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">Speakers</a>
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
                        <th scope="col">User_address</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>1</td>
                        <td>John</td>
                        <td>Watson</td>
                        <td>Watson</td>
                        <td>johnwatson@gmail.com</td>
                        <td>Lviv</td>
                        <td><a href="#" class="btn btn-success">Edit</a>
                            <a href="#" class="btn btn-danger">Delete</a></td>
                    </tr>
                    <tr>
                        <th scope="row">1</th>
                        <td>1</td>
                        <td>John</td>
                        <td>Watson</td>
                        <td>Watson</td>
                        <td>johnwatson@gmail.com</td>
                        <td>Lviv</td>
                        <td><a href="#" class="btn btn-success">Edit</a>
                            <a href="#" class="btn btn-danger">Delete</a></td>
                    </tr>
                    <tr>
                        <th scope="row">1</th>
                        <td>1</td>
                        <td>John</td>
                        <td>Watson</td>
                        <td>Watson</td>
                        <td>johnwatson@gmail.com</td>
                        <td>Lviv</td>
                        <td><a href="#" class="btn btn-success">Edit</a>
                            <a href="#" class="btn btn-danger">Delete</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </c:when>
        <c:when test="${sessionScope.role_id==2}"><p class="text-secondary mb-1">

            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center">Events with my performance</h3></div>
                <hr>
                <div class="container text-center">
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">All events</a>
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">Finished events</a>
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">Upcoming events</a>
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
                    <tr>
                        <th scope="row">1</th>
                        <td>Collections</td>
                        <td>Ekoland</td>
                        <td>2022-05-10 18:00:00</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Testing and Logging</td>
                        <td>Malevich</td>
                        <td>2022-02-10 18:00:00</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>Filters and Sessions</td>
                        <td>Art Hotel</td>
                        <td>2022-02-28 18:00:00</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <hr>
            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center">Active prepositions</h3></div>
                <hr>
                <div class="container text-center">
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">From moderator</a>
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">For report</a>
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">Reports for event</a>
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
                    <tr>
                        <th scope="row">1</th>
                        <td>Collections</td>
                        <td>Deep dive into collection</td>
                        <td><a href="#" class="btn btn-success">Accept</a>
                            <a href="#" class="btn btn-danger">Reject</a></td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Testing and Logging</td>
                        <td>What is it</td>
                        <td><a href="#" class="btn btn-danger">Reject</a></td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>Filters and Sessions</td>
                        <td>Master-class of filters</td>
                        <td><a href="#" class="btn btn-danger">Reject</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </p></c:when>
        <c:otherwise><p class="text-secondary mb-1">

            <div class="row align-items-center justify-content-center">
                <div class="container text-center"><h3 class="text-center">My Events</h3></div>
                <hr>
                <div class="container text-center">
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">All events</a>
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">Finished events</a>
                    <a href="#" class="btn btn-success" style="background-color: #1E93F9;">Upcoming events</a>
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
                    <tr>
                        <th scope="row">1</th>
                        <td>Collections</td>
                        <td>Ekoland</td>
                        <td>2022-05-10 18:00:00</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Testing and Logging</td>
                        <td>Malevich</td>
                        <td>2022-02-10 18:00:00</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>Filters and Sessions</td>
                        <td>Art Hotel</td>
                        <td>2022-02-28 18:00:00</td>
                    </tr>
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
</script>
</body>
</html>
