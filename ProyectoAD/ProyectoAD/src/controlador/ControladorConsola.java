package controlador;
import java.util.ArrayList;


import java.util.HashMap;
import java.util.Scanner;

import AccesoDatos.*;
import modelo.Vehiculos;
import vista.Consola;
public class ControladorConsola {
	   	private BBDD bd;
	    private Binarios binarios;
	    private Ficheros fichero;
	    private XML xml;
	    private Consola consola;
	    private Hibernate hb;
	    private SQLite sqlite;
	    private PHP php;
	    private DBO dbo;
	    private BaseX basex;
	    private Mongo mongo;
	    public ControladorConsola() {
	    	this.bd=new BBDD();
	    	this.binarios=new Binarios();
	    	this.fichero= new Ficheros();
	    	this.xml= new XML();
	    	this.hb= new Hibernate();
	    	this.consola=new Consola();
	    	this.sqlite=new SQLite();
	    	this.php= new PHP();
	    	this.dbo=new DBO();
	    	this.basex=new BaseX();
	    	this.mongo= new Mongo();
	    }
	    
	   



		public void iniciarconsola() {
	        int opc1;
	        do {
	            opc1 = consola.mostrarMenuSecundario();
	            switch (opc1) {
	                case 1 -> FicherosManager();
	                case 2 -> BinariosManager();
	                case 3 -> XMLManager();
	                case 4 -> BaseDatosManager();
	                case 5 -> HibernateManager();
	                case 6 -> SqliteManager();
	                case 7 -> PhpManager();
	                case 8 -> DboManager();
	                case 9 -> BasexManager();
	                case 10 -> MongoManager();
	                case 11 -> transferenciadatos();
	                case 12 -> consola.mostrarMensaje("Has salido del menu principal");




	                
	            }
	        } while (opc1 != 12);
	    }

	  private void FicherosManager() {
		  int opc2;
		  try {
			  do {
				  opc2=consola.mostrarMenuPrincipal();
				  switch(opc2) {
				  case 1->fichero.mostrar();
				  case 2->fichero.añadir();
				  case 3->fichero.modificar();
				  case 4->fichero.borrar();
				  case 5->fichero.buscarID();
				  case 6->consola.mostrarMensaje("Has salido del menu secundario");

				  
				  
				  }

			  }while(opc2!=6);
			  
		  }catch(Exception e) {
			  
		  }
	  }
	  
	  private void BinariosManager() {
		  int opc2;

		  try {
			  do {
				  opc2=consola.mostrarMenuPrincipal();
				  switch(opc2) {
				  case 1->binarios.mostrar();
				  case 2->binarios.añadir();
				  case 3->binarios.modificar();
				  case 4->binarios.borrar();
				  case 5->binarios.buscarID();
				  case 6->consola.mostrarMensaje("Has salido del menu secundario");

				  
				  
				  }

			  }while(opc2!=6);
		  }catch(Exception e) {
			  
		  }
	  }
	  
	  private void XMLManager() {
		  int opc2;

		  try {
			  do {
				  opc2=consola.mostrarMenuPrincipal();
				  switch(opc2) {
				  case 1->xml.mostrar();
				  case 2->xml.añadir();
				  case 3->xml.modificar();
				  case 4->xml.borrar();
				  case 5->xml.buscarID();
				  case 6->consola.mostrarMensaje("Has salido del menu secundario");

				  
				  
				  }

			  }while(opc2!=6);
		  }catch(Exception e) {
			  
		  }
	  }
	  private void BaseDatosManager() {
		  int opc2;

		  try {
			  do {
				  opc2=consola.mostrarMenuPrincipal();
				  switch(opc2) {
				  case 1->bd.mostrar();
				  case 2->bd.añadir();
				  case 3->bd.modificar();
				  case 4->bd.borrar();
				  case 5->bd.buscarID();
				  case 6->consola.mostrarMensaje("Has salido del menu secundario");

				  
				  
				  }

			  }while(opc2!=6);
		  }catch(Exception e) {
			  
		  }
	  }
	  
