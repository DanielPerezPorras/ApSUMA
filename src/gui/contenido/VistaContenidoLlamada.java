package gui.contenido;

import gui.UtilidadesGUI;
import modelo.contenido.Contenido;
import modelo.contenido.ContenidoDocumento;
import modelo.contenido.ContenidoLlamada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class VistaContenidoLlamada extends VistaContenido
{

    public VistaContenidoLlamada(Contenido cont, boolean modoEdicion)
    {
        super(cont, modoEdicion);
        ContenidoLlamada contenidoEnlace = (ContenidoLlamada)cont;
        String enlace = contenidoEnlace.getEnlace();
        String textoVisibleEscapado = UtilidadesGUI.escaparHtml(contenidoEnlace.getTextoVisible());

        JLabel resultado = new JLabel(textoVisibleEscapado);
        resultado.setForeground(new Color(51, 153, 51));
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
