import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Venta implements Serializable {
    private Cliente comprador;
    private PersonalPromotor promotor;
    private PersonalCajero cajero;
    private Factura factura;
    private String cod;

    private Libro libro;

    public Venta(Cliente comprador, PersonalPromotor promotor, PersonalCajero cajero, Factura factura, String cod, Libro libro) {
        this.comprador = comprador;
        this.promotor = promotor;
        this.cajero = cajero;
        this.factura = factura;
        this.cod = cod;
        this.libro = libro;
    }

    public Cliente getComprador() {
        return comprador;
    }

    public PersonalPromotor getPromotor() {
        return promotor;
    }

    public PersonalCajero getCajero() {
        return cajero;
    }

    public Factura getFactura() {
        return factura;
    }

    public String getCod() {
        return cod;
    }

    public Libro getLibro() {
        return libro;
    }

    public static Venta procesarVenta(Cliente comprador, PersonalCajero cajero, PersonalPromotor promotor, Libro libro) {
        Venta venta;
        System.out.println(comprador.getCarnet().getFechaNacimiento().getMonthValue()+","+ LocalDate.now().getMonthValue());
        if((comprador.getCarnet().getFechaNacimiento().getMonthValue() == LocalDate.now().getMonthValue() &&
                comprador.getCarnet().getFechaNacimiento().getDayOfMonth() == LocalDate.now().getDayOfMonth()) &&
            comprador instanceof ClienteAntiguo){
            venta = procesarVentaEspecial(comprador, cajero, promotor, libro);
        }else{
            venta = procesarVentaNormal(comprador, cajero, promotor, libro);
        }
        libro.reducirStock();
        asignarComisionPromotor(promotor, libro);
        comprador.agregarLibro(libro);
        return venta;
    }

    private static void asignarComisionPromotor(PersonalPromotor promotor, Libro vendido) {
        if(promotor != null){
            double comision = ((5 * vendido.getPrecio()) /100);
            promotor.agregarVenta(new Comision(vendido.getCod(), comision));
        }
    }

    private static Venta procesarVentaNormal(Cliente comprador, PersonalCajero cajero, PersonalPromotor promotor, Libro libro) {
        double precioOriginal = libro.getPrecio();
        double descuento = 0;
        double precioconDescuento = precioOriginal ;
        Factura factura = new Factura(precioOriginal,descuento,precioconDescuento, LocalDate.now());

        return  new Venta(comprador, promotor, cajero, factura, generarCodigo(), libro);

    }

    private static Venta procesarVentaEspecial(Cliente comprador, PersonalCajero cajero, PersonalPromotor promotor, Libro libro) {
        System.out.println("Por motivo de su Cumpleaños accedio a un descuento");
        double precioOriginal = libro.getPrecio();
        double descuento = 10;
        double precioconDescuento = precioOriginal - ((descuento * precioOriginal) /100);
        Factura factura = new Factura(precioOriginal,descuento,precioconDescuento, LocalDate.now());

        return  new Venta(comprador, promotor, cajero, factura, generarCodigo(), libro);
    }

    public static String generarCodigo(){
        char char1 = (char)((int)(Math.random()*(65-90+1)+90));
        char char2 = (char)((int)(Math.random()*(65-90+1)+90));
        char char3 = (char)((int)(Math.random()*(65-90+1)+90));
        int parteNumerica = (int)(Math.random()*(10-99+1)+99);

        return new String(""+char1+char2+char3+parteNumerica);
    }

    @Override
    public String toString() {
        String descuento = factura.getDescuento() == 0 ? "No se aplico descuento" : "Se aplico descuento";
        return "\t"+ factura.getFecha()+ " "+libro.getTitulo() +" "+ comprador.getNombre() +" "+ factura.getPrecioConDescuento() +" "+ descuento+"\n";
    }


}
