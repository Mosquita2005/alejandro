package AccesoDatos;
import java.io.File;



import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import Interfaz.Interfaz;
import modelo.Vehiculos;

public class XML implements Interfaz {
	
	private HashMap <Integer,Vehiculos>mapaxml= new HashMap<>();
	private File Xml= new File("ficheros/documento.xml");
	
	
	
	public XML() {
		
		mapaxml=capturar();
	}
	
	@Override
	public void mostrar() throws IOException{
		
		try {
			SAXBuilder sb = new SAXBuilder();
        	Document doc= sb.build(Xml);
			Element elemento = doc.getRootElement();
			
			List<Element>lista=elemento.getChildren();
			System.out.println("-----------------------");
			for(int i=0; i<lista.size(); i++) {
				Element e= lista.get(i);
				System.out.println("Raiz-> "+e.getName());
				System.out.println("Id-> "+e.getAttributeValue("id"));
				System.out.println("Marca-> "+e.getChild("Marca").getText());
				System.out.println("Modelo-> "+e.getChild("Modelo").getText());
				System.out.println("Matricula-> "+e.getChild("Matricula").getText());
				System.out.println("\n==========================\n");
				
				
			}
		}catch(Exception e) {
			
		}
		System.out.println(mapaxml);
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
	        mapaxml.put(id, vehiculo);
	        
	        try {
	        	SAXBuilder sb = new SAXBuilder();
	        	Document doc= sb.build(Xml);
	        	String i= String.valueOf(id);
	        	Element raiz= doc.getRootElement();
	        	
	        	Element coche= new Element("Coche");
	        	raiz.addContent(coche);
	        	Element mar= new Element("Marca");
	        	mar.setText(marca);
	        	Element mod= new Element("Modelo");
	        	mod.setText(modelo);
	        	Element mat= new Element("Matricula");
	        	mat.setText(matricula);
	        	
	        	coche.addContent(mar);
	        	coche.addContent(mod);
	        	coche.addContent(mat);
	        	
	        	Attribute atr= new Attribute("id",i);
	        	
	        	coche.setAttribute(atr);
	        	
	        	Format t= Format.getPrettyFormat();
	        	t.setEncoding("gbk");
	        	t.setOmitDeclaration(false);
				XMLOutputter xmlOut = new XMLOutputter(t);
				FileOutputStream fos= new FileOutputStream(Xml);
				xmlOut.output(raiz, fos);
				System.out.println("Vehiculo añadido");
	        	
	        }catch(Exception e) {
	        	System.out.println(e.getMessage());
	        	e.printStackTrace();
	        }
	}

	@Override
	public void modificar() throws IOException{
		 Scanner sc = new Scanner (System.in);

		 boolean encontrado= false;

		 System.out.println("Introduzca el id del vehiculo");

		 int id=sc.nextInt();

		 Vehiculos v;

		 for(Map.Entry<Integer, Vehiculos> x: mapaxml.entrySet()) {

		 v=x.getValue();

			 if(id==x.getKey()) {
			 System.out.println("Introduzca la marca del vehiculo");
		
				 String marca=sc.next();
		
				 System.out.println("Introduzca el modelo del vehiculo");
		
				 String modelo=sc.next();
		
				 System.out.println("Introduzca la matricula del vehiculo");
		
				 String matricula=sc.next();
		
		
			
					 v.setMarca(marca);
			
					 v.setModelo(modelo);
		
					 v.setMatricula(matricula);
			 try {
	
			 SAXBuilder sb= new SAXBuilder();
	
			 Document doc= sb.build(Xml);
	
			 Element raiz= doc.getRootElement();
	
			 List<Element>lista = raiz.getChildren("Coche");
	
			 for(int i=0; i<lista.size();i++) {
	
			 Element a=lista.get(i);
	
			 if(a.getAttributeValue("id").equals(String.valueOf(id))) {
	
				 encontrado=true;
				 a.getChild("Marca").setText(v.getMarca());
				 a.getChild("Modelo").setText(v.getModelo());
				 a.getChild("Matricula").setText(v.getMatricula());
		
	
	
				 Format f= Format.getPrettyFormat();
		
				 f.setEncoding("gbk");
		
				 f.setOmitDeclaration(false);
		
		
		
				 XMLOutputter out= new XMLOutputter(f);
		
		
		
		
		
				 out.output(raiz, new FileOutputStream(Xml));
		
				 System.out.println("Vehiculo modificado");
	
	
			 }
	
			 }
			 }catch(Exception e) {
	
	
	
			 }
		}
	}

		 if(!encontrado) {

		 System.out.println("Vehiculo no encontrado");

		 }
		
		
	}

