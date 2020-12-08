package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la pantalla de login.
 */
public class ControladorLogin implements ActionListener {

    private final VistaLogin vista;

    public ControladorLogin(VistaLogin vista) 
    {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "INICIAR":
                // Comprobar si existe un usuario con el nombre y contraseña dados.
                // Si existe, iniciar sesión. Si no, mostrar un mensaje.
                JOptionPane.showMessageDialog(vista,
                        "Usuario o contraseña incorrectos.",
                        "Error inicio de sesión",
                        JOptionPane.ERROR_MESSAGE);

                break;

            case "REGISTRAR":
                // Ir a la pantalla de Registro.
                break;

            case "INVITADO":
                // Abrir como invitado la pantalla principal
                break;

        }
    }

}