	  private void HibernateManager() {
		  int opc2;

		  try {
			  do {
				  opc2=consola.mostrarMenuPrincipal();
				  switch(opc2) {
				  case 1->hb.mostrar();
				  case 2->hb.añadir();
				  case 3->hb.modificar();
				  case 4->hb.borrar();
				  case 5->hb.buscarID();
				  case 6->consola.mostrarMensaje("Has salido del menu secundario");

				  
				  
				  }

			  }while(opc2!=6);
		  }catch(Exception e) {
			  
		  }
	  }
	  
	  private void SqliteManager() {
		  int opc2;

		  try {
			  do {
				  opc2=consola.mostrarMenuPrincipal();
				  switch(opc2) {
				  case 1->sqlite.mostrar();
				  case 2->sqlite.añadir();
				  case 3->sqlite.modificar();
				  case 4->sqlite.borrar();
				  case 5->sqlite.buscarID();
				  case 6->consola.mostrarMensaje("Has salido del menu secundario");

				  
				  
				  }

			  }while(opc2!=6);
		  }catch(Exception e) {
			  
		  }
	  }
	  
	  private void PhpManager() {
		  int opc2;

		  try {
			  do {
				  opc2=consola.mostrarMenuPrincipal();
				  switch(opc2) {
				  case 1->php.mostrar();
				  case 2->php.añadir();
				  case 3->php.modificar();
				  case 4->php.borrar();
				  case 5->php.buscarID();
				  case 6->consola.mostrarMensaje("Has salido del menu secundario");

				  
				  
				  }

			  }while(opc2!=6);
		  }catch(Exception e) {
			  
		  }
	  }
	  
	  private void  DboManager() {
		  int opc2;

		  try {
			  do {
				  opc2=consola.mostrarMenuPrincipal();
				  switch(opc2) {
				  case 1->dbo.mostrar();
				  case 2->dbo.añadir();
				  case 3->dbo.modificar();
				  case 4->dbo.borrar();
				  case 5->dbo.buscarID();
				  case 6->consola.mostrarMensaje("Has salido del menu secundario");

				  
				  
				  }

			  }while(opc2!=6);
		  }catch(Exception e) {
			  
		  }
	  }
	  
	  private void BasexManager() {
		  int opc2;

		  try {
			  do {
				  opc2=consola.mostrarMenuPrincipal();
				  switch(opc2) {
				  case 1->basex.mostrar();
				  case 2->basex.añadir();
				  case 3->basex.modificar();
				  case 4->basex.borrar();
				  case 5->basex.buscarID();
				  case 6->consola.mostrarMensaje("Has salido del menu secundario");

				  
				  
				  }

			  }while(opc2!=6);
			  
		  }catch(Exception e) {
			  
		  }
	  }
	  
	  private void MongoManager() {
		  int opc2;

		  try {
			  do {
				  opc2=consola.mostrarMenuPrincipal();
				  switch(opc2) {
				  case 1->mongo.mostrar();
				  case 2->mongo.añadir();
				  case 3->mongo.modificar();
				  case 4->mongo.borrar();
				  case 5->mongo.buscarID();
				  case 6->consola.mostrarMensaje("Has salido del menu secundario");

				  
				  
				  }

			  }while(opc2!=6);
		  }catch(Exception e) {
			  
		  }
	  }
	    
	    
	    private void transferenciadatos() {
	    	int opc3;
	    	
	    	do {
	    		opc3=consola.mostrarMenuTerciario();
	    		switch(opc3) {
		    		case 1 -> transferirFicheroABinario();
	                case 2 -> transferirFicheroAXML();
	                case 3 -> transferirFicheroABD();
	                case 4 -> transferirFicheroAHibernate();
	                case 5 -> transferirBinarioAFichero();
	                case 6 -> transferirBinarioAXML();
	                case 7 -> transferirBinarioABD();
	                case 8 -> transferirBinarioAHibernate();
	                case 9 -> transferirXMLAFichero();
	                case 10 -> transferirXMLABinario();
	                case 11 -> transferirXMLABD();
	                case 12 -> transferirXMLAHibernate();
	                case 13 -> transferirBDAFichero();
	                case 14 -> transferirBDAFicheroBinario();
	                case 15 -> transferirBDAXML();
	                case 16 -> transferirBDAHibernate();
	                case 17 -> transferirHibernateAFichero();
	                case 18 -> transferirHibernateABinario();
	                case 19 -> transferirHibernateAXML();
	                case 20 -> transferirHibernateABD();
	                case 21 -> consola.mostrarMensaje("Regresando al menu secundario");
	    		}
	    	}while(opc3!=21);
	    }

