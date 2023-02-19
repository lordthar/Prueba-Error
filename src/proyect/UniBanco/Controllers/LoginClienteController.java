package proyect.UniBanco.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import proyect.UniBanco.Aplicacion.Main;
import proyect.UniBanco.Exceptions.ClienteException;
import proyect.UniBanco.Model.Cliente;
import proyect.UniBanco.Model.Cuenta;

import java.io.IOException;


public class LoginClienteController {

        private Main main;

        @FXML
        private Button btnVolverAlMain;
        @FXML
        private Button btnCrearCuenta;

        @FXML
        private Button btnIniciarSesion;

        @FXML
        private TextField txtCedula;

        @FXML
        private TextField txtNumeroCuenta;

        @FXML
        void VolverAlMain(ActionEvent event) {
                main.mostrarMainWindow();
        }

        @FXML
        void IniciarSesion(ActionEvent event) throws IOException, ClienteException {
                iniciarSesionEvent(event);
        }

        private void iniciarSesionEvent(ActionEvent event) throws IOException, ClienteException {
                String cedula ="";
                String numeroCuenta="";
                cedula= txtCedula.getText();
                numeroCuenta= txtNumeroCuenta.getText();
                if(verificarFields(cedula,numeroCuenta)){
                        if(main.verificarCuenta(cedula,numeroCuenta)){
                                Cliente cliente = main.obtenerCliente(cedula);
                                main.mostrarMainMenuCliente(cliente);
                        }else{
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText("Datos incorrectos");
                                alert.setContentText("Por favor verifica tus datos y vuelve a intentar");
                                alert.showAndWait();
                        }
                }else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Atencion");
                        alert.setContentText("Completa todos los campos para poder continuar");
                        alert.showAndWait();
                }
        }
        private boolean verificarFields(String cedula, String numeroCuenta) {
                if(cedula.equals("")||numeroCuenta.equals("")){
                        return false;
                }
                return true;
        }
        public void setMain(Main main) {
                this.main = main;
        }

    }


