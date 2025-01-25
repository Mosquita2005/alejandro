package AccesoDatos;
import java.io.File;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;

import Interfaz.Interfaz;
import modelo.Vehiculos;
public class Ficheros implements Interfaz {
    
    private HashMap<Integer, Vehiculos> mapatexto = new HashMap<>();
    private File archivo=new File("ficheros/texto.txt");

   

    public void mostrar() throws IOException {
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
        br.close();
    }

    public void añadir() throws IOException {
    	int cont=mapatexto.size()+1;
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
        mapatexto.put(id, vehiculo);
        
        System.out.println("Vehiculo añadido");
        
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("VEHICULO " + cont + "\n");
        bw.write("-------------------------\n");
        bw.write(vehiculo.toString() + "\n");
        bw.close();
    }

    @Override
    public void modificar() throws IOException {
    	
        Scanner sc = new Scanner(System.in);
        int cont=0;
        System.out.println("Introduce el ID del vehiculo para modificar");
        int id = sc.nextInt();
        
        Vehiculos vehiculo = mapatexto.get(id);
        
       
        if (vehiculo != null) {
            System.out.println("Introduzca nueva marca");
            String marca = sc.next();
            System.out.println("Introduzca nuevo modelo");
            String modelo = sc.next();
            System.out.println("Introduzca nueva matricula");
            String matricula = sc.next();
            
            vehiculo.setMarca(marca);
            
            vehiculo.setModelo(modelo);
            
            vehiculo.setMatricula(matricula);
            
            System.out.println("Vehiculo modificado");
            
        } else {
            System.out.println("Vehiculo no encontrado");
        }
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        for (Map.Entry<Integer, Vehiculos> x : mapatexto.entrySet()) {
            id  = x.getKey();
            cont++;
            vehiculo = x.getValue();
            bw.write("VEHICULO " + cont + "\n");
            bw.write("-------------------------\n");
            bw.write(x.getValue().toString() + "\n");
        }
        bw.close();
        
    }

    @Override
    public void borrar() throws IOException {
    	int cont=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el ID del vehiculo para borrar");
        int id = sc.nextInt();
       
        if (mapatexto.remove(id) != null) {
            System.out.println("Vehiculo borrado");
        } else {
            System.out.println("Vehiculo no encontrado");
        }
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        for (Map.Entry<Integer, Vehiculos> x : mapatexto.entrySet()) {
             id = x.getKey();
             cont++;
            Vehiculos vehiculo = x.getValue();
            bw.write("VEHICULO " + cont + "\n");
            bw.write("-------------------------\n");
            bw.write(vehiculo.toString() + "\n");
        }
        bw.close();
    }

    public void buscarID() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el ID del vehiculo");
        int id = sc.nextInt();
        
        Vehiculos vehiculo = mapatexto.get(id);
        if (vehiculo != null) {
            System.out.println("Vehiculo encontrado: \n" + vehiculo);
        } else {
            System.out.println("Vehiculo no encontrado");
        }
    }

	public HashMap<Integer, Vehiculos> getMapatexto() {
		return mapatexto;
	}

	public void setMapatexto(HashMap<Integer, Vehiculos> mapatexto) {
		this.mapatexto = mapatexto;
	}

	
	

	@Override
	public void guardar() throws Exception {
		// TODO Auto-generated method stub
		int cont=mapatexto.size();
		  FileWriter fw = new FileWriter(archivo);
	      BufferedWriter bw = new BufferedWriter(fw);
	      for (Map.Entry<Integer, Vehiculos> x : mapatexto.entrySet()) {
	    	  	cont++;
	    	  	bw.write("VEHICULO " + cont + "\n");
	            bw.write("-------------------------\n");
	            bw.write(x.getValue().toString() + "\n");
				System.out.println("Informacion copiada");

	        	
	        }
	      bw.close();

	        	
	}


    
}