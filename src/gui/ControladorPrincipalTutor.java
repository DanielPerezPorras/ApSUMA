package gui;

import modelo.Evento;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipalTutor implements ActionListener, ListSelectionListener {

    private final VistaPrincipalTutor vista;

    public ControladorPrincipalTutor(VistaPrincipalTutor vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "ENTRAR EVENTO":
                Evento ev = vista.getEventoSeleccionado();
                if (ev != null) {
                    vista.dispose();
                    ev.abrirEvento();
                }
                break;

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

}
