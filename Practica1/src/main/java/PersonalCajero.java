import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonalCajero extends Personal {


    public PersonalCajero(String nombre, String apellido, LocalDate fechaContratacion) {
        super(nombre, apellido, fechaContratacion);
    }

    @Override
    public String toString() {
        return "\t"+nombre+ " "+ apellido+ " "+ fechaContratacion +" Cajero\n";
    }
}
