package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import modelo.BD;

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
				vista.anyadirTexto(listaNombres.toArray(new String[0]));
				break;
			case "PENALIZAR":
				break;
			case "ELIMINAR":
				
				break;
		}
		
	}

}
