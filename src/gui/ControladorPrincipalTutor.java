package gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipalTutor implements ActionListener, ListSelectionListener {

    private final VistaPrincipalTutor vista;

    public ControladorPrincipalTutor(VistaPrincipalTutor vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
        case "CREAR" : 
        	switch (vista.indiceComboBox()) {
        	case 0 : VistaNuevoCurso.abrirVentana();
        	break;
        	case 1 : VistaNuevoActividades.abrirVentana(); 
        	break;
        	case 2 : VistaNuevoConferencia.abrirVentana();
        	break;
        	}
        break;
        default : vista.cargarEventos();;
        
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent evt) {
    	if (!evt.getValueIsAdjusting()) {
			System.out.println("SELECCIONADISIMO!!!");
		}
    }

}
