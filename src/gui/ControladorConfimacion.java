package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la pantalla de confirmación.
 */
public class ControladorConfimacion implements ActionListener {

    private final VistaConfirmacion confirmacion;

    public ControladorConfimacion(VistaConfirmacion confirmacion)
    {
        this.confirmacion = confirmacion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "SI":
                JOptionPane.showMessageDialog(confirmacion,
                        "Operación realizada con éxito",
                        "ÉXITO",
                        JOptionPane.INFORMATION_MESSAGE);
                confirmacion.dispose();
                break;

            case "NO":
                confirmacion.dispose();
                break;
        }
    }

}
