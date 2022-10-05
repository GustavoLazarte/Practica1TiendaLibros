import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;

public abstract  class Personal implements Serializable {
    protected String nombre, apellido;
    protected String cod;
    protected LocalDate fechaContratacion;

    public Personal(String nombre, String apellido, LocalDate fechaContratacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaContratacion = fechaContratacion;
        this.cod = generarCodigo();
    }

    @Override
    public abstract String toString();

    private String generarCodigo(){
        String aux1 = fechaContratacion.getMonthValue() > 9 ? ""+fechaContratacion.getMonthValue(): "0"+fechaContratacion.getMonthValue();
        String aux2 =fechaContratacion.getDayOfMonth() > 9 ? ""+fechaContratacion.getDayOfMonth(): "0"+fechaContratacion.getDayOfMonth();
         return apellido.substring(0,3).toUpperCase()+aux1+ aux2;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCod() {
        return cod;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }
}
