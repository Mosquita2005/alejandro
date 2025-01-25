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
private String ruta="ficheros/BaseX.xml";
private String nombre="BaseX";
private HashMap<Integer, Vehiculos> mapabasex= new HashMap<Integer, Vehiculos>();
	public BaseX() {
		
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
		String query ="/Vehiculos";
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
		cerrar();
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
			nuevo.setAttribute(atr);
			
			nmarca.setText(marca);
			nmodelo.setText(modelo);
			nmaatricula.setText(matricula);
			
			nuevo.addContent(nmarca);
			nuevo.addContent(nmodelo);
			nuevo.addContent(nmaatricula);
			XMLOutputter xmlOut = new XMLOutputter();
			String formateado = xmlOut.outputString(nuevo);
			
			String queryInsert = "insert node " + formateado + " into /Vehiculos ";
			
			String datosconsulta= new XQuery(queryInsert).execute(context);
			System.out.println("Vehiculo insertado");
			cerrar();
	}

	@Override
	public void modificar() throws IOException, Exception {
		// TODO Auto-generated method stub
		   new Open(nombre).execute(context);
		   cerrar();
	}

	@Override
	public void borrar() throws IOException, Exception {
		// TODO Auto-generated method stub
		new Open(nombre).execute(context);
		   cerrar();
	}

	@Override
	public void buscarID() throws IOException, Exception {
		// TODO Auto-generated method stub
		new Open(nombre).execute(context);
		   cerrar();
	}

	@Override
	public void guardar() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public HashMap<Integer, Vehiculos> capturar() {
	    HashMap<Integer, Vehiculos> aux = new HashMap<>();
	
	    return aux;
	}
	public void cerrar() {
	context.close();
	}
	
}
