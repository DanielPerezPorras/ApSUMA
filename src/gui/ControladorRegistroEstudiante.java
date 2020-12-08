package gui;

import modelo.Estudiante;
import modelo.Sesion;
import modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorRegistroEstudiante implements ActionListener {

    private VistaRegistroEstudiante vista;

    public ControladorRegistroEstudiante(VistaRegistroEstudiante vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "ATRAS":
                vista.dispose();
                VistaTipoRegistro.abrirVentana();
                break;

            case "REGISTRO ESTUDIANTE":

                String error = null;
                String correo = vista.getTextoCorreo();
                String usuario = vista.getTextoUsuario();
                String contrasenia = vista.getTextoContrasenia();
                String confirmaContrasenia = vista.getTextoConfirmaContrasenia();

                // Comprobar:
                // - que el correo no haya sido usado
                // - que las contraseñas sean iguales
                // - que no haya campos vacíos
                if (correo.length() == 0 || usuario.length() == 0
                    || contrasenia.length() == 0 || confirmaContrasenia.length() == 0) {
                    error = "Hay campos vacíos.";
                } else if (!contrasenia.equals(confirmaContrasenia)) {
                    error = "Las contraseñas no coinciden";
                } else if (Usuario.correoEstaUsado(correo)) {
                    error = "Dirección de correo electrónico ya usada.";
                }

                if (error == null) {
                    Usuario nuevoUsuario = new Estudiante(correo, usuario, contrasenia);
                    Sesion.setUsuarioLogueado(nuevoUsuario);
                    System.out.println("Usuario creado: " + correo + "(" + usuario + ")");
                } else {
                    JOptionPane.showMessageDialog(vista,
                            error,
                            "Error registro",
                            JOptionPane.ERROR_MESSAGE);
                }

                break;

        }
    }

}
