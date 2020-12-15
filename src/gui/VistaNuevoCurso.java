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

public class VistaNuevoCurso extends JFrame {

	private JPanel panalPrincipal;
	private JTextField textField;
	private JLabel lblNombreDelCurso;
	private JButton btnCancelar;
	private JButton btnCrearCurso;

	/**
	 * Create the frame.
	 */
	public VistaNuevoCurso() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 158);
		panalPrincipal = new JPanel();
		panalPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panalPrincipal);
		panalPrincipal.setLayout(null);
		
		lblNombreDelCurso = new JLabel("Nombre del curso");
		lblNombreDelCurso.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblNombreDelCurso.setBounds(72, 13, 153, 40);
		panalPrincipal.add(lblNombreDelCurso);
		
		textField = new JTextField();
		textField.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		textField.setBounds(232, 18, 279, 32);
		panalPrincipal.add(textField);
		textField.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnCancelar.setBounds(12, 76, 129, 40);
		panalPrincipal.add(btnCancelar);
		
		btnCrearCurso = new JButton("Crear curso");
		btnCrearCurso.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnCrearCurso.setBounds(412, 76, 129, 40);
		panalPrincipal.add(btnCrearCurso);
	}
	
	public static void abrirVentana() {
		try {
			VistaNuevoCurso frame = new VistaNuevoCurso();
			frame.controlador(new ControladorNuevoCurso(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void controlador(ActionListener ctr)
	{
		btnCrearCurso.addActionListener(ctr);
		btnCrearCurso.setActionCommand("CREAR");
		btnCancelar.addActionListener(ctr);
		btnCancelar.setActionCommand("CANCELAR");
	}

	public String getTextoNombre() {
		return textField.getText();
	}

}
