package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorRegistroColaborador implements ActionListener {

    private VistaRegistroColaborador vista;

    public ControladorRegistroColaborador(VistaRegistroColaborador vista) {
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
