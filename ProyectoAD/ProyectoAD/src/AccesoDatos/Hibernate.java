package AccesoDatos;
import org.hibernate.HibernateException;





import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.TypedQuery;
import javax.transaction.Transaction;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Interfaz.Interfaz;
import modelo.Vehiculos;

public class Hibernate implements Interfaz {
	Session session;

	private Vehiculos v;
	private HashMap<Integer,Vehiculos>mapahb= new HashMap<>();
	public Hibernate() {
			session =HibernateUtil.getSession();
			mapahb=capturar();
		 
	}

@Override
public void mostrar() throws IOException, ClassNotFoundException {
	// TODO Auto-generated method stub
	TypedQuery<Vehiculos>t=session.createQuery("select v from Vehiculos v");
	List results= t.getResultList();
	
	Iterator iterator= results.iterator();
	
	while(iterator.hasNext()) {
		 v= (Vehiculos) iterator.next();
		System.out.println("ID -> " +v.getId()+"\nMarca ->"+v.getMarca()+"\nModelo -> "+v.getModelo()+"\nMatricula -> "+ v.getMatricula());
		System.out.println("=========================================");
	}
	System.out.println(mapahb);
}




@Override
public void añadir() throws IOException {
	// TODO Auto-generated method stub
	Scanner sc = new Scanner(System.in);
    System.out.println("Introduzca la marca del vehiculo");
    String marca = sc.next();
    System.out.println("Introduzca el modelo del vehiculo");
    String modelo = sc.next();
    System.out.println("Introduzca la matricula del vehiculo");
    String matricula = sc.next();
    System.out.println("Introduzca el id del vehiculo");
    int id = sc.nextInt();
    Vehiculos v= new Vehiculos(marca,modelo,matricula,id);
    mapahb.put(id, v);
    v.setMarca(marca);
    v.setModelo(modelo);
    v.setMatricula(matricula);
    v.setId(id);
    
  
    session.beginTransaction();
    session.save(v);
    session.getTransaction().commit();
    System.out.println("Vehiculo añadido");
}




@Override
public void modificar() throws IOException {
	// TODO Auto-generated method stub
	  Scanner sc = new Scanner(System.in);
	    System.out.println("Introduzca el ID a modificar");
	    int id = sc.nextInt();

	    // Buscar en el HashMap
	    if (mapahb.containsKey(id)) {
	        session.beginTransaction();

	        Vehiculos vehiculoAModificar = session.get(Vehiculos.class, id);
	        if (vehiculoAModificar != null) {
	            System.out.println("Introduzca nueva marca:");
	            String nuevaMarca = sc.next();
	            System.out.println("Introduzca nuevo modelo:");
	            String nuevoModelo = sc.next();
	            System.out.println("Introduzca nueva matricula:");
	            String nuevaMatricula = sc.next();

	            vehiculoAModificar.setMarca(nuevaMarca);
	            vehiculoAModificar.setModelo(nuevoModelo);
	            vehiculoAModificar.setMatricula(nuevaMatricula);

	            // Actualizar en la base de datos
	            session.update(vehiculoAModificar);
	            session.getTransaction().commit();
	            System.out.println("Vehículo actualizado correctamente en la base de datos.");

	            // Actualizar en el HashMap
	            mapahb.put(id, vehiculoAModificar);
	            System.out.println("Vehículo actualizado correctamente en el HashMap.");
	        } else {
	            session.getTransaction().rollback();
	            System.out.println("El vehículo no se encontró en la base de datos.");
	        }
	    } else {
	        System.out.println("El vehículo no se encontró en el HashMap.");
	    }
}




@Override
public void borrar() throws IOException {
	Scanner sc = new Scanner(System.in);
    System.out.println("Introduzca el ID a borrar");
    int idbuscar = sc.nextInt();
    boolean encontrado = false;

    if(mapahb.containsKey(idbuscar)) {
    	
    	encontrado = true;

         mapahb.remove(idbuscar);
           
            session.beginTransaction();
            Vehiculos vehiculoAEliminar = session.get(Vehiculos.class, idbuscar);
            if (vehiculoAEliminar != null) {
                session.delete(vehiculoAEliminar); 
                session.getTransaction().commit();
                System.out.println("Vehiculo borrado del hashmap");
                System.out.println("Vehiuculo borrado de la base de datos.");
            } else {
                session.getTransaction().rollback();
                System.out.println("Vehiculo no encontrado en la base de datos.");
            }
        }
    

    if (!encontrado) {
        System.out.println("Vehiculo no encontrado en el hashmap.");
    }
}




@Override
public void buscarID() throws IOException {
	Scanner sc= new Scanner (System.in);
	System.out.println("Introduzca el ID A buscar");
	int idbuscar=sc.nextInt();
	Vehiculos v= session.get(Vehiculos.class, idbuscar);
	
	if(v!=null) {
		System.out.println("ID -> " +v.getId()+"\nMarca ->"+v.getMarca()+"\nModelo -> "+v.getModelo()+"\nMatricula -> "+ v.getMatricula());
		System.out.println("=========================================");
		
	}else {
		System.out.println("Vehiculo no encontrado");
	}

}





@Override
public void guardar() throws Exception{
	// TODO Auto-generated method stub
	
	for(Map.Entry<Integer, Vehiculos>x: mapahb.entrySet()) {
		session.beginTransaction();
		session.save(x.getValue());
		session.getTransaction().commit();
		System.out.println("Informacion copiada");
	}
	
}

public HashMap<Integer, Vehiculos> capturar(){
	HashMap<Integer, Vehiculos> aux= new HashMap<Integer, Vehiculos>();
	// TODO Auto-generated method stub
		TypedQuery<Vehiculos>t=session.createQuery("select v from Vehiculos v");
		List results= t.getResultList();
		
		Iterator iterator= results.iterator();
		
		while(iterator.hasNext()) {
			 v= (Vehiculos) iterator.next();
		aux.put(v.getId(), v);
		}

	return aux;


}

public HashMap<Integer, Vehiculos> getMapahb() {
	return mapahb;
}

public void setMapahb(HashMap<Integer, Vehiculos> mapahb) {
	this.mapahb = mapahb;
}	
}




