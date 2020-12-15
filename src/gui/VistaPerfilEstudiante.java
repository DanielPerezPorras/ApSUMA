package gui;

import modelo.Estudiante;
import modelo.Sesion;
import modelo.Usuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Container;

import javax.swing.JComboBox;
import java.awt.SystemColor;

public class VistaPerfilEstudiante extends JFrame {

	private JPanel panelPrincipal;
	private JLabel lLogo;
	private JLabel lblNombreUsuario;
	private JLabel lblCorreo;
	private JLabel lblCampoNombreUsuario;
	private JLabel lblTutorgmailcom;
	private JButton btModificarDatos;
	private JButton btAtrs;
	private JButton btCerrarSesin;
	private Container cbMisCursos;
	private JComboBox cbMisConferencias;
	private JComboBox cbMisActividades;

	
	
	/**
	 * Create the frame.
	 */
	public VistaPerfilEstudiante() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 432);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		lLogo = new JLabel("");
		lLogo.setBounds(34, 34, 207, 192);
		UtilidadesGUI.ajustarImagenALabel(lLogo, "/recursosApp/gato.png");
		panelPrincipal.add(lLogo);
		
		lblNombreUsuario = new JLabel("Nombre de usuario:");
		lblNombreUsuario.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 15));
		lblNombreUsuario.setBounds(288, 34, 146, 27);
		panelPrincipal.add(lblNombreUsuario);
		
		lblCorreo = new JLabel("Correo electr\u00F3nico:");
		lblCorreo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 15));
		lblCorreo.setBounds(288, 88, 146, 27);
		panelPrincipal.add(lblCorreo);
		
		lblCampoNombreUsuario =  new JLabel(Sesion.getUsuarioLogueado().getNombreUsuario());
		lblCampoNombreUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblCampoNombreUsuario.setBounds(475, 34, 146, 27);
		panelPrincipal.add(lblCampoNombreUsuario);
		
		lblTutorgmailcom = new JLabel("tutor@gmail.com");
		lblTutorgmailcom.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblTutorgmailcom.setBounds(475, 88, 146, 27);
		panelPrincipal.add(lblTutorgmailcom);
		
		btModificarDatos = new JButton("Modificar datos");
		btModificarDatos.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btModificarDatos.setBackground(SystemColor.activeCaption);
		btModificarDatos.setBounds(352, 128, 187, 25);
		panelPrincipal.add(btModificarDatos);
		
		btAtrs = new JButton("Atr\u00E1s");
		btAtrs.setBackground(SystemColor.activeCaption);
		btAtrs.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btAtrs.setBounds(529, 353, 143, 41);
		panelPrincipal.add(btAtrs);
		
		btCerrarSesin = new JButton("Cerrar sesi\u00F3n");
		btCerrarSesin.setBackground(SystemColor.activeCaption);
		btCerrarSesin.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btCerrarSesin.setBounds(13, 353, 143, 41);
		panelPrincipal.add(btCerrarSesin);
		
		cbMisCursos = new JComboBox();
		cbMisCursos.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		cbMisCursos.setBounds(352, 194, 179, 34);
		panelPrincipal.add(cbMisCursos);
		
		cbMisConferencias = new JComboBox();
		cbMisConferencias.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		cbMisConferencias.setBounds(352, 241, 179, 34);
		panelPrincipal.add(cbMisConferencias);
		
		cbMisActividades = new JComboBox();
		cbMisActividades.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		cbMisActividades.setBounds(352, 288, 179, 34);
		panelPrincipal.add(cbMisActividades);
	}

	public static void abrirVentana() {
		try {
			VistaPerfilEstudiante frame = new VistaPerfilEstudiante();
			frame.controlador(new ControladorPerfilEstudiante(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void controlador(ActionListener ctr)
	{
		btModificarDatos.addActionListener(ctr);
		btModificarDatos.setActionCommand("MODIFICAR");
		btCerrarSesin.addActionListener(ctr);
		btCerrarSesin.setActionCommand("CERRAR SESION");
		btAtrs.addActionListener(ctr);
		btAtrs.setActionCommand("ATRAS");
	}
}
