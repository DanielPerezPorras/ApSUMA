package gui;

import modelo.BD;
import modelo.Sesion;
import modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorPrincipalAdmin implements ActionListener 
{

private final VistaPrincipalAdmin vista;
	
	public ControladorPrincipalAdmin(VistaPrincipalAdmin vista)
	{
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		BD bd = new BD();
		switch(e.getActionCommand())
		{
			case "BUSCAR":
				//Busca los usuarios que coincidan con la cadena de texto pedida
				String busqueda = vista.getTextBoxValue();
				
				List<String> listaNombres = new ArrayList<String>();
				List<Object[]> listaResultado = bd.Select("SELECT * FROM Usuario WHERE nombreUsuario LIKE '%" + busqueda + "%';");
				for (Object[] objects : listaResultado) 
				{
					listaNombres.add((String)objects[0]);
				}
				if(listaResultado.size() < 1)
				{
					JOptionPane.showMessageDialog(vista, new Exception("Lo siento, no hay usuarios con ese nombre"), 
							"No hay usuarios con ese nombre", JOptionPane.ERROR_MESSAGE);
				}else
				{
					vista.anyadirTexto(listaNombres.toArray(new String[0]));
				}
				break;
			case "PENALIZAR":
				break;
			case "ELIMINAR":
				Usuario usu = vista.darUsuario();
				usu.eliminarCuenta();
				vista.limpiar();
				break;
			case "ACTUALIZAR":
				vista.mostrarDatosUsuarioSel();
				break;
			case "CAMBIAR_ROL":
				String vistaRol = vista.getVerComo();
				switch (vistaRol) {
					case "Tutor":
						Sesion.setPermisos(1);
						break;
					case "Usuario":
						Sesion.setPermisos(2);
						break;
					case "Invitado":
						Sesion.setPermisos(3);
						break;
				}
				VistaPrincipal.abrirVentana();
				vista.dispose();
				break;
		}
		
	}
}