<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.css" media="screen">
<link rel="stylesheet" href="https://bootswatch.com/_assets/css/custom.min.css">
<html>
<head>
    <title>All Players</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-8 offset-sm-2">
            <form action="${pageContext.request.contextPath}/logOut" method="get">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Log out</button>
                </div>
            </form>
            <br/>
            <br/>
            <form action="${pageContext.request.contextPath}/addPlayer" method="get">
                <button type="submit" class="btn btn-primary">Add player</button>
            </form>
            <table class="table table-hover" id="table">
                <tr class="table-active">
                    <th>Name</th>
                    <th>Last Name</th>
                    <th>Market Value</th>
                    <th>Country</th>
                    <th>Club</th>
                    <th>Position</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:if test="${requestScope.answer=='ok'}">
                    <c:forEach items="${requestScope.players}" var="player">
                        <tr class="table-secondary">
                            <td><c:out value="${player.name}"/></td>
                            <td><c:out value="${player.lastName}"/></td>
                            <td><c:out value="${player.marketValue}"/></td>
                            <td><c:out value="${player.country}"/></td>
                            <td><c:out value="${player.club}"/></td>
                            <td><c:out value="${player.position.name}"/></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/updatePlayer" method="get">
                                    <input type="submit" value="Update">
                                    <input name="id" type="hidden" value="<c:out value="${player.id}"/>"/>
                                    <input name="name" type="hidden" value="<c:out value="${player.name}"/>"/>
                                    <input name="lastName" type="hidden" value="<c:out value="${player.lastName}"/>"/>
                                    <input name="marketValue" type="hidden" value="<c:out value="${player.marketValue}"/>"/>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/deletePlayer" method="get">
                                    <input type="submit" value="Delete">
                                    <input name="id" type="hidden" value="<c:out value="${player.id}"/>"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${requestScope.answer!='ok'}">
                    <tr class="table-secondary">
                        <td><c:out value="${requestScope.answer}"/></td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</div>
</body>
</html>



