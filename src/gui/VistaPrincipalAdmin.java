package gui;

import modelo.BD;
import modelo.Sesion;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class VistaPrincipalAdmin extends JFrame {

	private JPanel panelPrincipal;
	private JTextField textField;
	private JLabel lbVerComo;
	private JComboBox cbRoles;
	private JSeparator separator;
	private JLabel lblBsquedaDeUsuarios;
	private JLabel lbNombreUsuario;
	private JButton btnBuscar;
	private JLabel lbFoto;
	private Container lbNombreDeUsuario;
	private JLabel lblUsuario;
	private JLabel lblCorreoElectrnico;
	private JLabel lblUsuarioumaes;
	private JLabel lblCorreoUma;
	private JLabel lblUsuarioumaes_1;
	private JButton btnPenalizar;
	private JButton btnEliminarUsuario;
	private JComboBox cbLista;
	private JButton btnAadirNoticia;
	private JTextArea tfNoticia;
	private JLabel lbNoticias;
	private JTextArea tfNoticiaActuales;

	public static void abrirVentana() {
		try {
			VistaPrincipalAdmin frame = new VistaPrincipalAdmin();
			frame.controlador(new ControladorPrincipalAdmin(frame));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VistaPrincipalAdmin()
	{
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 579);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		lbVerComo = new JLabel("Ver como: ");
		lbVerComo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		lbVerComo.setBounds(42, 42, 99, 27);
		panelPrincipal.add(lbVerComo);

		cbRoles = new JComboBox();
		cbRoles.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Tutor", "Usuario", "Invitado"}));
		cbRoles.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		cbRoles.setBounds(151, 42, 144, 27);
		panelPrincipal.add(cbRoles);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(335, 44, 25, 487);
		panelPrincipal.add(separator);

		lblBsquedaDeUsuarios = new JLabel("B\u00FAsqueda de usuarios");
		lblBsquedaDeUsuarios.setFont(new Font("Microsoft JhengHei UI", Font.BOLD | Font.ITALIC, 16));
		lblBsquedaDeUsuarios.setBounds(372, 41, 186, 27);
		panelPrincipal.add(lblBsquedaDeUsuarios);

		lbNombreUsuario = new JLabel("Nombre de usuario:");
		lbNombreUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lbNombreUsuario.setBounds(400, 81, 144, 27);
		panelPrincipal.add(lbNombreUsuario);

		textField = new JTextField();
		textField.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		textField.setBounds(564, 81, 144, 27);
		panelPrincipal.add(textField);
		textField.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		btnBuscar.setBounds(720, 81, 97, 27);
		panelPrincipal.add(btnBuscar);

		lbFoto = new JLabel("");
		lbFoto.setBounds(372, 342, 144, 140);
		UtilidadesGUI.ajustarImagenALabel(lbFoto, "/recursosApp/gato.png");
		panelPrincipal.add(lbFoto);

		lbNombreDeUsuario = new JLabel("Nombre de usuario:");
		lbNombreDeUsuario.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lbNombreDeUsuario.setBounds(564, 342, 144, 31);
		panelPrincipal.add(lbNombreDeUsuario);

		lblUsuario = new JLabel("");
		lblUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblUsuario.setBounds(720, 346, 97, 23);
		panelPrincipal.add(lblUsuario);

		lblCorreoElectrnico = new JLabel("Correo electr\u00F3nico:");
		lblCorreoElectrnico.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblCorreoElectrnico.setBounds(564, 375, 133, 27);
		panelPrincipal.add(lblCorreoElectrnico);

		lblUsuarioumaes = new JLabel("");
		lblUsuarioumaes.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblUsuarioumaes.setBounds(720, 375, 118, 27);
		panelPrincipal.add(lblUsuarioumaes);

		lblCorreoUma = new JLabel("Correo corp:");
		lblCorreoUma.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblCorreoUma.setBounds(564, 412, 133, 27);
		panelPrincipal.add(lblCorreoUma);

		lblUsuarioumaes_1 = new JLabel("");
		lblUsuarioumaes_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		lblUsuarioumaes_1.setBounds(720, 415, 118, 27);
		panelPrincipal.add(lblUsuarioumaes_1);

		btnPenalizar = new JButton("Penalizar");
		btnPenalizar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		btnPenalizar.setBounds(372, 494, 118, 37);
		panelPrincipal.add(btnPenalizar);

		btnEliminarUsuario = new JButton("Eliminar usuario");
		btnEliminarUsuario.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		btnEliminarUsuario.setBounds(579, 494, 202, 37);
		panelPrincipal.add(btnEliminarUsuario);

		cbLista = new JComboBox();
		cbLista.setBounds(564, 138, 253, 22);
		panelPrincipal.add(cbLista);

		btnAadirNoticia = new JButton("A\u00F1adir noticia");
		btnAadirNoticia.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
		btnAadirNoticia.setBounds(68, 494, 202, 37);
		panelPrincipal.add(btnAadirNoticia);

		tfNoticia = new JTextArea();
		tfNoticia.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		tfNoticia.setBounds(42, 324, 253, 158);
		tfNoticia.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panelPrincipal.add(tfNoticia);

		lbNoticias = new JLabel("Noticias actuales:");
		lbNoticias.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 16));
		lbNoticias.setBounds(42, 82, 191, 27);
		panelPrincipal.add(lbNoticias);

		tfNoticiaActuales = new JTextArea();
		tfNoticiaActuales.setEditable(false);
		tfNoticiaActuales.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		tfNoticiaActuales.setBounds(42, 122, 253, 158);
		tfNoticiaActuales.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		noticiasActuales();
		panelPrincipal.add(tfNoticiaActuales);
	}



	public String getTextBoxValue()
	{
		return textField.getText();
	}

	public String getCorreoActual()
	{
		return cbLista.getSelectedItem().toString();
	}

	private void noticiasActuales()
	{
		tfNoticiaActuales.setText("");
		BD bd = new BD();
		for (Object[] not : bd.Select("SELECT titulo FROM Noticia")) {
			tfNoticiaActuales.append("-" + not[0].toString() + "\n");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void anyadirTexto(String[] lista)
	{
		cbLista.setModel(new DefaultComboBoxModel(lista));
		mostrarDatosUsuarioSel();
	}

	public void mostrarDatosUsuarioSel()
	{
		Usuario usu = Usuario.buscarUsuario(this.getCorreoActual());
		mostrarDatosUsuario(usu.getNombreUsuario(),usu.getCorreo(),usu.getCorp());
	}

	//Muestra el nombre, correo del usuario
	public void mostrarDatosUsuario(String usuario, String correo, String correoUma)
	{
		lblUsuario.setText(usuario);
		lblUsuarioumaes.setText(correo);
		if(correoUma.equals(""))
		{
			lblCorreoUma.setVisible(false);
		}else
		{
			lblCorreoUma.setVisible(true);
		}
		lblUsuarioumaes_1.setText(correoUma);
	}

	public String getVerComo()
	{
		return cbRoles.getSelectedItem().toString();
	}

	public void actualizar()
	{
		mostrarDatosUsuario((String) cbLista.getSelectedItem(),(String)  cbLista.getSelectedItem(),(String) cbLista.getSelectedItem());
	}

	public Usuario darUsuario()
	{
		return Usuario.buscarUsuario(this.getCorreoActual());
	}
	
	public void anyadirNoticia()
	{
		String texto = tfNoticia.getText();
		if(texto.equals(""))
		{
			JOptionPane.showMessageDialog(null, new Exception("Necesita asignar titulo a la noticia"), 
					"Necesita asignar titulo a la noticia", JOptionPane.ERROR_MESSAGE);
		}else
		{
			BD bd = new BD();

			List<Object[]> lista = bd.Select("SELECT MAX(idNoticia) FROM Noticia");
			int id;
			try {
				id = Integer.parseInt(lista.get(0)[0].toString())+1;
			} catch (NullPointerException ex) {
				id = 0;
			}
			bd.Insert("INSERT INTO Noticia VALUES (" + id + ",'" + texto + "')");
			noticiasActuales();
			JOptionPane.showMessageDialog(null,"¡Noticia añadida con éxito!");
		}
	}

	public void limpiar()
	{
		cbLista.setModel(new DefaultComboBoxModel(new String[] {}));
		textField.setText("");
		lblUsuario.setText("");
		lblUsuarioumaes.setText("");
		lblUsuarioumaes_1.setText("");
	}

	public void controlador(ActionListener ctr)
	{
		btnBuscar.addActionListener(ctr);
		btnBuscar.setActionCommand("BUSCAR");
		btnPenalizar.addActionListener(ctr);
		btnPenalizar.setActionCommand("PENALIZAR");
		btnEliminarUsuario.addActionListener(ctr);
		btnEliminarUsuario.setActionCommand("ELIMINAR");
		cbLista.addActionListener(ctr);
		cbLista.setActionCommand("ACTUALIZAR");
		cbRoles.addActionListener(ctr);
		cbRoles.setActionCommand("CAMBIAR_ROL");
		btnAadirNoticia.addActionListener(ctr);
		btnAadirNoticia.setActionCommand("NOTICIA");
	}

	public void limpiarNoticias() {
		tfNoticia.setText("");
	}
}
