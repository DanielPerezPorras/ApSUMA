package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class VistaNuevoEvento extends JFrame {

	private JPanel panalPrincipal;
	private JTextField tFNombre;
	private JLabel lblNombre;
	private JButton btnCancelar;
	private JButton btnCrear;
	private JTextField tFFecha;
	private JLabel lblfecha;
	private String tipo;
	private JLabel lblDatos;
	private JTextField tFLugar;
	private JTextField tFNumClases;
	private JTextField tFDuracion;

	/**
	 * Create the frame.
	 */
	public VistaNuevoEvento(String tipo) {
		super ( tipo.equals("curso") ? "Nuevo Curso" : ("Nueva" + tipo));
		this.tipo=tipo;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 274);
		panalPrincipal = new JPanel();
		panalPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panalPrincipal);
		panalPrincipal.setLayout(null);
		
		lblNombre = new JLabel("Nombre de" + (tipo.equals("curso") ? "l Curso" : (" la " + tipo)));
		lblNombre.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblNombre.setBounds(72, 13, 153, 40);
		panalPrincipal.add(lblNombre);
		
		tFNombre = new JTextField();
		tFNombre.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tFNombre.setBounds(232, 18, 279, 32);
		panalPrincipal.add(tFNombre);
		tFNombre.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnCancelar.setBounds(10, 194, 129, 40);
		panalPrincipal.add(btnCancelar);
		
		btnCrear = new JButton("Crear " + tipo);
		btnCrear.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		btnCrear.setBounds(414, 194, 129, 40);
		panalPrincipal.add(btnCrear);
		
		lblfecha = new JLabel("Fecha (yyyy-mm-dd)");
		lblfecha.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblfecha.setBounds(72, 75, 153, 40);
		panalPrincipal.add(lblfecha);
		
		tFFecha = new JTextField();
		tFFecha.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tFFecha.setColumns(10);
		tFFecha.setBounds(232, 80, 279, 32);
		panalPrincipal.add(tFFecha);
		
		lblDatos = new JLabel((tipo.equals("curso") ? "Nº Clases y Duracion" : (tipo.equals("Conferencia") ? "Enlace" : "Lugar")));
		lblDatos.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblDatos.setBounds(72, 136, 153, 40);
		panalPrincipal.add(lblDatos);
		
		tFLugar = new JTextField();
		tFLugar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tFLugar.setColumns(10);
		tFLugar.setBounds(232, 140, 279, 32);
		panalPrincipal.add(tFLugar);
		tFLugar.setVisible(!tipo.equals("Curso"));
		
		tFNumClases = new JTextField();
		tFNumClases.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tFNumClases.setColumns(10);
		tFNumClases.setBounds(232, 140, 139, 32);
		panalPrincipal.add(tFNumClases);
		tFNumClases.setVisible(tipo.equals("Curso"));
		
		tFDuracion = new JTextField();
		tFDuracion.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		tFDuracion.setColumns(10);
		tFDuracion.setBounds(372, 140, 139, 32);
		panalPrincipal.add(tFDuracion);
		tFDuracion.setVisible(tipo.equals("Curso"));
	}
	
	public static void abrirVentana(String tipo) {
		try {
			VistaNuevoEvento frame = new VistaNuevoEvento(tipo);
			frame.controlador(new ControladorNuevoEvento(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void controlador(ActionListener ctr)
	{
		btnCrear.addActionListener(ctr);
		btnCrear.setActionCommand("CREAR");
		btnCancelar.addActionListener(ctr);
		btnCancelar.setActionCommand("CANCELAR");
	}

	public String getTextoNombre() {
		return tFNombre.getText();
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public Date getFecha() {
		String[] f = tFFecha.getText().split("[-]");
		Date fecha;
		if (f.length!=3) {
			fecha = null;
		} else {
			fecha = new Date(Integer.parseInt(f[0])-1900, Integer.parseInt(f[1])-1, Integer.parseInt(f[2]));
		}
		return fecha;
	}
	
	public int getClases() {
		return Integer.parseInt(tFNumClases.getText());
	}
	
	public int getDuracion() {
		return Integer.parseInt(tFDuracion.getText());
	}
	
	public String getDato() {
		return tFLugar.getText();
	}
	
}
