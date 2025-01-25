package controlador;

import AccesoDatos.*;
import vista.Consola;
import vista.Grafico;

public class ControladorGrafico {
	private BBDD bd;
    private Binarios binarios;
    private Ficheros fichero;
    private XML xml;
    private Hibernate hb;
	public ControladorGrafico() {
		this.bd=new BBDD();
    	this.binarios=new Binarios();
    	this.fichero= new Ficheros();
    	this.xml= new XML();
    	this.hb= new Hibernate();
    	
	
	}
	
	public void iniciar() {
		Grafico g= new Grafico();
		g.ventana();
	}
	
	private void ManejarMostrar() {
		

	}
	private void ManejarAñadir() {
		
	}    
    
    
}
