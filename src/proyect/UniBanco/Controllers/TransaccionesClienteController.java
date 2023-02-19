package proyect.UniBanco.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import proyect.UniBanco.Aplicacion.Main;
import proyect.UniBanco.Exceptions.CuentaException;
import proyect.UniBanco.Exceptions.TransaccionException;
import proyect.UniBanco.Model.Cliente;
import proyect.UniBanco.Model.Cuenta;
import proyect.UniBanco.Model.Estado_Transaccion;
import proyect.UniBanco.Model.Tipo_Transaccion;

import java.time.LocalDate;

public class TransaccionesClienteController {

    Cliente clienteLoggeado = null;
    private Main main;
    @FXML
    private TextField txtHora;

    @FXML
    private TextField txtMinutos;

    @FXML
    private TextField txtRegistroValor;
    @FXML
    private DatePicker datePicker;
    @FXML
    void depositarAction(ActionEvent event) throws CuentaException, TransaccionException{
        String registroValor = txtRegistroValor.getText();
        String hora = txtHora.getText();
        String minuto= txtMinutos.getText();
        LocalDate fecha = datePicker.getValue();
        Tipo_Transaccion tipoTransaccion = Tipo_Transaccion.DEPOSITAR_DINERO;
        Estado_Transaccion estado_transaccion = Estado_Transaccion.EXITOSA;
        String horaTotal = hora + ":" + minuto;
        double saldo = clienteLoggeado.getCuenta().getSaldo();
        try{
            if(vefiricarCampos(registroValor,hora,minuto,fecha)){
                if(isnumeric(registroValor)){
                    double registroValorNum = Double.parseDouble(registroValor);
                    double nuevoSaldo= saldo + registroValorNum;
                    Cuenta cuenta = main.buscarCuenta(clienteLoggeado.getCuenta().getNumeroCuenta());
                    cuenta.setSaldo(nuevoSaldo);
                    main.crearTransaccion(fecha, horaTotal, registroValorNum, Tipo_Transaccion.DEPOSITAR_DINERO, Estado_Transaccion.EXITOSA, clienteLoggeado.getCedula());
                    mostrarMensaje("Notificacion Cliente","Transaccion Completa","La transaccion se completo", Alert.AlertType.INFORMATION);
                }else{
                    main.crearTransaccion(fecha, horaTotal, 0.0, Tipo_Transaccion.DEPOSITAR_DINERO, Estado_Transaccion.RECHAZADA, clienteLoggeado.getCedula());
                    mostrarMensaje("Notificacion Cliente","Transaccion No Completada","La transaccion no se completo", Alert.AlertType.ERROR);
                }
            }else{
                mostrarMensaje("Notificacion Cliente","Campos no llenados","La transaccion no se completo", Alert.AlertType.ERROR);
            }
        } catch (CuentaException | TransaccionException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void regresarAction(ActionEvent event) {
        main.mostrarMainMenuCliente(clienteLoggeado);
    }

    @FXML
    void retirarAction(ActionEvent event) {
        String registroValor = txtRegistroValor.getText();
        String hora = txtHora.getText();
        String minuto= txtMinutos.getText();
        LocalDate fecha = datePicker.getValue();
        Tipo_Transaccion tipoTransaccion = Tipo_Transaccion.DEPOSITAR_DINERO;
        Estado_Transaccion estado_transaccion = Estado_Transaccion.EXITOSA;
        String horaTotal = hora + ":" + minuto;
        double saldo = clienteLoggeado.getCuenta().getSaldo();
        try{
            if(vefiricarCampos(registroValor,hora,minuto,fecha)) {
                if (isnumeric(registroValor)) {
                    double registroValorNum = Double.parseDouble(registroValor);
                    if (saldo >= registroValorNum) {
                        double nuevoSaldo = saldo - registroValorNum;
                        Cuenta cuenta = main.buscarCuenta(clienteLoggeado.getCuenta().getNumeroCuenta());
                        cuenta.setSaldo(nuevoSaldo);
                        main.crearTransaccion(fecha, horaTotal, registroValorNum, Tipo_Transaccion.RETIRAR_DINERO, Estado_Transaccion.EXITOSA, clienteLoggeado.getCedula());
                        mostrarMensaje("Notificacion Cliente", "Transaccion Completa", "La transaccion fue completada", Alert.AlertType.INFORMATION);
                    } else {
                        main.crearTransaccion(fecha, horaTotal, 0.0, Tipo_Transaccion.RETIRAR_DINERO, Estado_Transaccion.SIN_FONDOS, clienteLoggeado.getCedula());
                        mostrarMensaje("Notificacion Cliente", "Fondos Insuficientes", "La transaccion no fue completada", Alert.AlertType.INFORMATION);
                    }
                } else {
                    main.crearTransaccion(fecha, horaTotal, 0.0, Tipo_Transaccion.RETIRAR_DINERO, Estado_Transaccion.RECHAZADA, clienteLoggeado.getCedula());
                    mostrarMensaje("Notificacion Cliente", "No Se Introdujo Un Valor", "La transaccion no fue completada", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificacion Cliente", "Los campos no se llenaron", "se rechazo por falta de informacion", Alert.AlertType.ERROR);
            }
        } catch (CuentaException | TransaccionException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void solicitarAction(ActionEvent event) {
        double saldo = clienteLoggeado.getCuenta().getSaldo();
            mensajeSolicitar("El saldo de la cuenta es:" + " " + saldo + " " + "saldo actual");
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
    public boolean vefiricarCampos(String registroValor, String hora, String minuto, LocalDate fecha){
        if(registroValor.equals("")){
            return false;
        }
        if (hora.equals("")){
            return false;
        }
        if (minuto.equals("")){
            return false;
        }
        if(fecha == null){
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

    public void mensajeSolicitar(String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
    public void setMain(Main main, Cliente cliente){
        this.main=main;
        this.clienteLoggeado = cliente;
    }

}

