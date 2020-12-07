package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Vista del proyecto sobre la cual se mostrarán las diferentes pantallas.
 * Mediante un CardLayout alternamos entre las diferentes pantallas, que son
 * diferentes vistas.
 */
public class VistaBase extends JPanel {

    private static final String PANTALLA_LOGIN = "PANTALLA_LOGIN";

    private final CardLayout layout;

    public VistaBase() {
        layout = new CardLayout();
        setLayout(layout);
        aniadirPantallas();
    }

    private void aniadirPantallas() {

        // Añadimos la pantalla de login
        VistaLogin vistaLogin = new VistaLogin();
        ControladorLogin ctrlLogin = new ControladorLogin(vistaLogin);
        vistaLogin.asignarControlador(ctrlLogin);
        add(vistaLogin, PANTALLA_LOGIN);

    }

}
