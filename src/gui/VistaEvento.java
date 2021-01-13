package gui;

import gui.contenido.VistaContenido;
import modelo.*;
import modelo.contenido.Contenido;

import javax.swing.*;
import java.util.ArrayList;
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
	private JButton btnEditarEvento;
	private JButton btnEliminarEvento;
	private JComboBox<String> cbNuevoContenido;

	private JList<Foro> listaForos;
	private JLabel lblNombreForo;
	private JTextField tfMensaje;
	private JButton btnEnviar;
	private JTextPane tpMensajes;
	private JButton btnCrearForo;
	private JButton btnEliminarForo;

	private JLabel lblTituloCurso;
	private JLabel lblDatosCurso;
	private final List<VistaContenido> vistasContenidos = new ArrayList<>();
	private List<Contenido> contenidos;
	private JPanel panelGestionarForos;
	private JPanel panelForosIzquierda;

	private final boolean soyCreadorEvento;
	private ControladorEvento controlador;
	private boolean enModoEdicion;

	public VistaEvento(Evento evento)  {

		super("Evento: " + evento.getNombre());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.evento = evento;

		soyCreadorEvento = Sesion.getUsuarioLogueado().getCorreo().equals(evento.getCreador().getCorreo());
		crearGUI();

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
    public void controlador(ControladorEvento ctr) {
		controlador = ctr;

		btnAnularInscripcin.addActionListener(ctr);
		btnAnularInscripcin.setActionCommand("ANULAR");
		
		btnVolver.addActionListener(ctr);
		btnVolver.setActionCommand("VOLVER");

		btnEditarEvento.addActionListener(ctr);
		btnEditarEvento.setActionCommand("EDITAR_EVENTO");

		btnEliminarEvento.addActionListener(ctr);
		btnEliminarEvento.setActionCommand("ELIMINAR");

		cbNuevoContenido.addActionListener(ctr);
		cbNuevoContenido.setActionCommand("NUEVO CONTENIDO");

		for (VistaContenido v : vistasContenidos) {
			v.controlador(ctr);
		}

		listaForos.addListSelectionListener(ctr);

		btnEnviar.addActionListener(ctr);
		btnEnviar.setActionCommand("ENVIAR MENSAJE");

		if (soyCreadorEvento) {
			btnCrearForo.addActionListener(ctr);
			btnCrearForo.setActionCommand("CREAR FORO");
			btnEliminarForo.addActionListener(ctr);
			btnEliminarForo.setActionCommand("ELIMINAR FORO");
		}

	}

	public Evento getEvento() { return evento; }

	public void setModoEdicion(boolean mostrar) {
		enModoEdicion = mostrar;
		if (mostrar) {
			panelPrincipal.add(scrollModoEdicion, BorderLayout.EAST);
			btnAnularInscripcin.setText("Ver como alumno");
			panelForosIzquierda.add(panelGestionarForos, BorderLayout.SOUTH);
		} else {
			panelPrincipal.remove(scrollModoEdicion);
			btnAnularInscripcin.setText("Editar");
			panelForosIzquierda.remove(panelGestionarForos);
		}
		for (VistaContenido v : vistasContenidos) {
			v.setModoEdicion(mostrar);
		}
	}
	public void alternarModoEdicion() {
		setModoEdicion(!enModoEdicion);
	}

	public boolean getSoyCreadorEvento() {
		return soyCreadorEvento;
	}
	public String getSeleccionNuevoContenido() {
		return (String)cbNuevoContenido.getSelectedItem();
	}
	public Contenido getContenidoPorPosicion(int pos) {
		if (pos >= 0 && pos < contenidos.size()) {
			return contenidos.get(pos);
		} else {
			return null;
		}
	}
	public int getPosicionDeContenido(Contenido c) {
		return contenidos.indexOf(c);
	}
	public void refrescarContenido() {
		panelContenido.removeAll();

		panelContenido.add(lblTituloCurso);
		lblTituloCurso.setMaximumSize(new Dimension(Integer.MAX_VALUE, lblTituloCurso.getPreferredSize().height));

		panelContenido.add(lblDatosCurso);
		lblDatosCurso.setMaximumSize(new Dimension(Integer.MAX_VALUE, lblDatosCurso.getPreferredSize().height));

		System.out.println("Recargando contenido...");
		contenidos = evento.getContenidos();
		vistasContenidos.clear();
		for (Contenido c : contenidos) {
			VistaContenido v = c.getVista(enModoEdicion);
			v.controlador(controlador);
			vistasContenidos.add(v);
			panelContenido.add(v);
		}
		System.out.println("Contenido recargado");

		revalidate();
		repaint();
	}
	public void actualizarTituloEvento() {
		lblTituloCurso.setText(evento.getNombre());
		crearSubtitulo();
	}

	public void refrescarListaForos() {
		List<Foro> foros = evento.getForos();
		Foro[] forosArray = new Foro[foros.size()];
		foros.toArray(forosArray);
		listaForos.setListData(forosArray);
	}
	public Foro getForoSeleccionado() {
		return listaForos.getSelectedValue();
	}
	public void cargarMensajes() {
		if (getForoSeleccionado() != null) {
			StringBuilder documento = new StringBuilder();

			List<MensajeForo> mensajes = getForoSeleccionado().getMensajes();
			if (mensajes.size() > 0) {
				for (MensajeForo m : mensajes) {
					documento.append(m.getHtml());
				}
			} else {
				documento.append("No hay mensajes en este foro");
			}


			tpMensajes.setText("<html>" + documento + "</html>");
		} else {
			tpMensajes.setText("<html>Seleccione un foro para visualizar aquí los mensajes.</html>");
		}
	}
	public String getTextoAEnviar() {
		return tfMensaje.getText();
	}
	public void vaciarTextoAEnviar() {
		tfMensaje.setText("");
	}

	// CÓDIGO PARA CREAR LA GUI
	// ------------------------

	private void crearGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
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
		crearSubtitulo();

		// Añadir contenidos
		refrescarContenido();

	}

	private void crearPanelForos() {
		panelForos = new JPanel(new GridLayout(1, 2));
		panelForos.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		panelForosIzquierda = new JPanel(new BorderLayout());
		panelForosIzquierda.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JLabel tituloListaForos = new JLabel("Foros");
		tituloListaForos.setFont(UtilidadesGUI.FUENTE_TITULOS);
		tituloListaForos.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelForosIzquierda.add(tituloListaForos, BorderLayout.NORTH);

		JPanel izdaCentro = new JPanel(new GridLayout(1, 1));
		izdaCentro.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		listaForos = new JList<>();
		listaForos.setFont(UtilidadesGUI.FUENTE);
		listaForos.setAlignmentX(Component.LEFT_ALIGNMENT);
		izdaCentro.add(new JScrollPane(listaForos));
		panelForosIzquierda.add(izdaCentro, BorderLayout.CENTER);

		if (soyCreadorEvento) {
			panelGestionarForos = new JPanel(new GridLayout(1, 2));
			panelGestionarForos.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

			btnCrearForo = new JButton("Crear foro");
			btnCrearForo.setFont(UtilidadesGUI.FUENTE);
			panelGestionarForos.add(btnCrearForo);

			btnEliminarForo = new JButton("Eliminar foro");
			btnEliminarForo.setFont(UtilidadesGUI.FUENTE);
			panelGestionarForos.add(btnEliminarForo);
		}

		JPanel dcha = new JPanel();
		dcha.setLayout(new BorderLayout());
		dcha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel dchaNorte = new JPanel();
		dchaNorte.setLayout(new BoxLayout(dchaNorte, BoxLayout.X_AXIS));

		lblNombreForo = new JLabel(" ");
		lblNombreForo.setFont(UtilidadesGUI.FUENTE_TITULOS);
		dchaNorte.add(lblNombreForo, BorderLayout.NORTH);

		JPanel dchaCentro = new JPanel(new GridLayout(1, 1));
		dchaCentro.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		tpMensajes = new JTextPane();
		tpMensajes.setContentType("text/html");
		tpMensajes.setText("<html>Seleccione un foro para visualizar aquí los mensajes.</html>");
		tpMensajes.setEditable(false);
		dchaCentro.add(new JScrollPane(tpMensajes));

		JPanel dchaSur = new JPanel();
		dchaSur.setLayout(new BoxLayout(dchaSur, BoxLayout.X_AXIS));

		tfMensaje = new JTextField();
		tfMensaje.setFont(UtilidadesGUI.FUENTE);
		dchaSur.add(tfMensaje);
		btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(UtilidadesGUI.FUENTE);
		dchaSur.add(btnEnviar);

		dcha.add(dchaNorte, BorderLayout.NORTH);
		dcha.add(dchaCentro, BorderLayout.CENTER);
		dcha.add(dchaSur, BorderLayout.SOUTH);


		panelForos.add(panelForosIzquierda);
		panelForos.add(dcha);

		refrescarListaForos();

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

		btnEditarEvento = new JButton("Cambiar nombre");
		btnEditarEvento.setFont(UtilidadesGUI.FUENTE);
		btnEditarEvento.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelModoEdicion.add(btnEditarEvento);

		panelModoEdicion.add(Box.createRigidArea(new Dimension(0, 25)));

		btnEliminarEvento = new JButton("Eliminar evento");
		btnEliminarEvento.setFont(UtilidadesGUI.FUENTE);
		btnEliminarEvento.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelModoEdicion.add(btnEliminarEvento);

		scrollModoEdicion = new JScrollPane(panelModoEdicion);
		scrollModoEdicion.setPreferredSize(new Dimension(240, -1));
	}

	private void crearSubtitulo() {
		lblDatosCurso = evento.getSubtituloPaginaEvento();
		lblDatosCurso.setFont(UtilidadesGUI.FUENTE);
		lblDatosCurso.setHorizontalAlignment(JLabel.CENTER);
	}

}
