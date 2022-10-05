import java.time.LocalDate;

public class PersonalGerente extends Personal{


    public PersonalGerente(String nombre, String apellido, LocalDate fechaContratacion) {
        super(nombre, apellido, fechaContratacion);
    }

    @Override
    public String toString() {
        return "\t"+nombre+ " "+ apellido +" "+fechaContratacion+" Gerente\n";
    }
}
