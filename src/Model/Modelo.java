
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Modelo {    
    Connection con;
    public Modelo(){
        con = new Conexion("examen", "root", "portomxadmin").getConexion();
    }
    public PersonaDTO  getDatos(String usuario){        
        PersonaDAOImpl operaciones=PersonaDAOImpl.getPersonaDAO();
        PersonaDTO resultado=operaciones.getDatos(usuario,con);      
        return resultado;
    }
    public int  registra(PersonaDTO dato){
        PersonaDAOImpl operaciones=PersonaDAOImpl.getPersonaDAO();
        int resultado=operaciones.Registrar(dato,con);        
        return resultado;
    }
    public int  elimina(String usuario){
        PersonaDAOImpl operaciones=PersonaDAOImpl.getPersonaDAO();
        int resultado=operaciones.Eliminar(usuario,con);       
        return resultado;
    }
    public int  modifica(String usuario,PersonaDTO persona){
        PersonaDAOImpl operaciones=PersonaDAOImpl.getPersonaDAO();
        int resultado=operaciones.Modificar(usuario,persona,con);       
        return resultado;
    }
    public boolean login(String usuario,String contrasenia){
        PersonaDAOImpl operaciones=PersonaDAOImpl.getPersonaDAO();
        boolean resultado=operaciones.verificar(usuario,contrasenia,con);      
        return resultado;
    }
     public DefaultTableModel getTabla() throws SQLException{
        if(con==null)
            return new DefaultTableModel();
        PreparedStatement consulta=con.prepareStatement("select concat(Nombre,' ',Apellido_Paterno,' ',Apellido_Materno)as Nombre , Nombre_de_Usuario,Email,Estatus from persona");        ResultSet datos=consulta.executeQuery();
        
        ResultSetMetaData metas=datos.getMetaData();
        
        int numerodecolumnas=metas.getColumnCount();
        String nombredecolumnas[]=new String[numerodecolumnas];
        for (int i = 0; i < numerodecolumnas; i++) {
            nombredecolumnas[i]=metas.getColumnName(i+1);
        }
        
        DefaultTableModel tabla=new DefaultTableModel();
        tabla.setColumnIdentifiers(nombredecolumnas);
        Object []registro=new Object[numerodecolumnas];
        while(datos.next()){
            for (int i = 0; i < numerodecolumnas; i++) {
              if(i==numerodecolumnas-1)
                    registro[i]=datos.getInt(i+1)==1?"ACTIVO":"INACTIVO";
                else
               registro[i]=datos.getObject(i+1);
            }
            tabla.addRow(registro);
        }
        return tabla;
    }
     
      public DefaultTableModel getTablaFiltros(String usuario) throws SQLException{
        if(con==null)
            return new DefaultTableModel();
        PreparedStatement consulta=con.prepareStatement("select concat(Nombre,' ',Apellido_Paterno,' ',Apellido_Materno)as Nombre , Nombre_de_Usuario,Email,Estatus from persona where Nombre=?");       
        consulta.setString(1, usuario);
        ResultSet datos=consulta.executeQuery();
        
        ResultSetMetaData metas=datos.getMetaData();
        
        int numerodecolumnas=metas.getColumnCount();
        String nombredecolumnas[]=new String[numerodecolumnas];
        for (int i = 0; i < numerodecolumnas; i++) {
            nombredecolumnas[i]=metas.getColumnName(i+1);
        }
        
        DefaultTableModel tabla=new DefaultTableModel();
        tabla.setColumnIdentifiers(nombredecolumnas);
        Object []registro=new Object[numerodecolumnas];
        while(datos.next()){
            for (int i = 0; i < numerodecolumnas; i++) {
              if(i==numerodecolumnas-1)
                    registro[i]=datos.getInt(i+1)==1?"ACTIVO":"INACTIVO";
                else
               registro[i]=datos.getObject(i+1);
            }
            tabla.addRow(registro);
        }
        return tabla;
    }
}
