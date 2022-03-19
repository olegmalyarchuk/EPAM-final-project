<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
<h1>Авторизація</h1>
<div class = index_div>
    <form action="<c:url value="/"/>" method="post">
        <h2>Помилка безпеки: у вас нема прав доступу на цю сторінку!</h2>
        <h2>Security error: You are not permitted to visit this page!</h2>
        <input type="submit" class="bigbutton" value="На главную страницу"/>
    </form>
</div>
</body>
</html>