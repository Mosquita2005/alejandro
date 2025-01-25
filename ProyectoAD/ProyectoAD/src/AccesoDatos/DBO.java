package AccesoDatos;

import java.io.IOException;


import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import Interfaz.Interfaz;
import modelo.Vehiculos;

public class DBO implements Interfaz{
	private HashMap<Integer, Vehiculos> mapadbo= new HashMap<>();
	private static EntityManager em;
	private static EntityManagerFactory emf;
	private Vehiculos v;
	public DBO() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("db/coches.odb");
        }
        if (em == null) {
            em = emf.createEntityManager();
        }
        mapadbo=capturar();
		
		}
	
	@Override
	public void mostrar() throws IOException, ClassNotFoundException, Exception {
		// TODO Auto-generated method stub
		   if (em == null) {
	            System.err.println("EntityManager is not initialized.");
	            return;
	        }

	        TypedQuery<Vehiculos> query = em.createQuery("SELECT b FROM Vehiculos b", Vehiculos.class);
	        List<Vehiculos> results = query.getResultList();
	        
	        for (Vehiculos bb : results) {
	            System.out.println(bb);
	        }
	        cerrar();
	}

	@Override
	public void añadir() throws IOException, Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		try {
		emf = Persistence.createEntityManagerFactory("db/coches.odb");
		em = emf.createEntityManager();
		 System.out.println("Introduzca la marca del vehiculo");
	     String marca = sc.next();
	     System.out.println("Introduzca el modelo del vehiculo");
	     String modelo = sc.next();
	     System.out.println("Introduzca la matricula del vehiculo");
	     String matricula = sc.next();
	     System.out.println("Introduzca el id del vehiculo");
	     int id = sc.nextInt();
	     
	     Vehiculos vehiculo = new Vehiculos(marca, modelo, matricula, id);
	     mapadbo.put(id, vehiculo);
	     
	 	 
	    	 
	         em.getTransaction().begin();
		     em.persist(vehiculo);
		     em.getTransaction().commit();
		     System.out.println("Vehiculo añadido");
	    	 
	     }catch(javax.persistence.PersistenceException ex) {
	    	 System.err.println("Se ha producido un error an intentar acceder a la base de datos\n"
						+ "Probablemente sea porque está abierta por el ObjectDB explorer");
	     }
	cerrar();
	}

	@Override
	public void modificar() throws IOException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar() throws IOException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscarID() throws IOException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void cerrar() {
		em.close();
	}
	
public HashMap<Integer, Vehiculos> capturar(){
		
		HashMap<Integer, Vehiculos> aux= new HashMap<>();

	    try {
	        // Asegúrate de que el EntityManager esté inicializado
	        if (em == null) {
	            throw new IllegalStateException("EntityManager no inicializado.");
	        }

	        TypedQuery<Vehiculos> query = em.createQuery("SELECT v FROM Vehiculos v", Vehiculos.class);
	        List<Vehiculos> vehiculosList = query.getResultList();

	        for (Vehiculos vehiculo : vehiculosList) {
	            aux.put(vehiculo.getId(), vehiculo); // 
	        }
	    } catch (Exception e) {
	        System.err.println("Error al capturar datos de la base de datos: " + e.getMessage());
	        e.printStackTrace();
	    }

		return aux;
	}

}
