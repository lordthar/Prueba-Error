package proyect.UniBanco.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import proyect.UniBanco.Aplicacion.Main;
import proyect.UniBanco.Exceptions.CuentaException;
import proyect.UniBanco.Model.Administrador;
import proyect.UniBanco.Model.Cliente;
import proyect.UniBanco.Model.Cuenta;
import proyect.UniBanco.Model.Tipo_Cuenta;

public class UpdateAdminController {

    Administrador administradorLoggeado = null;
    Cliente clienteSeleccionado = null;
    private Main main;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtApellidosCliente;
    @FXML
    private TextField txtCedulaCliente;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNumeroCuenta;
    @FXML
    private TextField txtSaldo;
    @FXML
    private ChoiceBox<Tipo_Cuenta> tipoCuentaBox;
    @FXML
    void ActualizarAction(ActionEvent event) throws CuentaException {
        actualizarCliente();
    }
    public void initialize() {
        tipoCuentaBox.getItems().addAll(Tipo_Cuenta.CuentaAhorro, Tipo_Cuenta.CuentaCorriente);
    }
    @FXML
    void RegresarAction(ActionEvent event) {
        main.mostrarMainAdmin(administradorLoggeado);
    }
    public void actualizarCliente() throws CuentaException {
        String nombre = txtNombreCliente.getText();
        String apellido = txtApellidosCliente.getText();
        String direccion = txtDireccion.getText();
        String cedula = clienteSeleccionado.getCedula();
        String email = txtEmail.getText();
        String numeroCuenta = clienteSeleccionado.getCuenta().getNumeroCuenta();
        String saldo = txtSaldo.getText();
        Tipo_Cuenta tipoCuenta = tipoCuentaBox.getValue();
        if (verificarCampos(nombre, apellido, cedula, saldo, direccion,email, numeroCuenta, tipoCuenta) == true) {
            if (isnumeric(saldo)) {
                Double saldo2 = Double.parseDouble(saldo);
                try {
                    main.actualizarCuenta(saldo2, numeroCuenta, tipoCuenta);
                    Cuenta cuenta = main.buscarCuenta(numeroCuenta);
                    main.actualizarCliente(nombre, apellido, cedula, direccion, email, cuenta);
                    mostrarMensaje("Notificacion Cliente", "Cliente Actualizado", "El cliente a sido actualizado", Alert.AlertType.INFORMATION);
                } catch (CuentaException e) {
                    mostrarMensaje("Notificacion Cliente", "Cliente no existe", "Registrar el cliente", Alert.AlertType.ERROR);
                }
            }else{
                mostrarMensaje("Notificacion Cliente", "Saldo incorrecto", "El saldo no es un numero", Alert.AlertType.ERROR);
            }

        }else{
            System.out.println("eso tilin");
        }
    }
    private boolean verificarCampos(String nombre, String apellido,String cedula,String saldo, String direccion,String email, String numeroCuenta, Tipo_Cuenta tipoCuenta) {
        if(nombre.equals("")){
            return false;
        }
        if(apellido.equals("")){
            return false;
        }
        if(cedula.equals("")){
            return false;
        }
        if(saldo.equals("")){
            return false;
        }
        if(direccion.equals("")){
            return false;
        }
        if(email.equals("")){
            return false;
        }
        if(numeroCuenta.equals("")){
            return false;
        }
        return true;
    }
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    private boolean isnumeric(String saldo) {
        boolean resultado;
        try {
            Double.parseDouble(saldo);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }
    public void initializeTxt(){
        txtNombreCliente.setText(clienteSeleccionado.getNombre());
        txtApellidosCliente.setText(clienteSeleccionado.getApellido());
        txtCedulaCliente.setText(clienteSeleccionado.getCedula());
        txtDireccion.setText(clienteSeleccionado.getDireccion());
        txtEmail.setText(clienteSeleccionado.getEmail());
        txtSaldo.setText(clienteSeleccionado.getCuenta().getSaldo() + "");
        txtNumeroCuenta.setText(clienteSeleccionado.getCuenta().getNumeroCuenta());
    }
    public void setMain(Main main, Cliente cliente, Administrador administrador) {
        this.main = main;
        this.clienteSeleccionado = cliente;
        this.administradorLoggeado = administrador;
        initializeTxt();
    }


}


