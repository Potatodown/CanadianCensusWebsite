package dao;

import java.sql.*;

public class Geodao {

    public boolean connectionSuccess(String username, String password) throws ClassNotFoundException {
        boolean connection = false;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmanager", username, password)){
            connection = true;
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return connection;
    }
    public ResultSet geographicAreas(String username, String password) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmanager", username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from geographicarea order by level");
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
