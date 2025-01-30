package AccesoDatos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import Interfaz.Interfaz;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import modelo.Vehiculos;
public class Mongo implements Interfaz{

	 MongoCollection vehiculos;
	 MongoClient mongoClient;
	 private HashMap<Integer, Vehiculos> mapamongo= new HashMap<Integer, Vehiculos>();

	 public Mongo() {
		
		 }
	 
	 

		@Override
		public void iniciar() {
			// TODO Auto-generated method stub
			System.err.println("Acceso a mongo");
			  mongoClient = new MongoClient("localhost", 27017);
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
		 Scanner sc= new Scanner(System.in);
		 System.out.println("Introduzca el ID del vehiculo a modificar ");
		 boolean encontrado=false;
		 int id= sc.nextInt();
		 Document elemento= new Document("id",id);
		 Vehiculos v= mapamongo.get(id);
		 if(v!=null) {
			 encontrado=true;
			   System.out.println("Introduzca nueva marca:");
	            String nuevaMarca = sc.next();
	            System.out.println("Introduzca nuevo modelo:");
	            String nuevoModelo = sc.next();
	            System.out.println("Introduzca nueva matricula:");
	            String nuevaMatricula = sc.next();
	           v.setMarca(nuevaMarca);
	           v.setMatricula(nuevaMatricula);
	           v.setModelo(nuevoModelo);
	           
	           Document actualizar= new Document();
	           actualizar.put("marca", nuevaMarca);
	           actualizar.put("modelo", nuevoModelo);
	           actualizar.put("matricula", nuevaMatricula);
	           
	           Document set= new Document("$set",actualizar);
	           vehiculos.updateOne(elemento, set);
	           System.out.println("vehiculo actualizado");
			 
		 }
		 
		 if(!encontrado) {
			 System.out.println("No se encontro el vehiculo");
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
		 Vehiculos v= mapamongo.get(id); 
		 if(v!=null) {
			encontrado=true;
		 vehiculos.deleteOne(elemento);
		 mapamongo.remove(id);
		 System.out.println("Vehiculo eliminado de la base de datos");
		 System.out.println("Vehiculo eliminado del hashmap");


		 }

		 if(!encontrado) {
	System.out.println("No se encontro el vehiculo");
		 }
		 
	}

	@Override
	public void buscarID() throws IOException, Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		boolean encontrado= false;
		System.out.println("Introduzca el ID para buscar el vehiculo");
		int id= sc.nextInt();
		Document buscar= new Document("id",id);
		
		Vehiculos v= mapamongo.get(id);
		if(v!=null) {
			encontrado=true;
			MongoCursor mc= vehiculos.find(buscar).iterator();
			while(mc.hasNext()) {
				
				 Document doc = (Document) mc.next();

				 System.out.println("ID :"+doc.getInteger("id") + "\nMARCA :" + doc.getString("marca")+"\nMATRICULA :"+doc.getString("matricula")+"\nMODELO : "+doc.getString("modelo"));
				 System.out.println("=========================================");
			}
			
		}
		
		
		if(!encontrado) {
			System.out.println("No se encontro el vehiculo");

		}
	}

	@Override
	public void guardar() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	 public HashMap<Integer, Vehiculos> capturar() {


		 HashMap<Integer, Vehiculos> aux = new HashMap<>();

			MongoCursor resultado = vehiculos.find().iterator();

			
			while(resultado.hasNext()) {
				 Document doc = (Document) resultado.next();
				 
				 int id=doc.getInteger("id");
				 String marca =doc.getString("marca");
				 String matricula=doc.getString("matricula");
				 String modelo=doc.getString("modelo");
				 Vehiculos v= new Vehiculos(matricula,marca,modelo,id);
				 
				 aux.put(id, v);
			}
		 


		 return aux;


		 }
	 public void cerrar() {
	mongoClient.close();
	 }




}
