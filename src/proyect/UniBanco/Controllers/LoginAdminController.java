package proyect.UniBanco.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import proyect.UniBanco.Aplicacion.Main;
import proyect.UniBanco.Model.Administrador;


public class LoginAdminController {

        private Main main;
        @FXML
        private Button btnLogIn;

        @FXML
        private Button btnRegresar;

        @FXML
        private PasswordField txtPassWord;

        @FXML
        private TextField txtUserName;

        @FXML
        void obtenerLogIn(ActionEvent event) {
                IniciarSesionAdmin(event);
        }
        @FXML
        void RegresarAction(ActionEvent event) {
                main.mostrarMainWindow();
        }

        private void IniciarSesionAdmin(ActionEvent event){
                String user= "";
                String passWord= "";
                user = txtUserName.getText();
                passWord= txtPassWord.getText();
                if(verificarCampos(user,passWord)){
                        if(main.verificarCuentaAdmin(user,passWord)){
                                Administrador admin = main.obtenerAdmin(user,passWord);
                                main.crearcuentaAdmin(admin);
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

        private boolean verificarCampos(String user, String passWord) {
                if(user.equals("")||passWord.equals("")){
                        return false;
                }
                return true;

        }

        public void setMain (Main main){
            this.main = main;
        }

    }



