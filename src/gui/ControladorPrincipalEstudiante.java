package gui;

import modelo.Evento;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipalEstudiante implements ActionListener, ListSelectionListener {

    private final VistaPrincipalEstudiante vista;

    public ControladorPrincipalEstudiante(VistaPrincipalEstudiante vista) {
        this.vista = vista;
        //this.usuario = usuario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {    	
    	switch (e.getActionCommand()) {

    	    case "PERFIL":
                VistaPerfilEstudiante.abrirVentana(); //TODO crear ventana perfil
                break;

            case "ENTRAR":
                Evento ev = vista.getEventoSeleccionado();
                if (vista.getEventoSeleccionado() != null) {
                    vista.dispose();
                    ev.abrirEvento();
                }
                break;

            default:
                vista.cargarEventos();

    	}
     
    }

	@Override
	public void valueChanged(ListSelectionEvent evt) {
		if (!evt.getValueIsAdjusting()) {
			System.out.println("SELECCIONADISIMO!!!");
		}
	}
	
	
	
}
