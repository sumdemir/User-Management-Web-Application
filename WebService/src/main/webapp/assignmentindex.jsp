<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.example.service.User" %>
<%@ page import="org.example.service.UserOperations" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.example.service.DatabaseOperations" %>
<%@ page import="java.util.List" %>

<%
  SessionFactory sessionFactory = DatabaseOperations.provideSessionFactory();
  UserOperations userService = new UserOperations(sessionFactory);
%>

<!DOCTYPE html>
<html>

<head>
  <title>FOURTH ASSIGNMENT</title>
</head>
<body>
<h2>Fourth Assignment</h2>

<h3>Insert User</h3>
<form action="UserServlet" method="post">
  <input type="hidden" name="action" value="insert">
  <label for="username">Username:</label> <br>
  <input type="text" id="username" name="username" required><br>
  <label for="password">Password:</label> <br>
  <input type="password" id="password" name="password" required><br>
  <label for="email">Email:</label> <br>
  <input type="email" id="email" name="email" required><br>
  <label for="birthday">Birthday:</label> <br>
  <input type="date" id="birthday" name="birthday" required><br>
  <label for="sex">Sex:</label> <br>
  <select id="sex" name="sex" required>
    <option value="1">Male</option>
    <option value="0">Female</option>
  </select><br>
  <label for="enabled">Enabled:</label> <br>
  <input type="checkbox" id="enabled" name="enabled"><br>
  <input type="submit" value="Insert User">
</form>

<h3>Update User</h3>
<form action="UserServlet" method="post">
  <input type="hidden" name="action" value="update">
  <label for="usernameUpdate">Username:</label> <br>
  <input type="text" id="usernameUpdate" name="username" required><br>
  <label for="emailUpdate">Email:</label> <br>
  <input type="email" id="emailUpdate" name="email" required><br>
  <label for="passwordUpdate">New Password:</label> <br>
  <input type="password" id="passwordUpdate" name="password"><br>
  <input type="submit" value="Update User">
</form>

<h3>Delete User</h3>
<form action="UserServlet" method="post">
  <input type="hidden" name="action" value="delete">
  <label for="usernameDelete">Username:</label> <br>
  <input type="text" id="usernameDelete" name="username" required><br>
  <input type="submit" value="Delete User">
</form>

<h3>Select User</h3>
<form action="UserServlet" method="get">
  <input type="hidden" name="action" value="getUser">
  <label for="usernameGet">Username:</label><br>
  <input type="text" id="usernameGet" name="username" required><br>
  <input type="submit" value="Get User">
</form>

<%
  User user = (User) request.getAttribute("user");
  if (user != null) {
%>
<h3>Selected User</h3>
<table>
  <tr>
    <th>Username</th>
    <th>Email</th>
    <th>Birthday</th>
    <th>Sex</th>
  </tr>
  <tr>
    <td><%= user.getUsername() %></td>
    <td><%= user.getEmail() %></td>
    <td><%= user.getBirthday() %></td>
    <td><%= user.getSex() == 1 ? "Male" : "Female" %></td>
  </tr>
</table>
<%
  }
%>


</body>
</html>
