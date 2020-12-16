package gui;

import modelo.Sesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorConferenciaAlumno implements ActionListener, MouseListener {

    private final VistaConferenciaAlumno vista;

    public ControladorConferenciaAlumno(VistaConferenciaAlumno vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	switch (e.getActionCommand()) {
    	case "VOLVER" : vista.dispose();
    					abreventana();
    					
    					break;
    	}
    }

	private void abreventana() {
		switch (Sesion.getPermisos()) {
		case 0 : VistaPrincipalAdmin.abrirVentana();
				 break;
		case 1 : VistaPrincipalTutor.abrirVentana();
				 break;
		case 2 : VistaPrincipalEstudiante.abrirVentana();
				 break;
		case 3 : VistaPrincipalInvitado.abrirVentana();
				 break;
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
