package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;

public class VistaConferenciaAlumno extends JFrame {

	private JPanel panelPrincipal;
	private JTabbedPane tabbedPane;
	private JPanel pInformacion;
	private JButton btnAnularInscripcin;
	private JLabel lblbienvenidoAlCurso;
	private JLabel lblCurso;
	private JLabel lbFoto;
	private JLabel lblDescripcion;
	private JLabel lblLinkMeetingCurso;
	private JLabel lbHyperLink;

	public static void abrirVentana() {
		try {
			VistaConferenciaAlumno frame = new VistaConferenciaAlumno();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public VistaConferenciaAlumno() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 579);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(12, 13, 826, 518);
		panelPrincipal.add(tabbedPane);
		
		pInformacion = new JPanel();
		tabbedPane.addTab("Informaci�n", null, pInformacion, null);
		pInformacion.setLayout(null);
		
		btnAnularInscripcin = new JButton("Anular Inscripci\u00F3n");
		btnAnularInscripcin.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		btnAnularInscripcin.setBounds(556, 471, 155, 27);
		pInformacion.add(btnAnularInscripcin);
		
		lblbienvenidoAlCurso = new JLabel("\u00A1Bienvenido al curso de ");
		lblbienvenidoAlCurso.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblbienvenidoAlCurso.setBounds(137, 13, 221, 50);
		pInformacion.add(lblbienvenidoAlCurso);
		
		lblCurso = new JLabel("CONFERENCIA !");
		lblCurso.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblCurso.setBounds(362, 13, 214, 50);
		pInformacion.add(lblCurso);
		
		lbFoto = new JLabel("");
		lbFoto.setBounds(12, 77, 147, 144);
		UtilidadesGUI.ajustarImagenALabel(lbFoto, "/recursosApp/gato.png");
		pInformacion.add(lbFoto);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblDescripcion.setBounds(209, 76, 481, 286);
		pInformacion.add(lblDescripcion);
		
		lblLinkMeetingCurso = new JLabel("Link Meeting Conferencia:");
		lblLinkMeetingCurso.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblLinkMeetingCurso.setBounds(12, 468, 200, 32);
		pInformacion.add(lblLinkMeetingCurso);
		
		lbHyperLink = new JLabel("Clase");
		lbHyperLink.setForeground(Color.BLUE.darker());
		lbHyperLink.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 16));
		lbHyperLink.setBounds(228, 473, 292, 27);
		lbHyperLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pInformacion.add(lbHyperLink);
		tabbedPane.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
	}
	
	public void controlador(ActionListener ctr)
	{
		btnAnularInscripcin.addActionListener(ctr);
		btnAnularInscripcin.setActionCommand("ANULAR");
	}
	
	public void controlador(MouseListener ctr)
	{
		lbHyperLink.addMouseListener(ctr);
	}
	
}
