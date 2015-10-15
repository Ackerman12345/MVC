
package Controller;

import Model.*;
import View.*;
import Principal.Inicio;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Controller implements ActionListener {

    Modelo modelo;
    Principal vista;
    private Login login;
    private String Mensaje = "El Nombre de usuario o la contraseña son incorrectos";
    private String MensajeRegistro = "";
    private String MensajeLogin = "";
    private String MensajeBienvenida = "Bienvenido al Sistema de Acceso";
    private String MensajeModificacion = "Error al realizar la modificacion";

    public Controller(Principal vista, Modelo modelo, Login login) {
        this.modelo = modelo;
        this.vista = vista;
        this.login = login;

        this.modelo = new Modelo();
        this.login.getBotonIngresar().addActionListener(this);
    }

    public boolean validarmodificacion() {
        MensajeRegistro = "";
        boolean respuesta = true;
        PersonaDTO persona = new PersonaDTO();
        persona.setNombre(vista.getPanelModificacion().getNombre());
        persona.setPaterno(vista.getPanelModificacion().getPaterno());
        persona.setMaterno(vista.getPanelModificacion().getMaterno());
        persona.setUsuario(vista.getPanelModificacion().getUsuario());
        persona.setContraseña(vista.getPanelModificacion().getPass());
        persona.setTelefono(vista.getPanelModificacion().getTelefono());
        persona.setEmail(vista.getPanelModificacion().getEmail());
        persona.setDireccion(vista.getPanelModificacion().getDireccion());
        persona.setEstatus(vista.getPanelModificacion().getEstatus());
        if (persona.getNombre().length() == 0) {
            respuesta = false;
            MensajeRegistro += "Campo Nombre invalido : Campo vacio\n";
        }
        if (persona.getPaterno().length() == 0) {
            respuesta = false;
            MensajeRegistro += "Campo Apellido Paterno invalido : Campo vacio\n";
        }
        if (persona.getMaterno().length() == 0) {
            respuesta = false;
            MensajeRegistro += "Campo Apellido Materno invalido : Campo vacio\n";
        }
        if (persona.getUsuario().length() == 0) {
            respuesta = false;
            MensajeRegistro += "Campo Nombre de Usuario invalido : Campo vacio\n";
        }
        if (persona.getUsuario().length() >= 12) {
            respuesta = false;
            MensajeRegistro += "Campo Nombre de Usuario invalido : Debe ser menor a 12 caracteres alfanumericos\n";
        }
        if (!(persona.getContraseña().length() >= 8 && persona.getContraseña().length() <= 15)) {
            respuesta = false;
            MensajeRegistro += "Campo Contraseña invalido : Debe ser tener entre 8 y  15 caracteres alfanumericos\n";
        }
        if (!persona.getEmail().matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
            respuesta = false;
            MensajeRegistro += "Campo Email invalido : \n";
        }
        if (persona.getTelefono().length() != 10) {
            respuesta = false;
            MensajeRegistro += "Campo Telefono invalido : Debe ser de 10 digitos\n";
        }
        if (persona.getDireccion().length() == 0) {
            respuesta = false;
            MensajeRegistro += "Campo Direccion invalido : Campo vacio\n";
        }

        return respuesta;
    }

    public boolean validar() {
        MensajeRegistro = "";
        boolean respuesta = true;
        PersonaDTO persona = new PersonaDTO();
        persona.setNombre(vista.getPanelAltaActual().getNombre());
        persona.setPaterno(vista.getPanelAltaActual().getPaterno());
        persona.setMaterno(vista.getPanelAltaActual().getMaterno());
        persona.setUsuario(vista.getPanelAltaActual().getUsuario());
        persona.setContraseña(vista.getPanelAltaActual().getPass());
        persona.setTelefono(vista.getPanelAltaActual().getTelefono());
        persona.setEmail(vista.getPanelAltaActual().getEmail());
        persona.setDireccion(vista.getPanelAltaActual().getDireccion());
        persona.setEstatus(vista.getPanelAltaActual().getEstatus());
        if (persona.getNombre().length() == 0) {
            respuesta = false;
            MensajeRegistro += "Campo Nombre invalido : Campo vacio\n";
        }
        if (persona.getPaterno().length() == 0) {
            respuesta = false;
            MensajeRegistro += "Campo Apellido Paterno invalido : Campo vacio\n";
        }
        if (persona.getMaterno().length() == 0) {
            respuesta = false;
            MensajeRegistro += "Campo Apellido Materno invalido : Campo vacio\n";
        }
        if (persona.getUsuario().length() == 0) {
            respuesta = false;
            MensajeRegistro += "Campo Nombre de Usuario invalido : Campo vacio\n";
        }
        if (persona.getUsuario().length() >= 12) {
            respuesta = false;
            MensajeRegistro += "Campo Nombre de Usuario invalido : Debe ser menor a 12 caracteres alfanumericos\n";
        }
        if (!(persona.getContraseña().length() >= 8 && persona.getContraseña().length() <= 15)) {
            respuesta = false;
            MensajeRegistro += "Campo Contraseña invalido : Debe ser tener entre 8 y  15 caracteres alfanumericos\n";
        }
        if (!persona.getEmail().matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
            respuesta = false;
            MensajeRegistro += "Campo Email invalido : \n";
        }
        if (persona.getTelefono().length() != 10) {
            respuesta = false;
            MensajeRegistro += "Campo Telefono invalido : Debe ser de 10 digitos\n";
        }
        if (persona.getDireccion().length() == 0) {
            respuesta = false;
            MensajeRegistro += "Campo Direccion invalido : Campo vacio\n";
        }

        return respuesta;
    }

    public boolean validarLogin(String usuario, String contrasenia) {
        boolean respuesta = true;
        MensajeLogin = "";
        if (usuario.length() == 0) {
            respuesta = false;
            MensajeLogin += "Campo Nombre de Usuario invalido: Campo vacio\n";
        }
        if (contrasenia.length() == 0) {
            respuesta = false;
            MensajeLogin += "Campo Contraseña invalido : Campo vacio";
        }
        return respuesta;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Entrar")) {
            String usuario = getLogin().getNombreUsuario();
            String contrasenia = login.getContrasenia();
            if (validarLogin(usuario, contrasenia)) {
                boolean verificar = modelo.login(login.getNombreUsuario(), login.getContrasenia());
                if (verificar) {
                    login.dispose();
                    vista = new Principal();
                    vista.getJMenuItemRegistro().addActionListener(this);
                    vista.getJMenuItemBusqueda().addActionListener(this);
                    vista.getMenuModificar().addActionListener(this);
                    vista.getMenuEliminar().addActionListener(this);
                    JOptionPane.showMessageDialog(login, MensajeBienvenida, "Mensaje de Bienvenida", 1);
                } else {
                    JOptionPane.showMessageDialog(login, Mensaje, "Notificaciones", 2);
                }
            } else {
                JOptionPane.showMessageDialog(login, MensajeLogin, "Notificaciones", 2);
            }
        } //Caso de modificicacion de usuario
        else if (e.getSource() == this.vista.getMenuModificar()) {
            String nombredeusuario = vista.getValueTableUsuario();
            JDialog d = new JDialog(vista, "Modificar", true);
            vista.setVentanaEmergenteModificacion(d);
            Container botones = new Container();
            botones.setLayout(new FlowLayout());
            PersonaDTO persona = modelo.getDatos(nombredeusuario);
            Principal.JPanelModificacionUsuarios panel = vista.new JPanelModificacionUsuarios(
                    persona.getNombre(),
                    persona.getPaterno(),
                    persona.getMaterno(),
                    persona.getUsuario(),
                    persona.getContraseña(),
                    persona.getTelefono(),
                    persona.getEmail(),
                    persona.getDireccion(),
                    persona.getEstatus()
            );
            panel.setUsuario(nombredeusuario);
            vista.setPanelModificacion(panel);
            vista.getPanelModificacion().getbotonModificar().addActionListener(this);
            d.add(panel, BorderLayout.CENTER);
            d.add(botones, BorderLayout.SOUTH);
            d.setSize(770, 500);
            d.setLocationRelativeTo(null);
            d.setVisible(true);

        } else if (e.getActionCommand().equals("Modificar")) {
            if (validarmodificacion()) {
                PersonaDTO persona = new PersonaDTO();
                persona.setNombre(vista.getPanelModificacion().getNombre());
                persona.setPaterno(vista.getPanelModificacion().getPaterno());
                persona.setMaterno(vista.getPanelModificacion().getMaterno());
                persona.setUsuario(vista.getPanelModificacion().getUsuario());
                persona.setContraseña(vista.getPanelModificacion().getPass());
                persona.setTelefono(vista.getPanelModificacion().getTelefono());
                persona.setEmail(vista.getPanelModificacion().getEmail());
                persona.setDireccion(vista.getPanelModificacion().getDireccion());
                persona.setEstatus(vista.getPanelModificacion().getEstatus());
                String notificacion = EstadoTransaccion.notificacion(modelo.modifica(vista.panelmodificacion.getUsuarioM(), persona));
                if (notificacion.equals("Transaccion Exitosa")) {
                    int fila = vista.getTabla().getSelectedRow();
                    vista.modelo.setValueAt(persona.getNombre() + " " + persona.getPaterno() + " " + persona.getMaterno(), fila, 0);
                    vista.modelo.setValueAt(persona.getUsuario(), fila, 1);
                    vista.modelo.setValueAt(persona.getEmail(), fila, 2);
                    vista.modelo.setValueAt(persona.getEstatus(), fila, 3);
                    JOptionPane.showMessageDialog(this.vista, notificacion);
                    vista.getVentanaEmergenteModificacion().dispose();
                } else {
                    JOptionPane.showMessageDialog(this.vista, MensajeModificacion);
                }

            } else {
                JOptionPane.showMessageDialog(this.vista, MensajeRegistro);
            }
        } else if (e.getSource() == vista.getMenuEliminar()) {
            vista.getMenuEliminar().setEnabled(false);
            vista.getMenuModificar().setEnabled(false);
            String notifica = EstadoTransaccion.notificacion(modelo.elimina(vista.getValueTableUsuario()));
            JOptionPane.showMessageDialog(this.vista, notifica);
            if (notifica.equals("Transaccion Exitosa")) {
                vista.modelo.removeRow(vista.getTabla().getSelectedRow());
            }
        } else if (e.getSource() == vista.getJMenuItemRegistro()) {
            vista.getMenuEliminar().setEnabled(false);
            vista.getMenuModificar().setEnabled(false);
            this.vista.getContentPane().removeAll();
            this.vista.getContentPane().repaint();
            this.vista.addComponent(this.vista.getPanelAlta());
            this.vista.getContentPane().validate();
            this.vista.getPanelAltaActual().botonRegistro().addActionListener(this);

            //this.vista.getPanelRegistroActual().botonRegistro().addActionListener(this);
        } //e.getSource()==this.vista.getPanelRegistroActual().botonRegistro(
        else if (e.getActionCommand().equals("Aceptar")) {
            if (validar()) {
                PersonaDTO persona = new PersonaDTO();
                persona.setNombre(vista.getPanelAltaActual().getNombre());
                persona.setPaterno(vista.getPanelAltaActual().getPaterno());
                persona.setMaterno(vista.getPanelAltaActual().getMaterno());
                persona.setUsuario(vista.getPanelAltaActual().getUsuario());
                persona.setContraseña(vista.getPanelAltaActual().getPass());
                persona.setTelefono(vista.getPanelAltaActual().getTelefono());
                persona.setEmail(vista.getPanelAltaActual().getEmail());
                persona.setDireccion(vista.getPanelAltaActual().getDireccion());
                persona.setEstatus(vista.getPanelAltaActual().getEstatus());
                JOptionPane.showMessageDialog(this.vista, EstadoTransaccion.notificacion(modelo.registra(persona)));
                vista.getPanelAltaActual().setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this.vista, MensajeRegistro);
            }

        } else if (e.getActionCommand().equals("Registrarse")) {
            PersonaDTO a = this.vista.getPanelRegistroActual().getDatosPanelRegistro();
            JOptionPane.showMessageDialog(this.vista, EstadoTransaccion.notificacion(modelo.registra(a)));
        } else if (e.getSource() == this.vista.getJMenuItemBusqueda()) {
            vista.getMenuEliminar().setEnabled(false);
            vista.getMenuModificar().setEnabled(false);
            this.vista.getContentPane().removeAll();
            this.vista.getContentPane().repaint();
            this.vista.addComponent(this.vista.getPanelSubbusqueda());
            this.vista.getContentPane().validate();
            this.vista.getPanelSubbusquedaActual().getSubBusqueda().addActionListener(this);

        } else if (e.getSource() == this.vista.getPanelSubbusquedaActual().getSubBusqueda()) {
            vista.getMenuEliminar().setEnabled(false);
            vista.getMenuModificar().setEnabled(false);
            if (vista.getTabla() != null) {
                for (int i = 0; i < vista.getTabla().getRowCount(); i++) {
                    vista.modelo.removeRow(i);
                }
            }
            if(this.vista.getContenedortabla()!=null)
                vista.getContentPane().remove(vista.getContenedortabla());
            if (vista.getPanelSubbusquedaActual().getRadioTodo().isSelected()) {
                this.vista.getContentPane().repaint();
                try {
                    this.vista.modelo = modelo.getTabla();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                vista.setContenedortabla(this.vista.panelBusqueda());
                this.vista.addComponent(vista.getContenedortabla());
                this.vista.getContentPane().validate();
            } else if (vista.getPanelSubbusquedaActual().getRadioFiltro().isSelected()) {
                this.vista.getContentPane().repaint();
                try {
                    this.vista.modelo = modelo.getTablaFiltros(vista.getPanelSubbusquedaActual().Nommbre());
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                vista.setContenedortabla(this.vista.panelBusqueda());
                this.vista.addComponent(vista.getContenedortabla());
                this.vista.getContentPane().validate();
            }
        }
    }

    public Login getLogin() {
        return login;
    }
    
}
