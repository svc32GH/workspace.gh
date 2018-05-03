<%--
  Created by IntelliJ IDEA.
  User: SChudakov
  Date: 03.05.2018
  Time: 7:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Yahoo!!</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <H1>Add a Todo</H1>
    <form:form method="post" commandName="todo">

        <form:hidden path="id"/>

        <fieldset class="form-group">
            <form:label path="desc">Description</form:label>
            <form:input path="desc" type="text" class="form-control" required="required"/>
        </fieldset>
        <input class="btn btn-success" type="submit" value="Submit">
        <form:errors path="desc" cssClass="text-danger" />
    </form:form>

</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>
</html>
