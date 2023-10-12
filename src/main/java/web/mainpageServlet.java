package web;

import dao.Geodao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "mainpageServlet", value = "/mainpageServlet")
public class mainpageServlet extends HttpServlet {

    private Geodao geoDao = new Geodao();
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        try {
            if(session.getAttribute("username") !=null && userName == null && password == null){
                 userName = (String) session.getAttribute("username");
                 password = (String) session.getAttribute("password");
            }
            if (!geoDao.connectionSuccess(userName,password)){
                req.setAttribute("error","Login Failed");
                req.getRequestDispatcher("index.jsp").forward(req, res);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("username", userName);
        session.setAttribute("password", password);
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        out.println("<html><body style=\"background: antiquewhite\">");
        out.println("<h1>Geogrpahic Area Classification List</h1>");
        out.println("<a href=\"agelistServlet\">Age listings</a>");
        try {
            ResultSet rs = geoDao.geographicAreas(userName,password);
            out.println("<table border=1 width=50% height=50% style=\"background: #d5dbce\">");
            out.println("<tr><th>Level</th><th>Name</th><th>View Details</th><tr>");
            while (rs.next()) {
                String level = rs.getString("level");
                String name = rs.getString("name");
                String geoId = rs.getString("geographicAreaID");
                out.println("<tr><td>" + level + "</td><td>" + name + "</td><td><a href=\"detailServlet?geoId="+geoId+"\">Details</a></td><tr>");
            }
            out.println("</table>");
            out.println("</html></body>");
        } catch (Exception e) {
            out.println("error");
        }

    }

    public void destroy() {
    }
}
