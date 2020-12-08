package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class VistaRegistroColaborador extends JFrame {

	private JPanel panelPrincipal;
	private JTextField tCorreoUMA;
	private JTextField tCorreo;
	private JTextField tNombre;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;
	private JLabel lbCorreoCorporativo;
	private JLabel lbCorreo;
	private JLabel lbNombreDeUsuario;
	private JLabel lbContrasea;
	private JLabel lbConfirmeSuContrasea;
	private JButton btnAtrs;
	private JButton btnSiguiente;

	public static void abrirVentana() {
		try {
			VistaRegistroColaborador frame = new VistaRegistroColaborador();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public VistaRegistroColaborador() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 367);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lbCorreoCorporativo = new JLabel("Correo coorporativo");
		lbCorreoCorporativo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbCorreoCorporativo.setBounds(44, 25, 212, 24);
		panelPrincipal.add(lbCorreoCorporativo);
		
		lbCorreo = new JLabel("Correo electr\u00F3nico");
		lbCorreo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbCorreo.setBounds(44, 75, 212, 24);
		panelPrincipal.add(lbCorreo);
		
		lbNombreDeUsuario = new JLabel("Nombre de usuario");
		lbNombreDeUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbNombreDeUsuario.setBounds(44, 124, 212, 24);
		panelPrincipal.add(lbNombreDeUsuario);
		
		lbContrasea = new JLabel("Contrase\u00F1a");
		lbContrasea.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbContrasea.setBounds(44, 179, 212, 24);
		panelPrincipal.add(lbContrasea);
		
		lbConfirmeSuContrasea = new JLabel("Confirme su contrase\u00F1a");
		lbConfirmeSuContrasea.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbConfirmeSuContrasea.setBounds(44, 236, 212, 24);
		panelPrincipal.add(lbConfirmeSuContrasea);
		
		tCorreoUMA = new JTextField();
		tCorreoUMA.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tCorreoUMA.setBounds(288, 23, 251, 31);
		panelPrincipal.add(tCorreoUMA);
		tCorreoUMA.setColumns(10);
		
		tCorreo = new JTextField();
		tCorreo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tCorreo.setColumns(10);
		tCorreo.setBounds(288, 73, 251, 31);
		panelPrincipal.add(tCorreo);
		
		tNombre = new JTextField();
		tNombre.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tNombre.setColumns(10);
		tNombre.setBounds(288, 122, 251, 31);
		panelPrincipal.add(tNombre);
		
		btnAtrs = new JButton("Atr\u00E1s");
		btnAtrs.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnAtrs.setBounds(12, 295, 118, 25);
		panelPrincipal.add(btnAtrs);
		
		btnSiguiente = new JButton("Registrarse");
		btnSiguiente.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnSiguiente.setBounds(432, 295, 118, 25);
		panelPrincipal.add(btnSiguiente);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(288, 178, 251, 31);
		panelPrincipal.add(passwordField);
		
		passwordConfirmField = new JPasswordField();
		passwordConfirmField.setBounds(288, 239, 251, 31);
		panelPrincipal.add(passwordConfirmField);
	}
	
	public void controlador(ActionListener ctr)
	{
		btnAtrs.addActionListener(ctr);
		btnAtrs.setActionCommand("ATRAS");
		btnSiguiente.addActionListener(ctr);
		btnSiguiente.setActionCommand("REGISTRO COLABORADOR");
	}
}
