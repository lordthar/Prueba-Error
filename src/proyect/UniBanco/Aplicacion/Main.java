package proyect.UniBanco.Aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import proyect.UniBanco.Controllers.*;
import proyect.UniBanco.Exceptions.ClienteException;
import proyect.UniBanco.Exceptions.CuentaException;
import proyect.UniBanco.Exceptions.TransaccionException;
import proyect.UniBanco.Model.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    public void crearcuentaAdmin(Administrador administrador) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/CrearCuentaAdmin.fxml"));
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            CrearCuentaAdminController controller = loader.getController();
            controller.setMain(this,administrador);
            stage.setScene(scene);
            stage.setTitle("Dovha");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void mostrarMainAdmin(Administrador admin){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/MainAdmin.fxml"));
            AnchorPane rootLayout = loader.load();
            MainAdminController controller = loader.getController();
            controller.SetMain(this,admin);
            controller.ingresarBienvenida(admin);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("si llego tilin");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void mostrarMainMenuCliente(Cliente cliente){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/MainMenuCliente.fxml"));
            AnchorPane rootLayout = loader.load();
            MainMenuClienteController controller = loader.getController();
            controller.setMain(this, cliente);
            controller.mostrarBienvenidaCliente(cliente);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("aqui vamos tilin");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizarClienteAdmin(Administrador administrador, Cliente cliente){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/UpdateAdmin.fxml"));
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            UpdateAdminController controller = loader.getController();
            controller.setMain(this,cliente,administrador);
            stage.setScene(scene);
            stage.setTitle("where do i start");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void TransaccionesCliente(Cliente cliente){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/TransaccionesCliente.fxml"));
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            TransaccionesClienteController controller = loader.getController();
            controller.setMain(this,cliente);
            stage.setScene(scene);
            stage.setTitle("a wasar");
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
    public ArrayList<Cliente> obtenerCliente() {
        return banco.getlistaClientes();
    }
    public ArrayList<Transaccion> obtenerListaTransaccion(Cliente cliente){
        return banco.obtenerListaTransacciones(cliente);
    }
    public boolean eliminarCliente(String cedula){
        return banco.eliminarCliente(cedula);
    }
    public boolean eliminarCuenta(String numeroCuenta) throws CuentaException {
        return banco.eliminarCuenta(numeroCuenta);
    }
    public boolean actualizarCuenta (Double saldo, String numeroCuenta,Tipo_Cuenta tipocuenta) throws CuentaException {
        return banco.actualizarCuenta(saldo,numeroCuenta, tipocuenta);
    }
    public boolean actualizarCliente(String nombre, String apellido, String cedula, String direccion, String email, Cuenta cuenta) {
        return banco.actualizarCliente(nombre, apellido, cedula, direccion, email, cuenta);
    }
    public boolean crearTransaccion(LocalDate fecha, String hora, Double registroValor, Tipo_Transaccion tipoTransaccion, Estado_Transaccion estado_transaccion, String cedula) throws TransaccionException {
        return banco.crearTransaccion(fecha, hora, registroValor, tipoTransaccion, estado_transaccion, cedula);
    }


}
