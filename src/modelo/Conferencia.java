package modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

public class Conferencia extends Evento {

    private String link;

    public Conferencia(Date dia, String nom,Usuario dueno,  String link) {
        super(dia,nom,dueno);
        BD bd = new BD();
        bd.Insert("INSERT INTO Conferencia VALUES('" + nom + "', '" + link + "');");
        this.link = link;
    }

    public Conferencia(String nombre) {
        super(nombre);
        BD bd = new BD();
        List<Object[]> confList = bd.Select("SELECT link FROM Conferencia WHERE nombre = '" + nombre + "';");

        if (confList.size() > 0) {
            link = confList.get(0)[0].toString();
        } else {
            throw new ErrorBD("No se ha encontrado una conferencia con nombre " + nombre);
        }
    }

    public String getEnlace() { return link; }

    public void modificar(Date fecha, String nombre, String enlace) {
        super.modificar(fecha, nombre);
        BD bd = new BD();
        bd.Update("UPDATE Conferencia SET link='" + enlace + "' WHERE nombre='" + getNombre() + "'");
        link = enlace;
    }

    @Override
    public JLabel getSubtituloPaginaEvento() {
        JLabel resultado = new JLabel("[" + formatoFecha.format(Fecha) + "]" + " Enlace a la conferencia");
        resultado.setForeground(Color.BLUE.darker());
        resultado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        resultado.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(link));
                } catch (IOException | URISyntaxException e1) {
                    JOptionPane.showMessageDialog(null,
                            "Enlace de la conferencia mal formado o no disponible.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }

        });

        return resultado;
    }

}
