package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ControladorNuevoActividades implements ActionListener {
	private final VistaNuevoActividades vista;

    public ControladorNuevoActividades(VistaNuevoActividades vista)
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
                        "Actividad creada con exito",
                        "ÉXITO",
                        JOptionPane.INFORMATION_MESSAGE);
                vista.dispose();
            }else{
                JOptionPane.showMessageDialog(vista,
                        error,
                        "Error al crear la actividad",
                        JOptionPane.ERROR_MESSAGE);
            }
            break;

        case "CANCELAR":
            vista.dispose();
            break;
		}

	}

}
