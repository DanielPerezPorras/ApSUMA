package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class VistaLogin extends JFrame {

	private JPanel panelTotal;
	private JPasswordField passwordField;
	private JTextField tFUsuario;
	private JLabel lblContrasea;
	private JLabel lbUsuario;
	private JLabel lbImagen;
	private JLabel lbDescripcion;
	private JLabel lbTitulo;
	private JLabel lbLogo;
	private JButton btIniciar;
	private JButton btRegistrarse;
	private JButton btnEntrarComoInvitado;


	public static void abrirVentana() {
		try {
			VistaLogin frame = new VistaLogin();
			frame.controlador(new ControladorLogin(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the frame.
	 */
	public VistaLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 478);
		panelTotal = new JPanel();
		panelTotal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelTotal);
		panelTotal.setLayout(null);
		
		lbLogo = new JLabel("");
		lbLogo.setBounds(148, 42, 61, 62);
		UtilidadesGUI.ajustarImagenALabel(lbLogo, "/recursosApp/ambulancia.png");
		panelTotal.add(lbLogo);
		
		lbTitulo = new JLabel("ApSUma");
		lbTitulo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 40));
		lbTitulo.setBounds(247, 35, 331, 69);
		panelTotal.add(lbTitulo);
		
		lbDescripcion = new JLabel("Trabajando por tu servicio");
		lbDescripcion.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lbDescripcion.setBounds(247, 116, 171, 16);
		panelTotal.add(lbDescripcion);
		
		lbImagen = new JLabel("");
		lbImagen.setBounds(94, 117, 115, 112);
		lbImagen.setHorizontalAlignment(SwingConstants.CENTER);
		UtilidadesGUI.ajustarImagenALabel(lbImagen, "/recursosApp/gato.png");
		panelTotal.add(lbImagen);
		
		lbUsuario = new JLabel("Usuario");
		lbUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lbUsuario.setBounds(247, 145, 107, 16);
		panelTotal.add(lbUsuario);
		
		lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblContrasea.setBounds(247, 191, 107, 16);
		panelTotal.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		passwordField.setBounds(408, 185, 232, 33);
		panelTotal.add(passwordField);
		
		tFUsuario = new JTextField();
		tFUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tFUsuario.setBounds(408, 139, 232, 33);
		panelTotal.add(tFUsuario);
		tFUsuario.setColumns(10);
		
		btIniciar = new JButton("Iniciar sesi\u00F3n");
		btIniciar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btIniciar.setBounds(39, 310, 190, 25);
		panelTotal.add(btIniciar);
		
		btRegistrarse = new JButton("Registrarse");
		btRegistrarse.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btRegistrarse.setBounds(241, 310, 190, 25);
		panelTotal.add(btRegistrarse);
		
		btnEntrarComoInvitado = new JButton("Entrar como invitado");
		btnEntrarComoInvitado.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		btnEntrarComoInvitado.setBounds(443, 310, 275, 25);
		panelTotal.add(btnEntrarComoInvitado);
	}
	
	public void controlador(ActionListener ctr)
	{
		btIniciar.addActionListener(ctr);
		btRegistrarse.addActionListener(ctr);
		btnEntrarComoInvitado.addActionListener(ctr);
		btIniciar.setActionCommand("INICIAR");
		btRegistrarse.setActionCommand("REGISTRAR");
		btnEntrarComoInvitado.setActionCommand("INVITADO");
	}

	public String getTextoUsuario() {
		return tFUsuario.getText();
	}

	public String getTextoContrasenia() {
		return passwordField.getText();
	}

}
