package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class VistaPrincipalAdmin extends JFrame {

	private JPanel panelPrincipal;
	private JTextField textField;
	private JLabel lbVerComo;
	private JComboBox cbRoles;
	private JSeparator separator;
	private JLabel lblBsquedaDeUsuarios;
	private JLabel lbNombreUsuario;
	private JButton btnBuscar;
	private JTextPane textPane;
	private JLabel lbFoto;
	private Container lbNombreDeUsuario;
	private JLabel lblUsuario;
	private JLabel lblCorreoElectrnico;
	private JLabel lblUsuarioumaes;
	private JLabel lblCorreoUma;
	private JLabel lblUsuarioumaes_1;
	private JButton btnPenalizar;
	private JButton btnEliminarUsuario;

	public static void abrirVentana() {
		try {
			VistaPrincipalAdmin frame = new VistaPrincipalAdmin();
			frame.controlador(new ControladorPrincipalAdmin(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public VistaPrincipalAdmin() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 579);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lbVerComo = new JLabel("Ver como: ");
		lbVerComo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		lbVerComo.setBounds(42, 42, 99, 27);
		panelPrincipal.add(lbVerComo);
		
		cbRoles = new JComboBox();
		cbRoles.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		cbRoles.setBounds(151, 42, 144, 27);
		panelPrincipal.add(cbRoles);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(335, 44, 25, 487);
		panelPrincipal.add(separator);
		
		lblBsquedaDeUsuarios = new JLabel("B\u00FAsqueda de usuarios");
		lblBsquedaDeUsuarios.setFont(new Font("Microsoft JhengHei UI", Font.BOLD | Font.ITALIC, 16));
		lblBsquedaDeUsuarios.setBounds(372, 41, 186, 27);
		panelPrincipal.add(lblBsquedaDeUsuarios);
		
		lbNombreUsuario = new JLabel("Nombre de usuario:");
		lbNombreUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lbNombreUsuario.setBounds(400, 81, 144, 27);
		panelPrincipal.add(lbNombreUsuario);
		
		textField = new JTextField();
		textField.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		textField.setBounds(564, 81, 144, 27);
		panelPrincipal.add(textField);
		textField.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		btnBuscar.setBounds(720, 81, 97, 27);
		panelPrincipal.add(btnBuscar);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		textPane.setBounds(564, 139, 253, 184);
		panelPrincipal.add(textPane);
		
		lbFoto = new JLabel("");
		lbFoto.setBounds(372, 342, 144, 140);
		UtilidadesGUI.ajustarImagenALabel(lbFoto, "/recursosApp/gato.png");
		panelPrincipal.add(lbFoto);
		
		lbNombreDeUsuario = new JLabel("Nombre de usuario:");
		lbNombreDeUsuario.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lbNombreDeUsuario.setBounds(564, 342, 144, 31);
		panelPrincipal.add(lbNombreDeUsuario);
		
		lblUsuario = new JLabel("usuario");
		lblUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblUsuario.setBounds(720, 346, 97, 23);
		panelPrincipal.add(lblUsuario);
		
		lblCorreoElectrnico = new JLabel("Correo electr\u00F3nico:");
		lblCorreoElectrnico.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblCorreoElectrnico.setBounds(564, 375, 133, 27);
		panelPrincipal.add(lblCorreoElectrnico);
		
		lblUsuarioumaes = new JLabel("usuario@uma.es");
		lblUsuarioumaes.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblUsuarioumaes.setBounds(720, 375, 118, 27);
		panelPrincipal.add(lblUsuarioumaes);
		
		lblCorreoUma = new JLabel("Correo UMA:");
		lblCorreoUma.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblCorreoUma.setBounds(564, 412, 133, 27);
		panelPrincipal.add(lblCorreoUma);
		
		lblUsuarioumaes_1 = new JLabel("usuario@uma.es");
		lblUsuarioumaes_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblUsuarioumaes_1.setBounds(720, 415, 118, 27);
		panelPrincipal.add(lblUsuarioumaes_1);
		
		btnPenalizar = new JButton("Penalizar");
		btnPenalizar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		btnPenalizar.setBounds(372, 494, 118, 37);
		panelPrincipal.add(btnPenalizar);
		
		btnEliminarUsuario = new JButton("Eliminar usuario");
		btnEliminarUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		btnEliminarUsuario.setBounds(579, 494, 202, 37);
		panelPrincipal.add(btnEliminarUsuario);
	}
	
	public void controlador(ActionListener ctr)
	{
		btnBuscar.addActionListener(ctr);
		btnBuscar.setActionCommand("BUSCAR");
		btnPenalizar.addActionListener(ctr);
		btnPenalizar.setActionCommand("PENALIZAR");
		btnEliminarUsuario.addActionListener(ctr);
		btnEliminarUsuario.setActionCommand("ELIMINAR");
	}

}
