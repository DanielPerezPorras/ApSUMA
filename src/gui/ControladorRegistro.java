package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorRegistro implements ActionListener {

    private VistaTipoRegistro vista;

    public ControladorRegistro(VistaTipoRegistro vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "ESTUDIANTE":
                break;

            case "TUTOR":
                break;

            case "COLABORADOR":
                break;

            case "ATRAS":
                break;

        }
    }

}
