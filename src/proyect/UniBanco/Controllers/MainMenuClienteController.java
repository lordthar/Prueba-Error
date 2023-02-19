package proyect.UniBanco.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import proyect.UniBanco.Aplicacion.Main;
import proyect.UniBanco.Model.Cliente;
import proyect.UniBanco.Model.Cuenta;
import proyect.UniBanco.Model.Tipo_Cuenta;

public class MainMenuClienteController {

    Cliente clienteSeleccionado = null;

    private Main main;
    @FXML
    private Label LabelBienvenidaCliente;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Label labelSaldo;
    @FXML
    private Label labelTipoCuenta;
    @FXML
    void miCuentaAction(ActionEvent event) {
    }
    @FXML
    void transaccionesAction(ActionEvent event) {

    }
    @FXML
    void cerrarSesionAction(ActionEvent event) {
        main.mostrarMainWindow();
    }

    public void mostrarBienvenidaCliente(Cliente cliente){
        Double saldo = cliente.getCuenta().getSaldo();
        Tipo_Cuenta tipo_cuenta = cliente.getCuenta().getTipoCuenta();
        LabelBienvenidaCliente.setText("Bienvenid@ " + cliente.getNombre() + " " + cliente.getApellido());
        clienteSeleccionado = cliente;
        labelTipoCuenta.setText(String.valueOf(tipo_cuenta));
        labelSaldo.setText("Este es tu saldo:" + "  " + saldo + "$" );

    }

    public void setMain(Main main, Cliente clienteSeleccionado) {
        this.main = main;
        this.clienteSeleccionado= clienteSeleccionado;
    }
}
