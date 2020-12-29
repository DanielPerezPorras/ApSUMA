package gui;

import modelo.Curso;
import modelo.Evento;
import modelo.Sesion;
import modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

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
                if (vista.getTipo().equals("Curso") && vista.getDuracion()<=-1) {
                    error = "Duración inválida";
                }
                if (vista.getTipo().equals("Curso") && vista.getClases()<=0) {
                    error = "Num Clases inválido";
                }
                if (!vista.getTipo().equals("Curso") && vista.getDato()=="") {
                    error = "Lugar o enlace vacío";
                }

                if(error == null){
                    JOptionPane.showMessageDialog(vista,
                             "Evento creado con exito",
                            "ÉXITO",
                            JOptionPane.INFORMATION_MESSAGE);
                    String tipo = vista.getTipo();
                    java.sql.Date fecha = new java.sql.Date(Sesion.getVistaPrincipal().getFechaSeleccionada().getTime());

                    Usuario usuarioActual = Sesion.getUsuarioLogueado();
                    switch (tipo) {
                        case "Curso":
                            usuarioActual.crearCurso (fecha, nombre, vista.getClases(), vista.getDuracion());
                            break;
                        case "Conferencia":
                            usuarioActual.crearConferencia(fecha, nombre, vista.getDato());
                            break;
                        case "Actividad":
                            usuarioActual.crearActividad(fecha, nombre, vista.getDato());
                            break;
                    }

                    // Para que no aparezca el mensaje "¿Quiere inscribirse?" al entrar al curso
                    Curso cursoCreado = new Curso(nombre);
                    cursoCreado.inscripcionUsuario(usuarioActual);

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
