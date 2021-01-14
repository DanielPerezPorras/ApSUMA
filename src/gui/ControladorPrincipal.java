package gui;

import modelo.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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
                    if (inscrito(ev)){
                        VistaEvento.abrirVentana(ev);
                        vista.dispose();
                    } else {
                        int dialogResult = JOptionPane.showConfirmDialog(vista,"¿Quieres inscribirte en este evento?");
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            accedeEvento = true;
                            Sesion.getUsuarioLogueado().apuntarseEvento(ev);
                            vista.dispose();
                            VistaEvento.abrirVentana(ev);
                        }
                    }
                }
                break;

            case "BUSCAR":
                String busqueda = vista.getBusqueda();

                List<String> listaNombres = new ArrayList<String>();
                BD bd = new BD();
                List<Object[]> listaResultado = bd.Select("SELECT * FROM Usuario WHERE nombreUsuario LIKE '%" + busqueda + "%' AND correo != '" + Sesion.getUsuarioLogueado().getCorreo() + "';");
                for (Object[] objects : listaResultado)
                {
                    listaNombres.add((String)objects[0]);
                }
                if(listaResultado.size() < 1) {
                    JOptionPane.showMessageDialog(vista, new Exception("Lo siento, no hay usuarios con ese nombre"),
                            "No hay usuarios con ese nombre", JOptionPane.ERROR_MESSAGE);
                }else
                {
                    vista.anyadirBusqueda(listaNombres.toArray(new String[0]));
                }
                break;

            case "ACTUALIZAR":
                if (Sesion.getUsuarioLogueado().hayMensajesNuevos()){
                    vista.cargarBuzon();
                }
                break;

            case "ENVIAR":
                String receptor = vista.getReceptor();
                String asunto = vista.getAsunto();
                String contenido = vista.getContenido();

                if (receptor == null || asunto == null || contenido == null){
                    JOptionPane.showMessageDialog(new JFrame(), "Error a la hora de enviar el mensaje, campos incompletos.", "Error!?",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    MensajeDirecto mensaje = new MensajeDirecto(Sesion.getUsuarioLogueado().getCorreo(), receptor, contenido, asunto);
                    JOptionPane.showMessageDialog(Sesion.getVistaPrincipal(), "¡Mensaje enviado con éxito!");
                }
                vista.limpiarEnviar();
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
        if (vista.getTabbedPane() == 0){
            System.out.println("maincra");
            if (!e.getValueIsAdjusting() && !ejecutandoListListener) {
                ejecutandoListListener = true;

                vista.setUltimaListaSeleccionada(e);
                vista.setTextoDescripcion(vista.getEventoSeleccionado());

                ejecutandoListListener = false;
            }
        } else {
            System.out.println("bruh");
            vista.cargarMensaje(vista.getSelectedIndex());
        }
    }

}
