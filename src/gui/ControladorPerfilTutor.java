package gui;

import modelo.Sesion;
import modelo.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPerfilTutor implements ActionListener {
    private final VistaPerfilTutor vista;
    boolean modificando = false;
    public ControladorPerfilTutor(VistaPerfilTutor vista)
    {
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
