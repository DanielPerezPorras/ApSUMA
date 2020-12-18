package gui;

import modelo.Evento;
import modelo.Sesion;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ControladorNuevaPrincipal implements ActionListener, ListSelectionListener, WindowListener {

    private VistaNuevaPrincipal vista;
    private boolean accedeEvento = false;
    private boolean ejecutandoListListener = false;

    public ControladorNuevaPrincipal(VistaNuevaPrincipal vista) {
        this.vista = vista;
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
                    int dialogResult = JOptionPane.showConfirmDialog(vista,"¿Quieres inscribirte en este evento?");
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        accedeEvento = true;
                        vista.dispose();
                        accedeEvento = true;
                        ev.abrirEvento();
                    }

                }
                break;

            case "ADMIN":
                vista.dispose();
                break;

            case "CREAR EVENTO":
                String tipo = vista.getTipoNuevoEvento();
                switch (tipo) {

                    case "Curso":
                        VistaNuevoCurso.abrirVentana();
                        break;

                    case "Actividad social":
                        VistaNuevoActividades.abrirVentana();
                        break;

                    case "Conferencia":
                        VistaNuevoConferencia.abrirVentana();
                        break;

                }
                vista.dispose();
                break;

            default:
                vista.cargarEventos();

        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        if (Sesion.logueadoComoAdmin() && !accedeEvento) {
            VistaPrincipalAdmin.abrirVentana();
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Si no pongo el "ejecutandoListListener" tendré una StackOverflowException!
        if (!e.getValueIsAdjusting() && !ejecutandoListListener) {
            ejecutandoListListener = true;

            vista.setUltimaListaSeleccionada(e);
            vista.setTextoDescripcion(vista.getEventoSeleccionado());

            ejecutandoListListener = false;
        }
    }

}
