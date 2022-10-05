import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;

public class Carnet implements Serializable {
    private long nro;
    private String extension;
    private LocalDate fechaNacimiento;

    public Carnet(long nro, String extension, LocalDate fechaNacimiento) {
        this.nro = nro;
        this.extension = extension;
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getNro() {
        return nro;
    }

    public String getExtension() {
        return extension;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
}
