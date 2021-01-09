package gui.contenido;

import gui.UtilidadesGUI;

import javax.swing.*;
import java.awt.*;

public class DialogoNuevoTexto extends JDialog {

    private JTextArea areaTexto;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private boolean seHaConfirmado = false;

    // Instanciar el diálogo con el constructor, él crea internamente su controlador.
    public DialogoNuevoTexto(JFrame propietario) {
        super(propietario, "Nuevo ítem de texto", true);
        crearGUI();
        asignarControlador(new ControladorDialogoNuevoTexto(this));
    }

    public String getTexto() {
        return areaTexto.getText();
    }

    public boolean seHaConfirmado() {
        return seHaConfirmado;
    }

    public void darConfirmacion() {
        seHaConfirmado = true;
    }

    private void crearGUI() {

        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contenido = new JPanel(new BorderLayout());
        setContentPane(contenido);

        JPanel norte = new JPanel(new GridLayout(1, 1));
        norte.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel enunciado = new JLabel("Introduzca texto:");
        enunciado.setFont(UtilidadesGUI.FUENTE);
        norte.add(enunciado);

        JPanel centro = new JPanel(new GridLayout(1, 1));
        centro.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        areaTexto = new JTextArea();
        areaTexto.setFont(UtilidadesGUI.FUENTE);
        centro.add(new JScrollPane(areaTexto));

        JPanel sur = new JPanel();
        sur.setLayout(new BoxLayout(sur, BoxLayout.LINE_AXIS));
        sur.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");
        btnConfirmar.setFont(UtilidadesGUI.FUENTE);
        btnCancelar.setFont(UtilidadesGUI.FUENTE);
        sur.add(btnConfirmar);
        sur.add(btnCancelar);

        contenido.add(norte, BorderLayout.NORTH);
        contenido.add(centro, BorderLayout.CENTER);
        contenido.add(sur, BorderLayout.SOUTH);

    }

    // PARTE QUE FUNCIONA COMO CONTROLADOR
    // -----------------------------------

    private void asignarControlador(ControladorDialogoNuevoTexto ctr) {
        btnConfirmar.setActionCommand("CONFIRMAR");
        btnCancelar.setActionCommand("CANCELAR");
        btnConfirmar.addActionListener(ctr);
        btnCancelar.addActionListener(ctr);
    }

}
