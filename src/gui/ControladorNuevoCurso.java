package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

                if(error == null){
                    JOptionPane.showMessageDialog(vista,
                            "Curso creado con exito",
                            "ÉXITO",
                            JOptionPane.INFORMATION_MESSAGE);
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
