package gui;

import modelo.Estudiante;
import modelo.Usuario;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipalEstudiante implements ActionListener {

    private final VistaPrincipalEstudiante vista;

    public ControladorPrincipalEstudiante(VistaPrincipalEstudiante vista) {
        this.vista = vista;
        //this.usuario = usuario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vista.compruebaFuenteEvento(e.getSource())) {

        } else {
            switch (e.getActionCommand()) {
                case "PERFIL" : VistaPerfilEstudiante.abrirVentana(); //TODO crear ventana perfil
                case "ENTRAR" : return; //TODO crear ventana evento
            }
        }
    }
}
