package gui;

import modelo.Sesion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaPerfilColaborador extends JFrame {

	private JPanel panelPrincipal;
	private JLabel lLogo;
	private JLabel lblNombreUsuario;
	private JLabel lblCorreo;
	private JLabel lblCorreoCorpo;
	private JButton btModificarDatos;
	private JButton btAtrs;
	private JButton btCerrarSesin;
	private JComboBox cbEventosCreados;
	private Container cbMisCursos;
	private JComboBox cbMisConferencias;
	private JComboBox cbMisActividades;
	private JButton btnEliminarCuenta;
	private JTextField tfCampoUsuario;
	private JTextField tfCampoCorreo;
	private JTextField tfCampoCorp;

	/**
	 * Create the frame.
	 */

	public static void abrirVentana() {
		try {
			VistaPerfilColaborador frame = new VistaPerfilColaborador();
			frame.controlador(new ControladorPerfilColaborador(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public VistaPerfilColaborador() {
		super("Perfil");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 539);
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
		
		lblCorreoCorpo = new JLabel("Correo corporativo:");
		lblCorreoCorpo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 15));
		lblCorreoCorpo.setBounds(288, 151, 146, 27);
		panelPrincipal.add(lblCorreoCorpo);
		
		btModificarDatos = new JButton("Modificar datos");
		btModificarDatos.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btModificarDatos.setBackground(SystemColor.activeCaption);
		btModificarDatos.setBounds(288, 201, 187, 25);
		panelPrincipal.add(btModificarDatos);
		
		btAtrs = new JButton("Atr\u00E1s");
		btAtrs.setBackground(SystemColor.activeCaption);
		btAtrs.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btAtrs.setBounds(528, 450, 143, 41);
		panelPrincipal.add(btAtrs);
		
		btCerrarSesin = new JButton("Cerrar sesi\u00F3n");
		btCerrarSesin.setBackground(SystemColor.activeCaption);
		btCerrarSesin.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btCerrarSesin.setBounds(12, 450, 143, 41);
		panelPrincipal.add(btCerrarSesin);
		
		cbEventosCreados = new JComboBox();
		cbEventosCreados.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		cbEventosCreados.setBounds(151, 274, 179, 34);
		panelPrincipal.add(cbEventosCreados);
		
		cbMisCursos = new JComboBox();
		cbMisCursos.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		cbMisCursos.setBounds(151, 359, 179, 34);
		panelPrincipal.add(cbMisCursos);
		
		cbMisConferencias = new JComboBox();
		cbMisConferencias.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		cbMisConferencias.setBounds(409, 274, 179, 34);
		panelPrincipal.add(cbMisConferencias);
		
		cbMisActividades = new JComboBox();
		cbMisActividades.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		cbMisActividades.setBounds(409, 359, 179, 34);
		panelPrincipal.add(cbMisActividades);
		
		btnEliminarCuenta = new JButton("Eliminar cuenta");
		btnEliminarCuenta.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnEliminarCuenta.setBackground(SystemColor.activeCaption);
		btnEliminarCuenta.setBounds(271, 450, 143, 41);
		panelPrincipal.add(btnEliminarCuenta);
		
		tfCampoUsuario = new JTextField();
		tfCampoUsuario.setEditable(false);
		tfCampoUsuario.setBounds(475, 34, 146, 27);
		panelPrincipal.add(tfCampoUsuario);
		tfCampoUsuario.setColumns(10);
		tfCampoUsuario.setText(Sesion.getUsuarioLogueado().getNombreUsuario());
		
		tfCampoCorreo = new JTextField();
		tfCampoCorreo.setEditable(false);
		tfCampoCorreo.setBounds(475, 88, 146, 27);
		panelPrincipal.add(tfCampoCorreo);
		tfCampoCorreo.setColumns(10);
		tfCampoCorreo.setText(Sesion.getUsuarioLogueado().getCorreo());
		
		tfCampoCorp = new JTextField();
		tfCampoCorp.setEditable(false);
		tfCampoCorp.setBounds(475, 151, 146, 27);
		panelPrincipal.add(tfCampoCorp);
		tfCampoCorp.setColumns(10);
		tfCampoCorp.setText(Sesion.getUsuarioLogueado().getCorp());
	}

	public void modificar()
	{
		btModificarDatos.setText("Guardar cambios");
		tfCampoUsuario.setEditable(true);
		tfCampoCorreo.setEditable(true);
		tfCampoCorp.setEditable(true);
	}
	
	public void guardarCambios()
	{
		btModificarDatos.setText("Modifcar datos");
		tfCampoUsuario.setEditable(false);
		tfCampoCorreo.setEditable(false);
		tfCampoCorp.setEditable(false);
		Sesion.getUsuarioLogueado().modificarInformacion(tfCampoCorreo.getText(), tfCampoUsuario.getText(), tfCampoCorp.getText());
	}
	
	public void controlador(ActionListener ctr)
	{
		btModificarDatos.addActionListener(ctr);
		btModificarDatos.setActionCommand("MODIFICAR");
		btCerrarSesin.addActionListener(ctr);
		btCerrarSesin.setActionCommand("CERRAR SESION");
		btAtrs.addActionListener(ctr);
		btAtrs.setActionCommand("ATRAS");
		btnEliminarCuenta.addActionListener(ctr);
		btnEliminarCuenta.setActionCommand("ELIMINAR CUENTA");
	}
}
