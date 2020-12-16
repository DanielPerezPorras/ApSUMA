package gui;

import modelo.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ControladorPrincipalTutor implements ActionListener, ListSelectionListener, WindowListener {

    private final VistaPrincipalTutor vista;
	private boolean accedeEvento = false;

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
            case "ENTRAR EVENTO":
            	Evento ev = vista.getEventoSeleccionado();
                if (vista.getEventoSeleccionado() != null) {
                    int dialogResult = JOptionPane.showConfirmDialog(null,"¿Quieres inscribirte en este evento?");
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        accedeEvento = true;
                        vista.dispose();
                        accedeEvento = true;
                        ev.abrirEvento();

                    }

                }
                break;
            case "ABRIR":
                Usuario usuario = Sesion.getUsuarioLogueado();
                if (usuario instanceof Tutor){
                    VistaPerfilTutor.abrirVentana(); //TODO crear ventana perfil
                } else if (usuario instanceof Colaborador){
                    VistaPerfilColaborador.abrirVentana();
                }
                break;
            default:
                vista.cargarEventos();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {

        }
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent arg0) {
        if (Sesion.logueadoComoAdmin() && !accedeEvento ) {
            VistaPrincipalAdmin.abrirVentana();
        }

    }

    @Override
    public void windowClosing(WindowEvent arg0) {


    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

}
