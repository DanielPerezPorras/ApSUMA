package gui.contenido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDialogoNuevoTest implements ActionListener
{

    private final DialogoNuevoTest dialogo;

    public ControladorDialogoNuevoTest (DialogoNuevoTest dialogo) {
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
