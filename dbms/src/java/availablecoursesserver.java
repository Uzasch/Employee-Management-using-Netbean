import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet(urlPatterns = { "/availablecoursesserver" })
public class availablecoursesserver extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            // Get parameters from the form
            String collegeCourseID = request.getParameter("College_Course_ID");
            String college = request.getParameter("college");
            String courseAvailable = request.getParameter("availablecourse");
            String fees = request.getParameter("fees");

            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Database connection URL for Oracle XE
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "system", "root");
            PreparedStatement ps = con.prepareStatement("INSERT INTO availablecoursestable VALUES (?, ?, ?, ?)");
            ps.setString(1, collegeCourseID);
            ps.setString(2, college);
            ps.setString(3, courseAvailable);
            ps.setString(4, fees);

            ps.executeUpdate();
            con.close();
            response.sendRedirect("index.html");
        } catch (Exception e) {
            out.println(e);
        }
    }
}
