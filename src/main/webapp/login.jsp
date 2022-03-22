
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<%
    if(session.getAttribute("email")!=null) {
        request.setAttribute("status", "registered");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
        dispatcher.forward(request, response);
    }
%>
<h6>Login page</h6><br>
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
<div align="center">
    <h1>User Login Form</h1>
    <form action="login" method="post">
        <table style="width: 80%">
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" required="required" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" required="required" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
    <a href="register.jsp">Create an account</a>
</div>

<!--- JS -->
<script src="vendor/jquery/jguery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweatalert.css ">
<script type="text/javascript">
    var status = document.getElementById("status").value;
    if(status=="wrongPass") {
        swal("Error", "The password is wrong.", "error");
        // alert("You have successfully registered.")
    }
    if(status=="unexistedEmail") {
        swal("Error", "Email is not registered yet.", "error");
        // alert("You have successfully registered.")
    }
    if(status=="success") {
        swal("Congrats", "You have successfully login", "success");
        // alert("You have successfully registered.")
    }
    if(status=="logout") {
        swal("Success", "You have successfully logged out", "success");
        // alert("You have successfully registered.")
    }
    if(status=="unregistered") {
        swal("Error", "You are not logged in", "error");
    }
    if(status=="servererror") {
        swal("Error", "It is technical work on the site. Please try again.", "error");
        //alert("It is technical work on the site. Please try again.")
    }
</script>
</body>
</html>
