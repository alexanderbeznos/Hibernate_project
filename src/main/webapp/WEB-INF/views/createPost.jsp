<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.css" media="screen">
<link rel="stylesheet" href="https://bootswatch.com/_assets/css/custom.min.css">
<html>
<head>
    <title>Add player</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-5 offset-sm-3">
            <legend><c:out value="${requestScope.answer}"/></legend>
            <form action="${pageContext.request.contextPath}/list" method="get">
                <button type="submit" class="btn btn-primary">Return to players</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
