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

import javax.swing.JTextField;
import javax.swing.JButton;

public class VistaNuevoActividades extends JFrame {

	private JPanel panalPrincipal;
	private JTextField textField;
	private JLabel lblNombreDeActividades;
	private JButton btnCancelar;
	private JButton btnCrearActividades;

	/**
	 * Create the frame.
	 */
	
	public static void abrirVentana() {
		try {
			VistaNuevoActividades frame = new VistaNuevoActividades();
			frame.controlador(new ControladorNuevoActividades(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public VistaNuevoActividades() {
		super("Nueva Actividad");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 158);
		panalPrincipal = new JPanel();
		panalPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panalPrincipal);
		panalPrincipal.setLayout(null);
		
		lblNombreDeActividades = new JLabel("Nombre de la actividad");
		lblNombreDeActividades.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblNombreDeActividades.setBounds(12, 13, 213, 40);
		panalPrincipal.add(lblNombreDeActividades);
		
		textField = new JTextField();
		textField.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		textField.setBounds(232, 18, 279, 32);
		panalPrincipal.add(textField);
		textField.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnCancelar.setBounds(12, 76, 129, 40);
		panalPrincipal.add(btnCancelar);
		
		btnCrearActividades = new JButton("Crear actividades");
		btnCrearActividades.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnCrearActividades.setBounds(386, 76, 155, 40);
		panalPrincipal.add(btnCrearActividades);
	}
	
	public void controlador(ActionListener ctr)
	{
		btnCrearActividades.addActionListener(ctr);
		btnCrearActividades.setActionCommand("CREAR");
		btnCancelar.addActionListener(ctr);
		btnCancelar.setActionCommand("CANCELAR");
	}

	public String getTextoNombre() {
		// TODO Auto-generated method stub
		return textField.getText();
	}

}
