
package View;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;


public class Principal extends JFrame {

    private Container contenedorprincipal;
    private Container contenedornorte;
    private Container contenedorcentral;
    private JDialog VentanaEmergenteModificacion;
    private String Mensaje = "El Nombre de usuario o la contraseña son incorrectos";
    private String MensajeRegistro = "";

    private Container contenedortabla;

    public Container getContenedortabla() {
        return contenedortabla;
    }

    public void setContenedortabla(Container contenedortabla) {
        this.contenedortabla = contenedortabla;
    }
    private String MensajeBienvenida = "Bienvenido al Sistema de Acceso";
    private String MensajeModificacion = "Error al realizar la modificacion";

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getMensajeRegistro() {
        return MensajeRegistro;
    }

    public void setMensajeRegistro(String MensajeRegistro) {
        this.MensajeRegistro = MensajeRegistro;
    }

    public String getMensajeBienvenida() {
        return MensajeBienvenida;
    }

    public void setMensajeBienvenida(String MensajeBienvenida) {
        this.MensajeBienvenida = MensajeBienvenida;
    }

    public String getMensajeModificacion() {
        return MensajeModificacion;
    }

    public void setMensajeModificacion(String MensajeModificacion) {
        this.MensajeModificacion = MensajeModificacion;
    }

    public JDialog getVentanaEmergenteModificacion() {
        return VentanaEmergenteModificacion;
    }

    public void setVentanaEmergenteModificacion(JDialog VentanaEmergenteModificacion) {
        this.VentanaEmergenteModificacion = VentanaEmergenteModificacion;
    }
    private JMenuBar barramenu;
    private JMenu menu;
    private JMenuItem Itemregistro, Itembusqueda;
    private JMenuItem modificar, eliminar;

    public DefaultTableModel modelo;
    private JTable tabla;

    //Componentes asociados a el panel subbusqueda
    private subBusqueda panelsubbusqueda;

    public subBusqueda getPanelSubbusqueda() {
        panelsubbusqueda = new subBusqueda();
        return panelsubbusqueda;
    }

    public subBusqueda getPanelSubbusquedaActual() {
        return panelsubbusqueda;
    }

    public void addComponent(subBusqueda panel) {
        contenedorprincipal.add(panel, BorderLayout.NORTH);
    }

    public void addComponent(Container panel) {
        contenedorprincipal.add(panel, BorderLayout.CENTER);
    }

    public JMenuItem getJMenuItemBusqueda() {
        return Itembusqueda;
    }
    //Componentes asociados a el panel subbusqueda

    //Componentes asociados a el panel registro
    private PanelRegistro panelregistro;

    public PanelRegistro getPanelRegistro() {
        panelregistro = new PanelRegistro();
        return panelregistro;
    }

    public PanelRegistro getPanelRegistroActual() {
        return panelregistro;
    }

    public void addComponent(PanelRegistro panel) {
        contenedorprincipal.add(panel, BorderLayout.CENTER);
    }

    public JMenuItem getJMenuItemRegistro() {
        return Itemregistro;
    }
    //Componentes asociados a el panel registro

    //Componentes asociados a el panel modificacion
    //Componentes asociados a el panel alta 
    public JPanelModificacionUsuarios panelmodificacion;

    public JPanelModificacionUsuarios getPanelModificacion() {
        return panelmodificacion;
    }

    public void setPanelModificacion(JPanelModificacionUsuarios panel) {
        panelmodificacion = panel;
    }

    //Componentes asociados a el panel alta 
    public JPanelAltaUsuarios panelalta;

    public JPanelAltaUsuarios getPanelAlta() {
        panelalta = new JPanelAltaUsuarios();
        return panelalta;
    }

    public JPanelAltaUsuarios getPanelAltaActual() {
        return panelalta;
    }

    public void addComponent(JPanelAltaUsuarios panel) {
        contenedorprincipal.add(panel, BorderLayout.CENTER);
    }

    //Componentes asociados a el panel registro
    public JMenuItem getMenuModificar() {
        return modificar;
    }

    public JMenuItem getMenuEliminar() {
        return eliminar;
    }

