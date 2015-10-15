
package Model;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAOImpl implements PersonaDAO {

    private static PersonaDAOImpl instancia = new PersonaDAOImpl();
    private PreparedStatement consulta;
    private Connection con;
    public final Lock monitor = new ReentrantLock();

    private PersonaDAOImpl() {
    }

    public synchronized static PersonaDAOImpl getPersonaDAO() {
        return instancia;
    }

    @Override
    public PersonaDTO getDatos(String usuario,Connection con) {
        PersonaDTO persona;
        boolean resultado = false;
        ResultSet filas;
        PreparedStatement staregistro = null;
        this.con=con;
        try {
            consulta = con.prepareStatement("select * from  persona where Nombre_de_Usuario=?");
            consulta.setString(1, usuario);
            filas = consulta.executeQuery();
            persona = new PersonaDTO();
            while (filas.next()) {
                persona.setNombre(filas.getString(1));
                persona.setPaterno(filas.getString(2));
                persona.setMaterno(filas.getString(3));
                persona.setUsuario(filas.getString(4));
                persona.setContraseña(filas.getString(5));
                persona.setTelefono(filas.getString(6));
                persona.setEmail(filas.getString(7));
                persona.setDireccion(filas.getString(8));
                persona.setEstatus(filas.getString(9).equals("1") ? "ACTIVO" : "INACTIVO");

            }
            return persona;
        } catch (SQLException e) {
            System.err.println("Error en la base de datos , Motivos {}. "+e);
            return null;
        } catch (NullPointerException e) {
            return null;
        } finally {
            cerrar(con);
            cerrar(consulta);
        }
    }

    @Override
    public boolean verificar(String usuario, String contrasenia,Connection con) {
        boolean resultado = false;
        ResultSet filas;
        this.con=con;
        PreparedStatement staregistro = null;
        try {
            consulta = con.prepareStatement("select * from  persona where Nombre_de_Usuario=? and Contrasenia=?");
            consulta.setString(1, usuario);
            consulta.setString(2, contrasenia);
            filas = consulta.executeQuery();
            while (filas.next()) {
                resultado = true;
            }
            return resultado;
        } catch (SQLException e) {
            return resultado;
        } catch (NullPointerException e) {
            return resultado;
        } finally {
            cerrar(con);
            cerrar(consulta);
        }
    }

    @Override
    public int Registrar(PersonaDTO persona,Connection con) {
        this.con=con;
        try {
            if (monitor.tryLock(1, TimeUnit.SECONDS)) {
                int resultado;
                con = new Conexion("examen", "root", "portomxadmin").getConexion();
                PreparedStatement staregistro = null;
                try {

                    con.setAutoCommit(false);
                    consulta = con.prepareStatement("insert into persona values(?,?,?,?,?,?,?,?,?)");
                    consulta.setString(1, persona.getNombre());
                    consulta.setString(2, persona.getPaterno());
                    consulta.setString(3, persona.getMaterno());
                    consulta.setString(4, persona.getUsuario());
                    consulta.setString(5, persona.getContraseña());
                    consulta.setString(6, persona.getTelefono());
                    consulta.setString(7, persona.getEmail());
                    consulta.setString(8, persona.getDireccion());
                    consulta.setString(9, persona.getEstatus().equals("ACTIVO") ? "1" : "0");
                    resultado = consulta.executeUpdate();;
                    con.commit();
                    return resultado;
                } catch (SQLException e) {
                    try {
                        con.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    return -1;
                } catch (NullPointerException e) {
                    return -1;
                } finally {
                    cerrar(con);
                    cerrar(consulta);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(PersonaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int Eliminar(String usuario,Connection con) {
        this.con=con;
        try {
            if (monitor.tryLock(1, TimeUnit.SECONDS)) {
                int resultado;
                con = new Conexion("examen", "root", "portomxadmin").getConexion();
                try {

                    con.setAutoCommit(false);
                    consulta = con.prepareStatement("delete from persona where Nombre_de_Usuario=?");
                    consulta.setString(1, usuario);
                    resultado = consulta.executeUpdate();
                    con.commit();
                    return resultado;
                } catch (SQLException e) {
                    try {
                        con.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    return -1;
                } catch (NullPointerException e) {
                    return -1;
                } finally {
                    cerrar(con);
                    cerrar(consulta);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(PersonaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int Modificar(String usuario, PersonaDTO anterior,Connection con) {
        this.con=con;
        try {
            if (monitor.tryLock(1, TimeUnit.SECONDS)) {
                int resultado;
                try {

                    con.setAutoCommit(false);
                    consulta = con.prepareStatement("update Persona set "
                            + "Nombre = ?, Apellido_Paterno = ?, Apellido_Materno = ? , "
                            + "Nombre_de_Usuario = ?, Contrasenia = ? , Numero_Telefonico = ?,"
                            + "Email = ?, Direccion = ?, Estatus = ? "
                            + "where "
                            + "Nombre_de_Usuario = ?");
                    consulta.setString(1, anterior.getNombre());
                    consulta.setString(2, anterior.getPaterno());
                    consulta.setString(3, anterior.getMaterno());
                    consulta.setString(4, anterior.getUsuario());
                    consulta.setString(5, anterior.getContraseña());
                    consulta.setString(6, anterior.getTelefono());
                    consulta.setString(7, anterior.getEmail());
                    consulta.setString(8, anterior.getDireccion());
                    consulta.setString(9, anterior.getEstatus().equals("ACTIVO") ? "1" : "0");
                    consulta.setString(10, usuario);
                    resultado = consulta.executeUpdate();;
                    con.commit();
                    return resultado;
                } catch (SQLException e) {
                    try {
                        con.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    return -1;
                } catch (NullPointerException e) {
                    return -1;
                } finally {
                    cerrar(con);
                    cerrar(consulta);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(PersonaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void cerrar(PreparedStatement sta) {
        if (sta != null) {
            try {
                sta.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cerrar(Connection con) {
        if (con != null) {
          /*  try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
                  */
        }
    }
}
