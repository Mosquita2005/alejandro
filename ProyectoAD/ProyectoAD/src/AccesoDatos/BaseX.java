package AccesoDatos;

import java.io.IOException;
import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.basex.core.*;
import org.basex.core.cmd.*;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import modelo.Vehiculos;
import Interfaz.Interfaz;

public class BaseX implements Interfaz {
Context context;
private String ruta="ficheros/coches.xml";
private String nombre="Coches";
private HashMap<Integer, Vehiculos> mapabasex= new HashMap<Integer, Vehiculos>();
	public BaseX() {
		
		  
	    }
	
	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		 this.context = new Context();
		   mapabasex=capturar();
	        try {
	             // Conectar a la base de datos
	            new CreateDB(nombre,ruta).execute(context);
	            System.out.println("Base de datos " + ruta + " creada y conectada.");
	        } catch (BaseXException e) {
	            System.err.println("Error al crear o conectar a la base de datos: " + e.getMessage());
	        }
	}
	
	@Override
	public void mostrar() throws IOException, ClassNotFoundException, Exception {
		new Open(nombre).execute(context);
		   
        String query =  "<Vehiculos>{" + 
                "for $c in /Vehiculos/Coche return $c" + 
                "}</Vehiculos>"; 
		String datosConsulta = new XQuery(query).execute(context);
		// TODO Auto-generated method stub
		SAXBuilder saxBuilder = new SAXBuilder();
		InputStream fichero = new ByteArrayInputStream(datosConsulta.getBytes());
		Document document = saxBuilder.build(fichero);
		Element classElement = document.getRootElement();

		List<Element> listaNodos = classElement.getChildren();
		for (int temp = 0; temp < listaNodos.size(); temp++) {
			Element elemento = listaNodos.get(temp);
		
			System.out.println("-------- VEHICULO " + (temp+1) + "--------");
			
			System.out.println("ID :"+elemento.getAttributeValue("id"));
			System.out.println("MARCA :"+elemento.getChild("Marca").getText());
			System.out.println("MODELO :"+elemento.getChild("Modelo").getText());
			System.out.println("MATRICULA :"+elemento.getChild("Matricula").getText());

			
			System.out.println("-------------------\n ");			
			
		}
		System.out.println(mapabasex);

	}
	

	@Override
	public void añadir() throws IOException, Exception {
		// TODO Auto-generated method stub
		   Scanner sc = new Scanner(System.in);
		   new Open(nombre).execute(context);
	        System.out.println("Introduzca la marca del vehiculo");
	        String marca = sc.next();
	        System.out.println("Introduzca el modelo del vehiculo");
	        String modelo = sc.next();
	        System.out.println("Introduzca la matricula del vehiculo");
	        String matricula = sc.next();
	        System.out.println("Introduzca el id del vehiculo");
	        int id = sc.nextInt();
	        
	        Vehiculos vehiculo = new Vehiculos(marca, modelo, matricula, id);
	        mapabasex.put(id, vehiculo);
	        
	        Element nuevo = new Element("Coche");
			Element nmarca = new Element("Marca");
			Element nmodelo = new Element("Modelo");
			Element nmaatricula = new Element("Matricula");
			Attribute atr= new Attribute("id",String.valueOf(id));
			
			nmarca.setText(marca);
			nmodelo.setText(modelo);
			nmaatricula.setText(matricula);
			nuevo.setAttribute(atr);
			nuevo.addContent(nmarca);
			nuevo.addContent(nmodelo);
			nuevo.addContent(nmaatricula);
			XMLOutputter xmlOut = new XMLOutputter();
			String formateado = xmlOut.outputString(nuevo);
			
			String queryInsert = "insert node  " + formateado + " into /Vehiculos ";
			
			String datosconsulta= new XQuery(queryInsert).execute(context);
			System.out.println("Vehiculo insertado");
	}

	@Override
	public void modificar() throws IOException, Exception {
		// TODO Auto-generated method stub
		   Scanner sc = new Scanner(System.in);
			System.out.println("Introduzca el ID del vehiculo a modificar ");
			int id=sc.nextInt();
			boolean encontrado= false;
			Vehiculos v= mapabasex.get(id);
			if(v!=null) {
				 new Open(nombre).execute(context);
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
				     
				String query="replace node /Vehiculos/Coche[@id = '"+id+ "'] with <Coche id ='"+id+"'> "
						+ "<Marca>" + marca + "</Marca>"
						+ "<Modelo>"+ modelo + "</Modelo>"
						+ "<Matricula>" +matricula+ "</Matricula>"
						+ "</Coche>";     
				new XQuery(query).execute(context);
				System.out.println("Vehiculo modificado");
			}
		 
			if(!encontrado) {
				System.out.println("Vehiculo no encontrado");
			}
	}

	@Override
	public void borrar() throws IOException, Exception {
		// TODO Auto-generated method stub
		   Scanner sc = new Scanner(System.in);
			System.out.println("Introduzca el ID del vehiculo a borrar ");
			int id=sc.nextInt();
			boolean encontrado= false;
			Vehiculos v= mapabasex.get(id);
			if(v!=null) {
				encontrado=true;
				mapabasex.remove(id); 
				new Open(nombre).execute(context);
				
				String query="delete node Vehiculos/Coche[@id= '" +id+ "']";
				System.out.println("Vehiculo borrado");
			}


		
		if(!encontrado) {
			System.out.println("Vehiculo no encontrado");
		}
	}

	@Override
	public void buscarID() throws IOException, Exception {
		// TODO Auto-generated method stub
		   Scanner sc = new Scanner(System.in);
		   new Open(nombre).execute(context);
		   System.out.println("Introduzca el id del Vehiculo a mostrar");
		   int id= sc.nextInt();
		   boolean encontrado=false;
		   Vehiculos v=mapabasex.get(id);
		
		   if(v!=null) {
			encontrado=true;
			  String query = "<Vehiculos> {for $a in /Vehiculos/Coche[@id = " + id + "] return $a } </Vehiculos>";
			String datosConsulta = new XQuery(query).execute(context);
			// TODO Auto-generated method stub
			SAXBuilder saxBuilder = new SAXBuilder();
			InputStream fichero = new ByteArrayInputStream(datosConsulta.getBytes());
			Document document = saxBuilder.build(fichero);
			Element classElement = document.getRootElement();

			List<Element> listaNodos = classElement.getChildren();
			for (int temp = 0; temp < listaNodos.size(); temp++) {
				Element elemento = listaNodos.get(temp);
			
				System.out.println("-------- VEHICULO " + (temp+1) + "--------");
				
				System.out.println("ID :"+elemento.getAttributeValue("id"));
				System.out.println("MARCA :"+elemento.getChild("Marca").getText());
				System.out.println("MODELO :"+elemento.getChild("Modelo").getText());
				System.out.println("MATRICULA :"+elemento.getChild("Matricula").getText());

				
				System.out.println("-------------------\n ");			
				
			}
			
		}
		   
		 if(!encontrado) {
			 System.out.println("Vehiculo no encontrado");
		 }
				
			
	}

	@Override
	public void guardar() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public HashMap<Integer, Vehiculos> capturar() {
	    HashMap<Integer, Vehiculos> aux = new HashMap<>();
	    try {
	        new Open(nombre).execute(context); 
	        
	        String query =  "<Vehiculos>{" + 
	                "for $c in /Vehiculos/Coche return $c" + 
	                "}</Vehiculos>"; 
	        String datosConsulta = new XQuery(query).execute(context);
	        
	        // Parsear los datos XML obtenidos
	        SAXBuilder saxBuilder = new SAXBuilder();
	        InputStream fichero = new ByteArrayInputStream(datosConsulta.getBytes());
	        Document document = saxBuilder.build(fichero);
	        Element rootElement = document.getRootElement();
	        
	        List<Element> listaNodos = rootElement.getChildren();
	        for (Element elemento : listaNodos) {
	            int id = Integer.parseInt(elemento.getAttributeValue("id"));
	            String marca = elemento.getChildText("Marca");
	            String modelo = elemento.getChildText("Modelo");
	            String matricula = elemento.getChildText("Matricula");

	            // Crear objeto Vehiculos y almacenarlo en el HashMap
	            Vehiculos vehiculo = new Vehiculos(marca, modelo, matricula, id);
	            aux.put(id, vehiculo);
	        }
	        
	    } catch (Exception e) {
	        System.err.println("Error al capturar datos de la base de datos: " + e.getMessage());
	    }
	    return aux;
	}
	public void cerrar() {
	context.close();
	}


	
}
