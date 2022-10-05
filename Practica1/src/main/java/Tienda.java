import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tienda implements Serializable {
    private String nombre;
    private String direccion;
    private List<Personal> empleados;
    private List<Libro> libros;
    private List<Cliente> clientes;

    private List<Venta> ventas;

    public Tienda(String nombre, String direccion, List<Personal> empleados , List<Cliente> clientes, List<Libro> libros){
        this.nombre = nombre;
        this.direccion = direccion;
        this.empleados =  empleados;
        this.clientes = clientes;
        this.libros = libros;
        ventas = new ArrayList<>();
    }

    public String listarEmpleados(){
        return "Lista de Empleados\n"+ empleados.toString()+"\n------------------------------------------------------------------------";
    }

    public String listarClientesAntiguos(){
        String res = "Lista de Clientes Antiguos:\n";
        for (Cliente cliente: clientes) {
            if(cliente instanceof  ClienteAntiguo){
                ClienteAntiguo clienteAntiguo = (ClienteAntiguo) cliente;
                String aux = clienteAntiguo.toString();
                res = res.concat(aux);
            }
        }
        return res+"\n------------------------------------------------------";
    }

    public String listarClientesNuevoConCorreo(){
        String res = "Lista de Clientes Nuevos con Correo\n";
        for (Cliente cliente: clientes) {
            if(cliente instanceof  ClienteNuevo){
                ClienteNuevo clienteNuevo = (ClienteNuevo) cliente;
                String aux = clienteNuevo.toString();
                res = res.concat(aux);
            }
        }
        return res;
    }

    public String mostrarLibros(){
        return "Lista de Libros de la tienda\n"+libros.toString()+"\n------------------------------------------------------";
    }

    public boolean realizarVenta(Cliente comprador, PersonalCajero cajero, PersonalPromotor promotor, Libro libro) throws IOException {
        Venta nueva = null;
        if(libros.contains(libro) && libro.isDisponible()){
            System.out.println("Libro Disponible");
            comprador = controlCliente(comprador);
            nueva= Venta.procesarVenta(comprador, cajero, promotor, libro);
            ventas.add(nueva);
            guardarDatosTienda();
        }else{
            System.out.println("El Libro no esta Disponible");
        }

        return nueva != null;
    }

    private Cliente controlCliente(Cliente comprador) {
        Cliente modificado = comprador;
        if(comprador instanceof  ClienteNuevo){
            modificado = new ClienteAntiguo(comprador);
            clientes.set(clientes.indexOf(comprador), new ClienteAntiguo(comprador));
        }else if(comprador instanceof Cliente && ! (comprador instanceof ClienteAntiguo)){
            modificado = new ClienteNuevo(comprador);
            clientes.set(clientes.indexOf(comprador), new ClienteNuevo(comprador));
        }

        return  modificado;
    }

    public String listarPromotores(){
        String res = "Lista de Promotores:\n";
        List<Personal> personalPromotores = new ArrayList<>();
        for (Personal personal:
                empleados) {
            if(personal instanceof  PersonalPromotor){
                res += ((PersonalPromotor) personal).reportarse()+"\n";
            }
        }
        return  res+"------------------------------------------------------";
    }

    public String listarLibrosPorGenero(String genero){
        List<Libro> resultado = new ArrayList<>();
        for (Libro libro:
             libros) {
            if(Enum.valueOf(Generos.class, genero ).equals(libro.getGenero())){
                resultado.add(libro);
            }
        }
        return  resultado .toString();
    }

    public String listarVentas(){
        return "Lista de Ventas de la Tienda\n"+ventas.toString();
    }

    public int cantidadVentasRegistradasPorCajero(PersonalCajero cajero, LocalDate fecha){
        int conteo = 0;
        if(cajero != null && empleados.contains(cajero)){
            for (Venta venta:
                    ventas) {
                if(venta.getFactura().getFecha().isEqual(fecha) && venta.getCajero().equals(cajero)){
                        conteo++;
                }
            }
        }

        return conteo;
    }

    public String mostrarLibroDelAutor(String autor){
        List<Libro> librosDelAutor = new ArrayList<>();
        for (Libro libro:
             libros) {
            if(libro.getAutor().equalsIgnoreCase(autor)){
                librosDelAutor.add(libro);
            }
        }
        return "Libros del Autor:" + autor+"\n"+librosDelAutor.toString()+"\n------------------------------------------------------";
    }

    public String librosNoDisponibles(){
        List<Libro> librosNoDisponibles = new ArrayList<>();
        for (Libro libro:
                libros) {
            if(!libro.isDisponible()){
                librosNoDisponibles.add(libro);
            }
        }
        return "Libros no disponibles:"+librosNoDisponibles.toString()+"\n------------------------------------------------------";
    }

    public void esDisponibleADescuento(long ci){
        Cliente cliente = buscarCliente(ci);
        if(cliente != null){
            if(cliente.getLibrosComprados().size() >= 3 || cliente.totalGastado() > 300){
                System.out.println("Es disponible a descuentos");
            }
        }
    }

    private Cliente buscarCliente(long ci) {
        Cliente buscado = null;
        for (Cliente cliente:
             clientes) {
            if(cliente.getCarnet().getNro() == ci){
                buscado = cliente;
            }
        }
        return buscado;
    }

    public void guardarDatosTienda() throws IOException {
        ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream("registrosApp.obj"));
        salida.writeObject(this);
        salida.close();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Personal> getEmpleados() {
        return empleados;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void agregar(ArrayList<Personal> empleados , ArrayList<Cliente> clientes, ArrayList<Libro> libros){
        this.empleados.addAll(empleados);
        this.clientes.addAll(clientes);
        this.libros.addAll(libros);
    }
}
