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

public class VistaNuevoConferencia extends JFrame {

	private JPanel panalPrincipal;
	private JTextField textField;
	private JLabel lblNombreDeConferencia;
	private JButton btnCancelar;
	private JButton btnCrearConferencia;

	/**
	 * Create the frame.
	 */
	
	public static void abrirVentana() {
		try {
			VistaNuevoConferencia frame = new VistaNuevoConferencia();
			frame.controlador(new ControladorNuevoConferencia(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public VistaNuevoConferencia() {
		super("Nueva Conferencia");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 158);
		panalPrincipal = new JPanel();
		panalPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panalPrincipal);
		panalPrincipal.setLayout(null);
		
		lblNombreDeConferencia = new JLabel("Nombre de la conferencia");
		lblNombreDeConferencia.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblNombreDeConferencia.setBounds(12, 13, 213, 40);
		panalPrincipal.add(lblNombreDeConferencia);
		
		textField = new JTextField();
		textField.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		textField.setBounds(232, 18, 279, 32);
		panalPrincipal.add(textField);
		textField.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnCancelar.setBounds(12, 76, 129, 40);
		panalPrincipal.add(btnCancelar);
		
		btnCrearConferencia = new JButton("Crear conferencia");
		btnCrearConferencia.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnCrearConferencia.setBounds(386, 76, 155, 40);
		panalPrincipal.add(btnCrearConferencia);
	}
	
	public void controlador(ActionListener ctr)
	{
		btnCrearConferencia.addActionListener(ctr);
		btnCrearConferencia.setActionCommand("CREAR");
		btnCancelar.addActionListener(ctr);
		btnCancelar.setActionCommand("CANCELAR");
	}
	
	public String getTextoNombre() {
		// TODO Auto-generated method stub
		return textField.getText();
	}

}
