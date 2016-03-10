<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %><%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 08.03.2016
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {color:green;}
        .exceeded {color:red;}
    </style>
</head>
<body>
<h2>Meal list</h2>
<h3><a href = "meals?action=create">Add meal</a></h3>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>

    </tr>

    <c:forEach items="${mealList}" var="meal">
        <jsp:useBean id="meal" scope="page" type = "ru.javawebinar.topjava.model.UserMealWithExceed"/>
        <tr class="${meal.exceed? 'exceeded' : 'normal'}">

            <td><%=TimeUtil.toString(meal.getDateTime())%>
            </td>
            <td><c:out value="${meal.description}" />
            </td>
            <td><c:out value="${meal.calories}" />
            </td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