    public Principal() {
        super("Mi Ventana");
        contenedorprincipal = getContentPane();
        barramenu = new JMenuBar();
        setJMenuBar(barramenu);

        menu = new JMenu("Menu Principal");
        Itemregistro = new JMenuItem("Alta de Usuario");
        menu.add(Itemregistro);
        Itembusqueda = new JMenuItem("Busqueda de Usuarios");
        menu.add(Itembusqueda);
        modificar = new JMenuItem("Edición de Usuario");
        menu.add(modificar);
        eliminar = new JMenuItem("Borrado de Usuario");
        menu.add(eliminar);
        barramenu.add(menu);

        modificar.setEnabled(false);
        eliminar.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(770, 500);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public class subBusqueda extends javax.swing.JPanel {

        public JButton getSubBusqueda() {
            return btnBuscar;
        }

        public JRadioButton getRadioTodo() {
            return todo;
        }

        public JRadioButton getRadioFiltro() {
            return filtro;
        }

        public String Nommbre() {
            return txtNombre.getText();
        }

        public subBusqueda() {
            initComponents();
            buttonGroup1.add(todo);
            buttonGroup1.add(filtro);
            btnBuscar.setEnabled(false);
            txtNombre.setEnabled(false);
            todo.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent e) {
                            btnBuscar.setEnabled(true);
                            txtNombre.setEnabled(false);
                        }
                    }
            );
            filtro.addItemListener(
                    new ItemListener() {
                        public void itemStateChanged(ItemEvent e) {
                            btnBuscar.setEnabled(true);
                            txtNombre.setEnabled(true);
                        }
                    }
            );
        }

        private void initComponents() {
            buttonGroup1 = new javax.swing.ButtonGroup();
            jLabel1 = new javax.swing.JLabel();
            todo = new javax.swing.JRadioButton();
            filtro = new javax.swing.JRadioButton();
            jLabel2 = new javax.swing.JLabel();
            txtNombre = new javax.swing.JTextField(15);
            btnBuscar = new javax.swing.JButton();

            jLabel1.setText("Tipo de Busqueda:");
            add(jLabel1);

            todo.setText("Todo");
            add(todo);

            filtro.setText("Filtros");
            add(filtro);

            jLabel2.setText("Nombre");
            add(jLabel2);
            add(txtNombre);

            btnBuscar.setText("Buscar");
            add(btnBuscar);
        }

        private javax.swing.JButton btnBuscar;
        private javax.swing.ButtonGroup buttonGroup1;
        private javax.swing.JRadioButton filtro;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JRadioButton todo;
        private javax.swing.JTextField txtNombre;
    }

    public class PanelRegistro extends javax.swing.JPanel {

        public Model.PersonaDTO getDatosPanelRegistro() {
            Model.PersonaDTO persona = new Model.PersonaDTO();
            persona.setNombre(this.txtNombre.getText());
            persona.setPaterno(this.txtPaterno.getText());
            persona.setMaterno(this.txtMaterno.getText());
            persona.setUsuario(this.txtUser.getText());
            persona.setContraseña(String.valueOf(this.Pass.getPassword()));
            persona.setTelefono(this.txtTelefono.getText());
            persona.setEmail(this.txtEmail.getText());
            persona.setDireccion(this.txtDireccion.getText());
            persona.setEstatus(this.activo.isSelected() ? "1" : "0");
            return persona;
        }

        public JButton botonRegistro() {
            return jButton1;
        }

        public PanelRegistro() {
            initComponents();
        }

        private void initComponents() {

            buttonGroup1 = new javax.swing.ButtonGroup();
            buttonGroup2 = new javax.swing.ButtonGroup();
            buttonGroup3 = new javax.swing.ButtonGroup();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            txtNombre = new javax.swing.JTextField();
            txtPaterno = new javax.swing.JTextField();
            txtMaterno = new javax.swing.JTextField();
            jLabel1 = new javax.swing.JLabel();
            txtUser = new javax.swing.JTextField();
            jLabel5 = new javax.swing.JLabel();
            Pass = new javax.swing.JPasswordField();
            jLabel6 = new javax.swing.JLabel();
            txtTelefono = new javax.swing.JTextField();
            jLabel7 = new javax.swing.JLabel();
            txtEmail = new javax.swing.JTextField();
            jLabel8 = new javax.swing.JLabel();
            txtDireccion = new javax.swing.JTextField();
            jLabel9 = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
            activo = new javax.swing.JRadioButton();
            inactivo = new javax.swing.JRadioButton();
            jButton1 = new javax.swing.JButton();

            setLayout(null);

            jLabel2.setText("Nombre:");
            add(jLabel2);
            jLabel2.setBounds(25, 33, 70, 14);

            jLabel3.setText("Apellido Paterno:");
            add(jLabel3);
            jLabel3.setBounds(249, 33, 110, 14);

            jLabel4.setText("Apellido Materno:");
            add(jLabel4);
            jLabel4.setBounds(510, 30, 100, 20);
            add(txtNombre);
            txtNombre.setBounds(95, 30, 144, 20);
            add(txtPaterno);
            txtPaterno.setBounds(360, 30, 143, 20);

            add(txtMaterno);
            txtMaterno.setBounds(620, 30, 160, 20);

            jLabel1.setText("Nombre de Usuario:");
            add(jLabel1);
            jLabel1.setBounds(27, 161, 160, 14);
            add(txtUser);
            txtUser.setBounds(160, 160, 170, 20);

            jLabel5.setText("Contraseña:");
            add(jLabel5);
            jLabel5.setBounds(350, 160, 80, 14);
            add(Pass);
            Pass.setBounds(440, 160, 190, 20);

            jLabel6.setText("Telefono:");
            add(jLabel6);
            jLabel6.setBounds(25, 71, 60, 14);
            add(txtTelefono);
            txtTelefono.setBounds(95, 68, 144, 20);

            jLabel7.setText("Correo Electronico:");
            add(jLabel7);
            jLabel7.setBounds(249, 71, 150, 14);
            add(txtEmail);
            txtEmail.setBounds(360, 70, 420, 20);

            jLabel8.setText("Direccion: ");
            add(jLabel8);
            jLabel8.setBounds(25, 109, 70, 14);
            add(txtDireccion);
            txtDireccion.setBounds(95, 106, 623, 20);
            add(jLabel9);
            jLabel9.setBounds(25, 225, 0, 0);

            jLabel10.setText("Estatus:");
            add(jLabel10);
            jLabel10.setBounds(27, 196, 70, 14);

            activo.setText("Activo");

            add(activo);
            activo.setBounds(63, 217, 90, 23);

            inactivo.setText("Inactivo");
            add(inactivo);
            inactivo.setBounds(63, 240, 100, 23);

            jButton1.setText("Registrarse");
            add(jButton1);
            jButton1.setBounds(172, 270, 334, 27);
        }// </editor-fold>                        

        private javax.swing.JPasswordField Pass;
        private javax.swing.JRadioButton activo;
        private javax.swing.ButtonGroup buttonGroup1;
        private javax.swing.ButtonGroup buttonGroup2;
        private javax.swing.ButtonGroup buttonGroup3;
        private javax.swing.JRadioButton inactivo;
        private javax.swing.JButton jButton1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JTextField txtDireccion;
        private javax.swing.JTextField txtEmail;
        private javax.swing.JTextField txtMaterno;
        private javax.swing.JTextField txtNombre;
        private javax.swing.JTextField txtPaterno;
        private javax.swing.JTextField txtTelefono;
        private javax.swing.JTextField txtUser;
    }

    public class PanelModificacion extends javax.swing.JPanel implements ActionListener {

        /**
         * Creates new form PanelRegistro
         */
        Model.PersonaDTO actual, nueva;

        public PanelModificacion(Model.PersonaDTO persona) {
            initComponents();
            actual = persona;
            buttonGroup1.add(activo);
            buttonGroup1.add(inactivo);
            this.txtNombre.setText(persona.getNombre());
            this.txtPaterno.setText(persona.getPaterno());
            this.txtMaterno.setText(persona.getMaterno());
            this.txtTelefono.setText(persona.getTelefono());
            this.txtEmail.setText(persona.getEmail());
            this.txtDireccion.setText(persona.getDireccion());
            this.txtUser.setText(persona.getUsuario());
            this.Pass.setText(persona.getContraseña());
            if (persona.getEstatus().equals("1")) {
                this.activo.setSelected(true);
            } else {
                this.inactivo.setSelected(true);
            }
            btnModificar.addActionListener(this);

        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnModificar) {
                //  new Controlador().modificar(e,actual,PersonaNueva());                                      
            }
        }
        /* public Persona PersonaNueva(){
         String status="1";;
         if(inactivo.isSelected())
         status="0";
         Persona nueva=new Persona(
         this.txtNombre.getText(),
         this.txtPaterno.getText(),
         this.txtMaterno.getText(),
         this.txtUser.getText(),
         String.valueOf(this.Pass.getPassword()),
         this.txtTelefono.getText(),
         this.txtEmail.getText(),
         this.txtDireccion.getText(),
         status
         );
         return nueva;
         }*/

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            buttonGroup1 = new javax.swing.ButtonGroup();
            buttonGroup2 = new javax.swing.ButtonGroup();
            buttonGroup3 = new javax.swing.ButtonGroup();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            txtNombre = new javax.swing.JTextField();
            txtPaterno = new javax.swing.JTextField();
            txtMaterno = new javax.swing.JTextField();
            jLabel1 = new javax.swing.JLabel();
            txtUser = new javax.swing.JTextField();
            jLabel5 = new javax.swing.JLabel();
            Pass = new javax.swing.JPasswordField();
            jLabel6 = new javax.swing.JLabel();
            txtTelefono = new javax.swing.JTextField();
            jLabel7 = new javax.swing.JLabel();
            txtEmail = new javax.swing.JTextField();
            jLabel8 = new javax.swing.JLabel();
            txtDireccion = new javax.swing.JTextField();
            jLabel9 = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
            activo = new javax.swing.JRadioButton();
            inactivo = new javax.swing.JRadioButton();
            btnModificar = new javax.swing.JButton();

            setLayout(null);

            jLabel2.setText("Nombre:");
            add(jLabel2);
            jLabel2.setBounds(25, 33, 70, 14);

            jLabel3.setText("Apellido Paterno:");
            add(jLabel3);
            jLabel3.setBounds(249, 33, 110, 14);

            jLabel4.setText("Apellido Materno:");
            add(jLabel4);
            jLabel4.setBounds(510, 30, 100, 20);
            add(txtNombre);
            txtNombre.setBounds(95, 30, 144, 20);
            add(txtPaterno);
            txtPaterno.setBounds(360, 30, 143, 20);

            txtMaterno.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtMaternoActionPerformed(evt);
                }
            });
            add(txtMaterno);
            txtMaterno.setBounds(620, 30, 160, 20);

            jLabel1.setText("Nombre de Usuario:");
            add(jLabel1);
            jLabel1.setBounds(27, 161, 160, 14);
            add(txtUser);
            txtUser.setBounds(160, 160, 170, 20);

            jLabel5.setText("Contraseña:");
            add(jLabel5);
            jLabel5.setBounds(350, 160, 80, 14);
            add(Pass);
            Pass.setBounds(440, 160, 190, 20);

            jLabel6.setText("Telefono:");
            add(jLabel6);
            jLabel6.setBounds(25, 71, 60, 14);
            add(txtTelefono);
            txtTelefono.setBounds(95, 68, 144, 20);

            jLabel7.setText("Correo Electronico:");
            add(jLabel7);
            jLabel7.setBounds(249, 71, 150, 14);
            add(txtEmail);
            txtEmail.setBounds(360, 70, 420, 20);

            jLabel8.setText("Direccion: ");
            add(jLabel8);
            jLabel8.setBounds(25, 109, 70, 14);
            add(txtDireccion);
            txtDireccion.setBounds(95, 106, 623, 20);
            add(jLabel9);
            jLabel9.setBounds(25, 225, 0, 0);

            jLabel10.setText("Estatus:");
            add(jLabel10);
            jLabel10.setBounds(27, 196, 70, 14);

            activo.setText("Activo");
            activo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    activoActionPerformed(evt);
                }
            });
            add(activo);
            activo.setBounds(63, 217, 90, 23);

            inactivo.setText("Inactivo");
            inactivo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    inactivoActionPerformed(evt);
                }
            });
            add(inactivo);
            inactivo.setBounds(63, 240, 100, 23);

            btnModificar.setText("Modificar");
            btnModificar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnModificarActionPerformed(evt);
                }
            });
            add(btnModificar);
            btnModificar.setBounds(172, 270, 334, 27);
        }// </editor-fold>                        

        private void txtMaternoActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
        }

        private void activoActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
        }

        private void inactivoActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
        }

        private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
        }

        // Variables declaration - do not modify                     
        private javax.swing.JPasswordField Pass;
        private javax.swing.JRadioButton activo;
        public javax.swing.JButton btnModificar;
        private javax.swing.ButtonGroup buttonGroup1;
        private javax.swing.ButtonGroup buttonGroup2;
        private javax.swing.ButtonGroup buttonGroup3;
        private javax.swing.JRadioButton inactivo;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JTextField txtDireccion;
        private javax.swing.JTextField txtEmail;
        private javax.swing.JTextField txtMaterno;
        private javax.swing.JTextField txtNombre;
        private javax.swing.JTextField txtPaterno;
        private javax.swing.JTextField txtTelefono;
        private javax.swing.JTextField txtUser;
        // End of variables declaration                   
    }

    public void setModeloTable(DefaultTableModel model) {
        this.modelo = model;
    }

    public JTable getTabla() {
        return tabla;
    }

    public TableModel getModeloTabla() {
        return tabla.getModel();
    }

    public String getValueTableUsuario() {
        String dato = "";
        try {
            dato = String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            return dato;
        }
        return dato;
    }

    public Container panelBusqueda() {
        Container contenedor = new Container();
        contenedor.setLayout(new BorderLayout());
        tabla = new JTable(modelo);
        tabla.setName("tabla");
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (tabla.getSelectedRowCount() == 1) {
                    modificar.setEnabled(true);
                    eliminar.setEnabled(true);
                } else {
                    modificar.setEnabled(false);
                    eliminar.setEnabled(false);
                }
            }
        });

        contenedor.add(new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        return contenedor;
    }

    public class JPanelModificacionUsuarios extends javax.swing.JPanel {

        public void setUsuario(String cad) {
            usuario = cad;
        }

        public String getUsuarioM() {
            return usuario;
        }

        public JButton getbotonModificar() {
            return btnModificar;
        }
        private String usuario;

        public JButton botonCancelar() {
            return btnCancelar;
        }

        /**
         * Creates new form JPanelAltaUsuarios
         */
        public JPanelModificacionUsuarios(
                String Nombre,
                String Paterno,
                String Materno,
                String Usuario,
                String Contraseña,
                String Telefono,
                String Email,
                String Direccion,
                String Estatus
        ) {
            initComponents();
            txtNombre.setText(Nombre);
            txtPaterno.setText(Paterno);
            txtMaterno.setText(Materno);
            txtUsuario.setText(Usuario);
            txtPass.setText(Contraseña);
            txtTelefono.setText(Telefono);
            txtEmail.setText(Email);
            txtDireccion.setText(Direccion);
            estatus.setSelectedItem(Estatus);
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            PanelTitulo = new javax.swing.JPanel();
            lblTitulo = new javax.swing.JLabel();
            panelentradas = new javax.swing.JPanel();
            lnlNombre = new javax.swing.JLabel();
            lblPaterno = new javax.swing.JLabel();
            lblMaterno = new javax.swing.JLabel();
            lnlUsuario = new javax.swing.JLabel();
            lnlContrasenia = new javax.swing.JLabel();
            lblTelefono = new javax.swing.JLabel();
            lblEmail = new javax.swing.JLabel();
            txtNombre = new javax.swing.JTextField();
            txtPaterno = new javax.swing.JTextField();
            txtMaterno = new javax.swing.JTextField();
            txtUsuario = new javax.swing.JTextField();
            txtTelefono = new javax.swing.JTextField();
            txtEmail = new javax.swing.JTextField();
            txtPass = new javax.swing.JPasswordField();

            txtestado = new javax.swing.JTextField();
            txtmunicipio = new javax.swing.JTextField();
            txtlocalidad = new javax.swing.JTextField();

            txtDireccion = new javax.swing.JTextField();
            lblDireccion = new javax.swing.JLabel();
            lblEstado = new javax.swing.JLabel();
            comboestado = new javax.swing.JComboBox();
            lblMunicipio = new javax.swing.JLabel();
            lblLocalidad = new javax.swing.JLabel();
            combomunicipio = new javax.swing.JComboBox();
            combolocalidad = new javax.swing.JComboBox();
            lblEstatus = new javax.swing.JLabel();
            btnModificar = new javax.swing.JButton();
            btnCancelar = new javax.swing.JButton();

            setMinimumSize(new java.awt.Dimension(500, 500));
            setPreferredSize(new java.awt.Dimension(700, 500));
            setLayout(null);

            PanelTitulo.setBackground(new java.awt.Color(153, 0, 51));
            PanelTitulo.setLayout(new java.awt.BorderLayout());

            lblTitulo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
            lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
            lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblTitulo.setText("MODIFICACION DE USUARIOS");
            lblTitulo.setToolTipText("");
            PanelTitulo.add(lblTitulo, java.awt.BorderLayout.CENTER);

            add(PanelTitulo);
            PanelTitulo.setBounds(10, 10, 740, 90);

            panelentradas.setLayout(null);

            lnlNombre.setText("Nombre:");
            panelentradas.add(lnlNombre);
            lnlNombre.setBounds(48, 30, 51, 14);

            lblPaterno.setText("Apellido Paterno:");
            panelentradas.add(lblPaterno);
            lblPaterno.setBounds(48, 61, 100, 14);

            lblMaterno.setText("Apellido Materno:");
            panelentradas.add(lblMaterno);
            lblMaterno.setBounds(48, 92, 100, 14);

            lnlUsuario.setText("Nombre de Usuario:");
            panelentradas.add(lnlUsuario);
            lnlUsuario.setBounds(48, 123, 120, 14);

            lnlContrasenia.setText("Contraseña:");
            panelentradas.add(lnlContrasenia);
            lnlContrasenia.setBounds(48, 154, 70, 14);

            lblTelefono.setText("Telefono:");
            panelentradas.add(lblTelefono);
            lblTelefono.setBounds(48, 185, 60, 14);

            lblEmail.setText("Email:");
            panelentradas.add(lblEmail);
            lblEmail.setBounds(48, 216, 40, 14);

            int x = 165;
            panelentradas.add(txtNombre);
            txtNombre.setBounds(x, 27, 242, 20);
            new ValidarTextos(txtNombre, 60, "cadena");

            panelentradas.add(txtPaterno);
            txtPaterno.setBounds(x, 89, 242, 20);
            new ValidarTextos(txtPaterno, 60, "cadena");

            panelentradas.add(txtMaterno);
            txtMaterno.setBounds(x, 58, 242, 20);
            new ValidarTextos(txtMaterno, 60, "cadena");

            panelentradas.add(txtUsuario);
            txtUsuario.setBounds(x, 120, 243, 20);
            new ValidarTextos(txtUsuario, 0, 12);

            panelentradas.add(txtTelefono);
            txtTelefono.setBounds(x, 182, 243, 20);
            new ValidarTextos(txtTelefono, 10, "telefono");

            panelentradas.add(txtEmail);
            txtEmail.setBounds(x, 213, 243, 20);
            panelentradas.add(txtPass);
            txtPass.setBounds(x, 151, 243, 20);
            new ValidarTextos(txtPass, 8, 15);

            lblDireccion.setText("Direccion");
            panelentradas.add(lblDireccion);
            lblDireccion.setBounds(439, 30, 63, 14);

            /* lblEstado.setText("Estado");
             panelentradas.add(lblEstado);
             lblEstado.setBounds(449, 61, 53, 14);
            
             */
            //    txtestado.setBounds(575, 58, 157, 20);
            //    panelentradas.add(txtestado);
            /*comboestado.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Seleccione una opción", "Item 2", "Item 3", "Item 4"}));
             panelentradas.add(comboestado);
             comboestado.setBounds(575, 58, 157, 20);
             */

            /*       lblMunicipio.setText("Municipio/Delegación");
             panelentradas.add(lblMunicipio);
             lblMunicipio.setBounds(449, 92, 120, 14);

             lblLocalidad.setText("Localidad");
             panelentradas.add(lblLocalidad);
             lblLocalidad.setBounds(449, 123, 64, 14);
             */
            panelentradas.add(txtDireccion);
            txtDireccion.setBounds(439, 92, 250, 20);
            new ValidarTextos(txtDireccion, 255, "direccion");
         //   txtmunicipio.setBounds(575, 89, 157, 20);
            //  panelentradas.add(txtmunicipio);
            /*combomunicipio.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
             panelentradas.add(combomunicipio);
             combomunicipio.setBounds(575, 89, 157, 20);
             */
            //   txtlocalidad.setBounds(575, 120, 157, 20);
            //   panelentradas.add(txtlocalidad);
            /*combolocalidad.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
             panelentradas.add(combolocalidad);
             combolocalidad.setBounds(575, 120, 157, 20);*/

            lblEstatus.setText("Estatus");
            panelentradas.add(lblEstatus);
            lblEstatus.setBounds(439, 154, 45, 14);

            estatus = new javax.swing.JComboBox();
            estatus.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"ACTIVO", "INACTIVO"}));
            estatus.setBounds(449, 181, 83, 23);
            panelentradas.add(estatus);

            btnModificar.setText("Modificar");
            panelentradas.add(btnModificar);
            btnModificar.setBounds(184, 256, 160, 34);

            /*btnCancelar.setText("Cancelar");
             panelentradas.add(btnCancelar);
             btnCancelar.setBounds(421, 256, 160, 34);*/
            add(panelentradas);
            panelentradas.setBounds(10, 140, 730, 290);
        }// </editor-fold>                        

        // Variables declaration - do not modify    
        private javax.swing.JTextField txtDireccion;
        private javax.swing.JButton btnModificar;
        private javax.swing.JButton btnCancelar;
        private javax.swing.JTextField txtestado;
        private javax.swing.JTextField txtmunicipio;
        private javax.swing.JTextField txtlocalidad;
        private javax.swing.JComboBox comboestado;
        private javax.swing.JComboBox combomunicipio;
        private javax.swing.JComboBox combolocalidad;
        private javax.swing.JLabel lblTitulo;
        private javax.swing.JLabel lblEstado;
        private javax.swing.JLabel lblMunicipio;
        private javax.swing.JLabel lblLocalidad;
        private javax.swing.JLabel lblEstatus;
        private javax.swing.JLabel lblDireccion;
        private javax.swing.JLabel lnlNombre;
        private javax.swing.JLabel lblPaterno;
        private javax.swing.JLabel lblMaterno;
        private javax.swing.JLabel lnlUsuario;
        private javax.swing.JLabel lnlContrasenia;
        private javax.swing.JLabel lblTelefono;
        private javax.swing.JLabel lblEmail;
        private javax.swing.JPanel PanelTitulo;
        private javax.swing.JPanel panelentradas;
        private javax.swing.JPasswordField txtPass;
        private javax.swing.JTextField txtNombre;
        private javax.swing.JTextField txtPaterno;
        private javax.swing.JTextField txtMaterno;
        private javax.swing.JTextField txtUsuario;
        private javax.swing.JTextField txtTelefono;
        private javax.swing.JTextField txtEmail;
        private javax.swing.JComboBox estatus;
        // End of variables declaration       

        public String getNombre() {
            return txtNombre.getText();
        }

        public String getPaterno() {
            return txtPaterno.getText();
        }

        public String getMaterno() {
            return txtMaterno.getText();
        }

        public String getUsuario() {
            return txtUsuario.getText();
        }

        public String getPass() {
            return String.valueOf(txtPass.getPassword());
        }

        public String getDireccion() {
            return txtDireccion.getText();
        }

        public String getEmail() {
            return txtEmail.getText();
        }

        public String getTelefono() {
            return txtTelefono.getText();
        }

        public String getEstatus() {
            return (String) estatus.getSelectedItem();
        }

    }
    /*
     *Panel de Alat de Usuario
     */

    public class JPanelAltaUsuarios extends javax.swing.JPanel {

        public JButton botonRegistro() {
            return btnAceptar;
        }

        public JButton botonCancelar() {
            return btnCancelar;
        }

        /**
         * Creates new form JPanelAltaUsuarios
         */
        public JPanelAltaUsuarios() {
            initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            PanelTitulo = new javax.swing.JPanel();
            lblTitulo = new javax.swing.JLabel();
            panelentradas = new javax.swing.JPanel();
            lnlNombre = new javax.swing.JLabel();
            lblPaterno = new javax.swing.JLabel();
            lblMaterno = new javax.swing.JLabel();
            lnlUsuario = new javax.swing.JLabel();
            lnlContrasenia = new javax.swing.JLabel();
            lblTelefono = new javax.swing.JLabel();
            lblEmail = new javax.swing.JLabel();
            txtNombre = new javax.swing.JTextField();
            txtPaterno = new javax.swing.JTextField();
            txtMaterno = new javax.swing.JTextField();
            txtUsuario = new javax.swing.JTextField();
            txtTelefono = new javax.swing.JTextField();
            txtEmail = new javax.swing.JTextField();
            txtPass = new javax.swing.JPasswordField();

            txtestado = new javax.swing.JTextField();
            txtmunicipio = new javax.swing.JTextField();
            txtlocalidad = new javax.swing.JTextField();

            txtDireccion = new javax.swing.JTextField();
            lblDireccion = new javax.swing.JLabel();
            lblEstado = new javax.swing.JLabel();
            comboestado = new javax.swing.JComboBox();
            lblMunicipio = new javax.swing.JLabel();
            lblLocalidad = new javax.swing.JLabel();
            combomunicipio = new javax.swing.JComboBox();
            combolocalidad = new javax.swing.JComboBox();
            lblEstatus = new javax.swing.JLabel();
            btnAceptar = new javax.swing.JButton();
            btnCancelar = new javax.swing.JButton();

            setMinimumSize(new java.awt.Dimension(500, 500));
            setPreferredSize(new java.awt.Dimension(700, 500));
            setLayout(null);

            PanelTitulo.setBackground(new java.awt.Color(153, 0, 51));
            PanelTitulo.setLayout(new java.awt.BorderLayout());

            lblTitulo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
            lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
            lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblTitulo.setText("ALTA DE  USUARIOS");
            lblTitulo.setToolTipText("");
            PanelTitulo.add(lblTitulo, java.awt.BorderLayout.CENTER);

            add(PanelTitulo);
            PanelTitulo.setBounds(10, 10, 740, 90);

            panelentradas.setLayout(null);

            lnlNombre.setText("Nombre:");
            panelentradas.add(lnlNombre);
            lnlNombre.setBounds(48, 30, 51, 14);

            lblPaterno.setText("Apellido Paterno:");
            panelentradas.add(lblPaterno);
            lblPaterno.setBounds(48, 61, 100, 14);

            lblMaterno.setText("Apellido Materno:");
            panelentradas.add(lblMaterno);
            lblMaterno.setBounds(48, 92, 100, 14);

            lnlUsuario.setText("Nombre de Usuario:");
            panelentradas.add(lnlUsuario);
            lnlUsuario.setBounds(48, 123, 120, 14);

            lnlContrasenia.setText("Contraseña:");
            panelentradas.add(lnlContrasenia);
            lnlContrasenia.setBounds(48, 154, 70, 14);

            lblTelefono.setText("Telefono:");
            panelentradas.add(lblTelefono);
            lblTelefono.setBounds(48, 185, 60, 14);

            lblEmail.setText("Email:");
            panelentradas.add(lblEmail);
            lblEmail.setBounds(48, 216, 40, 14);

            int x = 165;
            panelentradas.add(txtNombre);
            txtNombre.setBounds(x, 27, 242, 20);
            new ValidarTextos(txtNombre, 60, "cadena");

            panelentradas.add(txtPaterno);
            txtPaterno.setBounds(x, 89, 242, 20);
            new ValidarTextos(txtPaterno, 60, "cadena");

            panelentradas.add(txtMaterno);
            txtMaterno.setBounds(x, 58, 242, 20);
            new ValidarTextos(txtMaterno, 60, "cadena");

            panelentradas.add(txtUsuario);
            txtUsuario.setBounds(x, 120, 243, 20);
            new ValidarTextos(txtUsuario, 0, 12);

            panelentradas.add(txtTelefono);
            txtTelefono.setBounds(x, 182, 243, 20);
            new ValidarTextos(txtTelefono, 10, "telefono");

            panelentradas.add(txtEmail);
            txtEmail.setBounds(x, 213, 243, 20);
            panelentradas.add(txtPass);
            txtPass.setBounds(x, 151, 243, 20);
            new ValidarTextos(txtPass, 8, 15);

            lblDireccion.setText("Direccion");
            panelentradas.add(lblDireccion);
            lblDireccion.setBounds(439, 30, 63, 14);

            /* lblEstado.setText("Estado");
             panelentradas.add(lblEstado);
             lblEstado.setBounds(449, 61, 53, 14);
            
             */
            //    txtestado.setBounds(575, 58, 157, 20);
            //    panelentradas.add(txtestado);
            /*comboestado.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Seleccione una opción", "Item 2", "Item 3", "Item 4"}));
             panelentradas.add(comboestado);
             comboestado.setBounds(575, 58, 157, 20);
             */

            /*       lblMunicipio.setText("Municipio/Delegación");
             panelentradas.add(lblMunicipio);
             lblMunicipio.setBounds(449, 92, 120, 14);

             lblLocalidad.setText("Localidad");
             panelentradas.add(lblLocalidad);
             lblLocalidad.setBounds(449, 123, 64, 14);
             */
            panelentradas.add(txtDireccion);
            txtDireccion.setBounds(439, 92, 250, 20);
            new ValidarTextos(txtDireccion, 255, "direccion");
         //   txtmunicipio.setBounds(575, 89, 157, 20);
            //  panelentradas.add(txtmunicipio);
            /*combomunicipio.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
             panelentradas.add(combomunicipio);
             combomunicipio.setBounds(575, 89, 157, 20);
             */
            //   txtlocalidad.setBounds(575, 120, 157, 20);
            //   panelentradas.add(txtlocalidad);
            /*combolocalidad.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
             panelentradas.add(combolocalidad);
             combolocalidad.setBounds(575, 120, 157, 20);*/

            lblEstatus.setText("Estatus");
            panelentradas.add(lblEstatus);
            lblEstatus.setBounds(439, 154, 45, 14);

            estatus = new javax.swing.JComboBox();
            estatus.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"ACTIVO", "INACTIVO"}));
            estatus.setBounds(449, 181, 83, 23);
            panelentradas.add(estatus);

            btnAceptar.setText("Aceptar");
            panelentradas.add(btnAceptar);
            btnAceptar.setBounds(184, 256, 160, 34);

            /* btnCancelar.setText("Cancelar");
             panelentradas.add(btnCancelar);
             btnCancelar.setBounds(421, 256, 160, 34);*/
            add(panelentradas);
            panelentradas.setBounds(10, 140, 730, 290);
        }// </editor-fold>                        

        // Variables declaration - do not modify    
        private javax.swing.JTextField txtDireccion;
        private javax.swing.JButton btnAceptar;
        private javax.swing.JButton btnCancelar;
        private javax.swing.JTextField txtestado;
        private javax.swing.JTextField txtmunicipio;
        private javax.swing.JTextField txtlocalidad;
        private javax.swing.JComboBox comboestado;
        private javax.swing.JComboBox combomunicipio;
        private javax.swing.JComboBox combolocalidad;
        private javax.swing.JLabel lblTitulo;
        private javax.swing.JLabel lblEstado;
        private javax.swing.JLabel lblMunicipio;
        private javax.swing.JLabel lblLocalidad;
        private javax.swing.JLabel lblEstatus;
        private javax.swing.JLabel lblDireccion;
        private javax.swing.JLabel lnlNombre;
        private javax.swing.JLabel lblPaterno;
        private javax.swing.JLabel lblMaterno;
        private javax.swing.JLabel lnlUsuario;
        private javax.swing.JLabel lnlContrasenia;
        private javax.swing.JLabel lblTelefono;
        private javax.swing.JLabel lblEmail;
        private javax.swing.JPanel PanelTitulo;
        private javax.swing.JPanel panelentradas;
        private javax.swing.JPasswordField txtPass;
        private javax.swing.JTextField txtNombre;
        private javax.swing.JTextField txtPaterno;
        private javax.swing.JTextField txtMaterno;
        private javax.swing.JTextField txtUsuario;
        private javax.swing.JTextField txtTelefono;
        private javax.swing.JTextField txtEmail;
        private javax.swing.JComboBox estatus;
        // End of variables declaration       

        public String getNombre() {
            return txtNombre.getText();
        }

        public String getPaterno() {
            return txtPaterno.getText();
        }

        public String getMaterno() {
            return txtMaterno.getText();
        }

        public String getUsuario() {
            return txtUsuario.getText();
        }

        public String getPass() {
            return String.valueOf(txtPass.getPassword());
        }

        public String getDireccion() {
            return txtDireccion.getText();
        }

        public String getEmail() {
            return txtEmail.getText();
        }

        public String getTelefono() {
            return txtTelefono.getText();
        }

        public String getEstatus() {
            return (String) estatus.getSelectedItem();
        }

    }

}

