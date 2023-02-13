package proyect.UniBanco.Model;

import java.util.Date;

public class Transaccion {
    private int registroValor;
    private Date fecha;
    private String hora;

    public Transaccion(int registroValor, Date fecha, String hora) {
        this.registroValor = registroValor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Transaccion() {
    }

    public int getRegistroValor() {
        return registroValor;
    }

    public void setRegistroValor(int registroValor) {
        this.registroValor = registroValor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}