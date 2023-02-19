package proyect.UniBanco.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import proyect.UniBanco.Aplicacion.Main;
import proyect.UniBanco.Model.*;

import java.time.LocalDate;
import java.util.Date;

public class MainMenuClienteController {

    Cliente clienteLoggeado = null;
    Transaccion transaccionesCliente = null;
    ObservableList<Transaccion> listaTransaccionesData = FXCollections.observableArrayList();
    private Main main;
    @FXML
    private TableColumn<Transaccion, Estado_Transaccion> columnEstadoTransaccion;
    @FXML
    private TableColumn<Transaccion, LocalDate> columnFecha;
    @FXML
    private TableColumn<Transaccion, String> columnHora;
    @FXML
    private TableColumn<Transaccion, Tipo_Transaccion> columnTipoTransaccion;
    @FXML
    private TableColumn<Transaccion, Double> columnValorTransaccion;
    @FXML
    private Label labelSaldo;

    @FXML
    private Label labelTipoCuenta;

    @FXML
    private TableView<Transaccion> tblTransacciones;
    @FXML
    private Label LabelBienvenidaCliente;
    @FXML
    void miCuentaAction(ActionEvent event) {
    }
    @FXML
    void transaccionesAction(ActionEvent event) {
        main.TransaccionesCliente(clienteLoggeado);
    }
    @FXML
    void cerrarSesionAction(ActionEvent event) {
        main.mostrarMainWindow();
    }

    public void mostrarBienvenidaCliente(Cliente cliente){
        Double saldo = cliente.getCuenta().getSaldo();
        Tipo_Cuenta tipo_cuenta = cliente.getCuenta().getTipoCuenta();
        LabelBienvenidaCliente.setText("Bienvenid@ " + cliente.getNombre() + " " + cliente.getApellido());
        clienteLoggeado = cliente;
        labelTipoCuenta.setText(String.valueOf(tipo_cuenta));
        labelSaldo.setText("Este es tu saldo:" + "  " + saldo + "$" );

    }
    public ObservableList<Transaccion> obtenerTransaccion(Cliente clienteLoggeado){
        tblTransacciones.getItems().clear();
        listaTransaccionesData.addAll(main.obtenerListaTransaccion(clienteLoggeado));
        return listaTransaccionesData;
    }
    @FXML
    void initialize(){
        this.columnValorTransaccion.setCellValueFactory(new PropertyValueFactory<>("registroValor"));
        this.columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.columnHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        this.columnTipoTransaccion.setCellValueFactory(new PropertyValueFactory<>("tipoTransaccion"));
        this.columnEstadoTransaccion.setCellValueFactory(new PropertyValueFactory<>("estado_transaccion"));
        tblTransacciones.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
           transaccionesCliente = newSelection;
        });
    }
    public void setMain(Main main, Cliente cliente) {
        this.main = main;
        this.clienteLoggeado= cliente;
        tblTransacciones.setItems(obtenerTransaccion(clienteLoggeado));
    }
}