	@Override
	public void borrar() throws IOException{
		boolean encontrado=false;
		Scanner sc= new Scanner(System.in);
		System.out.println("Introduce el  id a borrar");
		int id= sc.nextInt();
		Vehiculos v ;
		for(Map.Entry<Integer, Vehiculos> x: mapaxml.entrySet()) {
			v=x.getValue();
			if(id==x.getKey()) {
				mapaxml.remove(id);
			        try {
			        	SAXBuilder sb = new SAXBuilder();
			        	Document doc= sb.build(Xml);
			        	
			        	Element raiz= doc.getRootElement();
			        	List<Element>lista=raiz.getChildren("Coche");
			        	for(int i= 0; i<lista.size();i++) {
			        		
			        		Element a= lista.get(i);
			        		if(a.getAttributeValue("id").equals(String.valueOf(id))) {
			        			encontrado=true;
			        		
			        			lista.remove(a);
			        			Format t= Format.getPrettyFormat();
					        	t.setEncoding("gbk");
					        	t.setOmitDeclaration(false);
								XMLOutputter xmlOut = new XMLOutputter(t);
								FileOutputStream fos= new FileOutputStream(Xml);
								xmlOut.output(raiz, fos);
								System.out.println("Vehiculo borrado");
			        			
			        			
			        		}
			        	}
			        	
			        	}catch(Exception e) {
			        	System.out.println(e.getMessage());
			        }
			}
			
		}
		
		if(!encontrado) {
			System.out.println("No se encontro el vehiculo");
		}
	}

	@Override
	public void buscarID() throws IOException{
		
		Scanner sc= new Scanner(System.in);
		boolean encontrado=false;
		System.out.println("Introduzca ID para buscar vehiculo");
		int id=sc.nextInt();
		
		for(Map.Entry<Integer, Vehiculos> aux: mapaxml.entrySet()) {
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

	public HashMap<Integer, Vehiculos> getMapaxml() {
		return mapaxml;
	}

	public void setMapaxml(HashMap<Integer, Vehiculos> mapaxml) {
		this.mapaxml = mapaxml;
	}

	
	

	@Override
	public void guardar() throws Exception {
		// TODO Auto-generated method stub
		
		for(Map.Entry<Integer, Vehiculos> x: mapaxml.entrySet()) {
			
			SAXBuilder sb = new SAXBuilder();
        	Document doc= sb.build(Xml);
        	Element raiz= doc.getRootElement();
        	Element coche= new Element("Coche");
        	raiz.addContent(coche);
        	Element marca= new Element("Marca");
        	marca.setText(x.getValue().getMarca());
        	Element matricula= new Element("Matricula");
        	matricula.setText(x.getValue().getMatricula());
        	Element modelo= new Element("Modelo");
        	modelo.setText(x.getValue().getModelo());
        	
        	Attribute atr= new Attribute("id", String.valueOf(x.getValue().getId()));
        	
        	coche.addContent(marca);
        	coche.addContent(matricula);
        	coche.addContent(modelo);
        	coche.setAttribute(atr);
        	Format f= Format.getPrettyFormat();
     		f.setEncoding("gbk");
			f.setOmitDeclaration(false);
			XMLOutputter out= new XMLOutputter(f);
			out.output(raiz, new FileOutputStream(Xml));
			
			System.out.println("Informacion copiada");
		}
	}
	
	
	public HashMap<Integer, Vehiculos> capturar(){
		HashMap<Integer, Vehiculos> aux= new HashMap<Integer, Vehiculos>();
		
		try {
			// Cargamos el arbol XML a partir del contenido del fichero
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc= saxBuilder.build(Xml);
			Element elemento = doc.getRootElement();
			
			List<Element>lista=elemento.getChildren();
			System.out.println(elemento+"\n");
			System.out.println("-----------------------");
			for(int i=0; i<lista.size(); i++) {
				Element e= lista.get(i);
				
				
				String id=e.getAttributeValue("id");
				String marca= e.getChild("Marca").getText();
				String modelo= e.getChild("Modelo").getText();
				String matricula= e.getChild("Matricula").getText();
				Vehiculos v= new Vehiculos(matricula,marca,modelo,Integer.parseInt(id));
				aux.put(Integer.parseInt(id), v);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
		return aux;
	}

}
