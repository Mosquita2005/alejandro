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
       
		
		}
	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		System.err.println("Acceso a ObjectDB");

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
	}

	@Override
	public void modificar() throws IOException, Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		System.out.println("Introduzca el ID del vehiculo a modificar ");
		boolean encontrado=false;
		int id= sc.nextInt();
		 
		try {
		Vehiculos v= em.find(Vehiculos.class,id);
			 
			if(v!=null) {
			encontrado=true;
			System.out.println("Introduzca la marca del vehiculo");
			     String marca = sc.next();
			     System.out.println("Introduzca el modelo del vehiculo");
			     String modelo = sc.next();
			     System.out.println("Introduzca la matricula del vehiculo");
			     String matricula = sc.next();
			     v.setMarca(marca);
			     v.setMatricula(matricula);
			     v.setModelo(modelo);
			     
			     em.getTransaction().begin();
			     em.merge(v);
			     em.getTransaction().commit();
			     System.out.println("Vehiculo modificado");
			 
			}
			 
		 
		 
		}catch(Exception e) {
		System.out.println(e.getMessage());
		 
		 
		}
		 
		if(!encontrado) {
		System.out.println("Vehiculo no encontrado");
		}
		 
	}
	

	@Override
	public void borrar() throws IOException, Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		System.out.println("Introduzca el ID del vehiculo a borrar ");
		boolean encontrado=false;
		int id= sc.nextInt();
		 
		try {
		Vehiculos v= em.find(Vehiculos.class,id);
		 
		if(v!=null) {
		 
		     em.getTransaction().begin();
		     em.remove(v);
		     em.getTransaction().commit();
		     
		     mapadbo.remove(id);
		     System.out.println("Vehiculo borrado del Hasmap");
		     System.out.println("Vehiculo borrado de la base de datos");
		 
		 
		}
		 
		 
		 
		}catch(Exception e) {
		System.out.println(e.getMessage());
		 
		 
		}
		 
		if(!encontrado) {
		System.out.println("Vehiculo no encontrado");
		}
		
	}

	@Override
	public void buscarID() throws IOException, Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		boolean encontrado= true;
		System.out.println("Introduzca el id del vehiculo a buscar");
		int id=sc.nextInt();
		 
		 
		try {
		Vehiculos v= em.find(Vehiculos.class, id);
		if(v!=null) {
		encontrado=true;
		  System.out.println("Vehículo encontrado:");
		            System.out.println("ID: " + v.getId());
		            System.out.println("Marca: " + v.getMarca());
		            System.out.println("Modelo: " + v.getModelo());
		            System.out.println("Matrícula: " + v.getMatricula());
		 
		} 
		}catch (Exception e) {
		System.out.println(e.getMessage());
		}
		 
		if(!encontrado) {
		System.out.println("Vehiculo no encontrado");
		}
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
