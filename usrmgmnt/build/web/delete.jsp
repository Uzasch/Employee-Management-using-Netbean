<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Delete User</title>
    <style>
        * {
            margin: 0;
            box-sizing: border-box;
        }

        body {
            font-family: times New roman;
            background-color: #f9f9f9;
            color: #333;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            padding: 20px;
        }

        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        h2 {
            color: #4caf50; 
            margin-bottom: 20px;
            font-size: 28px;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        p {
            color: #555;
            margin-bottom: 20px;
            font-size: 18px;
        }

        button {
            background-color: #4caf50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #b71c1c;
        }
    </style>
</head>

<body>
    <div class="container">
        <h2>Delete User</h2>
        <form action="deleteserver" method="post">
            <input type="hidden" name="userId" value="<%= request.getParameter("id") %>">
            <p>Are you sure you want to delete this user?</p>
            <button type="submit">Delete</button>
        </form>
    </div>
</body>

</html>
