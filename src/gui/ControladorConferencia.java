package gui;

import modelo.Sesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorConferencia implements ActionListener, MouseListener {

    private final VistaConferencia vista;

    public ControladorConferencia(VistaConferencia vista) {
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
