
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
<%
    if(session.getAttribute("email")!=null) {
        request.setAttribute("status", "registered");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
       dispatcher.forward(request, response);
    }
%>
<h6>Registration page</h6><br>
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
<div align="center">
    <h1>User Register Form</h1>
    <form action="register" method="post">
        <table style="width: 80%">
            <tr>
                <td>First name</td>
                <td><input type="text" name="firstname" required="required" /></td>
            </tr>
            <tr>
                <td>Last name</td>
                <td><input type="text" name="lastname" required="required"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" required="required" /></td>
            </tr>
            <tr>
                <td>Confirm password</td>
                <td><input type="password" name="confirmpassword" required="required" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" required="required" /></td>
            </tr>
            <tr>
                <td>Phone</td>
                <td><input type="text" name="phone" required="required" /></td>
            </tr>
            <tr>
                <td>Location</td>
                <td><input type="text" name="address" required="required" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
    <a href="login.jsp">Already have an account</a>
</div>

<!--- JS -->
<script src="vendor/jquery/jguery.min.js"></script>
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
