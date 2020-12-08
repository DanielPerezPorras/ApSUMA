package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class VistaRegistroTutor extends JFrame {

	private JPanel panelPrincipal;
	private JTextField tCorreoUMA;
	private JTextField tCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaRegistroTutor frame = new VistaRegistroTutor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaRegistroTutor() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 367);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lbCorreoUMA = new JLabel("Correo electr\u00F3nico UMA");
		lbCorreoUMA.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbCorreoUMA.setBounds(44, 13, 212, 24);
		panelPrincipal.add(lbCorreoUMA);
		
		JLabel lbCorreo = new JLabel("Correo electr\u00F3nico");
		lbCorreo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbCorreo.setBounds(44, 58, 212, 24);
		panelPrincipal.add(lbCorreo);
		
		JLabel lbNombreDeUsuario = new JLabel("Nombre de usuario");
		lbNombreDeUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbNombreDeUsuario.setBounds(44, 107, 212, 24);
		panelPrincipal.add(lbNombreDeUsuario);
		
		JLabel lbContrasea = new JLabel("Contrase\u00F1a");
		lbContrasea.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbContrasea.setBounds(44, 162, 212, 24);
		panelPrincipal.add(lbContrasea);
		
		JLabel lbConfirmeSuContrasea = new JLabel("Confirme su contrase\u00F1a");
		lbConfirmeSuContrasea.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		lbConfirmeSuContrasea.setBounds(44, 219, 212, 24);
		panelPrincipal.add(lbConfirmeSuContrasea);
		
		tCorreoUMA = new JTextField();
		tCorreoUMA.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tCorreoUMA.setBounds(288, 11, 251, 31);
		panelPrincipal.add(tCorreoUMA);
		tCorreoUMA.setColumns(10);
		
		tCorreo = new JTextField();
		tCorreo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tCorreo.setColumns(10);
		tCorreo.setBounds(288, 61, 251, 31);
		panelPrincipal.add(tCorreo);
	}
}
