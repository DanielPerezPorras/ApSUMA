package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.*;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JTabbedPane;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VistaPrincipalTutor extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelEventos;
	private JPanel panelMensajeria;
	private JList<Evento> listInscritos;
	private JList<Evento> listOrganizados;
	private JPanel panelCalendario;
	private JButton bPerfil;
	private JLabel lblNombrePerfil;
	private JLabel lblDesc;
	private JButton btnEntrar;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JComboBox comboBox;
	private JButton btnCrear;
	private JButton btnVistaadmin;

	public static void abrirVentana() {
		try {
			Sesion.setPermisos(1);
			VistaPrincipalTutor frame = new VistaPrincipalTutor();
			frame.controlador(new ControladorPrincipalTutor(frame));
			frame.rellenarListaOrganizados();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VistaPrincipalTutor() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 11));
		tabbedPane.setBounds(10, 11, 830, 528);
		contentPane.add(tabbedPane);

		panelEventos = new JPanel();
		tabbedPane.addTab("Eventos", null, panelEventos, null);
		panelEventos.setLayout(null);

		listInscritos = new JList<Evento>();
		listInscritos.setBounds(38, 48, 362, 172);
		panelEventos.add(listInscritos);

		listOrganizados = new JList<Evento>();
		listOrganizados.setBounds(38, 266, 362, 172);
		panelEventos.add(listOrganizados);

		panelCalendario = new JPanel();
		panelCalendario.setBounds(596, 66, 219, 225);
		panelEventos.add(panelCalendario);

		panelMensajeria = new JPanel();
		tabbedPane.addTab("Mensajeria", null, panelMensajeria, null);

		tabbedPane.setEnabledAt(1, false);


		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);

		panelCalendario.add(datePicker);

		bPerfil = new JButton("");
		bPerfil.setBounds(753, 11, 62, 49);
		UtilidadesGUI.ajustarImagenAButton(bPerfil, "/recursosApp/gato.png");
		panelEventos.add(bPerfil);

		lblNombrePerfil = new JLabel(Sesion.getUsuarioLogueado().getNombreUsuario());
		lblNombrePerfil.setBounds(662, 27, 46, 14);
		panelEventos.add(lblNombrePerfil);

		lblDesc = new JLabel("");
		lblDesc.setBounds(410, 49, 181, 127);
		panelEventos.add(lblDesc);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(410, 187, 89, 23);
		panelEventos.add(btnEntrar);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Crear Curso", "Crear Actividad", "Crear Conferencia"}));
		comboBox.setBounds(596, 326, 155, 32);
		panelEventos.add(comboBox);

		btnCrear = new JButton("Crear");
		btnCrear.setBounds(596, 380, 89, 23);
		panelEventos.add(btnCrear);

		btnVistaadmin = new JButton("Volver a Admin");
		btnVistaadmin.setBounds(10, 465, 103, 23);
		btnVistaadmin.setVisible(Sesion.logueadoComoAdmin());
		panelEventos.add(btnVistaadmin);

	}

	public void controlador(ControladorPrincipalTutor ctr) {
		listOrganizados.addListSelectionListener(ctr);
		listInscritos.addListSelectionListener(ctr);
		btnCrear.addActionListener(ctr);
		btnCrear.setActionCommand("CREAR");
		comboBox.addActionListener(ctr);
		datePicker.addActionListener(ctr);
		btnEntrar.addActionListener(ctr);
		btnEntrar.setActionCommand("ENTRAR EVENTO");
		this.addWindowListener(ctr);
		btnVistaadmin.addActionListener(ctr);
		btnVistaadmin.setActionCommand("ADMIN");
		bPerfil.addActionListener(ctr);
		bPerfil.setActionCommand("ABRIR");

	}

	public int indiceComboBox() {
		return comboBox.getSelectedIndex();
	}

	public Date fechaSeleccionada() {
		return (Date) datePicker.getModel().getValue();
	}

	public boolean compruebaFuenteEvento(Object source) {
		return source.equals(datePicker);
	}

	public void cargarEventos() {
		System.out.println("cargamoseventos");
		Usuario usuarioLogueado = Sesion.getUsuarioLogueado();
		Evento[] eventos;
		ArrayList<Evento> listaEventos;

		listaEventos = usuarioLogueado.getEventosFecha(this.fechaSeleccionada());

		eventos = new Evento[listaEventos.size()];
		listaEventos.toArray(eventos);
		listInscritos.setListData(eventos);
	}

	public void rellenarListaOrganizados() {
		Usuario usuarioLogueado = Sesion.getUsuarioLogueado();
		Evento[] eventos;
		ArrayList<Evento> listaEventos;

		if (usuarioLogueado instanceof Tutor) {
			Tutor tutorLogueado = (Tutor)usuarioLogueado;
			listaEventos = tutorLogueado.getPropuesto();

		} else if (usuarioLogueado instanceof Colaborador) {
			Colaborador tutorLogueado = (Colaborador)usuarioLogueado;
			listaEventos = tutorLogueado.getCreado();
		} else {
			throw new RuntimeException("Tipo de usuario no válido para VistaPrincipalTutor");
		}
		eventos = new Evento[listaEventos.size()];
		listaEventos.toArray(eventos);
		listOrganizados.setListData(eventos);
	}

	public JList<Evento> getListaOrganizados() {
		return listOrganizados;
	}

	// El evento seleccionado puede ser uno al que el usuario estÃ© inscrito o
	// que haya organizado.
	public Evento getEventoSeleccionado() {
		Evento ev = listOrganizados.getSelectedValue();
		if (ev == null){
			ev = listInscritos.getSelectedValue();
		}
		return ev;
	}

}
