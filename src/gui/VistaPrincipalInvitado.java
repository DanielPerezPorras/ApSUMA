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

public class VistaPrincipalInvitado extends JFrame {

	private JLabel lblDesc;
	private JLabel lblNombrePerfil;
	private JButton btnEntrar;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelEventos;
	private JList<Evento> listInscritos;
	private JPanel panelCalendario;
	private JButton bPerfil;
	private JButton btnVistaadmin;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	public static void abrirVentana() {
		try
		{
			Sesion.setPermisos(3);
			VistaPrincipalInvitado frame = new VistaPrincipalInvitado();
			frame.controlador(new ControladorPrincipalInvitado(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarActividades() {

	}

	public VistaPrincipalInvitado() {
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

		listInscritos = new JList<Evento>();
		listInscritos.setBounds(38, 48, 362, 172);
		panelEventos.add(listInscritos);

		panelCalendario = new JPanel();
		panelCalendario.setBounds(596, 66, 219, 225);
		panelEventos.add(panelCalendario);


		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);

		panelCalendario.add(datePicker);

		bPerfil = new JButton("");
		bPerfil.setBounds(753, 11, 62, 49);
		UtilidadesGUI.ajustarImagenAButton(bPerfil, "/recursosApp/gato.png");
		panelEventos.add(bPerfil);

		lblNombrePerfil = new JLabel("Invitado");
		lblNombrePerfil.setBounds(662, 27, 46, 14);
		panelEventos.add(lblNombrePerfil);

		lblDesc = new JLabel("");
		lblDesc.setBounds(410, 49, 181, 127);
		panelEventos.add(lblDesc);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(410, 187, 89, 23);
		panelEventos.add(btnEntrar);

		btnVistaadmin = new JButton("Volver a Admin");
		btnVistaadmin.setBounds(10, 465, 103, 23);
		btnVistaadmin.setVisible(Sesion.logueadoComoAdmin());
		panelEventos.add(btnVistaadmin);

	}

	public void controlador(ControladorPrincipalInvitado ctr) {
		btnEntrar.addActionListener(ctr);
		btnEntrar.setActionCommand("ENTRAR");

		this.addWindowListener(ctr);

		datePicker.addActionListener(ctr);
		btnVistaadmin.addActionListener(ctr);
		btnVistaadmin.setActionCommand("ADMIN");
		listInscritos.addListSelectionListener(ctr);

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

	public Date fechaSeleccionada() {
		return (Date) datePicker.getModel().getValue();
	}

	public Evento getEventoSeleccionado() {
		return listInscritos.getSelectedValue();
	}

}
