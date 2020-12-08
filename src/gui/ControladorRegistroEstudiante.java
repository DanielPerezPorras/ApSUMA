package gui;

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

        }
    }

}
