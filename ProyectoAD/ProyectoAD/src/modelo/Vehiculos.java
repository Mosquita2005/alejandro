package modelo;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Vehiculos implements Serializable{
/**
	 * 
	 */

@Id
private int id;
private static final long serialVersionUID = 1L;
private String marca;
private String modelo;
private String matricula;




public Vehiculos(String marca, String modelo, String matricula, int id) {
	this.marca = marca;
	this.modelo = modelo;
	this.matricula = matricula;
	this.id = id;
}
public Vehiculos() {

	
	
	
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMarca() {
	return marca;
}
public void setMarca(String marca) {
	this.marca = marca;
}
public String getModelo() {
	return modelo;
}
public void setModelo(String modelo) {
	this.modelo = modelo;
}
public String getMatricula() {
	return matricula;
}
public void setMatricula(String matricula) {
	this.matricula = matricula;
}
@Override
public String toString() {
	return "MARCA->"+marca+"\n"+"MATRICULA->"+matricula+"\n"+"MODELO->"+modelo+"\n"+"ID->"+id+"\n";
}












}
