<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html>
<head>
    <title><fmt:message key="eventform.title" /></title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="shortcut icon" href="/resources/images/logo.png" type="image/png">
</head>
<body>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${event != null}">
            <form action="updateEvent" method="post">
                </c:if>
                <c:if test="${event == null}">
                <form action="insertEvent" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${event != null}">
                                <fmt:message key="eventform.editevent" />
                            </c:if>
                            <c:if test="${event == null}">
                                <fmt:message key="eventform.addnewevent" />
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${event != null}">
                        <input type="hidden" name="event_id" value="<c:out value='${event.event_id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label><fmt:message key="eventform.eventnameua" /></label> <input type="text"
                                                        value="<c:out value='${event.event_name_ua}' />" class="form-control"
                                                        name="event_name_ua" required="required">
                    </fieldset>

                        <fieldset class="form-group">
                            <label><fmt:message key="eventform.eventnameen" /></label> <input type="text"
                                                                value="<c:out value='${event.event_name_en}' />" class="form-control"
                                                                name="event_name_en" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label><fmt:message key="eventform.eventplaceua" /></label> <input type="text"
                                                                value="<c:out value='${event.event_place_ua}' />" class="form-control"
                                                                name="event_place_ua" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label><fmt:message key="eventform.eventplaceen" /></label> <input type="text"
                                                                value="<c:out value='${event.event_place_en}' />" class="form-control"
                                                                name="event_place_en" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label><fmt:message key="eventform.eventdescriptionua" /></label> <input type="text"
                                                                value="<c:out value='${event.event_description_ua}' />" class="form-control"
                                                                name="event_description_ua" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label><fmt:message key="eventform.eventdescriptionen" /></label> <input type="text"
                                                                value="<c:out value='${event.event_description_en}' />" class="form-control"
                                                                name="event_description_en" required="required">
                        </fieldset>
                            <label><fmt:message key="eventform.eventdate" /></label> <input type="text"
                                                                value="<c:out value='${event.event_date}' />" class="form-control"
                                                                name="event_date" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label><fmt:message key="eventform.eventphotourl" /></label> <input type="text"
                                                                value="<c:out value='${event.event_photo_url}' />" class="form-control"
                                                                name="event_photo_url" required="required">
                        </fieldset>


                        <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                </form>
        </div>
    </div>
</div>
</body>
</html>