package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaNuevoTest extends JDialog
{
    private JButton btnCrear;
    private JButton btnCancelar;
    private String test;
    private JPanel panalPrincipal;
    private JLabel lblIntroducir;
    private JTextField tFLink;

    /**
     * Create the frame.
     */
    public VistaNuevoTest(JFrame propietario, String test)
    {
        super(propietario, "Nuevo " + test, true);
        this.test=test;
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 569, 297);
        panalPrincipal = new JPanel();
        panalPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panalPrincipal);
        panalPrincipal.setLayout(null);

        lblIntroducir = new JLabel("Introduzca aquí el link de su " + test);
        lblIntroducir.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
        lblIntroducir.setBounds(72, 13, 153, 40);
        panalPrincipal.add(lblIntroducir);

        tFLink = new JTextField();
        tFLink.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
        tFLink.setBounds(232, 18, 279, 32);
        panalPrincipal.add(tFLink);
        tFLink.setColumns(10);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
        btnCancelar.setBounds(10, 194, 129, 40);
        panalPrincipal.add(btnCancelar);

        btnCrear = new JButton("Crear " + test);
        btnCrear.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
        btnCrear.setBounds(414, 194, 129, 40);
        panalPrincipal.add(btnCrear);
    }

}
