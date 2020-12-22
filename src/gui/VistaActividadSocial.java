package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaActividadSocial extends JFrame {

	private JPanel panelPrincipal;
	private JTabbedPane tabbedPane;
	private JPanel pInicio;
	private JButton btnAnularInscripcin;
	private JLabel lblbienvenidoAlCurso;
	private JLabel lblCurso;
	private JLabel lbFoto;
	private JLabel lblDescripcion;
	private JLabel lblLinkMeetingCurso;
	private JLabel lbHyperLink;
	private JButton btnVolver;

	/**
	 * Create the frame.
	 */
	public VistaActividadSocial()
	{
		super("Actividad Social");
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
		
		pInicio = new JPanel();
		tabbedPane.addTab("Inicio", null, pInicio, null);
		pInicio.setLayout(null);
		
		btnAnularInscripcin = new JButton("Anular Inscripci\u00F3n");
		btnAnularInscripcin.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		btnAnularInscripcin.setBounds(556, 471, 155, 27);
		pInicio.add(btnAnularInscripcin);
		
		lblbienvenidoAlCurso = new JLabel("\u00A1Bienvenido al curso de ");
		lblbienvenidoAlCurso.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblbienvenidoAlCurso.setBounds(137, 13, 221, 50);
		pInicio.add(lblbienvenidoAlCurso);
		
		lblCurso = new JLabel("ACTIVIDAD !");
		lblCurso.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblCurso.setBounds(362, 13, 214, 50);
		pInicio.add(lblCurso);
		
		lbFoto = new JLabel("");
		lbFoto.setBounds(12, 77, 147, 144);
		UtilidadesGUI.ajustarImagenALabel(lbFoto, "/recursosApp/gato.png");
		pInicio.add(lbFoto);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblDescripcion.setBounds(209, 76, 481, 286);
		pInicio.add(lblDescripcion);
		
		lblLinkMeetingCurso = new JLabel("Link Meeting Actividad:");
		lblLinkMeetingCurso.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblLinkMeetingCurso.setBounds(12, 468, 200, 32);
		pInicio.add(lblLinkMeetingCurso);
		
		lbHyperLink = new JLabel("Clase");
		lbHyperLink.setForeground(Color.BLUE.darker());
		lbHyperLink.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 16));
		lbHyperLink.setBounds(228, 473, 292, 27);
		lbHyperLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pInicio.add(lbHyperLink);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 434, 89, 23);
		pInicio.add(btnVolver);
		tabbedPane.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
	}

    public static void abrirVentana() {
		try {
			VistaActividadSocial frame = new VistaActividadSocial();
			frame.controlador(new ControladorActividadSocial(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void controlador(ControladorActividadSocial ctr)
	{
		btnAnularInscripcin.addActionListener(ctr);
		btnAnularInscripcin.setActionCommand("ANULAR");
		
		btnVolver.addActionListener(ctr);
		btnVolver.setActionCommand("VOLVER");
		
		lbHyperLink.addMouseListener(ctr);
	}
	
	
}
