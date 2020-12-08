package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorTipoRegistro implements ActionListener {

    private VistaTipoRegistro vista;

    public ControladorTipoRegistro(VistaTipoRegistro vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "ESTUDIANTE":
                break;

            case "TUTOR":
                vista.dispose();
                VistaRegistroTutor.abrirVentana();
                break;

            case "COLABORADOR":
                vista.dispose();
                VistaRegistroColaborador.abrirVentana();
                break;

            case "ATRAS":
                vista.dispose();
                VistaLogin.abrirVentana();
                break;

        }
    }

}
