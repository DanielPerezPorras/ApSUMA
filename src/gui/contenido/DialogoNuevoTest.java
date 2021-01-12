package gui.contenido;

import gui.UtilidadesGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DialogoNuevoTest extends JDialog {

    private  String tipo;
    private JTextField tfEnlace;
    private JTextField tfTextoVisible;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private boolean seHaConfirmado = false;
    private JLabel lblAyuda;
    private JLabel lblHyperlink;

    // Instanciar el diálogo con el constructor, él crea internamente su controlador.
    public DialogoNuevoTest(JFrame propietario, String tipo) {
        super(propietario, "Nuevo " + tipo, true);
        this.tipo = tipo;
        crearGUI();
        asignarControlador(new ControladorDialogoNuevoTest(this));
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
        setSize(400, 300);

        JPanel contenido = new JPanel(new BorderLayout());
        setContentPane(contenido);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel lblEnlace = new JLabel("URL del " + tipo + ": ");
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
        lblAyuda = new JLabel("¿No tienes tu test de conocimiento todavía creado?");
        lblAyuda.setFont(UtilidadesGUI.FUENTE);
        lblAyuda.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblHyperlink = new JLabel("¡Creélo aquí!");
        lblHyperlink.setFont(UtilidadesGUI.FUENTE);
        lblHyperlink.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblHyperlink.setForeground(Color.BLUE.darker());
        lblHyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblHyperlink.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                try {

                    Desktop.getDesktop().browse(new URI("https://docs.google.com/forms/u/0/"));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                // the mouse has entered the label
                lblHyperlink.setText("<html><a href=''>¡Creélo aquí!</a></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }
        });

        centro.add(lblEnlace);
        centro.add(tfEnlace);
        centro.add(Box.createRigidArea(new Dimension(0, 10)));
        centro.add(lblTextoVisible);
        centro.add(tfTextoVisible);
        centro.add(lblAyuda);
        centro.add(lblHyperlink);

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

    private void asignarControlador(ControladorDialogoNuevoTest ctr) {
        btnConfirmar.setActionCommand("CONFIRMAR");
        btnCancelar.setActionCommand("CANCELAR");
        btnConfirmar.addActionListener(ctr);
        btnCancelar.addActionListener(ctr);
    }

}
