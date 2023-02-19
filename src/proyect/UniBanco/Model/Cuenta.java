package proyect.UniBanco.Model;

import javafx.scene.control.DatePicker;
import proyect.UniBanco.Exceptions.TransaccionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Cuenta {
    private String numeroCuenta;
    private double saldo;
    private Tipo_Cuenta tipoCuenta;
    private Tipo_Transaccion tipoTransaccion;
    private Estado_Transaccion estado_transaccion;
    private ArrayList<Transaccion> listaTransacciones;
    public Cuenta(String numeroCuenta, double saldo, Tipo_Cuenta tipoCuenta, Tipo_Transaccion tipoTransaccion, Estado_Transaccion estado_transaccion) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.tipoTransaccion = tipoTransaccion;
        this.estado_transaccion = estado_transaccion;
        listaTransacciones = new ArrayList<>();
    }
    public Cuenta() {
        listaTransacciones = new ArrayList<>();
    }
    public Tipo_Cuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(Tipo_Cuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }
    public boolean crearTransaccion(LocalDate fecha, String hora, Double registroValor, Tipo_Transaccion tipoTransaccion, Estado_Transaccion estado_transaccion) throws TransaccionException {
            Transaccion transaccion = new Transaccion();
            transaccion.setRegistroValor(registroValor);
            transaccion.setHora(hora);
            transaccion.setFecha(fecha);
            transaccion.setTipoTransaccion(tipoTransaccion);
            transaccion.setEstado_transaccion(estado_transaccion);
            if(existeTransaccion(fecha,hora)){
                throw new TransaccionException("Transaccion Creada");
            }
                getListaTransacciones().add(transaccion);
            return true;
            }

    private boolean existeTransaccion(LocalDate fecha, String hora) {
        for(Transaccion transaccion: getListaTransacciones()){
            if(transaccion.getFecha().equals(fecha) && transaccion.getHora().equals(hora)){
                return true;
            }
        }
        return false;
    }

    public boolean actualizarTransaccion(Double registroValor, LocalDate fecha, String hora, Tipo_Transaccion tipoTransaccion, Estado_Transaccion estado_transaccion) {
        for(Transaccion transaccion : getListaTransacciones()){
            if(transaccion.getFecha().equals(fecha) && transaccion.getHora().equals(hora) ){
                transaccion.setFecha(fecha);
                transaccion.setHora(hora);
                transaccion.setRegistroValor(registroValor);
                transaccion.setTipoTransaccion(tipoTransaccion);
                transaccion.setEstado_transaccion(estado_transaccion);
                return true;
            }
        }
        return false;
    }
    public boolean eliminarTransaccion(DatePicker fecha, String hora) throws TransaccionException {
        if(existeTransaccion(fecha.getValue(), hora)){
            for (Transaccion transaccion: getListaTransacciones()) {
                if(transaccion.getFecha().equals(fecha) && transaccion.getHora().equals(hora)){
                    getListaTransacciones().remove(transaccion);
                    return true;
                }
            }
        }else{
            throw new TransaccionException("Transaccion no eliminada");
        }
        return false;
    }
    public Transaccion buscarTransaccion(DatePicker fecha, String hora) throws TransaccionException{
        Transaccion transaccionEncontrada = null;
        if(existeTransaccion(fecha.getValue(), hora)){
           for(Transaccion transaccion: getListaTransacciones()){
               if(transaccion.getFecha().equals(fecha) && transaccion.getFecha().equals(hora)){
                   transaccionEncontrada = transaccion;
                   return transaccionEncontrada;
               }
            }
        }
        if(transaccionEncontrada == null){
            throw new TransaccionException("Transaccion no encontrada");
        }
        return transaccionEncontrada;
    }

}
