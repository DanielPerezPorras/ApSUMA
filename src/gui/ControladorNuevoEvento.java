package gui;

import modelo.Evento;
import modelo.Sesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Controlador para la pantalla de confirmación.*/
public class ControladorNuevoEvento implements ActionListener {

    private final VistaNuevoEvento vista;

    public ControladorNuevoEvento(VistaNuevoEvento vista)
    {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "CREAR":
                String error = null;
                String nombre = vista.getTextoNombre();
                if (nombre.length() == 0){
                    error = "El nombre está vacio.";
                }
                if (!(Evento.buscarEvento(nombre) == null)){
                    error = "Ya existe un evento con ese nombre.";
                }
                if (vista.getDuracion()<=-1) {
                    error = "Duración inválida";
                }
                if (vista.getClases()<=0) {
                    error = "Num Clases inválido";
                }
                if (vista.getDato()==null) {
                    error = "Lugar o enlace vacío";
                }

                if(error == null){
                    JOptionPane.showMessageDialog(vista,
                             "Evento creado con exito",
                            "ÉXITO",
                            JOptionPane.INFORMATION_MESSAGE);
                    String tipo = vista.getTipo();
                    switch (tipo) {
                    case "Curso" : Sesion.getUsuarioLogueado().crearCurso (Sesion.getVistaPrincipal().getFechaSeleccionada(), nombre, vista.getClases(), vista.getDuracion());
                    	break;
                    case "Conferencia" : Sesion.getUsuarioLogueado().crearConferencia(Sesion.getVistaPrincipal().getFechaSeleccionada(), nombre, vista.getDato());
                    	break;
                    case "Actividad" : Sesion.getUsuarioLogueado().crearActividad(Sesion.getVistaPrincipal().getFechaSeleccionada(), nombre, vista.getDato());
                    	break;
                    }

                    Sesion.getVistaPrincipal().cargarEventos();
                    Sesion.getVistaPrincipal().cargarEventosUsuario();
                    vista.dispose();
                }else{
                    JOptionPane.showMessageDialog(vista,
                            error,
                            "Error al crear el evento",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "CANCELAR":
                vista.dispose();
                break;
        }
    }

}
