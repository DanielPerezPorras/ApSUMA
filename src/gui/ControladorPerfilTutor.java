package gui;

import modelo.Sesion;
import modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPerfilTutor implements ActionListener {
    private final VistaPerfilTutor vista;

    public ControladorPerfilTutor(VistaPerfilTutor vista)
    {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "MODIFICAR":

                break;

            case "CERRAR SESION":
                Sesion.setUsuarioLogueado(null);
                vista.dispose();
                VistaLogin.abrirVentana();
                break;

            case "ATRAS":
                vista.dispose();
                break;

            case "ELIMINAR CUENTA"    :
                Usuario usuario = Sesion.getUsuarioLogueado();
                usuario.eliminarCuenta();
                Sesion.setUsuarioLogueado(null);
                vista.dispose();
                VistaLogin.abrirVentana();
                break;
        }
    }
}
