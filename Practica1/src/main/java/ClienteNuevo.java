public class ClienteNuevo extends Cliente{


    public ClienteNuevo(String nombre, String apellido, Carnet carnet, String correoElectronico) {
        super(nombre, apellido, carnet, correoElectronico);
    }

    public ClienteNuevo(Cliente cliente){
        super(cliente);
    }

    @Override
    public String toString() {
        String res="";
        if(correoElectronico != null && !correoElectronico.equals("")){
            res = nombre +" "+correoElectronico;
        }

        return res;
    }
}
