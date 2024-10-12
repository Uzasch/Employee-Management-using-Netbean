import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet(urlPatterns = { "/coursedetailserver" })
public class coursedetailserver extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            // Get parameters from the form
            String course = request.getParameter("coursedetailcourse");
            String subject1 = request.getParameter("subject1");
            String subject2 = request.getParameter("subject2");
            String subject3 = request.getParameter("subject3");

            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Database connection URL for Oracle XE
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "system", "root");
            
            // Prepare SQL statement
            PreparedStatement ps = con.prepareStatement("INSERT INTO coursedetailtable (course, subject1, subject2, subject3) VALUES (?, ?, ?, ?)");

            // Add a validation check for a non-null and non-empty course value
            if (course != null && !course.isEmpty()) {
                ps.setString(1, course);
                ps.setString(2, subject1);
                ps.setString(3, subject2);
                ps.setString(4, subject3);

                ps.executeUpdate();
                con.close();
                response.sendRedirect("index.html");
            } else {
                out.println("Course value is null or empty. Please provide a valid course.");
            }
        } catch (Exception e) {
            out.println(e);
        }
    }
}
