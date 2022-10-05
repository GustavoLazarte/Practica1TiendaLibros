public class ClienteAntiguo extends Cliente{

    private Cliente cliente;

    public ClienteAntiguo(String nombre, String apellido, Carnet carnet, String correoElectronico) {
        super(nombre, apellido, carnet, correoElectronico);
    }

    public ClienteAntiguo(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String toString() {
        return "\t"+nombre+ " " + apellido + " " + carnet.getNro() + " " + correoElectronico;
    }
}
