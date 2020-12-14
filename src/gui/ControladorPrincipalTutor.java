package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipalTutor implements ActionListener {

    private final VistaPrincipalTutor vista;

    public ControladorPrincipalTutor(VistaPrincipalTutor vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

        }
    }

}
