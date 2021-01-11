package gui;

import modelo.BD;
import modelo.Correo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ControladorOlvidar implements ActionListener {
    private VistaOlvidar vista;
    public ControladorOlvidar(VistaOlvidar frame) {
        vista = frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ENVIAR")) {
            String correo = vista.getTextoCorreo();
            BD bd = new BD();
            List<Object[]> objeto = bd.Select("SELECT contra FROM Usuario WHERE correo = '" + correo + "';");
            if (objeto.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Correo no encontrado", "Error", 1);
            } else {
                try {
                    Correo.mandarCorreo(correo,"Contraseña olvidada", "La contraseña de su cuenta es: " + (String) objeto.get(0)[0] );
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(), "Error" , 1);
                }
            }
        }
        vista.dispose();
    }
}
