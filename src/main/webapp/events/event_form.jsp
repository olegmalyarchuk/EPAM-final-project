<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Event form</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #0074D9">

        <ul class="navbar-nav">
            <li><a href="list"
                   class="nav-link">Events</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${event != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${event == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${event != null}">
                                Edit Event
                            </c:if>
                            <c:if test="${event == null}">
                                Add New Event
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${event != null}">
                        <input type="hidden" name="event_id" value="<c:out value='${event.event_id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Event Name UA</label> <input type="text"
                                                        value="<c:out value='${event.event_name_ua}' />" class="form-control"
                                                        name="event_name_ua" required="required">
                    </fieldset>

                        <fieldset class="form-group">
                            <label>Event Name EN</label> <input type="text"
                                                                value="<c:out value='${event.event_name_en}' />" class="form-control"
                                                                name="event_name_en" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Event Place UA</label> <input type="text"
                                                                value="<c:out value='${event.event_place_ua}' />" class="form-control"
                                                                name="event_place_ua" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Event Place EN</label> <input type="text"
                                                                value="<c:out value='${event.event_place_en}' />" class="form-control"
                                                                name="event_place_en" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Event Decription UA</label> <input type="text"
                                                                value="<c:out value='${event.event_description_ua}' />" class="form-control"
                                                                name="event_description_ua" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Event Description EN</label> <input type="text"
                                                                value="<c:out value='${event.event_description_en}' />" class="form-control"
                                                                name="event_description_en" required="required">
                        </fieldset>
                            <label>Event Date</label> <input type="text"
                                                                value="<c:out value='${event.event_date}' />" class="form-control"
                                                                name="event_date" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Event Photo URL</label> <input type="text"
                                                                value="<c:out value='${event.event_photo_url}' />" class="form-control"
                                                                name="event_photo_url" required="required">
                        </fieldset>


                        <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>