
package Model;

import java.sql.Connection;

public interface PersonaDAO {
    public PersonaDTO getDatos(String usuario,Connection con);
    public boolean verificar(String usuario, String contrasenia,Connection con);
    public int Registrar(PersonaDTO persona,Connection con);
    public int Eliminar(String usuario,Connection con) ;                 
}
