<%--
  Created by IntelliJ IDEA.
  User: SChudakov
  Date: 03.05.2018
  Time: 7:18
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">

    <H1>Add a Todo</H1>
    <form:form method="post" commandName="todo">

        <form:hidden path="id"/>

        <fieldset class="form-group">
            <form:label path="desc">Description</form:label>
            <form:input path="desc" type="text" class="form-control" required="required"/>
            <form:errors path="desc" cssClass="text-danger" />
        </fieldset>

        <fieldset class="form-group">
            <form:label path="targetDate">Target Date</form:label>
            <form:input path="targetDate" type="text" class="form-control" required="required"/>
            <form:errors path="targetDate" cssClass="text-danger" />
        </fieldset>

        <input class="btn btn-success" type="submit" value="Submit">
    </form:form>

</div>

<%@ include file="common/footer.jspf"%>