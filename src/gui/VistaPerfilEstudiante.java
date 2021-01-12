package gui;

import modelo.Curso;
import modelo.Estudiante;
import modelo.Sesion;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;

import java.awt.Container;

import java.awt.SystemColor;

public class VistaPerfilEstudiante extends JFrame {

	private JPanel panelPrincipal;
	private JLabel lLogo;
	private JLabel lblNombreUsuario;
	private JLabel lblCorreo;
	private JButton btModificarDatos;
	private JButton btAtrs;
	private JButton btCerrarSesin;
	private JButton btnEliminarCuenta;
	private JTextField tfCampoUsuario;
	private JTextField tfCampoCorreo;


	
	/**
	 * Create the frame.
	 */
	public VistaPerfilEstudiante() {
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 358);
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
		
		btModificarDatos = new JButton("Modificar datos");
		btModificarDatos.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btModificarDatos.setBackground(SystemColor.activeCaption);
		btModificarDatos.setBounds(352, 128, 187, 25);
		panelPrincipal.add(btModificarDatos);
		
		btAtrs = new JButton("Atr\u00E1s");
		btAtrs.setBackground(SystemColor.activeCaption);
		btAtrs.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btAtrs.setBounds(529, 274, 143, 41);
		panelPrincipal.add(btAtrs);
		
		btCerrarSesin = new JButton("Cerrar sesi\u00F3n");
		btCerrarSesin.setBackground(SystemColor.activeCaption);
		btCerrarSesin.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btCerrarSesin.setBounds(13, 274, 143, 41);
		panelPrincipal.add(btCerrarSesin);
		
		btnEliminarCuenta = new JButton("Eliminar cuenta");
		btnEliminarCuenta.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnEliminarCuenta.setBackground(SystemColor.activeCaption);
		btnEliminarCuenta.setBounds(271, 274, 143, 41);
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

	public void modificar()
	{
		btModificarDatos.setText("Guardar cambios");
		tfCampoUsuario.setEditable(true);
		tfCampoCorreo.setEditable(true);
	}
	
	public void guardarCambios()
	{
		btModificarDatos.setText("Modifcar datos");
		tfCampoUsuario.setEditable(false);
		tfCampoCorreo.setEditable(false);
		if (Usuario.buscarUsuario(tfCampoCorreo.getText()) == null || tfCampoCorreo.getText().equals(Sesion.getUsuarioLogueado().getCorreo())) {
			Sesion.getUsuarioLogueado().modificarInformacion(tfCampoCorreo.getText(), tfCampoUsuario.getText(), null);
		} else {
			JOptionPane.showMessageDialog(this,
					"Correo no válido",
					"Error modificar datos",
					JOptionPane.ERROR_MESSAGE);
			tfCampoUsuario.setText(Sesion.getUsuarioLogueado().getNombreUsuario());
			tfCampoCorreo.setText(Sesion.getUsuarioLogueado().getCorreo());
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
		btnEliminarCuenta.addActionListener(ctr);
		btnEliminarCuenta.setActionCommand("ELIMINAR CUENTA");
	}
}
