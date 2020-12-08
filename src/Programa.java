import gui.VistaBase;
import modelo.BD;

import javax.swing.*;
import java.awt.*;

public class Programa {

    private static final String TITULO_VENTANA = "ApSUMA";
    private static final Dimension TAMANIO_VENTANA = new Dimension(800, 450);
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://ingreq2021-mysql.cobadwnzalab.eu-central-1.rds.amazonaws.com";
    private static final String DB_SCHEMA = "apsgrupo10";
    private static final String USER = "grupo10";
    private static final String PASS = "guillermoeduardo2021";

    public static void main(String[] args) {
        System.out.println("Abriendo ApSUMA...");
        BD miBD = new BD(JDBC_DRIVER, DB_URL, DB_SCHEMA, USER, PASS);
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
