<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html>
<head>
    <title><fmt:message key="login.title" /></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Font Icon -->
    <link rel="stylesheet" href="resources/fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
    <!-- Bootstrap Font Icon CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <!-- Main css -->
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="shortcut icon" href="/resources/images/logo.png" type="image/png">
</head>
<body>
<%
    if(session.getAttribute("email")!=null) {
        session.setAttribute("status", "registered");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listEvent");
        dispatcher.forward(request, response);
    }
%>
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
<input type="hidden" id="statusSession" value="<%=session.getAttribute("status")%>">
<input type="hidden" id="lang" value="${sessionScope.lang}">
<div class="main">
    <!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure><img src="resources/images/auth/signin-image.jpg" alt="sing up image"></figure>
                    <a href="register.jsp" class="signup-image-link"><fmt:message key="login.createaccount" /></a>
                </div>

                <div class="signin-form">
                    <h2 class="form-title"><fmt:message key="login.sighup" /></h2>
                    <form action="login" method="post" class="register-form" id="login-form">
                        <div class="form-group">
                            <label for="email"><i class="bi bi-envelope-fill"></i></label>
                            <input type="email" name="email" required="required" id="email" placeholder="<fmt:message key="login.youremail" />"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="bi bi-key-fill"></i></label>
                            <input type="password" name="password" required="required" id="password" placeholder="<fmt:message key="login.password" />"/>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin" class="form-submit" value="<fmt:message key="login.login" />"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
<!--- JS -->
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/js/main.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweatalert.css ">
<script type="text/javascript">
    var status = document.getElementById("status").value;
    var lang = document.getElementById("lang").value;
    if(lang=="en") {
        if (status == "wrongPass") {
            swal("Error", "The password is wrong.", "error");
            // alert("You have successfully registered.")
        }
        if (status == "unexistedEmail") {
            swal("Error", "Email is not registered yet.", "error");
            // alert("You have successfully registered.")
        }
        if (status == "success") {
            swal("Congrats", "You have successfully login", "success");
            // alert("You have successfully registered.")
        }
        if (status == "logout") {
            swal("Success", "You have successfully logged out", "success");
            // alert("You have successfully registered.")
        }
        if (status == "unregistered") {
            swal("Error", "You are not logged in", "error");
        }
        if (status == "servererror") {
            swal("Error", "It is technical work on the site. Please try again.", "error");
            //alert("It is technical work on the site. Please try again.")
        }
    } else {
        if (status == "wrongPass") {
            swal("Помилка", "Пароль невірний", "error");
            // alert("You have successfully registered.")
        }
        if (status == "unexistedEmail") {
            swal("Помилка", "Емейл ще не зареєстрований", "error");
            // alert("You have successfully registered.")
        }
        if (status == "success") {
            swal("Успіх", "Ви успішно увійшли", "success");
            // alert("You have successfully registered.")
        }
        if (status == "logout") {
            swal("Успіх", "Ви успішно вийшли", "success");
            // alert("You have successfully registered.")
        }
        if (status == "unregistered") {
            swal("Помилка", "Ви не увійшли", "error");
        }
        if (status == "servererror") {
            swal("Помилка", "На сайті технічні роботи, спробуйте пізніше", "error");
            //alert("It is technical work on the site. Please try again.")
        }
    }
</script>
</body>
</html>
