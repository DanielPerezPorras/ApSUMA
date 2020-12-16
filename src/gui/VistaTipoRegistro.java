package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaTipoRegistro extends JFrame {

	private JPanel panelPrincipal;
	private JButton btTutor;
	private JButton btEstudiante;
	private JButton btColaborador;
	private JButton btAtras;

	/**
	 * Launch the application.
	 */

	public static void abrirVentana() {
		try {
			VistaTipoRegistro frame = new VistaTipoRegistro();
			frame.controlador(new ControladorTipoRegistro(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public VistaTipoRegistro() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 291);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btEstudiante = new JButton("Estudiante");
		btEstudiante.setBackground(new Color(0, 102, 204));
		btEstudiante.setForeground(new Color(0, 0, 0));
		btEstudiante.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 20));
		btEstudiante.setBounds(12, 49, 156, 59);
		panelPrincipal.add(btEstudiante);
		
		btTutor = new JButton("Tutor");
		btTutor.setBackground(new Color(204, 0, 0));
		btTutor.setForeground(new Color(0, 0, 0));
		btTutor.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 20));
		btTutor.setBounds(193, 49, 156, 59);
		panelPrincipal.add(btTutor);
		
		btColaborador = new JButton("Colaborador");
		btColaborador.setBackground(new Color(0, 153, 51));
		btColaborador.setForeground(new Color(0, 0, 0));
		btColaborador.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 20));
		btColaborador.setBounds(374, 49, 156, 59);
		panelPrincipal.add(btColaborador);
		
		btAtras = new JButton("Atr\u00E1s");
		btAtras.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 15));
		btAtras.setBounds(12, 218, 97, 25);
		panelPrincipal.add(btAtras);
	}
	
	public void controlador(ActionListener ctr)
	{
		btAtras.addActionListener(ctr);
		btEstudiante.addActionListener(ctr);
		btColaborador.addActionListener(ctr);
		btTutor.addActionListener(ctr);
		btAtras.setActionCommand("ATRAS");
		btEstudiante.setActionCommand("ESTUDIANTE");
		btColaborador.setActionCommand("COLABORADOR");
		btTutor.setActionCommand("TUTOR");
	}

}
