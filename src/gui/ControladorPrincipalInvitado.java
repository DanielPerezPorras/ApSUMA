package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipalInvitado implements ActionListener {

    private final VistaPrincipalInvitado vista;

    public ControladorPrincipalInvitado(VistaPrincipalInvitado vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "ENTRAR":
                vista.dispose();
                VistaActividadSocialAlumno.abrirVentana();
                break;
        }
    }
}
