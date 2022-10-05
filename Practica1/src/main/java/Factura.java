import java.io.Serializable;
import java.time.LocalDate;

public class Factura implements Serializable {
    private double precio, descuento, precioConDescuento;
    private LocalDate fecha;

    public Factura(double precio, double descuento, double precioConDescuento, LocalDate fecha) {
        this.precio = precio;
        this.descuento = descuento;
        this.precioConDescuento = precioConDescuento;
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getPrecioConDescuento() {
        return precioConDescuento;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
