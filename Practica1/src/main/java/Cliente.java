import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
    protected String nombre, apellido;
    protected Carnet carnet;
    protected String correoElectronico;
    protected List<Libro> librosComprados;

    public Cliente(String nombre, String apellido, Carnet carnet, String correoElectronico){
        this.nombre = nombre;
        this.apellido = apellido;
        this.carnet = carnet;
        this.correoElectronico = correoElectronico;
        librosComprados = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido, Carnet carnet) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.carnet = carnet;
        librosComprados = new ArrayList<>();
    }

    public Cliente(Cliente cliente) {
        this.nombre = cliente.nombre;
        this.apellido = cliente.apellido;
        this.carnet = cliente.carnet;
        this.correoElectronico = cliente.correoElectronico;
        this.librosComprados = cliente.librosComprados;
    }

    @Override
    public String toString() {
        return nombre +" "+ apellido +" "+ correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public List<Libro> getLibrosComprados() {
        return librosComprados;
    }

    public void agregarLibro(Libro libro) {
        librosComprados.add(libro);
    }

    public int totalGastado(){
        int res = 0;
        for (Libro libro:
             librosComprados) {
            res += libro.getPrecio();
        }

        return res;
    }
}
