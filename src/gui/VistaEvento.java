package gui;

import modelo.Evento;
import modelo.Sesion;
import modelo.contenido.Contenido;

import javax.swing.*;
import java.util.List;
import java.awt.*;

public class VistaEvento extends JFrame {

	private final Evento evento;

	private JPanel panelPrincipal;
	private JPanel panelContenido;
	private JPanel panelForos;
	private JPanel panelInferior;
	private JScrollPane scrollModoEdicion;

	private JButton btnAnularInscripcin;
	private JButton btnVolver;
	private JButton btnEliminarEvento;
	private JComboBox<String> cbNuevoContenido;

	private JLabel lblTituloCurso;
	private JLabel lblDatosCurso;

	private final boolean soyCreadorEvento;
	private boolean enModoEdicion;

	/**
	 * Create the frame.
	 */
	public VistaEvento(Evento evento)
	{
		super("Evento: " + evento.getNombre());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.evento = evento;

		crearGUI();

		soyCreadorEvento = Sesion.getUsuarioLogueado().getCorreo().equals(evento.getCreador().getCorreo());
		if (soyCreadorEvento) {
			setModoEdicion(false);
		}

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

		btnEliminarEvento.addActionListener(ctr);
		btnEliminarEvento.setActionCommand("ELIMINAR");

		cbNuevoContenido.addActionListener(ctr);
		cbNuevoContenido.setActionCommand("NUEVO CONTENIDO");
	}

	public void setModoEdicion(boolean mostrar) {
		enModoEdicion = mostrar;
		if (mostrar) {
			panelPrincipal.add(scrollModoEdicion, BorderLayout.EAST);
			btnAnularInscripcin.setText("Ver como alumno");
		} else {
			panelPrincipal.remove(scrollModoEdicion);
			btnAnularInscripcin.setText("Editar");
		}
	}

	public void alternarModoEdicion() {
		setModoEdicion(!enModoEdicion);
	}

	public boolean getSoyCreadorEvento() {
		return soyCreadorEvento;
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
		lblTituloCurso.setHorizontalAlignment(JLabel.CENTER);

		// Mostrar algunos datos del evento (duración, número de clases, enlace...)
		lblDatosCurso = evento.getSubtituloPaginaEvento();
		lblDatosCurso.setFont(UtilidadesGUI.FUENTE);
		lblDatosCurso.setHorizontalAlignment(JLabel.CENTER);

		// Añadir contenidos
		refrescarContenido();

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
		JPanel panelModoEdicion = new JPanel();
		BoxLayout layout = new BoxLayout(panelModoEdicion, BoxLayout.Y_AXIS);
		panelModoEdicion.setLayout(layout);
		panelModoEdicion.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		JLabel tituloModoEdicion = new JLabel("Modo Edición");
		tituloModoEdicion.setFont(UtilidadesGUI.FUENTE_TITULOS);
		tituloModoEdicion.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelModoEdicion.add(tituloModoEdicion);

		panelModoEdicion.add(Box.createRigidArea(new Dimension(0, 25)));

		cbNuevoContenido = new JComboBox<>(new String[]{"Añadir contenido...", "Texto", "Enlace"});
		cbNuevoContenido.setFont(UtilidadesGUI.FUENTE);
		cbNuevoContenido.setMaximumSize(new Dimension(180, 40));
		cbNuevoContenido.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelModoEdicion.add(cbNuevoContenido);

		panelModoEdicion.add(Box.createRigidArea(new Dimension(0, 25)));

		JLabel descripcionModoEdicion = new JLabel(
				"<html><p>Pulse en los iconos de cada elemento del evento para editarlo o borrarlo.</p></html>");
		descripcionModoEdicion.setFont(UtilidadesGUI.FUENTE);
		descripcionModoEdicion.setAlignmentX(Component.LEFT_ALIGNMENT);
		descripcionModoEdicion.setPreferredSize(new Dimension(180, -1));
		panelModoEdicion.add(descripcionModoEdicion);

		panelModoEdicion.add(Box.createRigidArea(new Dimension(0, 25)));

		btnEliminarEvento = new JButton("Eliminar evento");
		btnEliminarEvento.setFont(UtilidadesGUI.FUENTE);
		btnEliminarEvento.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelModoEdicion.add(btnEliminarEvento);

		scrollModoEdicion = new JScrollPane(panelModoEdicion);
		scrollModoEdicion.setPreferredSize(new Dimension(240, -1));
	}

	private void refrescarContenido() {
		panelContenido.removeAll();

		panelContenido.add(lblTituloCurso);
		lblTituloCurso.setMaximumSize(new Dimension(Integer.MAX_VALUE, lblTituloCurso.getPreferredSize().height));

		panelContenido.add(lblDatosCurso);
		lblDatosCurso.setMaximumSize(new Dimension(Integer.MAX_VALUE, lblDatosCurso.getPreferredSize().height));

		List<Contenido> contenidos = evento.getContenidos();
		for (Contenido c : contenidos) {
			panelContenido.add(c.getVista());
		}

	}

	public Evento getEvento(){ return evento; }
}
