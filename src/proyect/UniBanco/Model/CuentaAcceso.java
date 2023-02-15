package proyect.UniBanco.Model;

public class CuentaAcceso {

    private String user;
    private String passWord;

    public CuentaAcceso(String usuario, String contraseña) {
        this.user = usuario;
        this.passWord = contraseña;
    }

    public CuentaAcceso() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
