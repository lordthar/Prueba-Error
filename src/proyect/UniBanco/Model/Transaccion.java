package proyect.UniBanco.Model;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class Transaccion {
    private Double registroValor;
    private LocalDate fecha;
    private String hora;
    private Tipo_Transaccion tipoTransaccion;
    private Estado_Transaccion estado_transaccion;

    public Transaccion(Double registroValor, DatePicker fecha, String hora, Tipo_Transaccion tipoTransaccion, Estado_Transaccion estado_transaccion) {
        this.registroValor = registroValor;
        this.fecha = fecha.getValue();
        this.hora = hora;
        this.estado_transaccion=estado_transaccion;
        this.tipoTransaccion = tipoTransaccion;
    }
    public Transaccion() {
    }
    public Tipo_Transaccion getTipoTransaccion() {
        return tipoTransaccion;
    }
    public void setTipoTransaccion(Tipo_Transaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
    public Estado_Transaccion getEstado_transaccion() {
        return estado_transaccion;
    }
    public void setEstado_transaccion(Estado_Transaccion estado_transaccion) {
        this.estado_transaccion = estado_transaccion;
    }
    public Double getRegistroValor() {
        return registroValor;
    }
    public void setRegistroValor(Double registroValor) {
        this.registroValor = registroValor;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }

}