package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaOlvidar extends JFrame {

	private JPanel panelTotal;
	private JTextField tFCorreo;
	private JLabel lbTitulo;
;
	private JButton btEnviar;
	private JButton btAtras;
	private JLabel lbImagen;



	public static void abrirVentana() {
		try {
			VistaOlvidar frame = new VistaOlvidar();
			frame.controlador(new ControladorOlvidar(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the frame.
	 */
	public VistaOlvidar() {
		super("Login APS_UMA");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 478);
		panelTotal = new JPanel();
		panelTotal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelTotal);
		panelTotal.setLayout(null);
		
		lbTitulo = new JLabel("Introduzca el correo al que mandaremos su contraseña");
		lbTitulo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		lbTitulo.setBounds(220, 110, 560, 69);
		panelTotal.add(lbTitulo);

		lbImagen = new JLabel("");
		lbImagen.setBounds(94, 117, 115, 112);
		lbImagen.setHorizontalAlignment(SwingConstants.CENTER);
		UtilidadesGUI.ajustarImagenALabel(lbImagen, "/recursosApp/gato.png");
		panelTotal.add(lbImagen);

		tFCorreo = new JTextField();
		tFCorreo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tFCorreo.setBounds(300, 169, 232, 33);
		panelTotal.add(tFCorreo);
		tFCorreo.setColumns(10);
		
		btEnviar = new JButton("Enviar");
		btEnviar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btEnviar.setBounds(94, 310, 190, 25);
		panelTotal.add(btEnviar);
		
		btAtras = new JButton("Atrás");
		btAtras.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btAtras.setBounds(450, 310, 190, 25);
		panelTotal.add(btAtras);

	}
	
	public void controlador(ActionListener ctr)
	{
		btEnviar.addActionListener(ctr);
		btAtras.addActionListener(ctr);
		tFCorreo.addActionListener(ctr);
		btEnviar.setActionCommand("ENVIAR");
		tFCorreo.setActionCommand("ENVIAR");
		btAtras.setActionCommand("ATRAS");
	}

	public String getTextoCorreo() {
		return tFCorreo.getText();
	}

}
