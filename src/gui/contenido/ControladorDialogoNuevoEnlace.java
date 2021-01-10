package gui.contenido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDialogoNuevoEnlace implements ActionListener {

    private final DialogoNuevoEnlace dialogo;

    public ControladorDialogoNuevoEnlace (DialogoNuevoEnlace dialogo) {
        this.dialogo = dialogo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("CONFIRMAR")) {
            dialogo.darConfirmacion();
        }
        dialogo.dispose();
    }

}
