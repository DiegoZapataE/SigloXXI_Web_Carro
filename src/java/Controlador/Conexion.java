package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con;
    private static String url="jdbc:oracle:thin:@localhost:1521:XE";
    private static String user="SIGLOXXI";
    private static String pass="1234";
    
//    Conexión con la nube
//    private static String url="jdbc:oracle:thin:@34.176.185.52:1521:ORA12C";
//    private static String user="SIGLOXXI";
//    private static String pass="Duoc.1234";
    
    public Connection conectar(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                con=DriverManager.getConnection(url,user,pass);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found."); 
        }
        return con;    
    }
    
    public void cerrarConexion(Connection c){       
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println("ERROR CONEXIÓN");
            System.out.println(e.getMessage());
        }
    }   
}
