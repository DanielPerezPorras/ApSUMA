package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Color;

public class VistaTipoRegistro extends JFrame {

	private JPanel panelPrincipal;
	private JButton btTutor;
	private JButton btEstudiante;
	private JButton btInvitado;
	private JButton btAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaTipoRegistro frame = new VistaTipoRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void abrirVentana() {
		try {
			VistaTipoRegistro frame = new VistaTipoRegistro();
			frame.controlador(new ControladorRegistro(frame));
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
		
		btInvitado = new JButton("Colaborador");
		btInvitado.setBackground(new Color(0, 153, 51));
		btInvitado.setForeground(new Color(0, 0, 0));
		btInvitado.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 20));
		btInvitado.setBounds(374, 49, 156, 59);
		panelPrincipal.add(btInvitado);
		
		btAtras = new JButton("Atr\u00E1s");
		btAtras.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 15));
		btAtras.setBounds(12, 218, 97, 25);
		panelPrincipal.add(btAtras);
	}
	
	public void controlador(ActionListener ctr)
	{
		btAtras.addActionListener(ctr);
		btEstudiante.addActionListener(ctr);
		btInvitado.addActionListener(ctr);
		btTutor.addActionListener(ctr);
		btAtras.setActionCommand("ATRAS");
		btEstudiante.setActionCommand("ESTUDIANTE");
		btInvitado.setActionCommand("INVITADO");
		btTutor.setActionCommand("TUTOR");
	}

}
