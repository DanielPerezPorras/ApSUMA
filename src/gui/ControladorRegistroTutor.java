package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorRegistroTutor implements ActionListener {

    private VistaRegistroTutor vista;

    public ControladorRegistroTutor(VistaRegistroTutor vista) {
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
