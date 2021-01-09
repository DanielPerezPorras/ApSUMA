package gui;

import gui.contenido.DialogoNuevoTexto;
import modelo.Sesion;
import modelo.contenido.Contenido;
import modelo.contenido.ContenidoTexto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorEvento implements ActionListener, MouseListener {

    private final VistaEvento vista;

    public ControladorEvento(VistaEvento vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	switch (e.getActionCommand()) {
    		case "VOLVER":
    			vista.dispose();
    			abreventana();
    			break;

			case "ANULAR":
				if (vista.getSoyCreadorEvento()) {
					vista.alternarModoEdicion();
				} else {
					Sesion.getUsuarioLogueado().desapuntarseEvento(vista.getEvento());
					vista.dispose();
					abreventana();
				}
				break;

			case "ELIMINAR":
				vista.getEvento().eliminar();
				vista.dispose();
				abreventana();
				break;

			case "NUEVO CONTENIDO":
				switch (vista.getSeleccionNuevoContenido()) {

					case "Texto":
						DialogoNuevoTexto dialogo = new DialogoNuevoTexto(vista);
						dialogo.setVisible(true);
						if (dialogo.seHaConfirmado()) {
							new ContenidoTexto(vista.getEvento(), dialogo.getTexto());
							vista.refrescarContenido();
						}
						break;

				}
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
