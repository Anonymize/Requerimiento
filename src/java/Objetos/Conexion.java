package Objetos;

import java.sql.*;


public class Conexion {
    public Connection getConexion(){
        Connection cn =null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost/requerimientos", "root", "");
        }catch(ClassNotFoundException | SQLException ex){}
        return cn;
    }
}
