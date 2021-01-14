package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class UtilidadesGUI {

    public static final Font FUENTE = new Font("Microsoft JhengHei UI", Font.PLAIN, 15);
    public static final Font FUENTE_TITULOS = new Font("Microsoft JhengHei UI", Font.BOLD, 20);

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

    public static String escaparHtml(String entrada) {
        StringBuilder resultado = new StringBuilder();
        for (char c : entrada.toCharArray()) {
            switch (c) {

                case '\n':
                    resultado.append("<br/>");
                    break;

                case '<':
                    resultado.append("&lt;");
                    break;

                case '>':
                    resultado.append("&gt;");
                    break;

                case '&':
                    resultado.append("&amp;");
                    break;

                default:
                    resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public static String escaparComillas(String entrada) {
        return entrada.replace("'", "\\'");
    }

}
