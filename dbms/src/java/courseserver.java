import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet(urlPatterns = { "/courseserver" })
public class courseserver extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            // Get parameters from the form
            String rollNo = request.getParameter("rollNo");
            String course = request.getParameter("coursename");
            String duration = request.getParameter("courseduration");

            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Database connection URL for Oracle XE
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "system", "root");
            PreparedStatement ps = con.prepareStatement("INSERT INTO coursetable VALUES (?, ?, ?)");
            ps.setString(1, rollNo);
            ps.setString(2, course);
            ps.setString(3, duration);

            ps.executeUpdate();
            con.close();
            response.sendRedirect("index.html");
        } catch (Exception e) {
            out.println(e);
        }
    }
}
