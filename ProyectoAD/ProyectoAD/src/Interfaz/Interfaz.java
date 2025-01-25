package Interfaz;
import java.io.File;

import java.io.IOException;

public interface Interfaz {
	
	public void mostrar() throws IOException ,ClassNotFoundException, Exception;
	public void añadir() throws IOException, Exception;
	public void modificar()throws IOException, Exception;
	public void borrar()throws IOException, Exception;
	public void buscarID() throws IOException, Exception;
	public void guardar() throws Exception;

}
