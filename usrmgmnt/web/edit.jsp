<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Edit User</title>
    <style>
        * {
            margin: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Times New Roman;
            background-color: #f4f4f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
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
            font-size: 24px;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: stretch;
        }

        label {
            color: #555;
            margin-bottom: 5px;
            font-weight: bold;
            text-align: left;
        }

        input[type="text"],
        input[type="email"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 16px;
        }

        button {
            background-color: #4caf50;
            color: #ffffff;
            padding: 12px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #45a049;
        }

        .message {
            color: #ff0000;
            margin-top: 20px;
        }
    </style>
</head>

<body>
    <div class="container">
        <h2>Edit User</h2>
        <%
            String userId = request.getParameter("userId");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usertable WHERE id = ?");
            ps.setInt(1, Integer.parseInt(userId));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
        %>
        <form action="editserver" method="post">
            <input type="hidden" name="userId" value="<%= userId %>">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= name %>" required>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= email %>" required>
            <label for="country">Country:</label>
            <input type="text" id="country" name="country" value="<%= country %>" required>
            <button type="submit">Update</button>
        </form>
        <%
            } else {
                out.println("<p class='message'>User not found!</p>");
            }
            con.close();
        %>
    </div>
</body>

</html>
