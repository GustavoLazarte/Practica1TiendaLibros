import java.io.Serializable;

public class Libro implements Serializable {
    private String titulo, descripcion, autor, editorial;
    private Generos genero;
    private boolean disponible;
    private int precio;
    private String cod;
    private int stock;

    public Libro() {
    }

    public Libro(String titulo, String descripcion, String autor, String editorial, Generos genero, String cod, int precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
        this.editorial = editorial;
        this.genero = genero;
        this.cod = cod;
        stock = 1;
        this.precio = precio;
        disponible = true;
    }

    public void aumentarStock(int cantidad){
        if(stock == 0) disponible = true;
        stock += cantidad;
    }


    @Override
    public String toString() {
        String auxiliar = "";
        auxiliar = disponible ?   "disponible" : "no disponible";
        return "\tLibro: "+ titulo +", "+ editorial +", "+ autor +", " +auxiliar+"\n";
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public Generos getGenero() {
        return genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getPrecio() {
        return precio;
    }

    public String getCod() {
        return cod;
    }

    public int getStock() {
        return stock;
    }

    public void reducirStock(){
        stock--;
        if(stock == 0){
            disponible = false;
        }
    }
}
