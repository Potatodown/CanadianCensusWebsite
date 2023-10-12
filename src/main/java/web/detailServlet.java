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

@WebServlet(name = "detailServlet", value = "/detailServlet")
public class detailServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String id =req.getParameter("geoId");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        res.setContentType("text/html");
        out.println("<html><body style=\"background: antiquewhite\">");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmanager", userName, password);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from geographicarea WHERE geographicAreaID = '"+id+"'");

            while (rs.next()) {
                String name = rs.getString("name");
                String code = rs.getString("code");
                String level = rs.getString("level");
                out.println("<h1>Details for "+name+"</h1>");
                out.println("<h3>Code: "+code+"</br> Level: "+level+"</h3>");
            }

            ResultSet populationset = stmt.executeQuery("SELECT SUM(combined) AS Sum_population from age where geographicArea = '"+id+"' AND censusYear = 1");
            while (populationset.next()){
                String population = String.valueOf(populationset.getLong("Sum_population"));
                out.println("<h3>Population:</h3>");
                out.println("<h2>"+population+"</h2>");
            }
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