class ValidarTextos {

    JTextComponent objeto;

    ValidarTextos(JTextComponent validar) {
        objeto = validar;
        objeto.addKeyListener(
                new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        if (e.getSource() == objeto) {
                            if (!(e.getKeyChar() >= 'A' && e.getKeyChar() <= 'z')) {
                                if (e.getKeyChar() == ' ') {
                                } else {
                                    e.consume();
                                }
                            }
                            if (objeto.getText().length() > 60) {
                                e.consume();
                            }
                        }
                    }
                }
        );
    }

    ValidarTextos(JTextComponent validar, final int menor, String cadena) {
        if (cadena.equals("direccion")) {
            objeto = validar;
            objeto.addKeyListener(
                    new KeyAdapter() {
                        public void keyTyped(KeyEvent e) {
                            if (e.getSource() == objeto) {
                                if (objeto.getText().length() > (menor - 1)) {
                                    e.consume();
                                }
                            }
                        }
                    }
            );
        } else if (cadena.equals("telefono")) {
            objeto = validar;
            objeto.addKeyListener(
                    new KeyAdapter() {
                        public void keyTyped(KeyEvent e) {
                            if (e.getSource() == objeto) {
                                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
                                } else {
                                    e.consume();
                                }
                                if (objeto.getText().length() > (menor - 1)) {
                                    e.consume();
                                }
                            }
                        }
                    }
            );
        } else if (cadena.equals("numero")) {
            objeto = validar;
            objeto.addKeyListener(
                    new KeyAdapter() {
                        public void keyTyped(KeyEvent e) {
                            if (e.getSource() == objeto) {
                                if (!(e.getKeyChar() >= 'A' && e.getKeyChar() <= 'z')) {
                                    if (e.getKeyChar() == ' ') {
                                    } else if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
                                    } else {
                                        e.consume();
                                    }
                                }
                                if (objeto.getText().length() > (menor - 1)) {
                                    e.consume();
                                }
                            }
                        }
                    }
            );
        } else if (cadena.equals("cadena")) {
            objeto = validar;
            objeto.addKeyListener(
                    new KeyAdapter() {
                        public void keyTyped(KeyEvent e) {
                            if (e.getSource() == objeto) {
                                if (!(e.getKeyChar() >= 'A' && e.getKeyChar() <= 'z')) {
                                    if (e.getKeyChar() == ' ') {
                                    } else {
                                        e.consume();
                                    }
                                }
                                if (objeto.getText().length() > (menor - 1)) {
                                    e.consume();
                                }
                            }
                        }
                    }
            );
        }
    }

    ValidarTextos(JTextComponent validar, final int menor, final int mayor) {
        objeto = validar;
        objeto.addKeyListener(
                new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        if (e.getSource() == objeto) {
                            if (!(e.getKeyChar() >= 'A' && e.getKeyChar() <= 'z')) {
                                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
                                } else {
                                    e.consume();
                                }
                            }
                            if (objeto.getText().length() >= mayor) {
                                e.consume();
                            }
                        }
                    }
                }
        );
    }

}
