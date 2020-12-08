package gui;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class VistaRegistroEstudiante extends JFrame {

	private JPanel panelPrincipal;
	private JTextField tCorreo;
	private JTextField tUsuario;
	private JPasswordField pfContr;
	private JPasswordField pfConfContr;
	private Container lbCorreo;
	private Container lbNombreDeUsuario;
	private Container lbContrasea;
	private Container lbConfirmeSuContrasea;
	private JButton btnAtras;
	private JButton btnRegistrarse;

	public static void abrirVentana() {
		try {
			VistaRegistroEstudiante frame = new VistaRegistroEstudiante();
			frame.controlador(new ControladorRegistroEstudiante(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VistaRegistroEstudiante() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 367);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lbCorreo = new JLabel("Correo electr\u00F3nico");
		lbCorreo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbCorreo.setBounds(44, 27, 212, 24);
		panelPrincipal.add(lbCorreo);
		
		lbNombreDeUsuario = new JLabel("Nombre de usuario");
		lbNombreDeUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbNombreDeUsuario.setBounds(44, 72, 212, 24);
		panelPrincipal.add(lbNombreDeUsuario);
		
		lbContrasea = new JLabel("Contrase\u00F1a");
		lbContrasea.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbContrasea.setBounds(44, 114, 212, 24);
		panelPrincipal.add(lbContrasea);
		
		lbConfirmeSuContrasea = new JLabel("Confirme su contrase\u00F1a");
		lbConfirmeSuContrasea.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbConfirmeSuContrasea.setBounds(44, 156, 212, 24);
		panelPrincipal.add(lbConfirmeSuContrasea);
		
		tCorreo = new JTextField();
		tCorreo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tCorreo.setColumns(10);
		tCorreo.setBounds(288, 25, 251, 31);
		panelPrincipal.add(tCorreo);
		
		tUsuario = new JTextField();
		tUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tUsuario.setColumns(10);
		tUsuario.setBounds(288, 70, 251, 31);
		panelPrincipal.add(tUsuario);
		
		pfContr = new JPasswordField();
		pfContr.setBounds(288, 114, 251, 31);
		panelPrincipal.add(pfContr);
		
		pfConfContr = new JPasswordField();
		pfConfContr.setBounds(288, 156, 251, 31);
		panelPrincipal.add(pfConfContr);
		
		btnAtras = new JButton("Atr\u00E1s");
		btnAtras.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 11));
		btnAtras.setBounds(12, 295, 128, 25);
		panelPrincipal.add(btnAtras);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 11));
		btnRegistrarse.setBounds(424, 295, 128, 25);
		panelPrincipal.add(btnRegistrarse);
	}
	
	public void controlador (ActionListener ctr) {
		btnAtras.addActionListener(ctr);
		btnRegistrarse.addActionListener(ctr);
		btnAtras.setActionCommand("ATRAS");
		btnRegistrarse.setActionCommand("REGISTRO ESTUDIANTE");
	}

	public String getTextoCorreo() {
		return tCorreo.getText();
	}

	public String getTextoUsuario() {
		return tUsuario.getText();
	}

	public String getTextoContrasenia() {
		return pfContr.getText();
	}

	public String getTextoConfirmaContrasenia() {
		return pfConfContr.getText();
	}

}
