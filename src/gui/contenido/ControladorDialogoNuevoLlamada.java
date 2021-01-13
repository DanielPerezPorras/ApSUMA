package gui.contenido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDialogoNuevoLlamada implements ActionListener
{

    private final DialogoNuevoLlamada dialogo;

    public ControladorDialogoNuevoLlamada(DialogoNuevoLlamada dialogo) {
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
