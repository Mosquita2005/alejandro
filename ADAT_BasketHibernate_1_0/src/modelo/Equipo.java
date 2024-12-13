package modelo;

import java.util.HashSet;
import java.util.Set;

public class Equipo implements java.io.Serializable {

	private int id;
	private String nombre;

	
	public Equipo(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
