package gui;

import javax.swing.*;

public class VistaPrincipalEstudiante extends JFrame {



    public static void abrirVentana() {
        try {
            VistaPrincipalEstudiante frame = new VistaPrincipalEstudiante();
            //frame.controlador(new ControladorPrincipalEstudiante(frame));
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
