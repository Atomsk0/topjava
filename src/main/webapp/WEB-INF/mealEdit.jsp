<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 10.03.2016
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<section>
    <h3>Edit meal</h3>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.UserMeal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name = "id" value = "${meal.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" name = "dateTime" value = "${meal.dateTime}"></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" name = "description" value = "${meal.description}"></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" name = "calories" value = "${meal.calories}"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>

</body>
</html>
