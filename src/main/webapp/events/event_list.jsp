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
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #39c2d7">
        <ul class="navbar-nav">
            <li><a href="list"
                   class="nav-link">Events</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Events</h3>
        <hr>
        <div class="container text-left">

            <a href="new" class="btn btn-success" style="background-color: #a3c644; color: #464547">Add
                New Event</a>
        </div>
        <br>
        <table class="table table-bordered">
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
                    <td><a href="edit?id=<c:out value='${event.event_id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${event.event_id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
