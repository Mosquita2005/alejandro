package controlador;

import java.util.Scanner;
import controlador.ControladorGrafico;
import controlador.ControladorConsola;
import vista.Grafico;
public class TipoInterfaz {
private Scanner sc= new Scanner (System.in);
private ControladorConsola controladorconsola;
private ControladorGrafico controladorgrafico;
 
public  TipoInterfaz() {
	this.controladorconsola=new ControladorConsola();
	this.controladorgrafico= new ControladorGrafico();
	
}


public void SeleccionInterfaz() {
	int opc=0;
	do {
		
		System.out.println("Seleccione el tipo interfaz que desea usar\n1.Consola\n2.Grafico\n3.Salir");
		opc=sc.nextInt();
		switch(opc) {
		
		case 1:
			controladorconsola.iniciarconsola();
			break;
		case 2:
			controladorgrafico.iniciar();
			break;
		case 3:
			System.out.println("Has Salido del programa");
			break;
		default:
			System.out.println(mensaje());
			break;	
			
		}
	}while(opc!=3);
	
	
	

}



public String mensaje() {
	
	return "Opcion no Válida";
}

}
