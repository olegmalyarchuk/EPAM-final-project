
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
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
<div class="main">
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Sign up</h2>
                    <form action="register" method="post" class="register-form" id="register-form">
                        <div class="form-group">
                            <label for="firstname"><i class="bi bi-person-fill"></i></label>
                            <input type="text" required="required" name="firstname" id="firstname" placeholder="Your Name"/>
                        </div>
                        <div class="form-group">
                            <label for="lastname"><i class="bi bi-person-fill"></i></label>
                            <input type="text" required="required" name="lastname" id="lastname" placeholder="Your Surname"/>
                        </div>

                        <div class="form-group">
                            <label for="phone"><i class="bi bi-telephone-fill"></i></label>
                            <input type="text" required="required" name="phone" id="phone" placeholder="Phone"/>
                        </div>
                        <div class="form-group">
                            <label for="address"><i class="bi bi-house-door-fill"></i></label>
                            <input type="text" required="required" name="address" id="address" placeholder="Location"/>
                        </div>
                        <div class="form-group">
                            <label for="email"><i class="bi bi-envelope-fill"></i></label>
                            <input type="email" required="required"  name="email" id="email" placeholder="Your Email"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="bi bi-key-fill"></i></label>
                            <input type="password" required="required"  name="password" id="password" placeholder="Password"/>
                        </div>
                        <div class="form-group">
                            <label for="confirmpassword"><i class="bi bi-lock-fill"></i></label>
                            <input type="password" required="required"  name="confirmpassword" id="confirmpassword" placeholder="Repeat your password"/>
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                            <label for="agree-term" required="required"  class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure><img src="resources/images/auth/signup-image.jpg" alt="sing up image"></figure>
                    <a href="login.jsp" class="signup-image-link">I am already member</a>
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
    if(status=="emptyFirstname") {
        swal("Error", "First name should not be empty", "error");
        //alert("First name should not be empty");
    }
    if(status=="emptyLastname") {
        swal("Error", "Last name should not be empty", "error");
        //alert("Last name should not be empty");
    }
    if(status=="emptyPassword") {
        swal("Error", "Password should not be empty", "error");
        //alert("Password should not be empty");
    }
    if(status=="emptyPhone") {
        swal("Error", "Phone should not be empty", "error");
        //alert("Phone should not be empty");
    }
    if(status=="emptyEmail") {
        swal("Error", "Email should not be empty", "error");
        //alert("Email should not be empty");
    }
    if(status=="emptyAddress") {
        swal("Error", "Location should not be empty", "error");
        //alert("Location should not be empty");
    }
    if(status=="invalidConfirmpassword") {
        swal("Error", "Password not match", "error");
        //alert("Password not match");
    }
    if(status=="invalidFirstname") {
        swal("Error", "First name should starts with a capital letter and no more than 25 letters long", "error");
        //alert("First name should starts with a capital letter and no more than 25 letters long")
    }
    if(status=="invalidLastname") {
        swal("Error", "Last name should starts with a capital letter and no more than 25 letters long", "error");
        //alert("Last name should starts with a capital letter and no more than 25 letters long")
    }
    if(status=="invalidPassword") {
        swal("Error", "Password must consist of at least 4 characters and not more than 16 characters", "error");
       // alert("Password must consist of at least 4 characters and not more than 16 characters")
    }
    if(status=="invalidPhone") {
        swal("Error", "Phone must starts with + and has 9 digits.", "error");
      //  alert("Phone must starts with + and has 9 digits.")
    }
    if(status=="invalidEmail") {
        swal("Error", "Email must has no less than 1 letter before '@', 2 letters after and domain is no longer than 4 characters", "error");
       // alert("Email must has no less than 1 letter before '@', 2 letters after and domain is no longer than 4 characters")
    }
    if(status=="dublicateEmail") {
        swal("Error", "Current email is already registered. Try to log in.", "error");
       // alert("Current email is already registered. Try to log in.")
    }
    if(status=="dublicatePhone") {
        swal("Error", "Current phone is already registered. Try to log in.", "error");
      // alert("Current phone is already registered. Try to log in.")
    }
    if(status=="success") {
        swal("Congrats", "You have successfully registered", "success");
       // alert("You have successfully registered.")
    }
    if(status=="servererror") {
        swal("Error", "It is technical work on the site. Please try again.", "error");
        //alert("It is technical work on the site. Please try again.")
    }
</script>
</body>
</html>
