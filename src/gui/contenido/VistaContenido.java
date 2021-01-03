package gui.contenido;

import javax.swing.*;
import java.awt.*;

public abstract class VistaContenido extends JPanel {

    protected JPanel areaContenido;

    public VistaContenido() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 15)));
        areaContenido = new JPanel(new GridLayout(1, 1));
        add(areaContenido);
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

}
