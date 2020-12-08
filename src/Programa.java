import gui.VistaBase;
import modelo.BD;

import javax.swing.*;
import java.awt.*;

public class Programa {

    private static final String TITULO_VENTANA = "ApSUMA";
    private static final Dimension TAMANIO_VENTANA = new Dimension(800, 450);

    public static void main(String[] args) {
        System.out.println("Abriendo ApSUMA...");
        BD miBD = new BD();
        //crearGUI();
    }

    private static void crearGUI() {

        JFrame ventana = new JFrame(TITULO_VENTANA);
        VistaBase vistaBase = new VistaBase();

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setContentPane(vistaBase);
        ventana.setPreferredSize(TAMANIO_VENTANA);
        ventana.pack();
        ventana.setVisible(true);

    }

}
