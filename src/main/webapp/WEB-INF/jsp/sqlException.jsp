<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/style.css" >
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SqlError</title>
</head>
<body>

<div class="message error"> We are working on a server, try again later </div>
<div> ${sqlError}</div>
<div class="titleTable"> Back to </div>

<form action="/" method="get">

    <input type="submit" value="Departments">  </form>
</body>
</html>