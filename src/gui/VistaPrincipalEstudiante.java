package gui;

import modelo.Evento;
import modelo.Sesion;
import modelo.Usuario;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class VistaPrincipalEstudiante extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelEventos;
	private JPanel panelMensajeria;
	private JPanel panelCalendario;
	private JButton bPerfil;
	private UtilDateModel model ;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JLabel lblDesc;
	private JLabel lblNombrePerfil;
	private JButton btnEntrar;
	private JList<Evento> listInscritos;
	private JButton btnVistaadmin;

	public static void abrirVentana() {
		try {
			Sesion.setPermisos(2);
			VistaPrincipalEstudiante frame = new VistaPrincipalEstudiante();
			frame.controlador(new ControladorPrincipalEstudiante(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VistaPrincipalEstudiante() {
		super("APS_UMA");
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

		lblNombrePerfil =  new JLabel(Sesion.getUsuarioLogueado().getNombreUsuario());
		lblNombrePerfil.setBounds(662, 27, 46, 14);
		panelEventos.add(lblNombrePerfil);

		lblDesc = new JLabel("");
		lblDesc.setBounds(410, 49, 181, 127);
		panelEventos.add(lblDesc);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(410, 187, 89, 23);
		panelEventos.add(btnEntrar);

		listInscritos = new JList<Evento>();
		listInscritos.setBounds(38, 48, 362, 172);
		panelEventos.add(listInscritos);

		btnVistaadmin = new JButton("Volver a Admin");
		btnVistaadmin.setBounds(10, 465, 103, 23);
		btnVistaadmin.setVisible(Sesion.logueadoComoAdmin());
		panelEventos.add(btnVistaadmin);

	}

	public void controlador(ControladorPrincipalEstudiante ctr) {
		btnEntrar.setActionCommand("ENTRAR");
		bPerfil.setActionCommand("PERFIL");

		btnEntrar.addActionListener(ctr);
		bPerfil.addActionListener(ctr);

		datePicker.addActionListener(ctr);
		listInscritos.addListSelectionListener(ctr);
		this.addWindowListener(ctr);
		btnVistaadmin.addActionListener(ctr);
		btnVistaadmin.setActionCommand("ADMIN");
	}

	public Date fechaSeleccionada() {
		return (Date) datePicker.getModel().getValue();
	}

	public void cargarEventos() {
		Usuario usuarioLogueado = Sesion.getUsuarioLogueado();
		Evento[] eventos;
		ArrayList<Evento> listaEventos;

		listaEventos = usuarioLogueado.getEventosFecha(this.fechaSeleccionada());

		eventos = new Evento[listaEventos.size()];
		listaEventos.toArray(eventos);
		listInscritos.setListData(eventos);
	}

	public Evento getEventoSeleccionado() {
		return listInscritos.getSelectedValue();
	}

}
