package gui.contenido;

import gui.UtilidadesGUI;

import javax.swing.*;
import java.awt.*;

public class VistaContenidoTexto extends VistaContenido {

    public VistaContenidoTexto(String texto) {
        super();
        JLabel label = new JLabel("<html><div>" + texto + "</div></html>");
        label.setFont(UtilidadesGUI.FUENTE);
        areaContenido.add(label);
    }

}
