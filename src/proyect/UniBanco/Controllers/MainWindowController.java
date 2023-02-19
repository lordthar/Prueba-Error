package proyect.UniBanco.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import proyect.UniBanco.Aplicacion.Main;
import proyect.UniBanco.Model.Administrador;

public class MainWindowController {
        private Main main;
        @FXML
        private Button btnLoginAdmin;
        @FXML
        private Button txtLoginCliente;
        @FXML
        void obtenerLoginAdmin(ActionEvent event) {
                main.mostrarLoginAdmin();
        }
        @FXML
        void obtenerLoginCliente(ActionEvent event) {
                main.mostrarLoginCliente();
        }
        public void setMain (Main main){
        this.main=main;
    }

}





