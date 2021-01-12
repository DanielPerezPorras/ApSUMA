package gui.contenido;

import gui.UtilidadesGUI;
import modelo.contenido.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class VistaContenidoTest extends VistaContenido
{

    public VistaContenidoTest(Contenido cont, boolean modoEdicion)
    {
        super(cont, modoEdicion);
        ContenidoTest contenidoEnlace = (ContenidoTest)cont;
        String enlace = contenidoEnlace.getEnlace();
        String textoVisibleEscapado = UtilidadesGUI.escaparHtml(contenidoEnlace.getTextoVisible());

        JLabel resultado = new JLabel(textoVisibleEscapado);
        resultado.setForeground(new Color(109, 60, 227));
        resultado.setFont(UtilidadesGUI.FUENTE);
        resultado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        resultado.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(enlace));
                } catch (IOException | URISyntaxException e1) {
                    JOptionPane.showMessageDialog(null,
                            "Enlace mal formado o no disponible.\n" + enlace,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                resultado.setText("<html><u>" + textoVisibleEscapado + "</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resultado.setText("<html>" + textoVisibleEscapado + "</html>");
            }

        });
        areaContenido.add(resultado);
    }

}
