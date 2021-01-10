package gui.contenido;

import gui.UtilidadesGUI;
import modelo.contenido.Contenido;
import modelo.contenido.ContenidoTexto;

import javax.swing.*;

public class VistaContenidoTexto extends VistaContenido {

    // Este constructor solo debería ser invocado desde Contenido.getVista()
    public VistaContenidoTexto(Contenido cont, boolean modoEdicion) {
        super(cont, modoEdicion);
        ContenidoTexto contTexto = (ContenidoTexto)cont;
        JLabel label = new JLabel("<html><div>" + UtilidadesGUI.escaparHtml(contTexto.getTexto()) + "</div></html>");
        label.setFont(UtilidadesGUI.FUENTE);
        areaContenido.add(label);
    }

}
