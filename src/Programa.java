import gui.VistaLogin;
import modelo.Correo;

import java.awt.*;

public class Programa {

    public static void main(String[] args) {
        System.out.println("Abriendo ApSUMA...");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaLogin.abrirVentana();
            }
        });

        /*Correo c = new Correo();
        Thread t = new Thread(c);
        t.start();*/

    }

}
