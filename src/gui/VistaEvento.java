package gui;

import modelo.Evento;

import javax.swing.*;
import java.awt.*;

public class VistaEvento extends JFrame {

	private Evento evento;

	private JPanel panelPrincipal;
	private JPanel panelModoEdicion;
	private JPanel panelContenido;
	private JPanel panelForos;
	private JPanel panelInferior;

	private JButton btnAnularInscripcin;
	private JLabel lblTituloCurso;
	private JButton btnVolver;

	/**
	 * Create the frame.
	 */
	public VistaEvento(Evento evento)
	{
		super("Evento: " + evento.getNombre());
		this.evento = evento;

		crearGUI();
		alternarModoEdicion(true);

		/*
		
		lblbienvenidoAlCurso = new JLabel("\u00A1Bienvenido al curso de ");
		lblbienvenidoAlCurso.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblbienvenidoAlCurso.setBounds(137, 13, 221, 50);
		panelContenido.add(lblbienvenidoAlCurso);
		
		lblCurso = new JLabel("ACTIVIDAD !");
		lblCurso.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		lblCurso.setBounds(362, 13, 214, 50);
		panelContenido.add(lblCurso);
		
		lbFoto = new JLabel("");
		lbFoto.setBounds(12, 77, 147, 144);
		UtilidadesGUI.ajustarImagenALabel(lbFoto, "/recursosApp/gato.png");
		panelContenido.add(lbFoto);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblDescripcion.setBounds(209, 76, 481, 286);
		panelContenido.add(lblDescripcion);
		
		lblLinkMeetingCurso = new JLabel("Link Meeting Actividad:");
		lblLinkMeetingCurso.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblLinkMeetingCurso.setBounds(12, 468, 200, 32);
		panelContenido.add(lblLinkMeetingCurso);
		
		lbHyperLink = new JLabel("Clase");
		lbHyperLink.setForeground(Color.BLUE.darker());
		lbHyperLink.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 16));
		lbHyperLink.setBounds(228, 473, 292, 27);
		lbHyperLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelContenido.add(lbHyperLink);

		*/

	}

    public static void abrirVentana(Evento ev) {
		try {
			VistaEvento frame = new VistaEvento(ev);
			frame.controlador(new ControladorEvento(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void controlador(ControladorEvento ctr)
	{
		btnAnularInscripcin.addActionListener(ctr);
		btnAnularInscripcin.setActionCommand("ANULAR");
		
		btnVolver.addActionListener(ctr);
		btnVolver.setActionCommand("VOLVER");
		
		//lbHyperLink.addMouseListener(ctr);
	}

	public void alternarModoEdicion(boolean mostrar) {
		if (mostrar) {
			panelPrincipal.add(panelModoEdicion, BorderLayout.EAST);
		} else {
			panelPrincipal.remove(panelModoEdicion);
		}
	}

	private void crearGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		setContentPane(panelPrincipal);

		JPanel panelCentral = new JPanel(new BorderLayout());
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(UtilidadesGUI.FUENTE);
		panelCentral.add(tabbedPane, BorderLayout.CENTER);

		crearPanelContenido();
		tabbedPane.addTab("Contenido", null, new JScrollPane(panelContenido));

		crearPanelForos();
		tabbedPane.addTab("Foros", null, panelForos);

		crearPanelInferior();
		panelCentral.add(panelInferior, BorderLayout.SOUTH);

		crearPanelModoEdicion();

	}

	private void crearPanelContenido() {
		panelContenido = new JPanel();
		BoxLayout layout = new BoxLayout(panelContenido, BoxLayout.Y_AXIS);
		panelContenido.setLayout(layout);
		panelContenido.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		// Mostrar el título del evento
		lblTituloCurso = new JLabel(evento.getNombre());
		lblTituloCurso.setFont(UtilidadesGUI.FUENTE_TITULOS);
		lblTituloCurso.setBackground(Color.RED);
		panelContenido.add(lblTituloCurso);

	}

	private void crearPanelForos() {
		panelForos = new JPanel();
	}

	private void crearPanelInferior() {
		panelInferior = new JPanel();
		BoxLayout layout = new BoxLayout(panelInferior, BoxLayout.X_AXIS);
		panelInferior.setLayout(layout);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(UtilidadesGUI.FUENTE);
		panelInferior.add(btnVolver);

		panelInferior.add(Box.createHorizontalGlue());

		btnAnularInscripcin = new JButton("Anular Inscripci\u00F3n");
		btnAnularInscripcin.setFont(UtilidadesGUI.FUENTE);
		panelInferior.add(btnAnularInscripcin);

	}

	private void crearPanelModoEdicion() {
		panelModoEdicion = new JPanel();
		BoxLayout layout = new BoxLayout(panelModoEdicion, BoxLayout.Y_AXIS);
		panelModoEdicion.setLayout(layout);
		panelModoEdicion.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		JLabel tituloModoEdicion = new JLabel("Modo Edición");
		tituloModoEdicion.setFont(UtilidadesGUI.FUENTE_TITULOS);
		panelModoEdicion.add(tituloModoEdicion);

		panelModoEdicion.setPreferredSize(new Dimension(240, -1));
	}
	
	
}
