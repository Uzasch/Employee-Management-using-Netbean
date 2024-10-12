<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User List</title>
    <style>
        * {
            margin: 0;
            box-sizing: border-box;
        }

        body {
            font-family: times new roman;
            background-repeat: no-repeat;
            background-size: cover;
            margin: 0;
            padding: 0;
        }

        .header-container {
            position: fixed;
            width: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
        }

        .header {
            background-color: #4caf50; /* Green theme */
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            height: 60px;
        }

        .header a {
            text-decoration: none;
            color: white;
        }

        .header button {
            background-color: #ffffff;
            color: #4caf50;
            border: 2px solid #4caf50;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        .header button:hover {
            background-color: #4caf50;
            color: white;
            border-color: transparent;
        }

        .title {
            padding: 80px 20px 20px;
            text-align: center;
        }

        .table-container {
            display: flex;
            justify-content: center;
            padding: 20px;
        }

        .Table {
            border: 1px solid #ddd;
            font-size: large;
            width: 100%;
            max-width: 1200px;
            background-color: #ffffff; /* White background */
            border-radius: 8px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 15px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #4caf50; /* Green theme for header */
            color: white;
        }

        td a {
            text-decoration: none;
            color: #4caf50;
            font-weight: bold;
        }

        td a:hover {
            color: #333;
        }
    </style>
</head>

<body>
    <div class="header-container">
        <div class="header">
            <a href="index.html"><button class="view">Add New User</button></a>
        </div>
    </div>
    <div class="title">
        <h1>User Table</h1>
    </div>
    <div class="table-container">
        <div class="Table">
            <table>
                <tr>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Country</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <%
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM usertable");
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getString("id") %></td>
                    <td><%= rs.getString("name") %></td>
                    <td><%= rs.getString("email") %></td>
                    <td><%= rs.getString("country") %></td>
                    <td><a href="edit.jsp?userId=<%= rs.getString("id") %>">Edit</a></td>
                    <td><a href="delete.jsp?id=<%= rs.getString("id") %>">Delete</a></td>
                </tr>
                <%
                    }
                    con.close();
                %>
            </table>
        </div>
    </div>
</body>

</html>
