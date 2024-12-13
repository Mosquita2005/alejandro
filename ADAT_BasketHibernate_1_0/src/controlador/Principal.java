package controlador;

import utils.AccesoHibernate;

public class Principal {
	
    public static void main(String[] args) {
    	
    	AccesoHibernate accesoh;
    
    	try{
    		System.out.println("INICIO PRUEBAS BASKET HIBERNATE");
    		
    		accesoh = new AccesoHibernate();
    		
            //accesoh.borrarDatos();
    		//accesoh.insertarDatosPrueba();
    		//accesoh.consultaEquipo();
            accesoh.consultaJugadorPosicion();
        
    		System.out.println("FIN PRUEBAS");
    	} catch (Exception ex){
    		System.out.println("EXCEPCION!!!!");
    		ex.printStackTrace();
    	} finally{
    		System.out.println("REQUETEFIN");
    		System.exit(0);
    	} 
    	
    }
        

}
