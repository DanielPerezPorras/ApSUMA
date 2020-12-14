package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Colaborador;
import modelo.Estudiante;
import modelo.Evento;
import modelo.Sesion;
import modelo.Tutor;
import modelo.Usuario;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JTabbedPane;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VistaPrincipalEstudiante extends JFrame {

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

	public static void abrirVentana() {
		try {
			VistaPrincipalEstudiante frame = new VistaPrincipalEstudiante();
			frame.controlador(new ControladorPrincipalEstudiante(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VistaPrincipalEstudiante() {
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
		
		lblNombrePerfil = new JLabel("");
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

	}

	public void controlador(ControladorPrincipalEstudiante ctr) {
		btnEntrar.setActionCommand("ENTRAR");
		bPerfil.setActionCommand("PERFIL");

		btnEntrar.addActionListener(ctr);
		bPerfil.addActionListener(ctr);

		datePicker.addActionListener(ctr);
		listInscritos.addListSelectionListener(ctr);
	}
	
	public Date fechaSeleccionada() {
		return (Date) datePicker.getModel().getValue();
	}

	public boolean compruebaFuenteEvento(Object source) {
		return source.equals(datePicker);
	}
	
	public void cargarEventos() {
		Usuario usuarioLogueado = Sesion.getUsuarioLogueado();
		Evento[] eventos;
		ArrayList<Evento> listaEventos;

		listaEventos = usuarioLogueado.getPropuesto(this.fechaSeleccionada());

		eventos = new Evento[listaEventos.size()];
		listaEventos.toArray(eventos);
		listInscritos.setListData(eventos);
	}
}
