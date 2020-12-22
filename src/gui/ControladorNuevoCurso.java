package gui;

import modelo.Evento;
import modelo.Sesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/* Controlador para la pantalla de confirmación.*/
public class ControladorNuevoCurso implements ActionListener {

    private final VistaNuevoCurso vista;

    public ControladorNuevoCurso(VistaNuevoCurso vista)
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

                if(error == null){
                    JOptionPane.showMessageDialog(vista,
                            "Curso creado con exito",
                            "ÉXITO",
                            JOptionPane.INFORMATION_MESSAGE);
                    Sesion.getUsuarioLogueado().crearCurso(java.sql.Date.valueOf("2020-12-18"), nombre, 3, 4);
                    Sesion.getVistaPrincipal().cargarEventos();
                    Sesion.getVistaPrincipal().cargarEventosUsuario();
                    vista.dispose();
                }else{
                    JOptionPane.showMessageDialog(vista,
                            error,
                            "Error al crear el curso",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "CANCELAR":
                vista.dispose();
                break;
        }
    }

}
