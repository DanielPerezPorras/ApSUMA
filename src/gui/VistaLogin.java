package gui;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Vista de la pantalla de login.
 */
public class VistaLogin extends JPanel {

    private ActionListener controlador;

    public VistaLogin() {
        add(new JLabel("¡Hola mundo!"));
    }

    public void asignarControlador(ActionListener listener) {
        controlador = listener;
    }

}
