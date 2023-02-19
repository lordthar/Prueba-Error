package proyect.UniBanco.Model;

public class Administrador {

        private String nombre;
        private String apellido;
        private String cedula;
        CuentaAcceso cuentaAcceso;
    public Administrador(String nombre, String apellido, String cedula, CuentaAcceso cuentaAcceso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.cuentaAcceso= cuentaAcceso;
    }
    public Administrador (){
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public CuentaAcceso getCuentaAcceso() {
        return cuentaAcceso;
    }
    public void setCuentaAcceso(CuentaAcceso cuentaAcceso) {
        this.cuentaAcceso = cuentaAcceso;
    }
    public boolean verificarCuentaAdmin(String user, String passWord){
        if(cuentaAcceso.getUser().equals(user) && cuentaAcceso.getPassWord().equals(passWord)){
            return true;
        }
        return false;
    }

}
