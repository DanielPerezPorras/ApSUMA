package gui.contenido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDialogoNuevoDocumento implements ActionListener
{

    private final DialogoNuevoDocumento dialogo;

    public ControladorDialogoNuevoDocumento(DialogoNuevoDocumento dialogo) {
        this.dialogo = dialogo;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("CONFIRMAR")) {
            dialogo.darConfirmacion();
        }
        dialogo.dispose();
    }

}
