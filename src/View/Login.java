
package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame {

    private JButton btnIngresar;
    private JLabel lnlImagen;
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JLabel lblPass;
    private JPanel PanelColor;
    private JPasswordField txtPass;
    private JTextField txtUsuario;
    private static String MensajeLogin = "";

    public static String getMensajeLogin() {
        return MensajeLogin;
    }

    public static void setMensajeLogin(String Mensaje) {
        MensajeLogin = Mensaje;
    }

    public Login() {
        super("Login");
        setSize(460, 506);
        add(PanelLogin());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getNombreUsuario() {
        return txtUsuario.getText();
    }

    public String getContrasenia() {
        return String.valueOf(txtPass.getPassword());
    }

    public JButton getBotonIngresar() {
        return btnIngresar;
    }

    public javax.swing.JPanel PanelLogin() {
        JPanel PanelLogin = new JPanel();
        lnlImagen = new javax.swing.JLabel();
        PanelColor = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();

        PanelLogin.setLayout(null);

        lnlImagen.setIcon(new javax.swing.ImageIcon("C:\\Users\\PMx-PHP\\Pictures\\login.jpg")); // NOI18N
        PanelLogin.add(lnlImagen);
        lnlImagen.setBounds(110, 80, 208, 220);

        PanelColor.setBackground(new java.awt.Color(153, 0, 51));
        PanelColor.setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("ACCESO DEL SISTEMA");
        PanelColor.add(lblTitulo);
        lblTitulo.setBounds(130, 20, 170, 17);

        PanelLogin.add(PanelColor);
        PanelColor.setBounds(10, 10, 420, 50);

        lblNombre.setText("Nombre de Usuario:");
        PanelLogin.add(lblNombre);
        lblNombre.setBounds(80, 330, 120, 14);
        PanelLogin.add(txtUsuario);
        txtUsuario.setBounds(200, 330, 190, 20);

        lblPass.setText("Contraseña:");
        PanelLogin.add(lblPass);
        lblPass.setBounds(80, 370, 760, 14);
        PanelLogin.add(txtPass);
        txtPass.setBounds(200, 370, 190, 20);

        btnIngresar.setText("Entrar");
        PanelLogin.add(btnIngresar);
        btnIngresar.setBounds(139, 417, 152, 38);

        return PanelLogin;

    }

}
