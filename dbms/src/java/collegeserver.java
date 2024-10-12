import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet(urlPatterns = { "/collegeserver" })
public class collegeserver extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            // Get parameters from the form
            String rollNo = request.getParameter("rollNo");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String college = request.getParameter("college");

            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Database connection URL for Oracle XE
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "system", "root");
            PreparedStatement ps = con.prepareStatement("INSERT INTO collegetable VALUES (?, ?, ?, ?)");
            ps.setString(1, rollNo);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, college);

            ps.executeUpdate();
            con.close();
            response.sendRedirect("index.html");
        } catch (Exception e) {
            out.println(e);
        }
    }
}
