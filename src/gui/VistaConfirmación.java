package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VistaConfirmación extends JFrame {

	private JPanel contentPane;
	private JLabel lblConfirmacion;
	private JButton btnSi;
	private JButton btnNo;

	public VistaConfirmación() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblConfirmacion = new JLabel("\u00BFSeguro que quiere realizar esta acci\u00F3n?");
		lblConfirmacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmacion.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblConfirmacion.setBounds(10, 48, 424, 27);
		contentPane.add(lblConfirmacion);
		
		btnSi = new JButton("S\u00ED");
		btnSi.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 11));
		btnSi.setBounds(43, 143, 89, 23);
		contentPane.add(btnSi);
		
		btnNo = new JButton("No");
		btnNo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 11));
		btnNo.setBounds(310, 143, 89, 23);
		contentPane.add(btnNo);
	}
	
	public void controlador (ActionListener ctr) {
		btnSi.addActionListener(ctr);
		btnNo.addActionListener(ctr);
		btnNo.setActionCommand("NO");
		btnSi.setActionCommand("SI");
	}

}
