
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.css" media="screen">
<link rel="stylesheet" href="https://bootswatch.com/_assets/css/custom.min.css">
<html>
<head>
    <title>Create account</title>
</head>
<br/>
<br/>
<br/>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-5 offset-sm-3">
            <form action="${pageContext.request.contextPath}/createAccount" method="post">
                <div class="form-group">
                    <label for="login">Login:</label>
                    <input type="text" class="form-control" id="login" name="login" placeholder="Enter login">
                </div>
                <br>
                <br>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
                </div>
                <br>
                <button type="submit" class="btn btn-primary">Create account</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>







