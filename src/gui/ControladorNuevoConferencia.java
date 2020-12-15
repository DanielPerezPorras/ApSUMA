package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ControladorNuevoConferencia implements ActionListener {

	private VistaNuevoConferencia vista;
	public ControladorNuevoConferencia(VistaNuevoConferencia vista)
    {
        this.vista = vista;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {

        case "CREAR":
            String error = null;
            String nombre = vista.getTextoNombre();
            if (nombre.length() == 0){
                error = "El nombre está vacio.";
            }

            if(error == null){
                JOptionPane.showMessageDialog(vista,
                        "Conferencia creada con exito",
                        "ÉXITO",
                        JOptionPane.INFORMATION_MESSAGE);
                vista.dispose();
            }else{
                JOptionPane.showMessageDialog(vista,
                        error,
                        "Error al crear la conferencia",
                        JOptionPane.ERROR_MESSAGE);
            }
            break;

        case "CANCELAR":
            vista.dispose();
            break;
		}

	}

}
