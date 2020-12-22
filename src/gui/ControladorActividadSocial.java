package gui;

import modelo.Sesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorActividadSocial implements ActionListener, MouseListener {

    private final VistaActividadSocial vista;

    public ControladorActividadSocial(VistaActividadSocial vista) {
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
		if (Sesion.logueadoComoAdmin()) {
			VistaPrincipalAdmin.abrirVentana();
		} else {
			VistaPrincipal.abrirVentana();
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
