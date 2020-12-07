package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la pantalla de login.
 */
public class ControladorLogin implements ActionListener {

    private final VistaLogin vista;

    public ControladorLogin(VistaLogin vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Lógica de la pantalla de login...
    }

}
