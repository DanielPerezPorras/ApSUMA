package gui;

import modelo.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ControladorPrincipal implements ActionListener, ListSelectionListener, WindowListener {

    private VistaPrincipal vista;
    private boolean accedeEvento = false;
    private boolean ejecutandoListListener = false;

    public ControladorPrincipal(VistaPrincipal vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "PERFIL":
                Usuario us = Sesion.getUsuarioLogueado();
                if (us instanceof Estudiante){
                    VistaPerfilEstudiante.abrirVentana(); //TODO crear ventana perfil
                } else if (us instanceof Tutor){
                    VistaPerfilTutor.abrirVentana();
                } else {
                    VistaPerfilColaborador.abrirVentana();
                }

                break;

            case "ENTRAR":
                Evento ev = vista.getEventoSeleccionado();
                if (vista.getEventoSeleccionado() != null) {
                    if (inscrito(ev) && !Sesion.getUsuarioLogueado().getSancion()){
                        vista.dispose();
                        VistaEvento.abrirVentana(ev);

                    } else if (!Sesion.getUsuarioLogueado().getSancion()){
                        int dialogResult = JOptionPane.showConfirmDialog(vista,"¿Quieres inscribirte en este evento?");
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            accedeEvento = true;
                            Sesion.getUsuarioLogueado().apuntarseEvento(ev);
                            vista.dispose();
                            VistaEvento.abrirVentana(ev);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usted ha sido sancionado recientemente y no puede acceder","Imposible acceder",1);
                    }
                }
                break;

            case "ADMIN":
                Sesion.setPermisos(0);
                vista.dispose();
                break;

            case "CREAR EVENTO":
                String tipo = vista.getTipoNuevoEvento();
                VistaNuevoEvento.abrirVentana(tipo);
                break;
            default:
                vista.cargarEventos();
                vista.alternarCreacion();

        }
    }

    private boolean inscrito(Evento ev) {
        for (Evento evento : Sesion.getUsuarioLogueado().getEventosInscritos()){
            if (ev.getNombre().equals(evento.getNombre())){
                return true;
            }
        }
        return false;
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
        vista.recargaDatos();
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
