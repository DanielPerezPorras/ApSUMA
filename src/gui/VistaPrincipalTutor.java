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
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VistaPrincipalTutor extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelEventos;
	private JPanel panelMensajeria;
	private JList<Evento> listInscritos;
	private JList<Evento> listOrganizados;
	private JPanel panelCalendario;
	private JButton bPerfil;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipalTutor frame = new VistaPrincipalTutor();
					frame.controlador(new ControladorPrincipalTutor(frame));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void abrirVentana() {
		try {
			VistaPrincipalTutor frame = new VistaPrincipalTutor();
			frame.controlador(new ControladorPrincipalTutor(frame));
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
		
		listInscritos = new JList<>();
		listInscritos.setBounds(38, 48, 362, 172);
		panelEventos.add(listInscritos);
		
		listOrganizados = new JList<>();
		listOrganizados.setBounds(38, 266, 362, 172);
		panelEventos.add(listOrganizados);
		
		panelCalendario = new JPanel();
		panelCalendario.setBounds(596, 66, 219, 225);
		panelEventos.add(panelCalendario);
		
		panelMensajeria = new JPanel();
		tabbedPane.addTab("Mensajeria", null, panelMensajeria, null);
		
		tabbedPane.setEnabledAt(1, false);
		
	
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		
		panelCalendario.add(datePicker);
		
		bPerfil = new JButton("");
		bPerfil.setBounds(753, 11, 62, 49);
		panelEventos.add(bPerfil);
		
		JLabel lblNombrePerfil = new JLabel("");
		lblNombrePerfil.setBounds(662, 27, 46, 14);
		panelEventos.add(lblNombrePerfil);
		
		JLabel lblDesc = new JLabel("");
		lblDesc.setBounds(410, 49, 181, 127);
		panelEventos.add(lblDesc);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(410, 187, 89, 23);
		panelEventos.add(btnEntrar);

	}

	public void controlador(ControladorPrincipalTutor ctr) {
		listOrganizados.addListSelectionListener(ctr);
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
}
