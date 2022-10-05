import java.io.Serializable;

public class Comision implements Serializable {
    private String codLibro;
    private double monto;

    public Comision(String codLibro, double monto) {
        this.codLibro = codLibro;
        this.monto = monto;
    }

    public String getCodVenta() {
        return codLibro;
    }

    public double getMonto() {
        return monto;
    }

}
