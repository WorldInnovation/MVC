
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/style.css" >

<html>
<head>

    <title> Employees </title>
</head>

<body>

<form action="employeeEdit" method="get">
    <input type="hidden" name="depID" value="${depID}" >
    <input type="hidden" name="empID" value="${null}" >
    <input type="submit" value="New Employee">
</form>

<form action="/" method="get">
    <input type="hidden" name="depID" value="${depID}" >
    <input type="submit" value="Departments">
</form>

<table >
    <caption>
        <span class="titleTable">
            Employees
        </span>
    </caption>
    <tr>
        <td>ID</td>
        <td>firstName</td>
        <td>secondName</td>
        <td>grade</td>
        <td>birthday</td>
        <td>eMail</td>

        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <tr>

        <c:forEach items="${employees}" var="emp">
    <tr>

        <td>${emp.id}</td>
        <td>${emp.firstName}</td>
        <td>${emp.secondName}</td>
        <td>${emp.grade}</td>
        <td>${emp.birthday}</td>
        <td>${emp.eMail}</td>

        <td><form action="employeeEdit" method="get">

            <input type="hidden" name="empID" value="${emp.id}" >
            <input type="hidden" name="firstName" value="${emp.firstName}" >
            <input type="hidden" name="secondName" value="${emp.secondName}" >
            <input type="hidden" name="grade" value="${emp.grade}" >
            <input type="hidden" name="birthday" value="${emp.birthday}" >
            <input type="hidden" name="eMail" value="${emp.eMail}" >

            <input type="hidden" name="depID" value="${depID}" >

            <input type="submit" value="Edit">
        </form> </td>

        <td> <form action="empDelete" method="post">
            <input type="hidden" name="empID" value="${emp.id}" >

            <input type="hidden" name="depID" value="${depID}" >

            <input type="submit" value="Delete">  </form> </td>

    </tr>
    </c:forEach>
</table>
</body>
</html>