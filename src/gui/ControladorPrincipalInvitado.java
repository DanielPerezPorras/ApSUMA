package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Evento;
import modelo.Sesion;

public class ControladorPrincipalInvitado implements ActionListener, WindowListener, ListSelectionListener {

    private final VistaPrincipalInvitado vista;
    private boolean accedeEvento = false;

    public ControladorPrincipalInvitado(VistaPrincipalInvitado vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "ENTRAR":
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
            case "ADMIN" : vista.dispose();
                break;
            default: vista.cargarEventos();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            System.out.println("SELECCIONADISIMO!!!");
        }
    }


    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent e) {
        if (Sesion.logueadoComoAdmin()) {
            VistaPrincipalAdmin.abrirVentana();
        }

    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }
}
