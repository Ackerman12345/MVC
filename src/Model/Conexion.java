
package Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion{
    private String Driver="com.mysql.jdbc.Driver";
    private String Url="jdbc:mysql://localhost/";
    private String basededatos;
    private String usuario,contrasenia;
    
    private Connection  con;
    public  Conexion(String basededatos,String usuario,String pass){  
       this.basededatos=basededatos;
       this.usuario=usuario;
       this.contrasenia=pass;
       Url+=basededatos;
    }
    public Connection getConexion(){
        try {
            Class.forName(Driver);
            con=DriverManager.getConnection(Url,usuario,contrasenia);
            return con;
        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            return null;
        }       
    }
    
}