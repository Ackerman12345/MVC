
package Model;


public class PersonaDTO {

    private String Nombre, Paterno, Materno, Usuario, Contraseña, Telefono, Email, Direccion, Estatus;

    public PersonaDTO() {
    }

    public PersonaDTO(String Nombre, String Paterno, String Materno, String Usuario, String Contraseña, String Telefono, String Email, String Direccion, String Estatus) {
        this.Nombre = Nombre;
        this.Paterno = Paterno;
        this.Materno = Materno;
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Direccion = Direccion;
        this.Estatus = Estatus;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPaterno() {
        return Paterno;
    }

    public void setPaterno(String Paterno) {
        this.Paterno = Paterno;
    }

    public String getMaterno() {
        return Materno;
    }

    public void setMaterno(String Materno) {
        this.Materno = Materno;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    @Override
    public String toString() {
        String cadena = "";
        cadena
                += "Nombre : " + this.Nombre
                + " Paterno :" + this.Paterno
                + " Materno : " + this.Materno
                + " Usuario : " + this.Usuario
                + " Contraseña :" + this.Contraseña
                + " Telefono : " + this.Telefono
                + " Email : " + this.Telefono
                + " Direccion :" + this.Direccion
                + " Estatus" + this.Estatus;
        return cadena;
    }

}
