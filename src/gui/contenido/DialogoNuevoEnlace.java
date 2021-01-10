package gui.contenido;

import gui.UtilidadesGUI;

import javax.swing.*;
import java.awt.*;

public class DialogoNuevoEnlace extends JDialog {

    private JTextField tfEnlace;
    private JTextField tfTextoVisible;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private boolean seHaConfirmado = false;

    // Instanciar el diálogo con el constructor, él crea internamente su controlador.
    public DialogoNuevoEnlace(JFrame propietario) {
        super(propietario, "Nuevo enlace", true);
        crearGUI();
        asignarControlador(new ControladorDialogoNuevoEnlace(this));
    }

    public String getEnlace() {
        return tfEnlace.getText();
    }

    public String getTextoVisible() {
        return tfTextoVisible.getText();
    }

    public boolean seHaConfirmado() {
        return seHaConfirmado;
    }

    public void darConfirmacion() {
        seHaConfirmado = true;
    }

    private void crearGUI() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 200);

        JPanel contenido = new JPanel(new BorderLayout());
        setContentPane(contenido);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel lblEnlace = new JLabel("URL del recurso:");
        lblEnlace.setFont(UtilidadesGUI.FUENTE);
        lblEnlace.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfEnlace = new JTextField();
        tfEnlace.setFont(UtilidadesGUI.FUENTE);
        tfEnlace.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel lblTextoVisible = new JLabel("Texto a mostrar:");
        lblTextoVisible.setFont(UtilidadesGUI.FUENTE);
        lblTextoVisible.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfTextoVisible = new JTextField();
        tfTextoVisible.setFont(UtilidadesGUI.FUENTE);
        tfTextoVisible.setAlignmentX(Component.LEFT_ALIGNMENT);

        centro.add(lblEnlace);
        centro.add(tfEnlace);
        centro.add(Box.createRigidArea(new Dimension(0, 10)));
        centro.add(lblTextoVisible);
        centro.add(tfTextoVisible);

        JPanel sur = new JPanel();
        sur.setLayout(new BoxLayout(sur, BoxLayout.LINE_AXIS));
        sur.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");
        btnConfirmar.setFont(UtilidadesGUI.FUENTE);
        btnCancelar.setFont(UtilidadesGUI.FUENTE);
        sur.add(btnConfirmar);
        sur.add(btnCancelar);

        contenido.add(centro, BorderLayout.CENTER);
        contenido.add(sur, BorderLayout.SOUTH);

    }

    // PARTE QUE FUNCIONA COMO CONTROLADOR
    // -----------------------------------

    private void asignarControlador(ControladorDialogoNuevoEnlace ctr) {
        btnConfirmar.setActionCommand("CONFIRMAR");
        btnCancelar.setActionCommand("CANCELAR");
        btnConfirmar.addActionListener(ctr);
        btnCancelar.addActionListener(ctr);
    }

}
