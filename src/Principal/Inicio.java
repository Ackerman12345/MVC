
package Principal;


import Model.Modelo;
import View.Principal;
import Controller.Controller;
import View.Login;
import java.util.Stack;

public class Inicio {

    Modelo modelo;
    Principal vista;
    Login login = new Login();

    public static void main(String[] args) {
        new Inicio().iniciar();
    }

    public void iniciar() {
        Controller controlador = new Controller(vista, modelo, login);
    }
}
