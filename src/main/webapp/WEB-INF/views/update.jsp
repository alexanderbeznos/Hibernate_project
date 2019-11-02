<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.css" media="screen">
<link rel="stylesheet" href="https://bootswatch.com/_assets/css/custom.min.css">
<html>
<head>
    <title>Update player</title>
</head>
<body>
<br/>
<div class="container">
    <div class="row">
        <div class="col-sm-5 offset-sm-3">
            <form action="${pageContext.request.contextPath}/updatePlayer" method="post">
                <input name="id" type="hidden" value="<c:out value="${param.id}"/>"/>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="<c:out value="${param.name}"/>">
                </div>
                <div class="form-group">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" value="<c:out value="${param.lastName}"/>">
                </div>
                <div class="form-group">
                    <label for="marketValue">Market value</label>
                    <input type="text" class="form-control" id="marketValue" name="marketValue" value="<c:out value="${param.marketValue}"/>">
                </div>
                <div class="form-group">
                    <label for="country">Country</label>
                    <input type="text" class="form-control" id="country" name="country" value="<c:out value="${param.country}"/>">
                </div>
                <div class="form-group">
                    <label for="club">Club</label>
                    <input type="text" class="form-control" id="club" name="club" value="<c:out value="${param.club}"/>">
                </div>
                <button type="submit" class="btn btn-primary">Update player</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

