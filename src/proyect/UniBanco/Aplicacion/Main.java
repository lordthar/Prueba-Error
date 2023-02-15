package proyect.UniBanco.Aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import proyect.UniBanco.Controllers.*;
import proyect.UniBanco.Exceptions.ClienteException;
import proyect.UniBanco.Exceptions.CuentaException;
import proyect.UniBanco.Model.*;


import java.io.IOException;

public class Main extends Application {


    Banco banco= new Banco("davivienda", "12312");

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
            this.stage=stage;
            mostrarMainWindow();
    }


    public void mostrarMainWindow()  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/MainWindow.fxml"));
            AnchorPane rootLayout = loader.load();
            MainWindowController controller = loader.getController();
            controller.setMain(this);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("Somo exe");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarLoginAdmin(){
        try{
            //cargamos el FXML
            FXMLLoader loader = new FXMLLoader();
            //Direccion del FXML
            loader.setLocation(Main.class.getResource("../View/LoginAdmin.fxml"));
            AnchorPane rootlayout = loader.load();
            LoginAdminController controller= loader.getController();
            controller.setMain(this);
            //Inicializa las Scenes
            Scene scene = new Scene(rootlayout);
            stage.setScene(scene);
            stage.setTitle("eresdfas");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarLoginCliente() {
        try{
            //cargamos el FXML
            FXMLLoader loader = new FXMLLoader();
            //Direccion del FXML
            loader.setLocation(Main.class.getResource("../View/LoginCliente.fxml"));
            AnchorPane rootlayout = loader.load();
            LoginClienteController controller= loader.getController();
            controller.setMain(this);
            //Inicializa las Scenes
            Scene scene = new Scene(rootlayout);
            stage.setScene(scene);
            stage.setTitle("Somos insanos");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearcuentaAdmin(Administrador admin) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/CrearCuentaAdmin.fxml"));
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            CrearCuentaAdminController controller = loader.getController();
            controller.setMain(this);
            stage.setScene(scene);
            stage.setTitle("Dovha");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarMainMenu(Cliente cliente){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/MainMenu.fxml"));
            AnchorPane rootLayout = loader.load();
            MainMenuController controller = loader.getController();
            controller.setMain(this);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("aqui vamos tilin");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean verificarCuenta(String cedula, String numeroCuenta){
        return banco.verificarLogin(cedula,numeroCuenta);
    }
    public boolean crearUser( String numeroCuenta , double saldo, Tipo_Cuenta tipoCuenta) throws CuentaException {
        return banco.crearCuenta(numeroCuenta,saldo, tipoCuenta);
    }

    public boolean crearCliente(String nombre, String apellido, String cedula, String direccion, String email, Cuenta cuenta) throws ClienteException {
           return banco.crearCliente( nombre, apellido, cedula, direccion, email, cuenta);
    }
    public Cliente obtenerCliente(String cedula) throws ClienteException {
        return banco.buscarCliente(cedula);
    }
    public Cuenta buscarCuenta(String numeroCuenta) throws CuentaException {
            return banco.buscarCuenta(numeroCuenta);
    }
    public boolean verificarCuentaAdmin (String user, String passWord){
        return banco.verificarLoginAdmin(user, passWord);
    }
    public Administrador obtenerAdmin (String user, String passWord){
        return banco.obtenerAdministrador(user, passWord);
    }
}
