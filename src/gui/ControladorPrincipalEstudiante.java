package gui;

import modelo.Evento;
import modelo.Sesion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class ControladorPrincipalEstudiante implements ActionListener, ListSelectionListener, WindowListener {

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
                    int dialogResult = JOptionPane.showConfirmDialog(null,"¿Quieres inscribirte en este evento?");
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        vista.setVisible(false);
                        ev.abrirEvento();

                    }

                }
                break;
            case "ADMIN" : vista.dispose();
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

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
        if (Sesion.logueadoComoAdmin()) {
            VistaPrincipalAdmin.abrirVentana();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {


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
