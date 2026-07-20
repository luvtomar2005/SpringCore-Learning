<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Student Registration</title>

    <style>
        .error{
            color:red;
        }
    </style>

</head>
<body>

<h2>Student Registration Form</h2>

<form:form action="processForm" method="post" modelAttribute="student">

    Name :
    <form:input path="name"/>
    <form:errors path="name" cssClass="error"/>
    <br><br>

    Email :
    <form:input path="email"/>
    <form:errors path="email" cssClass="error"/>
    <br><br>

    Age :
    <form:input path="age"/>
    <form:errors path="age" cssClass="error"/>
    <br><br>

    Course :
    <form:input path="course"/>
    <form:errors path="course" cssClass="error"/>
    <br><br>

    <input type="submit" value="Register">

</form:form>

</body>
</html>