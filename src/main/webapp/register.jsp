<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html>
<head>
    <title><fmt:message key="register.title"/></title>
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
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
<input type="hidden" id="statusSession" value="<%=session.getAttribute("status")%>">
<input type="hidden" id="lang" value="<%=request.getSession().getAttribute("lang")%>">
<div class="main">
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title"><fmt:message key="register.signup"/></h2>
                    <form action="register" method="post" class="register-form" id="register-form">
                        <div class="form-group">
                            <label for="firstname"><i class="bi bi-person-fill"></i></label>
                            <input type="text" required="required" name="firstname" id="firstname" placeholder="<fmt:message key="register.yourname"/>"/>
                        </div>
                        <div class="form-group">
                            <label for="lastname"><i class="bi bi-person-fill"></i></label>
                            <input type="text" required="required" name="lastname" id="lastname" placeholder="<fmt:message key="register.yoursurname"/>"/>
                        </div>

                        <div class="form-group">
                            <label for="phone"><i class="bi bi-telephone-fill"></i></label>
                            <input type="text" required="required" name="phone" id="phone" placeholder="<fmt:message key="register.phone"/>"/>
                        </div>
                        <div class="form-group">
                            <label for="address"><i class="bi bi-house-door-fill"></i></label>
                            <input type="text" required="required" name="address" id="address" placeholder="<fmt:message key="register.location"/>"/>
                        </div>
                        <div class="form-group">
                            <label for="email"><i class="bi bi-envelope-fill"></i></label>
                            <input type="email" required="required"  name="email" id="email" placeholder="<fmt:message key="register.youremail"/>"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="bi bi-key-fill"></i></label>
                            <input type="password" required="required"  name="password" id="password" placeholder="<fmt:message key="register.password"/>"/>
                        </div>
                        <div class="form-group">
                            <label for="confirmpassword"><i class="bi bi-lock-fill"></i></label>
                            <input type="password" required="required"  name="confirmpassword" id="confirmpassword" placeholder="<fmt:message key="register.repeatpassword"/>"/>
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                            <label for="agree-term" required="required"  class="label-agree-term"><span><span></span></span><fmt:message key="register.termbefore"/><a href="#" class="term-service"><fmt:message key="register.termafter"/></a></label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="<fmt:message key="register.register"/>"/>
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure><img src="resources/images/auth/signup-image.jpg" alt="sing up image"></figure>
                    <a href="login.jsp" class="signup-image-link"><fmt:message key="register.alreadymember"/></a>
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
        if (status == "emptyFirstname") {
            swal("Error", "First name should not be empty", "error");
            //alert("First name should not be empty");
        }
        if (status == "emptyLastname") {
            swal("Error", "Last name should not be empty", "error");
            //alert("Last name should not be empty");
        }
        if (status == "emptyPassword") {
            swal("Error", "Password should not be empty", "error");
            //alert("Password should not be empty");
        }
        if (status == "emptyPhone") {
            swal("Error", "Phone should not be empty", "error");
            //alert("Phone should not be empty");
        }
        if (status == "emptyEmail") {
            swal("Error", "Email should not be empty", "error");
            //alert("Email should not be empty");
        }
        if (status == "emptyAddress") {
            swal("Error", "Location should not be empty", "error");
            //alert("Location should not be empty");
        }
        if (status == "invalidConfirmpassword") {
            swal("Error", "Password not match", "error");
            //alert("Password not match");
        }
        if (status == "invalidFirstname") {
            swal("Error", "First name should starts with a capital letter and no more than 25 letters long", "error");
            //alert("First name should starts with a capital letter and no more than 25 letters long")
        }
        if (status == "invalidLastname") {
            swal("Error", "Last name should starts with a capital letter and no more than 25 letters long", "error");
            //alert("Last name should starts with a capital letter and no more than 25 letters long")
        }
        if (status == "invalidPassword") {
            swal("Error", "Password must consist of at least 4 characters and not more than 16 characters", "error");
            // alert("Password must consist of at least 4 characters and not more than 16 characters")
        }
        if (status == "invalidPhone") {
            swal("Error", "Phone must starts with + and has 9 digits.", "error");
            //  alert("Phone must starts with + and has 9 digits.")
        }
        if (status == "invalidEmail") {
            swal("Error", "Email must has no less than 1 letter before '@', 2 letters after and domain is no longer than 4 characters", "error");
            // alert("Email must has no less than 1 letter before '@', 2 letters after and domain is no longer than 4 characters")
        }
        if (status == "dublicateEmail") {
            swal("Error", "Current email is already registered. Try to log in.", "error");
            // alert("Current email is already registered. Try to log in.")
        }
        if (status == "dublicatePhone") {
            swal("Error", "Current phone is already registered. Try to log in.", "error");
            // alert("Current phone is already registered. Try to log in.")
        }
        if (status == "success") {
            swal("Congrats", "You have successfully registered", "success");
            // alert("You have successfully registered.")
        }
        if (status == "servererror") {
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
