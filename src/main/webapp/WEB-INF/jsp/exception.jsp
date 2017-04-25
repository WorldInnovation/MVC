<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exception</title>
</head>
<body>
<div class="message error"> Server overload, try  again later </div>
<div> ${error}</div>
<div class="titleTable"> Back to </div>

<form action="/" method="get">

    <input type="submit" value="Departments">  </form>
</body>
</html>
