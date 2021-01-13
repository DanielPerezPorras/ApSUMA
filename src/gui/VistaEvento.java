package gui;

import gui.contenido.VistaContenido;
import modelo.BD;
import modelo.Evento;
import modelo.Sesion;
import modelo.Usuario;
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
	private JPanel panelUsuarios;
	private JPanel panelInferior;
	private JScrollPane scrollModoEdicion;

	private JButton btnAnularInscripcin;
	private JButton btnVolver;
	private JButton btnEditarEvento;
	private JButton btnEliminarEvento;
	private JComboBox<String> cbNuevoContenido;

	private JLabel lblTituloCurso;
	private JLabel lblDatosCurso;
	private JLabel lblBuscaUsuarios;
	private final List<VistaContenido> vistasContenidos = new ArrayList<>();
	private List<Contenido> contenidos;

	private final boolean soyCreadorEvento;
	private ControladorEvento controlador;
	private boolean enModoEdicion;

	private JPanel panelBusqueda;
	private JTextField textField;
	private JButton btnBuscar_1;
	private JList listResultado;
	private JPanel panelDatos;
	private JLabel lblNU;
	private JLabel lblCU;
	private JLabel lblCC;
	private JLabel lblNombreUsuario;
	private JPanel panel;
	private JButton btSancionar;
	private JButton btElimSanc;
	private JButton btKick;
	private JPanel panelBotones;
	private JButton btAdd;
	private JLabel lblCorreoUsuario;
	private JLabel lblCorreoCorpUsuario;

	public VistaEvento(Evento evento)  {

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

		btnBuscar_1.addActionListener(ctr);
		btnBuscar_1.setActionCommand("BUSCAR");

		textField.addActionListener(ctr);
		textField.setActionCommand("BUSCAR");

		btSancionar.addActionListener(ctr);
		btSancionar.setActionCommand("SANCION");

		btElimSanc.addActionListener(ctr);
		btElimSanc.setActionCommand("ELIM_SANCION");

		btKick.addActionListener(ctr);
		btKick.setActionCommand("EXPULSAR");

		btAdd.addActionListener(ctr);
		btAdd.setActionCommand("ANYADIR");

		listResultado.addListSelectionListener(ctr);

		for (VistaContenido v : vistasContenidos) {
			v.controlador(ctr);
		}
	}

	public Evento getEvento() { return evento; }
	public void setModoEdicion(boolean mostrar) {
		enModoEdicion = mostrar;
		if (mostrar) {
			panelPrincipal.add(scrollModoEdicion, BorderLayout.EAST);
			btnAnularInscripcin.setText("Ver como alumno");
		} else {
			panelPrincipal.remove(scrollModoEdicion);
			btnAnularInscripcin.setText("Editar");
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

	// pos != id en la BD del contenido!!!
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

		crearPanelUsuarios();

		if (Sesion.getPermisos()<=1) {
			tabbedPane.addTab("Usuarios", null, panelUsuarios);
		}
		
		panelBusqueda = new JPanel();
		panelBusqueda.setMaximumSize(new Dimension(90000, 23));
		panelUsuarios.add(panelBusqueda);
		panelBusqueda.setLayout(new BoxLayout(panelBusqueda, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		panelBusqueda.add(textField);
		textField.setColumns(10);
		
		btnBuscar_1 = new JButton("Buscar");
		panelBusqueda.add(btnBuscar_1);
		
		listResultado = new JList();
		listResultado.setPreferredSize(new Dimension(500, 150));
		listResultado.setMaximumSize(new Dimension(500, 500));
		JScrollPane listaCursosScroll = new JScrollPane(listResultado);
		panelUsuarios.add(listaCursosScroll);

		panelDatos = new JPanel();
		panelUsuarios.add(panelDatos);
		panelDatos.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panelDatos.add(panel);
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		

		lblNU = new JLabel("Nombre de Usuario: ");
		panel.add(lblNU);

		lblNombreUsuario = new JLabel("");
		panel.add(lblNombreUsuario);
		
		lblCU = new JLabel("Correo: ");
		panel.add(lblCU);
		
		lblCorreoUsuario = new JLabel("");
		panel.add(lblCorreoUsuario);

		lblCC = new JLabel("CorreoCorp: ");
		panel.add(lblCC);

		lblCorreoCorpUsuario = new JLabel("");
		panel.add(lblCorreoCorpUsuario);
		
		panelBotones = new JPanel();
		panelDatos.add(panelBotones, BorderLayout.EAST);
		panelBotones.setLayout(new GridLayout(2, 2, 0, 0));
		
		btSancionar = new JButton("Sancionar");
		panelBotones.add(btSancionar);
		
		btElimSanc = new JButton("Eliminar Sancion");
		panelBotones.add(btElimSanc);
		
		btKick = new JButton("Expulsar");
		panelBotones.add(btKick);
		
		btAdd = new JButton("A\u00F1adir al curso");
		panelBotones.add(btAdd);

		limpiar();

		crearPanelInferior();
		panelCentral.add(panelInferior, BorderLayout.SOUTH);

		crearPanelModoEdicion();

	}

	private void crearPanelUsuarios() {

		panelUsuarios = new JPanel();
		BoxLayout layout = new BoxLayout(panelContenido, BoxLayout.Y_AXIS);
		panelContenido.setLayout(layout);
		panelContenido.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		panelUsuarios.setLayout(new BoxLayout(panelUsuarios, BoxLayout.PAGE_AXIS));

		// Mostrar título de la pestaña
		lblBuscaUsuarios = new JLabel("B\u00FAsqueda de Usuarios");
		lblBuscaUsuarios.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		lblBuscaUsuarios.setHorizontalAlignment(JLabel.CENTER);
		panelUsuarios.add(lblBuscaUsuarios);

		

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

		//cbNuevoContenido = new JComboBox<>(new String[]{"Añadir contenido...", "Texto", "Enlace", "Test", "Cuestionario", "Documento", "Llamada"});
		//TODO CAMBIAR ESTO
					cbNuevoContenido = new JComboBox<>();
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

    public String getTextBoxValue() {
			return textField.getText();
    }

	public void resultadoBusqueda(String[] nombres) {
		listResultado.setListData(nombres);
	}

	public String getValorLista() {
		return listResultado.getSelectedValue().toString();
	}

	public void datosUsuario(Usuario user) {

		lblNombreUsuario.setText(user.getNombreUsuario());
		lblCorreoUsuario.setText(user.getCorreo());
		lblCorreoCorpUsuario.setText(user.getCorp());
		if (inscrito(user,this.evento)) {
			btSancionar.setVisible(true);
			btElimSanc.setVisible(true);
			btKick.setVisible(true);
			btAdd.setVisible(false);
		} else {
			btSancionar.setVisible(false);
			btElimSanc.setVisible(false);
			btKick.setVisible(false);
			btAdd.setVisible(true);
		}

	}

	public int selectedRows() {
		return listResultado.getSelectedIndices().length;
	}

	private boolean inscrito(Usuario user, Evento ev) {
		for (Evento evento : user.getEventosInscritos()){
			if (ev.getNombre().equals(evento.getNombre())){
				return true;
			}
		}
		return false;
	}


	public void limpiar() {
		lblNombreUsuario.setText("");
		lblCorreoUsuario.setText("");
		lblCorreoCorpUsuario.setText("");
		btSancionar.setVisible(false);
		btElimSanc.setVisible(false);
		btKick.setVisible(false);
		btAdd.setVisible(false);
	}
}
