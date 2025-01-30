package AccesoDatos;
import java.io.File;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; 
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Interfaz.Interfaz;
import modelo.Vehiculos;

public class Binarios implements Interfaz{
	private HashMap <Integer,Vehiculos>mapabinario= new HashMap<>();
	private File Bin= new File("ficheros/textobin.bin");
	public Binarios() {
		
		
	}
	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		System.err.println("Acceso a binarios");

	}
	@Override
	public void mostrar() throws IOException, ClassNotFoundException {
		FileInputStream fis=new FileInputStream (Bin);
		ObjectInputStream ois= new ObjectInputStream(fis);
		
		while(fis.available()>0) {
			Vehiculos v= (Vehiculos)ois.readObject();
			System.out.println(v);
			
		}
		ois.close();
		
	}

	@Override
	public void añadir() throws IOException {

		 Scanner sc = new Scanner(System.in);
	        System.out.println("Introduzca la marca del vehiculo");
	        String marca = sc.next();
	        System.out.println("Introduzca el modelo del vehiculo");
	        String modelo = sc.next();
	        System.out.println("Introduzca la matricula del vehiculo");
	        String matricula = sc.next();
	        System.out.println("Introduzca el id del vehiculo");
	        int id = sc.nextInt();
	        
	        Vehiculos vehiculo = new Vehiculos(marca, modelo, matricula, id);
	        mapabinario.put(id, vehiculo);
	        System.out.println("Vehiculo añadido");
	        
	        FileOutputStream fos= new FileOutputStream(Bin);
	        ObjectOutputStream oos= new ObjectOutputStream(fos);
	        
	    
	        oos.writeObject(vehiculo);
	        
	        oos.close();
	}

	@Override
	public void modificar() throws IOException {
		Scanner sc= new Scanner(System.in);
		System.out.println("Introduzca el id para modificar el vehiculo");
		int id=sc.nextInt();
		boolean encontrado= false;
		Vehiculos v;
		for(Map.Entry<Integer, Vehiculos> aux:mapabinario.entrySet()) {
			v= aux.getValue();
			if(id==aux.getKey()) {
				 System.out.println("Introduzca nueva marca");
		            String marca = sc.next();
		            System.out.println("Introduzca nuevo modelo");
		            String modelo = sc.next();
		            System.out.println("Introduzca nueva matricula");
		            String matricula = sc.next();
		            v.setMarca(marca);
		            v.setModelo(modelo);
		            v.setMatricula(matricula);
		             System.out.println("Vehiculo modificado");
		             encontrado=true;
			}
			
		}
		
	 
		
		if(!encontrado) {
			System.out.println("Vehiculo no encontrado");
		}
		
	
		
		  
        FileOutputStream fos= new FileOutputStream(Bin);
        ObjectOutputStream oos= new ObjectOutputStream(fos);
		for(Map.Entry<Integer, Vehiculos> aux:mapabinario.entrySet()) {
			  
			  oos.writeObject(aux.getValue());
			
		}
	       oos.close();

	}

	@Override
	public void borrar()  throws IOException {
		Scanner sc= new Scanner(System.in);
		System.out.println("Introduzca el id del vehiculo a borrar");
		int id =sc.nextInt();
		boolean encontrado=false;
		for(Map.Entry<Integer, Vehiculos> aux: mapabinario.entrySet()) {
			if(id==aux.getKey()) {
				encontrado=true;
				mapabinario.remove(id);
				System.out.println("Vehiculo eliminado");
				
			}
		}
		
		
		if(!encontrado) {
			System.out.println("Vehiculo no encontrado");
		}
		
		FileOutputStream fos= new FileOutputStream(Bin);
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        for(Map.Entry<Integer, Vehiculos> aux:mapabinario.entrySet()) {
        	oos.writeObject(aux.getValue());
        }
        oos.close();
	}

	
	public void buscarID()  {
		Scanner sc= new Scanner(System.in);
		boolean encontrado=false;
		System.out.println("Introduzca ID para buscar vehiculo");
		int id=sc.nextInt();
		
		for(Map.Entry<Integer, Vehiculos> aux: mapabinario.entrySet()) {
			if(id==aux.getKey()) {
				System.out.println("Vehiculo encontrado");
				encontrado=true;
				System.out.println(aux.toString());
			}
			
		}
		
		if(!encontrado) {
			System.out.println("Vehiculo no encontrado");

		}
		
	}

	public HashMap<Integer, Vehiculos> getMapabinario() {
		return mapabinario;
	}

	public void setMapabinario(HashMap<Integer, Vehiculos> mapabinario) {
		this.mapabinario = mapabinario;
	}

	
	
	@Override
	public void guardar() throws Exception {
		// TODO Auto-generated method stub
		FileOutputStream fos= new FileOutputStream(Bin);
        ObjectOutputStream oos= new ObjectOutputStream(fos);
		for(Map.Entry<Integer, Vehiculos> aux:mapabinario.entrySet()) {
			  
			  oos.writeObject(aux.getValue());
			  System.out.println("Informacion copiada");
		}
	       oos.close();
	      

	}

	
	
	
	

}
