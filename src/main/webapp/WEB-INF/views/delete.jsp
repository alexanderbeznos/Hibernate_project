<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.css" media="screen">
<link rel="stylesheet" href="https://bootswatch.com/_assets/css/custom.min.css">
<html>
<head>
    <title>Delete Player</title>
</head>
<body>
<br/>
<br/>
<div class="container">
    <div class="row">
        <div class="col-sm-5 offset-sm-3">
            <form action="${pageContext.request.contextPath}/deletePlayer" method="post">
                <div class="form-group">
                    <legend>Delete player?</legend>
                    <input name="id" type="hidden" value="<c:out value="${param.id}"/>"/>
                    <button type="submit" class="btn btn-primary">Yes</button>
                </div>
            </form>
            <form action="${pageContext.request.contextPath}/list" method="get">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">No</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>




