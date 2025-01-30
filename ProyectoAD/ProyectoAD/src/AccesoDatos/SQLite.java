package AccesoDatos;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Interfaz.Interfaz;
import modelo.Vehiculos;

public class SQLite implements Interfaz{
private HashMap<Integer, Vehiculos>mapasqlite=new  HashMap<Integer, Vehiculos>();
	static Connection conn;


	public SQLite() {
		
		
	}


	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		System.err.println("Acceso a sqlite");
		try {
			Class.forName("org.sqlite.JDBC");
			
			conn=DriverManager.getConnection("jdbc:sqlite:coches.db");
		}catch (Exception e) {
			// TODO: handle exception
		}
		mapasqlite=capturar();
	}
	@Override
	public void mostrar() throws IOException, ClassNotFoundException, Exception {
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
	
	}

	@Override
	public void añadir() throws IOException, Exception {
		// TODO Auto-generated method stub
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
	     mapasqlite.put(id, vehiculo);
		String query="Insert into vehiculos (id,marca,modelo,matricula) values ("+id+","+"'"+marca+"'"+","+"'"+modelo+"'"+","+"'"+matricula+"'"+")";
		Statement st =conn.createStatement();
		 st.executeUpdate(query);
		System.out.println("vehiculo añadido a la Base de datos");
		st.close();
	}

	@Override
	public void modificar() throws IOException, Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner (System.in);
		
		System.out.println("Introduzca el ID del vehiculo a modificar ");
		int id= sc.nextInt();
		Vehiculos v;
		boolean encontrado=false;
		for(Map.Entry<Integer, Vehiculos>aux: mapasqlite.entrySet()) {
			v=aux.getValue();
			if(id==aux.getKey()) {
				encontrado= true;
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

	@Override
	public void borrar() throws IOException, Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner (System.in);
		System.out.println("Introduzca el ID del vehiculo a borrar ");
		int id=sc.nextInt();
		boolean encontrado=false;
		
		for(Map.Entry<Integer, Vehiculos>aux: mapasqlite.entrySet()) {
			if(id==aux.getKey()) {
				mapasqlite.remove(id);
				Statement st= conn.createStatement();
				String query="delete from vehiculos where id =" +id;
				st.executeUpdate(query);
				System.out.println("vehiculo borrado");
			}
			
			
		}
		if(!encontrado) {
			
			System.out.println("Vehiculo no encontrado");
		}
	
				
		
	}

	@Override
	public void buscarID() throws IOException, Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner (System.in);
		System.out.println("Introduzca el ID A buscar");
		int idbuscar=sc.nextInt();
		boolean encontrado=false;
		for(Map.Entry<Integer, Vehiculos>aux: mapasqlite.entrySet()) {
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
						st.close();
					}
				}
			
		}
		if(!encontrado) {
			
			System.out.println("Vehiculo no encontrado");
		}
	
		
	}

	@Override
	public void guardar() throws Exception {
		// TODO Auto-generated method stub
		Statement st= conn.createStatement();

		for(Map.Entry<Integer, Vehiculos> x: mapasqlite.entrySet()) {
		
			st.executeUpdate("Insert into vehiculos (id,marca,modelo,matricula) values("+x.getValue().getId()+",'"+x.getValue().getMarca()+"',"+"'"+x.getValue().getModelo()+"',"+"'"+x.getValue().getMatricula()+"')");
		
			System.out.println("Informacion copiada");
		}
		st.close();
		
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
			Vehiculos v= new Vehiculos(modelo,marca,matricula,id);
					aux.put(id, v);
					
				}
				st.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}

		return aux;
	}
	
	public void cerrar() {
		// TODO Auto-generated method stub
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Vehiculos> getMapasqlite() {
	return mapasqlite;
	}

	public void setMapasqlite(HashMap<Integer, Vehiculos> mapasqlite) {

	this.mapasqlite = mapasqlite;
}

}
