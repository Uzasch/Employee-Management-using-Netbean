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

@WebServlet("/editserver")
public class editserver extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String userId = request.getParameter("userId");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String country = request.getParameter("country");

            if (userId == null || userId.isEmpty()) {
                throw new IllegalArgumentException("User ID is missing");
            }

            int id = Integer.parseInt(userId);

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
            PreparedStatement ps = con.prepareStatement("UPDATE usertable SET name = ?, email = ?, country = ? WHERE id = ?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, country);
            ps.setInt(4, id);

            int rowsAffected = ps.executeUpdate();
            con.close();

            if (rowsAffected > 0) {
                response.sendRedirect("list.jsp");
            } else {
                out.println("<html><body><h3>No user found with ID: " + id + "</h3></body></html>");
            }
        } catch (NumberFormatException e) {
            out.println("<html><body><h3>Invalid User ID format: " + e.getMessage() + "</h3></body></html>");
        } catch (IllegalArgumentException e) {
            out.println("<html><body><h3>" + e.getMessage() + "</h3></body></html>");
        } catch (Exception e) {
            out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
        }
    }
}
