package gui.contenido;

import gui.UtilidadesGUI;
import modelo.Evento;
import modelo.contenido.Contenido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class VistaContenido extends JPanel {

    protected JPanel areaContenido;
    protected Contenido contenido;

    private JPanel zonaModoEdicion;
    private JButton btnEliminar;

    private boolean enModoEdicion;

    public VistaContenido(Contenido cont, boolean modoEdicion) {
        contenido = cont;
        crearElemento(modoEdicion);
    }

    public void controlador(ActionListener ctr) {
        btnEliminar.setActionCommand("ELIMINAR_CONTENIDO_" + contenido.getId());
        btnEliminar.addActionListener(ctr);
        btnEliminar.setActionCommand("ELIMINAR_CONTENIDO_" + contenido.getId());
        btnEliminar.addActionListener(ctr);
        btnEliminar.setActionCommand("ELIMINAR_CONTENIDO_" + contenido.getId());
        btnEliminar.addActionListener(ctr);
    }

    public void setModoEdicion(boolean mostrar) {
        enModoEdicion = mostrar;
        if (mostrar) {
            add(zonaModoEdicion);
        } else {
            remove(zonaModoEdicion);
        }
    }

    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        return new Dimension(getParent().getParent().getWidth() - 60, size.height);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    private void crearElemento(boolean modoEdicion) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 15)));

        areaContenido = new JPanel(new BorderLayout());
        add(areaContenido, BorderLayout.CENTER);

        crearZonaModoEdicion();
        setModoEdicion(modoEdicion);
    }

    private void crearZonaModoEdicion() {

        zonaModoEdicion = new JPanel();
        zonaModoEdicion.setLayout(new BoxLayout(zonaModoEdicion, BoxLayout.LINE_AXIS));

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(UtilidadesGUI.FUENTE);
        zonaModoEdicion.add(btnEliminar);

        zonaModoEdicion.add(Box.createHorizontalGlue());

    }

}