		private void transferirHibernateABD() {
			// TODO Auto-generated method stub
			
		
			HashMap<Integer, Vehiculos> HB= hb.getMapahb();
			bd.setMapabd(HB);
			try {
				bd.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}

		private void transferirHibernateAXML() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> HB= hb.getMapahb();
			xml.setMapaxml(HB);
			try {
				xml.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
			
		}

		private void transferirHibernateABinario() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> HB= binarios.getMapabinario();
			binarios.setMapabinario(HB);
			try {
				fichero.guardar();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		}

		private void transferirHibernateAFichero() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> HB= fichero.getMapatexto();
			fichero.setMapatexto(HB);
			try {
				fichero.guardar();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		}

		private void transferirBDAHibernate() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> BD= bd.getMapabd();
			hb.setMapahb(BD);
			try {
				hb.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
			
		}

		private void transferirBDAXML() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> BD= bd.getMapabd();
			xml.setMapaxml(BD);
			try {
				xml.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}

		private void transferirBDAFicheroBinario() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> BD= bd.getMapabd();
			binarios.setMapabinario(BD);
			try {
				binarios.guardar();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		}

		private void transferirBDAFichero() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> BD=bd.getMapabd();
			fichero.setMapatexto(BD);
			try {
				fichero.guardar();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		}

		private void transferirXMLAHibernate() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> Xml= xml.getMapaxml();
			hb.setMapahb(Xml);
			try {
				hb.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}

		private void transferirXMLABD() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> Xml= xml.getMapaxml();
			bd.setMapabd(Xml);
			try {
				bd.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}

		private void transferirXMLABinario() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> Xml= xml.getMapaxml();
			binarios.setMapabinario(Xml);
			try {
				binarios.guardar();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		}

		private void transferirXMLAFichero() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> Xml= xml.getMapaxml();
			fichero.setMapatexto(Xml);
			try {
				fichero.guardar();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		}

		private void transferirBinarioAHibernate() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> Bin= binarios.getMapabinario();
			hb.setMapahb(Bin);
			try {
				hb.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
			
		}

		private void transferirBinarioABD() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> Bin= binarios.getMapabinario();
			bd.setMapabd(Bin);
			try {
				bd.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}

		private void transferirBinarioAXML() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> Bin= binarios.getMapabinario();
			xml.setMapaxml(Bin);
			try {
				xml.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}

		private void transferirBinarioAFichero() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> Bin= binarios.getMapabinario();
			fichero.setMapatexto(Bin);
			try {
				fichero.guardar();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}

		private void transferirFicheroAHibernate() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> File= fichero.getMapatexto();
			hb.setMapahb(File);
			try {
				hb.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}

		private void transferirFicheroABD() {
			// TODO Auto-generated method stub
			HashMap<Integer, Vehiculos> File= fichero.getMapatexto();
			bd.setMapabd(File);
			try {
				bd.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		
		}

		private void transferirFicheroAXML() {
			// TODO Auto-generated method stub

			HashMap<Integer, Vehiculos> File= fichero.getMapatexto();
			xml.setMapaxml(File);
			try {
				xml.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}

		private void transferirFicheroABinario() {
			// TODO Auto-generated method stub
			
			HashMap<Integer, Vehiculos> File= fichero.getMapatexto();
			binarios.setMapabinario(File);
			try {
				binarios.guardar();
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
			
		}

	
		
		
		
		
		
		
		
		
		
		
		
	}

