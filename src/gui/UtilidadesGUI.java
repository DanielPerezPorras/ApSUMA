package gui;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Image;

public class UtilidadesGUI {

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
