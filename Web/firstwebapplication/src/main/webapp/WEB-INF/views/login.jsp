<%--
  Created by IntelliJ IDEA.
  User: SChudakov
  Date: 30.04.2018
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Yahoo!!!!!!!! From JSP</title>
</head>
<body>
<html>
<head>
    <title>Yahoo!!!!!!!</title>
</head>
<%
    System.out.println(request.getParameter("name"));
    Date date = new Date();
%>
<div>Current Date is <%= date %></div>
<body>First JSP Page ${name}</body>
</html>
</body>
</html>
