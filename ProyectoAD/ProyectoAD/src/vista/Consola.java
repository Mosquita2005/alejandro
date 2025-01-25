package vista;
import java.util.Scanner;
public class Consola {
private Scanner sc = new Scanner(System.in);
    
    

	public int mostrarMenuPrincipal() {
        System.out.println("---MENU PRINCIPAL---\n1: Mostrar datos\n2: Añadir uno\n3: Modificar uno\n4: Borrar uno\n5: Buscar por ID\n6: Salir");
        return sc.nextInt();
    }

    public int mostrarMenuSecundario() {
        System.out.println("---MENU SECUNDARIO---\n1: Fichero\n2: Fichero Binario\n3: Fichero XML\n4: Base de Datos\n5: Hibernate\n6: SQLite\n7: PHP\n8: DBO \n9: BASEX\n10: Mongo\n11: Transferencia de Datos\n12: Salir" );
        return sc.nextInt();
    }
    
    public int mostrarMenuTerciario() {
    	
    	
    	System.out.println("---MENU DE TRANSFERENCIA DE DATOS---");
        System.out.println("1: Transferir de Fichero a Fichero Binario");
        System.out.println("2: Transferir de Fichero a XML");
        System.out.println("3: Transferir de Fichero a Base de Datos");
        System.out.println("4: Transferir de Fichero a Hibernate");
        System.out.println("5: Transferir de Fichero Binario a Fichero");
        System.out.println("6: Transferir de Fichero Binario a XML");
        System.out.println("7: Transferir de Fichero Binario a Base de Datos");
        System.out.println("8: Transferir de Fichero Binario a Hibernate");
        System.out.println("9: Transferir de XML a Fichero");
        System.out.println("10: Transferir de XML a Fichero Binario");
        System.out.println("11: Transferir de XML a Base de Datos");
        System.out.println("12: Transferir de XML a Hibernate");
        System.out.println("13: Transferir de Base de Datos a Fichero");
        System.out.println("14: Transferir de Base de Datos a Fichero Binario");
        System.out.println("15: Transferir de Base de Datos a XML");
        System.out.println("16: Transferir de Base de Datos a Hibernate");
        System.out.println("17: Transferir de Hibernate a Fichero");
        System.out.println("18: Transferir de Hibernate a Fichero Binario");
        System.out.println("19: Transferir de Hibernate a XML");
        System.out.println("20: Transferir de Hibernate a Base de Datos");
        System.out.println("21: Volver al menú principal");
    	return sc.nextInt();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
