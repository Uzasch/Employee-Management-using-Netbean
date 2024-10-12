package abc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteserver")
public class deleteserver extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Retrieve the userId from request
            String userId = request.getParameter("userId");

            // Check if userId is null or empty
            if (userId == null || userId.isEmpty()) {
                throw new IllegalArgumentException("User ID is missing");
            }

            // Convert userId to integer if your 'id' column is numeric
            int id = Integer.parseInt(userId);

            // Load the database driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish a connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");

            // Prepare the SQL delete statement
            PreparedStatement ps = con.prepareStatement("DELETE FROM usertable WHERE id = ?");
            ps.setInt(1, id); // Set the userId as parameter

            // Execute the delete operation
            int rowsAffected = ps.executeUpdate();
            con.close();

            if (rowsAffected > 0) {
                response.sendRedirect("list.jsp");
            } else {
                out.println("<html><body><h3>No user found with ID: " + id + "</h3></body></html>");
            }
        } catch (NumberFormatException e) {
            // Handle case where userId is not a valid integer
            out.println("<html><body><h3>Invalid User ID format: " + e.getMessage() + "</h3></body></html>");
        } catch (IllegalArgumentException e) {
            // Handle case where userId is missing
            out.println("<html><body><h3>" + e.getMessage() + "</h3></body></html>");
        } catch (Exception e) {
            // Handle other exceptions
            out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
        }
    }
}
