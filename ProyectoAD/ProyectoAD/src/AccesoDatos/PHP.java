    package AccesoDatos;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.mysql.cj.xdevapi.JsonValue;

import auxiliar.ApiRequests;
import Interfaz.Interfaz;
import modelo.Vehiculos;

public class PHP  implements Interfaz{
	auxiliar.ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_CAR, ADD_CAR , SET_CAR, DEL_CAR, GET_CARID; 
	private HashMap<Integer, Vehiculos> mapaphp= new HashMap<>();
	
	public PHP() {
	

	}
	
	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		System.err.println("Acceso a php");
		encargadoPeticiones = new auxiliar.ApiRequests();
		SERVER_PATH = "http://localhost/alejandro/VehiculosJSONServer/";
		GET_CAR = "leervehiculos.php";
		ADD_CAR="añadirvehiculos.php";
		SET_CAR= "modificarvehiculo.php";
		DEL_CAR= "eliminarvehiculo.php";
		GET_CARID="leervehiculosid.php";
		mapaphp= capturar();
	
	}


	public void mostrar() throws IOException, ClassNotFoundException, Exception {
		// TODO Auto-generated method stub
	
		try {
			String url=  SERVER_PATH + GET_CAR;
			
			String response = encargadoPeticiones.getRequest(url);
			JSONObject respuesta= (JSONObject) JSONValue.parse(response.toString());
			if (respuesta == null) { 
		
			System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
			} else { 
				String estado = (String) respuesta.get("estado"); 
			if (estado.equals("ok")) { 
			JSONArray array = (JSONArray) respuesta.get("jugadores");
			
			if (array.size() > 0) {
			
			// Declaramos variables
			Vehiculos v;
			String marca;
			String modelo;
			String matricula;
			int id;

		for (int i = 0; i < array.size(); i++) {
			JSONObject row = (JSONObject) array.get(i);
		
			 marca= row.get("marca").toString();
			modelo = row.get("modelo").toString();
			matricula =row.get("matricula").toString();
			id = Integer.parseInt(row.get("id").toString());
		
		
		
			
		}
		for(Map.Entry<Integer, Vehiculos> aux:mapaphp.entrySet()) {
			System.out.println(aux.getValue()+"\n");
			
		}
		
		System.out.println(respuesta);
		
		
		
		
		
		System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
		System.out.println();
		
		} 
			else { // El array de jugadores est� vac�o
				System.out.println("Acceso JSON Remoto - No hay datos que tratar");
				System.out.println();
			}	
		
			} else { // Hemos recibido el json pero en el estado se nos
				// indica que ha habido alg�n error
			
			System.out.println("Ha ocurrido un error en la busqueda de datos");
			System.out.println("Error: " + (String) respuesta.get("error"));
			System.out.println("Consulta: " + (String) respuesta.get("query"));
			
		
		
		}
		}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ha ocurrido un error en la busqueda de datos");
		}
		
		
	}

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
        
        
        try {
        	
        	
        	Vehiculos	v = new Vehiculos(marca,modelo,matricula,id);
        	JSONObject objVehiculo = new JSONObject();
			JSONObject objPeticion = new JSONObject();
			objVehiculo.put("marca", v.getMarca());
			objVehiculo.put("matricula", v.getMatricula());
			objVehiculo.put("modelo", v.getModelo());
			objVehiculo.put("id", v.getId());
			
			objPeticion.put("peticion","add");
        	objPeticion.put("añadirvehiculo",objVehiculo);
        	

			String json = objPeticion.toJSONString();

        	String url= SERVER_PATH + ADD_CAR;
        	
        	
        	String response= encargadoPeticiones.postRequest(url, json);
        	
        	JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
        	
        	if (respuesta == null) { 
				
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
				System.exit(-1);
				} 	else { 
				
				String estado = (String) respuesta.get("estado"); 
						if (estado.equals("ok")) {
				
				System.out.println("Almacenado jugador enviado por JSON Remoto");
				
				} else { 
					
				System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
				System.out.println("Error: " + (String) respuesta.get("error"));
				System.out.println("Consulta: " + (String) respuesta.get("query"));
				
				System.exit(-1);
				
				}
				}
        }catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
        
	}

	public void modificar() throws IOException, Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner (System.in);
		boolean encontrado=false;
		System.out.println("Introduzca el ID del vehiculo a modificar");
		int id= sc.nextInt();
		Vehiculos v;
		for(Map.Entry<Integer, Vehiculos> aux: mapaphp.entrySet()) {
			v=aux.getValue();
			
			if(id==aux.getKey()) {
				encontrado=true;
				  try {
			        	
					  System.out.println("Introduzca la marca del vehiculo");
					  String marca = sc.next();
					  System.out.println("Introduzca el modelo del vehiculo");
					  String modelo = sc.next();
					  System.out.println("Introduzca la matricula del vehiculo");
					  String matricula = sc.next();
			           v.setMarca(marca);
			           v.setModelo(modelo);
			           v.setMatricula(matricula);
					  
			        	JSONObject objVehiculo = new JSONObject();
						JSONObject objPeticion = new JSONObject();
						objVehiculo.put("marca", v.getMarca());
						objVehiculo.put("matricula", v.getMatricula());
						objVehiculo.put("modelo", v.getModelo());
						objVehiculo.put("id", v.getId());
						
						objPeticion.put("peticion","update");
			        	objPeticion.put("modificarvehiculo",objVehiculo);
			        	

						String json = objPeticion.toJSONString();

			        	String url= SERVER_PATH + SET_CAR;
			        	
			        	
			        	String response= encargadoPeticiones.putRequest(url, json);
			        	
			        	JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
			        	
			        	if (respuesta == null) { 
							
							System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
							} 	else { 
							
							String estado = (String) respuesta.get("estado"); 
									if (estado.equals("ok")) {
							
							System.out.println("Vehiculo Modificado ");
							
							} else { 
								
							System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
							System.out.println("Error: " + (String) respuesta.get("error"));
							System.out.println("Consulta: " + (String) respuesta.get("query"));
							
							
							}
							}
			        }catch (Exception e) {
						System.out.println(e.getMessage());
					}
				
			}
		}
		
		if(!encontrado) {
			
			System.out.println("Vehiculo no encontrado");
		}
	}

	public void borrar() throws IOException, Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner (System.in);
		boolean encontrado=false;
		System.out.println("Introduzca el ID del vehiculo a borrar");
		int id= sc.nextInt();
		Vehiculos v;
		for(Map.Entry<Integer, Vehiculos> aux: mapaphp.entrySet()) {
			v=aux.getValue();
			
			if(id==aux.getKey()) {
				encontrado=true;
				mapaphp.remove(id);
				  try {
			        
			         
					  
			        	JSONObject objVehiculo = new JSONObject();
						JSONObject objPeticion = new JSONObject();
						objVehiculo.put("marca", v.getMarca());
						objVehiculo.put("matricula", v.getMatricula());
						objVehiculo.put("modelo", v.getModelo());
						objVehiculo.put("id", v.getId());
						
						objPeticion.put("peticion","delete");
			        	objPeticion.put("eliminarvehiculo",objVehiculo);
			        	

						String json = objPeticion.toJSONString();

			        	String url= SERVER_PATH + DEL_CAR;
			        	
			        	
			        	String response= encargadoPeticiones.postRequest(url, json);
			        	
			        	JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
			        	
			        	if (respuesta == null) { 
							
							System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
							} 	else { 
							
							String estado = (String) respuesta.get("estado"); 
									if (estado.equals("ok")) {
							
							System.out.println("Vehiculo borrado");
							
							} else { 
								
							System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
							System.out.println("Error: " + (String) respuesta.get("error"));
							System.out.println("Consulta: " + (String) respuesta.get("query"));
							
							
							}
							}
			        }catch (Exception e) {
						System.out.println(e.getMessage());
					}
				
			}
		}
		
		if(!encontrado) {
			
			System.out.println("Vehiculo no encontrado");
		}
	}

	public void buscarID() throws IOException, Exception {
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);

			System.out.println("Introduzca el id del vehiculo que quieres ver");
			int idn= sc.nextInt();
			
			for(Map.Entry<Integer, Vehiculos> map: mapaphp.entrySet()) {
				
				if(idn==map.getKey()) {
					try {
						String url=  SERVER_PATH + GET_CARID +"?id=" + idn;
						
						String response = encargadoPeticiones.getRequest(url);
						JSONObject respuesta= (JSONObject) JSONValue.parse(response.toString());
						if (respuesta == null) { 
					
						System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");

						} else { 
							String estado = (String) respuesta.get("estado"); 
						if (estado.equals("ok")) { 
						JSONArray array = (JSONArray) respuesta.get("vehiculos");
						
						if (array.size() > 0) {
						
						// Declaramos variables
						Vehiculos v;
						String marca;
						String modelo;
						String matricula;
						int id;

					for (int i = 0; i < array.size(); i++) {
						JSONObject row = (JSONObject) array.get(0);
					
						 marca= row.get("marca").toString();
						modelo = row.get("modelo").toString();
						matricula =row.get("matricula").toString();
						id = Integer.parseInt(row.get("id").toString());
					
						v = new Vehiculos(marca,modelo,matricula,id);
					
						mapaphp.put(id, v);
						
					}
					for(Map.Entry<Integer, Vehiculos> aux:mapaphp.entrySet()) {
						System.out.println(aux.getValue()+"\n");
						
					}
					
					System.out.println(respuesta);
					
					
					
					
					
					System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
					System.out.println();
					
					} 
						else { // El array de jugadores est� vac�o
							System.out.println("Acceso JSON Remoto - No hay datos que tratar");
							System.out.println();
						}	
					
						} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido alg�n error
						
						System.out.println("Ha ocurrido un error en la busqueda de datos");
						System.out.println("Error: " + (String) respuesta.get("error"));
						System.out.println("Consulta: " + (String) respuesta.get("query"));
						
					
					
					}
					}
						
					}catch (Exception e) {
						// TODO: handle exception
						System.out.println("Ha ocurrido un error en la busqueda de datos");
					}
				}
				}
				
	}

	public void guardar() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public HashMap<Integer, Vehiculos> capturar(){
		
		HashMap<Integer, Vehiculos> aux= new HashMap<Integer, Vehiculos>();
		
		
		try {

			System.out.println("---------- Leemos datos de JSON --------------------");

			System.out.println("Lanzamos peticion JSON para jugadores");

			String url = SERVER_PATH + GET_CAR; // Sacadas de configuracion

			System.out.println("La url a la que lanzamos la petici�n es " +  url); // Traza para pruebas

			String response = encargadoPeticiones.getRequest(url);

//			System.out.println(response); // Traza para pruebas
//
//			System.exit(-1);
			
			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay alg�n error de parseo (json
										// incorrecto porque hay alg�n caracter
										// raro, etc.) la respuesta ser� null
				System.out.println("El json recibido no es correcto. Finaliza la ejecuci�n");
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay alg�n problema
				String estado = (String) respuesta.get("estado"); 
				// Si ok, obtenemos array de jugadores para recorrer y generar hashmap
				if (estado.equals("ok")) { 
					JSONArray array = (JSONArray) respuesta.get("jugadores");

					if (array.size() > 0) {

						// Declaramos variables
						
						String matricula;
						String marca;
						String modelo;
						int id;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);

							matricula = row.get("matricula").toString();
							marca = row.get("marca").toString();
							id = Integer.parseInt(row.get("id").toString());
							modelo= row.get("modelo").toString();
							Vehiculos v= new Vehiculos(marca,modelo,matricula,id);

							aux.put(id, v);
						}

						System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
						System.out.println();

					} else { // El array de jugadores est� vac�o
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido alg�n error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));


				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

		}
		return aux;
		
		
	}

	
	


}
