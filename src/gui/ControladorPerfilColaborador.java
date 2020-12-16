package gui;

import modelo.Sesion;
import modelo.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPerfilColaborador implements ActionListener {

    private final VistaPerfilColaborador vista;
    private boolean modificando = false;

    public ControladorPerfilColaborador(VistaPerfilColaborador vista){
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "MODIFICAR":
                if (modificando) {
                    modificando = false;
                    vista.guardarCambios();
                } else {
                    modificando = true;
                    vista.modificar();
                }
                break;
            case "CERRAR SESION":
                Sesion.setUsuarioLogueado(null);
                java.awt.Window[] win = java.awt.Window.getWindows();
                for (java.awt.Window window : win) {
                    window.dispose();
                }
                VistaLogin.abrirVentana();
                break;
            case "ATRAS":
                vista.dispose();
                break;
            case "ELIMINAR CUENTA":
                Usuario usuario = Sesion.getUsuarioLogueado();
                usuario.eliminarCuenta();
                Sesion.setUsuarioLogueado(null);
                win = java.awt.Window.getWindows();
                for (java.awt.Window window : win) {
                    window.dispose();
                }
                VistaLogin.abrirVentana();
                break;
        }
    }
}
