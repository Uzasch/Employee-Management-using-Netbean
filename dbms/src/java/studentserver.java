import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import oracle.jdbc.OracleTypes;

@WebServlet(urlPatterns = { "/studentserver" })
public class studentserver extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String country = request.getParameter("country");

            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Database connection URL for Oracle XE
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "system", "root");

            // Step 1: Insert the new student using a stored procedure
            CallableStatement insertStmt = con.prepareCall("{call insert_student(?, ?, ?)}");
            insertStmt.setString(1, name);
            insertStmt.setString(2, email);
            insertStmt.setString(3, country);
            insertStmt.executeUpdate();

            // Step 2: Use cursor to fetch all student records
            CallableStatement fetchStmt = con.prepareCall("{call fetch_students(?)}");
            fetchStmt.registerOutParameter(1, OracleTypes.CURSOR);
            fetchStmt.execute();

            // Step 3: Get the cursor result set for students
            ResultSet studentRs = (ResultSet) fetchStmt.getObject(1);

            // Step 4: Display student records in an HTML table with styling
            out.println("<html><head>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; color: #333; padding: 20px; }");
            out.println("h1 { color: #4CAF50; }");
            out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
            out.println("table, th, td { border: 1px solid #ddd; }");
            out.println("th, td { padding: 12px; text-align: left; }");
            out.println("th { background-color: #4CAF50; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("tr:hover { background-color: #ddd; }");
            out.println("</style>");
            out.println("</head><body>");

            out.println("<h1>New student added successfully!</h1>");
            out.println("<h2>Current Student List:</h2>");
            out.println("<table>");
            out.println("<tr><th>Name</th><th>Email</th><th>Country</th></tr>");

            while (studentRs.next()) {
                out.println("<tr>");
                out.println("<td>" + studentRs.getString("name") + "</td>");
                out.println("<td>" + studentRs.getString("email") + "</td>");
                out.println("<td>" + studentRs.getString("country") + "</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            
            // Step 5: Fetch log message from log table
            PreparedStatement logStmt = con.prepareStatement("SELECT log_message FROM student_log ORDER BY log_date DESC FETCH FIRST 1 ROWS ONLY");
            ResultSet logRs = logStmt.executeQuery();

            String logMessage = "";
            if (logRs.next()) {
                logMessage = logRs.getString("log_message");
            }

            // Step 6: Show a pop-up with the log message (JavaScript alert)
            out.println("<script type='text/javascript'>");
            out.println("alert('" + logMessage + "');"); // JavaScript pop-up
            out.println("</script>");

            // Step 7: Call the function to get the updated total number of students
            CallableStatement countStmt = con.prepareCall("{? = call get_student_count}");
            countStmt.registerOutParameter(1, Types.INTEGER); // Register the return value as a number
            countStmt.execute();

            // Get the total number of students
            int totalStudents = countStmt.getInt(1);

            // Step 8: Display the updated student count
            out.println("<p><strong>Total number of students: " + totalStudents + "</strong></p>");
            out.println("</body></html>");

            con.close();
        } catch (Exception e) {
            out.println(e);
        }
    }
}
