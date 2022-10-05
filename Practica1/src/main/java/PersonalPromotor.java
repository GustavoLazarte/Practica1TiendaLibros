import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonalPromotor extends Personal{

    private List<Comision> comisionesObtenidas;

    public PersonalPromotor(String nombre, String apellido, LocalDate fechaContratacion) {
        super(nombre, apellido, fechaContratacion);
        comisionesObtenidas = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "\t"+nombre+ " "+ apellido +" "+fechaContratacion+" Promotor\n";
    }

    public String reportarse(){
        String montoComisionado = sumarComisiones()>0 ? "Bs."+ sumarComisiones() : "Bs.0";
        return "\t"+ nombre+" "+fechaContratacion + "  "+ (comisionesObtenidas.size())+ " "+ montoComisionado;
    }

    public void agregarVenta(Comision c){
        comisionesObtenidas.add(c);
    }

    public List<Comision> getComisionesObtenidas() {
        return comisionesObtenidas;
    }

    public int sumarComisiones(){
        int suma= 0;
        for (Comision comision: comisionesObtenidas) {
            suma += comision.getMonto();
        }

        return suma;
    }
}
