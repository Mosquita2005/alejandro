package AccesoDatos;
import java.sql.Connection;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.TypedQuery;

import Interfaz.Interfaz;
import modelo.Vehiculos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BBDD implements Interfaz {
	Connection conn;
	private HashMap<Integer,Vehiculos>mapabd=new HashMap<>();
	private static final String driver="com.mysql.cj.jdbc.Driver";
	private static final String nombre="coches";
	private static final String host="localhost";
	private static final String  puerto="3306";
	private static final String url="jdbc:mysql://" + host + ":" + puerto + "/" + nombre;
	private static final String user="root";
	private static final String password="";
	private static boolean printed=false;
	private Vehiculos v;
	public BBDD() {
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if(!printed) {
				System.out.println(url);
				printed=true;
			}
			
			mapabd=capturar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	public void añadir() throws Exception {
	Scanner sc= new Scanner(System.in);
	 System.out.println("Introduzca la marca del vehiculo");
     String marca = sc.next();
     System.out.println("Introduzca el modelo del vehiculo");
     String modelo = sc.next();
     System.out.println("Introduzca la matricula del vehiculo");
     String matricula = sc.next();
     System.out.println("Introduzca el id del vehiculo");
     int id = sc.nextInt();
     
     Vehiculos vehiculo = new Vehiculos(marca, modelo, matricula, id);
     mapabd.put(id, vehiculo);
	String query="Insert into vehiculos (id,Marca,Modelo,Matricula) values ("+id+","+"'"+marca+"'"+","+"'"+modelo+"'"+","+"'"+matricula+"'"+")";
	Statement st =conn.createStatement();
	 st.executeUpdate(query);
	System.out.println("vehiculo añadido a la Base de datos");
	st.close();
	
		
	}
	
	
	
	public void mostrar() throws Exception {
		
		String query="select * from vehiculos";
		Statement st =conn.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			int id=rs.getInt(1);
			String marca=rs.getString(2);
			String modelo=rs.getString(3);
			String matricula=rs.getString(4);

			System.out.println(id+" | "+ marca+" | "+" | "+ modelo+" | "+ matricula+" | \n" );
		}
		st.close();
		cerrar();
	}
	
	
	
	public void modificar() throws Exception {
		Scanner sc= new Scanner (System.in);
		boolean encontrado=false;
		System.out.println("Introduzca el ID del vehiculo a modificar ");
		int id= sc.nextInt();
		Vehiculos v;
		for(Map.Entry<Integer, Vehiculos> aux: mapabd.entrySet()) {
			v=aux.getValue();
			
			if(id==aux.getKey()) {
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
				Statement st= conn.createStatement();
				String query="update vehiculos set marca ="+"'"+marca+"', modelo="+"'"+modelo+"',matricula="+"'"+matricula+"'"+"where id = "+id;
				st.executeUpdate(query);
			}
		}
		
		if(!encontrado) {
			
			System.out.println("Vehiculo no encontrado");
		}
				
		
	}
	
	public void borrar() throws Exception {
		Scanner sc= new Scanner (System.in);
		boolean encontrado=false;
		System.out.println("Introduzca el ID del vehiculo a borrar ");
		int id=sc.nextInt();
		for(Map.Entry<Integer, Vehiculos> aux: mapabd.entrySet()) {
			if(id==aux.getKey()) {
				Statement st= conn.createStatement();
				String query="delete from vehiculos where id =" +id;
				encontrado=true;
				st.executeUpdate(query);
				System.out.println("vehiculo borrado");
				
			}
			
		}
	
				
		if(!encontrado) {
			
			System.out.println("Vehiculo no encontrado");
		}
			
		
		
	}
	




	public void buscarID() throws Exception  {
		Scanner sc= new Scanner (System.in);
		System.out.println("Introduzca el ID A buscar");
		int idbuscar=sc.nextInt();
		boolean encontrado=false;
		
		for(Map.Entry<Integer, Vehiculos> aux: mapabd.entrySet()) {
			
			if(idbuscar==aux.getKey()) {
				encontrado=true;
				String query="select * from vehiculos where id ="+idbuscar;
				Statement st =conn.createStatement();
				ResultSet rs= st.executeQuery(query);
				while(rs.next()) {
					int id=rs.getInt(1);
					String marca=rs.getString(2);
					String modelo=rs.getString(3);
					String matricula=rs.getString(4);

					System.out.println(id+" | "+ marca+" | "+" | "+ modelo+" | "+ matricula+" | \n" );
				}
				st.close();
				
			}
			
		}
		
		if(!encontrado) {
			
			System.out.println("Vehiculo no encontrado");
		}
		
	}
	
	public HashMap<Integer, Vehiculos> getMapabd() {
		return mapabd;
	}






	public void setMapabd(HashMap<Integer, Vehiculos> mapabd) {
		this.mapabd = mapabd;
	}





	public HashMap<Integer, Vehiculos> capturar(){
		HashMap<Integer, Vehiculos> aux= new HashMap<Integer, Vehiculos>();
		// TODO Auto-generated method stub
			try {
				String query="select * from vehiculos";
				Statement st =conn.createStatement();
				ResultSet rs= st.executeQuery(query);
				while(rs.next()) {
					int id=rs.getInt(1);
					String marca=rs.getString(2);
					String modelo=rs.getString(3);
					String matricula=rs.getString(4);
					v= new Vehiculos(modelo,marca,matricula,id);
					aux.put(id, v);
					
				}
				st.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}

		return aux;
	}

	
	






	@Override
	public void guardar() throws Exception {
		
		Statement st= conn.createStatement();

		for(Map.Entry<Integer, Vehiculos> x: mapabd.entrySet()) {
		
			st.executeUpdate("Insert into vehiculos (id,Marca,Modelo,Matricula) values("+x.getValue().getId()+",'"+x.getValue().getMarca()+"',"+"'"+x.getValue().getModelo()+"',"+"'"+x.getValue().getMatricula()+"')");
		
			System.out.println("Informacion copiada");
		}
		st.close();
	}	
	public void cerrar() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
