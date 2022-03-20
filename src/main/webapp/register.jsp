
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
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
</div>
<script type="text/javascript">
    var status = document.getElementById("status").value;
    if(status=="emptyFirstname") {
        alert("First name should not be empty");
    }
    if(status=="emptyLastname") {
        alert("Last name should not be empty");
    }
    if(status=="emptyPassword") {
        alert("Password should not be empty");
    }
    if(status=="emptyPhone") {
        alert("Phone should not be empty");
    }
    if(status=="emptyEmail") {
        alert("Email should not be empty");
    }
    if(status=="emptyAddress") {
        alert("Location should not be empty");
    }
    if(status=="invalidConfirmpassword") {
        alert("Password not match");
    }
    if(status=="invalidFirstname") {
        alert("First name should starts with a capital letter and no more than 25 letters long")
    }
    if(status=="invalidLastname") {
        alert("Last name should starts with a capital letter and no more than 25 letters long")
    }
    if(status=="invalidPassword") {
        alert("Password must consist of at least 4 characters and not more than 16 characters")
    }
    if(status=="invalidPhone") {
        alert("Phone must starts with + and has 9 digits.")
    }
    if(status=="invalidEmail") {
        alert("Email must has no less than 1 letter before '@', 2 letters after and domain is no longer than 4 characters")
    }
    if(status=="dublicateEmail") {
        alert("Current email is already registered. Try to log in.")
    }
    if(status=="dublicatePhone") {
        alert("Current phone is already registered. Try to log in.")
    }
    if(status=="success") {
        alert("You have successfully registered.")
    }
    if(status=="servererror") {
        alert("It is technical work on the site. Please try again.")
    }
</script>
</body>
</html>
