package proyect.UniBanco.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import proyect.UniBanco.Aplicacion.Main;
import proyect.UniBanco.Exceptions.CuentaException;
import proyect.UniBanco.Model.Administrador;
import proyect.UniBanco.Model.Cliente;

public class MainAdminController {

    Administrador administradorLoggeado = null;
    private Main main;
    Cliente clienteSeleccionado = null;
    ObservableList<Cliente> listaClienteData= FXCollections.observableArrayList();
    private Label labelBienvenidaAdmin;
    @FXML
    private TableColumn<Cliente, String> columnApellido;
    @FXML
    private TableColumn<Cliente, String> columnNombre;
    @FXML
    private TableColumn<Cliente, String> columnCedula;
    @FXML
    private TableColumn<Cliente, String> columnDireccion;
    @FXML
    private TableView<Cliente> tblCrearCliente;
    @FXML
    void actualizarClienteAction(ActionEvent event) {
        if(clienteSeleccionado !=null){
            main.actualizarClienteAdmin(administradorLoggeado, clienteSeleccionado);
        }else{
            mostrarMensaje("Cliente no seleccionado", "cliente no ha sido seleccionado", "Seleccione el cliente", Alert.AlertType.ERROR);
        }
    }
    @FXML
    void cerrarSesionAction(ActionEvent event) {
            main.mostrarMainWindow();
    }
    @FXML
    void crearClienteAction(ActionEvent event) {
            main.crearcuentaAdmin(administradorLoggeado);
    }
    @FXML
    void eliminarClienteAction(ActionEvent event) throws CuentaException {
        eliminarCliente();
    }
    private void eliminarCliente() throws CuentaException {
        if(clienteSeleccionado == null){
            mostrarMensaje("Notificacion Cliente", "Selecione Al Cliente", "Debe seleccionar el Cliente", Alert.AlertType.ERROR);
        }else{
            boolean eliminar = main.eliminarCliente(clienteSeleccionado.getCedula());
            main.eliminarCuenta(clienteSeleccionado.getCuenta().getNumeroCuenta());
            if(eliminar){
                listaClienteData.remove(clienteSeleccionado);
                clienteSeleccionado=null;
                tblCrearCliente.refresh();
                tblCrearCliente.getSelectionModel().clearSelection();
                mostrarMensaje("Notificacion Cliente", "Cliente eliminado", "el cliente ha sido eliminado", Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje("Notificacion Cliente", "Cliente no eliminado", "el cliente no ha sido eliminado", Alert.AlertType.INFORMATION);
            }
        }
    }
    @FXML
    void initialize(){
        this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.columnCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        this.columnDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        tblCrearCliente.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            clienteSeleccionado = newSelection;
        });
    }
    public void SetMain(Main main, Administrador admin){
        this.main =main;
        this.administradorLoggeado = admin;
        tblCrearCliente.getItems().clear();
        tblCrearCliente.setItems(obtenerListaClientes());
    }
    private ObservableList<Cliente> obtenerListaClientes() {
        listaClienteData.addAll(main.obtenerCliente());
        return listaClienteData;
    }
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    public void ingresarBienvenida(Administrador administrador){
        labelBienvenidaAdmin.setText("Bienvenido "+ administrador.getNombre()+ " " + administrador.getApellido());
        administradorLoggeado = administrador;
    }
}
