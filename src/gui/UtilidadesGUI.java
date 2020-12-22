package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class UtilidadesGUI {

    static final Font FUENTE = new Font("Microsoft JhengHei UI", Font.PLAIN, 15);
    static final Font FUENTE_TITULOS = new Font("Microsoft JhengHei UI", Font.BOLD, 20);

    public static void ajustarImagenALabel(JLabel label, String ruta) {
        File archivo = new File(VistaLogin.class.getResource(ruta).getPath());
        try {
            BufferedImage buff = ImageIO.read(archivo);
            Image img = buff.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icono = new ImageIcon(img);
            label.setIcon(icono);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void ajustarImagenAButton(JButton label, String ruta) {
        File archivo = new File(VistaLogin.class.getResource(ruta).getPath());
        try {
            BufferedImage buff = ImageIO.read(archivo);
            Image img = buff.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icono = new ImageIcon(img);
            label.setIcon(icono);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
