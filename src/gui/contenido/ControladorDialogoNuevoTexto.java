package gui.contenido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDialogoNuevoTexto implements ActionListener {

    private final DialogoNuevoTexto dialogo;

    public ControladorDialogoNuevoTexto (DialogoNuevoTexto dialogo) {
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
