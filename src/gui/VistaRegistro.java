package gui;

import javax.swing.*;

public class VistaRegistro extends JFrame {

    private ControladorRegistro controlador;

    public static void abrirVentana() {
        try {
            VistaRegistro frame = new VistaRegistro();
            frame.controlador(new ControladorRegistro(frame));
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private VistaRegistro() {
        controlador = null;
    }

    public void controlador(ControladorRegistro c) {
        controlador = c;
    }

}
