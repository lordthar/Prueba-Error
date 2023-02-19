package proyect.UniBanco.Model;

import proyect.UniBanco.Exceptions.ClienteException;

public class Cliente {

        private String nombre;
        private String apellido;
        private String cedula;
        private String direccion;
        private String email;
        Cuenta cuenta;
        public Cliente(String nombre,String apellido, String cedula, String direccion, String email, Cuenta cuenta) {
            this.nombre = nombre;
            this.apellido= apellido;
            this.cedula= cedula;
            this.direccion= direccion;
            this.email= email;
            this.cuenta=cuenta;

        }
        public Cliente() {
        }
        public Cuenta getCuenta() {
            return cuenta;
        }

        public void setCuenta(Cuenta cuenta) {
            this.cuenta = cuenta;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getCedula() {
            return cedula;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public  boolean verificarCuenta(String numeroCuenta) {
            if(cuenta.getNumeroCuenta().equals(numeroCuenta)){
                return true;
            }

            return false;
        }
    }


