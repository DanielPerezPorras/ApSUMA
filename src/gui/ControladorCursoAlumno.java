package gui;

import modelo.Sesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorCursoAlumno implements ActionListener, MouseListener {

    private final VistaCursoAlumno vista;

    public ControladorCursoAlumno(VistaCursoAlumno vista) {
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
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
