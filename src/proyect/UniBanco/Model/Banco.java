package proyect.UniBanco.Model;

import java.sql.Array;
import java.util.ArrayList;

import proyect.UniBanco.Exceptions.AdminException;
import proyect.UniBanco.Exceptions.ClienteException;
import proyect.UniBanco.Exceptions.CuentaException;

public class Banco {
    private String nombre;
    private String nit;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Cuenta> listaCuentas;

    private ArrayList<Administrador> listaAdmins;

    private void inicializarAdmin(){
        CuentaAcceso cuentaAcceso = new CuentaAcceso();
        cuentaAcceso.setUser("2");
        cuentaAcceso.setPassWord("2");

        Administrador admin = new Administrador();
        admin.setCedula("213443");
        admin.setApellido("etesech");
        admin.setNombre("no llego tilin");
        admin.setCuentaAcceso(cuentaAcceso);
        listaAdmins.add(admin);

    }

    private void inicializar(){
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("2");
        cuenta.setTipoCuenta(Tipo_Cuenta.CUENTA_AHORROS);
        cuenta.setSaldo(23);

        Cliente cliente = new Cliente();
        cliente.setApellido("wasa");
        cliente.setCedula("123");
        cliente.setCuenta(cuenta);
        cliente.setNombre("tilin");
        cliente.setDireccion("fada");
        cliente.setEmail("sdg");
        listaCuentas.add(cuenta);
        listaClientes.add(cliente);
    }

    public Banco(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
        this.listaClientes = new ArrayList<>();
        this.listaCuentas = new ArrayList<>();
        this.listaAdmins = new ArrayList<>();
        inicializarAdmin();
    }

    public Banco() {
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public ArrayList<Administrador> getListaAdmins() {
        return listaAdmins;
    }

    public void setListaAdmins(ArrayList<Administrador> listaAdmins) {
        this.listaAdmins = listaAdmins;
    }

    public ArrayList<Cliente> getlistaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    //Crud a Cliente

    public boolean crearCliente(String nombre, String apellido, String cedula, String direccion, String email, Cuenta cuenta) throws ClienteException {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setCedula(cedula);
        cliente.setDireccion(direccion);
        cliente.setEmail(email);
        cliente.setCuenta(cuenta);
        if (existeCliente(cedula)) {
            throw new ClienteException("Cliente creado");
        }
        getlistaClientes().add(cliente);
        return true;
    }


    private boolean existeCliente(String cedula) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    public boolean actualizarCliente(String nombre, String apellido, String cedula, String direccion, String email) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCedula().equals(cedula)) {
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setDireccion(direccion);
                cliente.setEmail(email);
                return true;
            }
        }
        return false;
    }

    /**
     * metodo para eliminar un producto
     *
     * @param cedula
     * @return
     */
    public boolean eliminarCliente(String cedula) {

        if (existeCliente(cedula)) {
            for (Cliente cliente : listaClientes) {
                if (cliente.getCedula().equals(cedula)) {
                    getlistaClientes().remove(cliente);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * metodo para buscar un producto
     *
     * @param cedula
     * @return
     * @throws ClienteException
     */
    public Cliente buscarCliente(String cedula) throws ClienteException {
        Cliente clienteEncontrado = null;
        if (existeCliente(cedula)) {
            for (Cliente cliente : getlistaClientes()) {
                if (cliente.getCedula().equals(cedula)) {
                    clienteEncontrado = cliente;
                    return clienteEncontrado;
                }
            }
        }
        if (clienteEncontrado == null) {
            throw new ClienteException("Cliente no encontrado");
        }
        return clienteEncontrado;
    }

    //CRUD de cuenta

    public boolean crearCuenta(String numeroCuenta, Double saldo, Tipo_Cuenta tipo_cuenta) throws CuentaException {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(numeroCuenta);
        cuenta.setSaldo(saldo);
        cuenta.setTipoCuenta(tipo_cuenta);
        if (existeCuenta(numeroCuenta)) {
            throw new CuentaException("Cuenta creada");
        }
        getListaCuentas().add(cuenta);
        return true;
    }

    private boolean existeCuenta(String numeroCuenta) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return true;
            }
        }
        return false;
    }

    public boolean actualizarCuenta(String numeroCuenta, Double saldo) throws CuentaException {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                cuenta.setSaldo(saldo);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCuenta(String numeroCuenta) throws CuentaException {
        if (existeCuenta(numeroCuenta)) {
            for (Cuenta cuenta : listaCuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    getListaCuentas().remove(cuenta);
                    return true;
                }
            }
        }
        return false;
    }

    public Cuenta buscarCuenta(String numeroCuenta) throws CuentaException {
        Cuenta cuentaEncontrada = null;
        if (existeCuenta(numeroCuenta)) {
            for (Cuenta cuenta : getListaCuentas()) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    cuentaEncontrada = cuenta;
                    return cuentaEncontrada;
                }
            }
        }
        if(cuentaEncontrada == null){
            throw new CuentaException("Cliente no encontrado");
        }
        return cuentaEncontrada;
    }

    public Boolean verificarLogin( String cedula, String numeroCuenta) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCedula().equals(cedula)) {
                return cliente.verificarCuenta(numeroCuenta);
            }
        }
        return false;
    }

    //CRUD Administrador
    public boolean crearAdmin(String nombre, String apellido , String cedula) throws AdminException {
        Administrador admin = new Administrador();
        admin.setNombre(nombre);
        admin.setApellido(apellido);
        admin.setCedula(cedula);
        if(existeAdmin(cedula)){
            throw new AdminException("Admin creado");
        }
        getListaAdmins().add(admin);
        return false;
    }

    private boolean existeAdmin(String cedula) {
        for (Administrador admin : listaAdmins) {
            if(admin.getCedula().equals(cedula))
                return true;
        }
        return false;
    }
    public Administrador obtenerAdministrador(String user, String password) {
        Administrador administradorEncontrado = null;
        for (Administrador administrador:listaAdmins) {
            if (administrador.verificarCuentaAdmin(user, password)){
                administradorEncontrado = administrador;
                break;
            }
        }
        return administradorEncontrado;
    }

    public boolean crearCuentaAdmin(String user, String passWord){
        CuentaAcceso cuentaAcceso = new CuentaAcceso();
        cuentaAcceso.setUser(user);
        cuentaAcceso.setPassWord(passWord);
        return true;
    }

    public Boolean verificarLoginAdmin( String user, String passWord) {
        for (Administrador admin : listaAdmins) {
            if (admin.verificarCuentaAdmin(user,passWord)){
                return true;
            }
        }
        return false;
    }




}





