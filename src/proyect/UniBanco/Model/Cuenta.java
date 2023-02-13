package proyect.UniBanco.Model;

import proyect.UniBanco.Exceptions.TransaccionException;

import java.util.ArrayList;
import java.util.Date;

public class Cuenta {
    private String numeroCuenta;
    private double saldo;

    private Tipo_Cuenta tipoCuenta;
    private ArrayList<Transaccion> listaTransacciones;

    public Cuenta(String numeroCuenta, double saldo, Tipo_Cuenta tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        listaTransacciones = new ArrayList<>();
    }

    public Cuenta() {
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

    public boolean crearTransaccion(int registroValor, Date fecha, String hora) throws TransaccionException {
            Transaccion transaccion = new Transaccion();
            transaccion.setRegistroValor(registroValor);
            transaccion.setHora(hora);
            transaccion.setFecha(fecha);
            if(existeTransaccion(registroValor)){
                throw new TransaccionException("Transaccion Creada");
            }
                getListaTransacciones().add(transaccion);
            return true;
            }

    private boolean existeTransaccion(int registroValor) {
        for(Transaccion transaccion: listaTransacciones){
            if(transaccion.getRegistroValor()==registroValor){
                return true;
            }
        }
        return false;
    }

    public boolean actualizarTransaccion(int registroValor, Date fecha, String hora) {
        for(Transaccion transaccion : listaTransacciones){
            if(transaccion.getRegistroValor() == registroValor){
                transaccion.setFecha(fecha);
                transaccion.setHora(hora);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarTransaccion(int registroValor){
        if(existeTransaccion(registroValor)){
            for (Transaccion transaccion: listaTransacciones) {
                if(transaccion.getRegistroValor()== registroValor){
                    getListaTransacciones().remove(transaccion);
                    return true;
                }
            }
        }
        return false;
    }

    public Transaccion buscarTransaccion(int registroValor) throws TransaccionException{
        Transaccion transaccion = null;
        if(existeTransaccion(registroValor)){
            if(transaccion.getRegistroValor()==registroValor){
                return transaccion;
            }
        }
        if(transaccion == null){
            throw new TransaccionException("Transaccion no encontrada");
        }
        return transaccion;
    }

}
