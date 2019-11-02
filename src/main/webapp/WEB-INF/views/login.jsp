<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.css" media="screen">
<link rel="stylesheet" href="https://bootswatch.com/_assets/css/custom.min.css">
<html>
<head>
    <title>Login</title>
</head>
<body>
<br/>
<div class="container">
    <div class="row">
        <div class="col-sm-5 offset-sm-3">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="login">Login:</label>
                    <input type="text" class="form-control" id="login" name="login" placeholder="Enter login">
                    <br>
                    <br>
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Log in</button>
                </div>
            </form>
            <br/>
            <form action="${pageContext.request.contextPath}/createAccount" method="get">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Create account</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
