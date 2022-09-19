<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    Users list
</head>
<body>
<table>
    <tbody>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Nickname</th>
        <th>email</th>
        <th>role</th>
    </tr>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.nickName}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.authorized}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>