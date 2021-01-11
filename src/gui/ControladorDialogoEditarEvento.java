package gui;

import modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/* Controlador para la pantalla de confirmación.*/
public class ControladorDialogoEditarEvento implements ActionListener {

    private final DialogoEditarEvento dialogo;

    public ControladorDialogoEditarEvento(DialogoEditarEvento dialogo)
    {
        this.dialogo = dialogo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "CONFIRMAR":
                Evento ev = dialogo.getEvento();
                String error = null;
                String nombre = dialogo.getTextoNombre();
                Date fecha = dialogo.getFecha();
                if (nombre.length() == 0){
                    error = "El nombre está vacio.";
                }
                if (!nombre.equals(ev.getNombre()) && Evento.buscarEvento(nombre) != null){
                    error = "Ya existe un evento con ese nombre.";
                }
                if (fecha == null){
                    error = "Especifique una fecha.";
                }
                if (dialogo.getEvento() instanceof Curso && dialogo.getDuracion() <= -1) {
                    error = "Duración no válida. Introduzca un valor entero positivo.";
                }
                if (dialogo.getEvento() instanceof Curso && dialogo.getClases() <= 0) {
                    error = "Número de clases no válido. Introduzca un valor entero positivo.";
                }
                if (dialogo.getEvento() instanceof ActividadSocial && dialogo.getDato().length() == 0) {
                    error = "Lugar de la actividad vacío.";
                }
                if (dialogo.getEvento() instanceof Conferencia && dialogo.getDato().length() == 0) {
                    error = "Enlace de la conferencia vacío.";
                }

                if (error == null) {
                    dialogo.darConfirmacion();
                    dialogo.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialogo,
                            error,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "CANCELAR":
                dialogo.dispose();
                break;
        }
    }

}
