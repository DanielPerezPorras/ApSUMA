package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDatosRegistro implements ActionListener {

    private final VistaDatosRegistro vista;

    public ControladorDatosRegistro(VistaDatosRegistro vista) { this.vista = vista;}

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "REGISTRARSE":

                break;
            case "ATRAS":

                break;
        }
    }
}
