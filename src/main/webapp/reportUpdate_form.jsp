<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html>
<head>
    <title><fmt:message key="reportupdateform.title" /></title>
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
            <form action="updateReport" method="post">

                <caption>
                    <h2>
                        <fmt:message key="reportupdateform.updatereport" />
                    </h2>
                </caption>
                <input type="hidden" name="report_id" value="<c:out value='${report_id}' />" />
                <fieldset class="form-group">
                    <label> <fmt:message key="reportupdateform.event_id" /></label> <input type="text" class="form-control" required="required" name="event_id" value="<c:out value='${event_id}' />" />
                </fieldset>
                <fieldset class="form-group">
                    <label> <fmt:message key="reportupdateform.reportnameua" /></label> <input type="text" class="form-control" name="report_name_ua" required="required">
                </fieldset>
                <fieldset class="form-group">
                    <label> <fmt:message key="reportupdateform.reportnameen" /></label> <input type="text" class="form-control" name="report_name_en" required="required">
                </fieldset>
                <button type="submit" class="btn btn-success"> <fmt:message key="button.save" /></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
