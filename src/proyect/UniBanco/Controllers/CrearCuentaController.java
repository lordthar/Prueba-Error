package proyect.UniBanco.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import proyect.UniBanco.Aplicacion.Main;
import proyect.UniBanco.Exceptions.ClienteException;
import proyect.UniBanco.Exceptions.CuentaException;
import proyect.UniBanco.Exceptions.LoginException;
import proyect.UniBanco.Model.Cliente;
import proyect.UniBanco.Model.Cuenta;
import proyect.UniBanco.Model.Tipo_Cuenta;

import java.io.IOException;

public class CrearCuentaController {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnRegresar;

    @FXML
    private TextField txtApellidoCliente;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtDireccionCliente;

    @FXML
    private TextField txtEmailCliente;

    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtNombreCuenta;
    @FXML
    private ChoiceBox<Tipo_Cuenta> tipoCuentaBox;
    @FXML
    private TextField txtSaldo;

    private Main main;

    @FXML
    void Regresar(ActionEvent event) throws IOException {
        main.mostrarLogin();
    }

    @FXML
    void crearCuenta(ActionEvent event) throws LoginException, ClienteException {
        crearCuentaAction();
    }

    @FXML
    void obtenerApellidoCliente(ActionEvent event) {

    }

    @FXML
    void obtenerCedula(ActionEvent event) {

    }

    @FXML
    void obtenerDireccion(ActionEvent event) {

    }

    @FXML
    void obtenerEmail(ActionEvent event) {

    }

    @FXML
    void obtenerNombreCliente(ActionEvent event) {

    }
    @FXML
    void obtenerSaldo(ActionEvent event) {

    }
    @FXML
    void obtenerNombreCuenta(ActionEvent event) {

    }

    public void initialize(){
        tipoCuentaBox.getItems().addAll(Tipo_Cuenta.CUENTA_AHORROS, Tipo_Cuenta.CUENTA_CORRIENTE);
    }

    private void crearCuentaAction() throws ClienteException, LoginException {
        String nombre = txtNombreCliente.getText();
        String apellido = txtApellidoCliente.getText();
        String cedula = txtCedula.getText();
        String direccion = txtDireccionCliente.getText();
        String email = txtEmailCliente.getText();
        String  numeroCuenta =txtNombreCuenta.getText();
        String saldo = txtSaldo.getText();
        Tipo_Cuenta tipoCuenta= tipoCuentaBox.getValue();

        if(verificarCampos(nombre,apellido,direccion, email, saldo, cedula, numeroCuenta, tipoCuenta) == true){
            Cuenta cuenta;
            if(isnumeric(saldo)) {
                double saldo1 = Double.parseDouble(txtSaldo.getText());
                try{
                  main.crearUser(numeroCuenta,saldo1,tipoCuenta);
                  cuenta = main.buscarCuenta(numeroCuenta);
                  main.crearCliente(nombre, apellido, cedula, direccion, email,cuenta);
                    limpiarDatos();
                    tipoCuentaBox.getSelectionModel().clearSelection();
                    mostrarMensaje("Notificacion Cliente", "Cliente registrado", "El cliente se ha registrado con exito", Alert.AlertType.INFORMATION);

                } catch (ClienteException e){
                    mostrarMensaje("Notificacion Cliente", "El cliente no registrado", "El cliente con cedula "+cedula+" ya se encuentra registrado", Alert.AlertType.ERROR);
                } catch (CuentaException e){
                    mostrarMensaje("Notificacion Cliente", "El cliente no registrado", "El cliente con cedula "+cedula+" ya se encuentra registrado", Alert.AlertType.ERROR);
                }
            }else{
                mostrarMensaje("Notificacion Cliente", "No es valido su saldo", "Escriba su saldo con numeros solamente", Alert.AlertType.ERROR);
            }
        }

    }

    private void limpiarDatos() {
        txtNombreCliente.setText("");
        txtApellidoCliente.setText("");
        txtCedula.setText("");
        txtDireccionCliente.setText("");
        txtEmailCliente.setText("");
        txtNombreCuenta.setText("");
        txtSaldo.setText("");
    }

    private boolean verificarCampos(String nombre, String apellidos, String direccion, String email, String saldo, String cedula, String numeroCuenta, Tipo_Cuenta tipoCuenta) {
        if(nombre.equals("")){
            return false;
        }
        if(apellidos.equals("")){
            return false;
        }
        if(direccion.equals("")){
            return false;
        }
        if(numeroCuenta.equals("")){
            return false;
        }
        if(cedula.equals("")){
            return false;
        }
        if(email.equals("")){
            return false;
        }
        if(saldo.equals("")){
            return false;
        }
        return true;
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


    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        DialogPane dialogPane = alert.getDialogPane();
        alert.showAndWait();
    }

    private boolean verificarCampos(String name, String apellidos, String direccion, String cedula, String numeroCuenta) {
        if(name.equals("")){
            return false;
        }
        if(apellidos.equals("")){
            return false;
        }
        if(direccion.equals("")){
            return false;
        }
        if(numeroCuenta.equals("")){
            return false;
        }
        if(cedula.equals("")){
            return false;
        }
        return true;
    }
    public void setMain(Main main) {
        this.main = main;
    }

}

