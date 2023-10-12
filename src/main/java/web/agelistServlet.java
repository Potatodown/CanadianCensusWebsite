package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "agelistServlet", value = "/agelistServlet")
public class agelistServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        out.println("<html><body style=\"background: antiquewhite\">");
        out.println("<h1>Age Listing for the Canada-Wide Population: 2016</h1>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmanager", userName, password);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT agegroup.description AS agegroup, SUM(age.male) AS Sum_male, SUM(age.female) AS Sum_female from age JOIN agegroup ON age.ageGroup = agegroup.agegroupID where censusYear = 2 GROUP BY ageGroup");
            out.println("<table border=1 width=50% height=50% style=\"background: #d5dbce\">");
            out.println("<tr><th>Age Group</th><th>Male</th><th>Female</th><tr>");
            while (rs.next()) {
                String agegroup = rs.getString("agegroup");
                String malpop = String.valueOf(rs.getLong("Sum_male"));
                String fempop = String.valueOf(rs.getLong("Sum_female"));
                out.println("<tr><td>" + agegroup + "</td><td>" + malpop + "</td><td>" + fempop + "</td><tr>");
            }
            out.println("</table>");
            out.println("<h1>Age Listing for the Canada-Wide Population: 2021</h1>");
            ResultSet rs2 = stmt.executeQuery("SELECT agegroup.description AS agegroup, SUM(age.male) AS Sum_male, SUM(age.female) AS Sum_female from age JOIN agegroup ON age.ageGroup = agegroup.agegroupID where censusYear = 1 GROUP BY ageGroup");
            out.println("<table border=1 width=50% height=50% style=\"background: #d5dbce\">");
            out.println("<tr><th>Age Group</th><th>Male</th><th>Female</th><tr>");
            while (rs2.next()) {
                String agegroup = rs2.getString("agegroup");
                String malpop = String.valueOf(rs2.getLong("Sum_male"));
                String fempop = String.valueOf(rs2.getLong("Sum_female"));
                out.println("<tr><td>" + agegroup + "</td><td>" + malpop + "</td><td>" + fempop + "</td><tr>");
            }
            out.println("</table>");
            out.println("<a href=\"mainpageServlet\">Back</a>");
            out.println("</html></body>");
            con.close();
        } catch (Exception e) {
            out.println("error");
        }

    }

    public void destroy() {
    }
}
