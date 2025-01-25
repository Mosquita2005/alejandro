package vista;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.ControladorGrafico;

public class Grafico extends JFrame {
private ControladorGrafico controladorgrafico;
	public Grafico()  {
	
	}

	public void ventana() {
		setTitle("Interfaz Grafica");
		setSize(500,900);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setLocationRelativeTo(null);
	     
	     JPanel panel = new JPanel(new GridLayout(0,1,10,10));

	     JButton mostrar= new JButton("Mostrar");
	     JButton añadir= new JButton("Añadir");
	     JButton eliminar= new JButton("Eliminar");
	     JButton buscarId= new JButton("BuscarId");
	     JButton modificar= new JButton("Modificar");
	     
		panel.add(añadir);
		panel.add(mostrar);
		panel.add(eliminar);
		panel.add(buscarId);
		panel.add(modificar);
		
		add(panel);
		controladorgrafico= new ControladorGrafico();
	
		setVisible(true);
		
		
		
	}
	
}
