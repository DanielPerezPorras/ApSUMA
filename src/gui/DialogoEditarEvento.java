package gui;

import modelo.*;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class DialogoEditarEvento extends JDialog {

	private Evento evento;

	private JButton btnCancelar;
	private JButton btnConfirmar;
	private JTextField tFFecha;
	private JLabel lblfecha;

	private JDatePickerImpl datePicker;
	private JTextField tFNombre;
	private JTextField tFLugar;
	private JTextField tFNumClases;
	private JTextField tFDuracion;
	private boolean seHaConfirmado = false;

	public DialogoEditarEvento(JFrame propietario, Evento evento) {
		super(propietario, "Editar evento", true);
		this.evento = evento;

		crearGUI();
		controlador(new ControladorDialogoEditarEvento(this));
	}
	
	public void controlador(ActionListener ctr) {
		btnConfirmar.addActionListener(ctr);
		btnConfirmar.setActionCommand("CONFIRMAR");
		btnCancelar.addActionListener(ctr);
		btnCancelar.setActionCommand("CANCELAR");
	}

	public Evento getEvento() { return evento; }

	public Date getFecha() { return (Date)datePicker.getModel().getValue(); }

	public String getTextoNombre() {
		return tFNombre.getText();
	}

	public int getClases() {
		try {
			return Integer.parseInt(tFNumClases.getText());
		} catch (NumberFormatException ex) {
			return -1;
		}
	}
	
	public int getDuracion() {
		try {
			return Integer.parseInt(tFDuracion.getText());
		} catch (NumberFormatException ex) {
			return -1;
		}
	}
	
	public String getDato() {
		return tFLugar.getText();
	}

	public boolean seHaConfirmado() {
		return seHaConfirmado;
	}

	public void darConfirmacion() {
		seHaConfirmado = true;
	}

	private void crearGUI() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		setContentPane(panelPrincipal);

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));
		centro.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

		JLabel lblNombre = new JLabel("Nombre del evento:");
		lblNombre.setFont(UtilidadesGUI.FUENTE);
		lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
		centro.add(lblNombre);

		tFNombre = new JTextField(evento.getNombre());
		tFNombre.setFont(UtilidadesGUI.FUENTE);
		tFNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
		centro.add(tFNombre);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(UtilidadesGUI.FUENTE);
		lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);
		centro.add(lblFecha);

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		datePicker.setAlignmentX(Component.LEFT_ALIGNMENT);
		model.setValue(evento.getFecha());
		centro.add(datePicker);

		Date fecha = evento.getFecha();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		model.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		model.setMonth(calendar.get(Calendar.MONTH));
		model.setYear(calendar.get(Calendar.YEAR));

		tFNumClases = new JTextField();
		tFNumClases.setFont(UtilidadesGUI.FUENTE);

		tFDuracion = new JTextField();
		tFDuracion.setFont(UtilidadesGUI.FUENTE);

		JPanel clasesDuracion = new JPanel(new GridLayout(1, 2));
		clasesDuracion.add(tFNumClases);
		clasesDuracion.add(tFDuracion);
		clasesDuracion.setAlignmentX(Component.LEFT_ALIGNMENT);

		tFLugar = new JTextField();
		tFLugar.setFont(UtilidadesGUI.FUENTE);

		JLabel lblDatos = new JLabel();
		if (evento instanceof Curso) {
			Curso curso = (Curso)evento;

			lblDatos.setText("N.º de clases y duración");
			tFNumClases.setText(curso.getNumClases() + "");
			tFDuracion.setText(curso.getDuracion() + "");
		} else if (evento instanceof ActividadSocial) {
			ActividadSocial actividad = (ActividadSocial)evento;

			lblDatos.setText("Lugar");
			tFLugar.setText(actividad.getLugar());
		} else if (evento instanceof Conferencia) {
			Conferencia conferencia = (Conferencia)evento;

			lblDatos.setText("Enlace");
			tFLugar.setText(conferencia.getEnlace());
		} else {
			throw new RuntimeException("Tipo de evento no reconocido");
		}
		lblDatos.setFont(UtilidadesGUI.FUENTE);
		lblDatos.setAlignmentX(Component.LEFT_ALIGNMENT);
		centro.add(lblDatos);

		centro.add(clasesDuracion);
		clasesDuracion.setVisible(evento instanceof Curso);

		centro.add(tFLugar);
		tFLugar.setVisible(!(evento instanceof Curso));


		JPanel sur = new JPanel();
		sur.setLayout(new BoxLayout(sur, BoxLayout.LINE_AXIS));
		sur.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(UtilidadesGUI.FUENTE);
		btnConfirmar = new JButton("Guardar cambios");
		btnConfirmar.setFont(UtilidadesGUI.FUENTE);
		sur.add(btnCancelar);
		sur.add(btnConfirmar);

		panelPrincipal.add(centro, BorderLayout.CENTER);
		panelPrincipal.add(sur, BorderLayout.SOUTH);

		pack();
	}

}
