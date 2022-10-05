import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream entrada=new ObjectInputStream(new FileInputStream("registrosApp.obj"));
        Tienda tienda=(Tienda)entrada.readObject();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        while (input != 14) {
            System.out.println("Escoja que Funcion desea realizar :");
            System.out.println("" +
                    "1. Listar empleados contratados \n" +
                    "2. Listar clientes antiguos \n" +
                    "3. Listar nombre y correo de clientes nuevos con correo \n" +
                    "4. Listar Libros \n" +
                    "5. Verificar disponibilidad de libro \n" +
                    "6. Verificar si es cumpleaños del cliente \n" +
                    "7. Listar promotores de venta\n" +
                    "8. Listar Libros por Genero \n" +
                    "9. Listar todas las ventas realizadas  \n" +
                    "10. Mostrar cantidad de ventas que realizo un cajero en una fecha \n" +
                    "11. Listar Libros por Autor \n" +
                    "12. Verificar si el cliente con CI es valido para un descuento \n" +
                    "13. Listar libros no disponibles \n" +
                    "14. Salir \n");
            input = Integer.parseInt(reader.readLine());
            //Las entradas obtenidas son a partir de la base de datos database.json
            switch (input) {
                case 1: {
                    System.out.println(tienda.listarEmpleados());
                    break;
                }
                case 2: {
                    System.out.println(tienda.listarClientesAntiguos());
                    break;
                }
                case 3: {
                    System.out.println(tienda.listarClientesNuevoConCorreo());
                    break;
                }
                case 4: {
                    System.out.println(tienda.mostrarLibros());
                    break;
                }
                case 5: {
                    Cliente cl = tienda.getClientes().get(2);
                    PersonalCajero pc= (PersonalCajero) tienda.getEmpleados().get(0);
                    PersonalPromotor pp = (PersonalPromotor) tienda.getEmpleados().get(1);
                    System.out.println(tienda.realizarVenta(cl,pc,pp,new Libro()));
                    break;
                }
                case 6: {
                    Cliente cl = tienda.getClientes().get(6);
                    PersonalCajero pc= (PersonalCajero) tienda.getEmpleados().get(4);
                    PersonalPromotor pp = (PersonalPromotor) tienda.getEmpleados().get(6);
                    System.out.println(tienda.realizarVenta(cl,pc,pp,tienda.getLibros().get(1)));;
                    break;
                }
                case 7: {
                    System.out.println(tienda.listarPromotores());
                    break;
                }
                case 8: {
                    System.out.println(tienda.listarLibrosPorGenero("FANTASIA"));
                    break;
                }
                case 9: {
                    System.out.println(tienda.listarVentas());
                    break;
                }
                case 10: {
                    PersonalCajero pc= (PersonalCajero) tienda.getEmpleados().get(4);
                    System.out.println(tienda.cantidadVentasRegistradasPorCajero(pc,LocalDate.of(2022,10,4)));
                    break;
                }
                case 11: {
                    System.out.println(tienda.mostrarLibroDelAutor("Del Perio"));
                    break;
                }
                case 12: {
                    tienda.esDisponibleADescuento(13554571);
                    break;
                }
                case 13: {
                    System.out.println(tienda.librosNoDisponibles());
                    break;
                }
                case 14: {
                    tienda.guardarDatosTienda();
                    break;
                }
                default: {
                    System.out.println("Ingreso no valido");
                    break;
                }
            }
        }

    }

    public static void main3(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream entrada=new ObjectInputStream(new FileInputStream("registrosApp.obj"));
        Tienda tienda=(Tienda)entrada.readObject();

        Personal p1 = new PersonalCajero("Ted", "Mosby",  LocalDate.of(2022, 4,3));
        Personal p2 = new PersonalPromotor("Marshall", "Eriksen",  LocalDate.of(2022, 6,27));
        Personal p3 = new PersonalPromotor("Robin" , "Scherbatsky",  LocalDate.of(2022,2,9));

        ArrayList<Personal> empleados = new ArrayList<>(Arrays.asList(p1,p2,p3));

        Carnet cl1c1 = new Carnet(13232671, "santa cruz", LocalDate.of(2003,9,23));
        Cliente cl1 = new Cliente("Travis", "Tanner", cl1c1, "tt22@gmail.com");
        Carnet cl2c2 = new Carnet(13597871, "la paz", LocalDate.of(2002,6,18));
        Cliente cl2 = new Cliente("Trevor", "Traitor", cl2c2, "ttra@gmai.com");
        Carnet cl3c3 = new Carnet(15646571, "pando", LocalDate.of(2001,9,17));
        Cliente cl3 = new Cliente("Jessica", "Pearson", cl3c3, "mross@gmai.com");
        Carnet cl4c4 = new Carnet(10987671, "beni", LocalDate.of(2005,3,29));
        Cliente cl4 = new Cliente("Katrina", "Bennet", cl4c4);

        ArrayList<Cliente> clientes = new ArrayList<>(Arrays.asList(cl1,cl2,cl3,cl4));

        Libro libro = new Libro("La divina comedia", "todo divino", "Danta y Gery","Mundi",Generos.FANTASIA,"123aa2", 175);
        libro.aumentarStock(23);
        Libro libro2 = new Libro("Vendedores Perros", "Ventas", "Jurguen klaric","Ventis",Generos.CUENTOS,"po325", 30);
        libro2.aumentarStock(12);
        Libro libro3 = new Libro("It", "un payaso", "Jessica Chastain","its",Generos.TERROR,"ad132", 150);
        libro3.aumentarStock(100);
        Libro libro4 = new Libro("Platero y yo", "caballos", "Carlos Plata","cuentoshoy",Generos.FANTASIA,"213aa", 60);
        libro4.aumentarStock(1);

        ArrayList<Libro> libros =new ArrayList<>(Arrays.asList(libro,libro2,libro3,libro4));

        tienda.agregar(empleados, clientes,libros);

        ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream("registrosApp.obj"));
        salida.writeObject(tienda);
        salida.close();

    }

    public static void main2(String[] args) throws IOException {
        Personal p1 = new PersonalCajero("Gustavo", "Lazarte",  LocalDate.of(2022, 7,3));
        Personal p2 = new PersonalPromotor("Gabriel", "Mamani",  LocalDate.now());
        Personal p3 = new PersonalGerente("Adriana", "Torrico",  LocalDate.of(2022,9,28));
        Personal p4 = new PersonalPromotor("Luis" , "Escobar",  LocalDate.of(2022,3,15));

        ArrayList<Personal> empleados = new ArrayList<>(Arrays.asList(p1,p2,p3,p4));

        Carnet cl1c1 = new Carnet(13595671, "cochabamba", LocalDate.of(2004,10,3));
        Cliente cl1 = new Cliente("Harvey", "Specter", cl1c1, "hs22@gmail.com");
        Carnet cl2c2 = new Carnet(13554571, "cochabamba", LocalDate.of(2006,10,4));
        Cliente cl2 = new Cliente("Dona", "poulsen", cl2c2, "dona@gmai.com");
        Carnet cl3c3 = new Carnet(12454671, "la paz", LocalDate.of(2004,12,25));
        Cliente cl3 = new Cliente("Mike", "ross", cl3c3, "mross@gmai.com");
        Carnet cl4c4 = new Carnet(10413671, "cochabamba", LocalDate.of(2000,12,17));
        Cliente cl4 = new Cliente("Luis", "litt", cl4c4);

        ArrayList<Cliente> clientes = new ArrayList<>(Arrays.asList(cl1,cl2,cl3,cl4));

        Libro libro = new Libro("You", "Quien eres", "Del Perio","Perial",Generos.COMEDIA,"32132", 350);
        libro.aumentarStock(25);
        Libro libro2 = new Libro("Star Wars", "Mucha guerra", "Lucas Film","Wars",Generos.CIENCIA_FICCION,"ssaasdf52", 420);
        libro2.aumentarStock(12);
        Libro libro3 = new Libro("Sangre de Campeon", "Adolescencia", "Carlos Cuathemoc","Adls",Generos.CUENTOS,"ss21af52", 220);
        libro3.aumentarStock(100);
        Libro libro4 = new Libro("Yo antes de ti", "amor", "Carlos Quienchucha","Amor y paz",Generos.TERROR,"1ahf52", 320);
        libro4.aumentarStock(20);

        ArrayList<Libro> libros =new ArrayList<>(Arrays.asList(libro,libro2,libro3,libro4));

        Tienda t = new Tienda("LibroTeca", "Calacala #208",empleados,clientes,libros);
        ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream("registrosApp.obj"));
        salida.writeObject(t);
    }
}
