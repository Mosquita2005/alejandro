package AccesoDatos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import Interfaz.Interfaz;
import modelo.Vehiculos;

public class Mongo implements Interfaz{

	MongoCollection vehiculos;
	private HashMap<Integer, Vehiculos> mapamongo= new HashMap<Integer, Vehiculos>();


	public Mongo() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);

		MongoDatabase database = mongoClient.getDatabase("vehiculos");
		
		vehiculos=database.getCollection("coches");
		mapamongo=capturar();
	}

	@Override
	public void mostrar() throws IOException, ClassNotFoundException, Exception {
		// TODO Auto-generated method stub
		MongoCursor resultado = vehiculos.find().iterator();

		System.out.println("\n VER DATOS \n");		
		
		while (resultado.hasNext()) {

			Document doc = (Document) resultado.next();
			System.out.println("ID :"+doc.getInteger("id") + "\nMARCA :" + doc.getString("marca")+"\nMATRICULA :"+doc.getString("matricula")+"\nMODELO : "+doc.getString("modelo"));
			System.out.println("=========================================");
		}

	}

	@Override
	public void añadir() throws IOException, Exception {
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
	        
	        Vehiculos vehiculo = new Vehiculos(marca, modelo, matricula, id);
	        mapamongo.put(id, vehiculo);
	        Document nuevo = new Document();
	        
	        nuevo.put("id", id);
	        nuevo.put("marca", marca);
	        nuevo.put("matricula", matricula);
	        nuevo.put("modelo", modelo);
	        
	        vehiculos.insertOne(nuevo);
	        System.out.println("Vehiculo Insertado");

	        
	}

	@Override
	public void modificar() throws IOException, Exception {
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el ID del vehiculo a modificar ");
		boolean encontrado=false;
		int id= sc.nextInt();
		Document elemento = new Document("id", id);
		
		Vehiculos v= mapamongo.get(id);
		
		if(v!=null) {
			encontrado=true;
			System.out.println("Introduzca marca del vehiculo");
			String marca=sc.next();
			System.out.println("Introduzca matriucla del vehiculo");
			String matricula=sc.next();
			System.out.println("Introduzca modelo del vehiculo");
			String modelo=sc.next();

			v.setMarca(marca);
			v.setMatricula(matricula);
			v.setModelo(modelo);
			
			Document actualizar=new Document();
			actualizar.put("marca", marca);
			actualizar.put("matricula", matricula);
			actualizar.put("modelo", modelo);
			
			Document set= new Document("$set",actualizar);
			vehiculos.updateOne(elemento, set);
			System.out.println("Vehiculo actualizado");
			
			
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
		Document elemento= new Document("id",id);
		Document vehiculo= (Document) vehiculos.find(elemento);
		if(vehiculo!=null) {
			vehiculos.deleteOne(vehiculo);
			System.out.println("Vehiculo eliminado de la base de datos");
		}
	}

	@Override
	public void buscarID() throws IOException, Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		System.out.println("Introduzca el ID del vehiculo a buscar ");
		int id= sc.nextInt();
		boolean encontrado=false;
		Document elemento= new Document("id",id);
		Vehiculos v= mapamongo.get(id);
		if(v!=null) {
			MongoCursor resultado = vehiculos.find().iterator();
			while (resultado.hasNext()) {

				Document doc = (Document) resultado.next();
				System.out.println("ID :"+doc.getInteger("id") + "\nMARCA :" + doc.getString("marca")+"\nMATRICULA :"+doc.getString("matricula")+"\nMODELO : "+doc.getString("modelo"));
				System.out.println("=========================================");
			}
		}
	
	
	}

	@Override
	public void guardar() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public HashMap<Integer, Vehiculos> capturar() {
	    HashMap<Integer, Vehiculos> aux = new HashMap<>();
	    MongoCursor mc= vehiculos.find().iterator();
	    while (mc.hasNext()) {

			Document doc = (Document) mc.next();
			int id=doc.getInteger("id");
			String marca=doc.getString("marca");
			String modelo=doc.getString("modelo");
			String matricula=doc.getString("matricula");
			
			Vehiculos v= new Vehiculos(marca,modelo,matricula,id);
			aux.put(id, v);
		}
	    
	    return aux;
	}

}
