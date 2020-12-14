package gui;

import modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la pantalla de login.
 */
public class ControladorLogin implements ActionListener {

    private final VistaLogin vista;

    public ControladorLogin(VistaLogin vista) 
    {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "INICIAR":

                // Comprobar si existe un usuario con el nombre y contraseña dados.
                String correo = vista.getTextoUsuario();
                String contrasenia = vista.getTextoContrasenia();

                BD bd = new BD();
                long numUsuarios = (long)bd.SelectEscalar("SELECT COUNT(*) FROM Usuario WHERE " +
                        "correo = '" + correo + "' AND contra = '" + contrasenia + "'");

                if (numUsuarios > 0) {
                    Usuario usuario = Usuario.buscarUsuario(correo);
                    Sesion.setUsuarioLogueado(usuario);
                    System.out.println(usuario.getCorreo() + " acaba de iniciar sesión");

                    // Cambiar ventana
                    vista.dispose();
                    usuario.abrirVentanaPrincipal();

                } else {
                    JOptionPane.showMessageDialog(vista,
                            "Usuario o contraseña incorrectos.",
                            "Error inicio de sesión",
                            JOptionPane.ERROR_MESSAGE);
                }

                break;

            case "REGISTRAR":
                vista.dispose();
                VistaTipoRegistro.abrirVentana();
                break;

            case "INVITADO":
                // Abrir como invitado la pantalla principal
                Usuario usuario = Usuario.crearInvitado();
                Sesion.setUsuarioLogueado(usuario);
                System.out.println("Invitado acaba de iniciar sesión");
                vista.dispose();
                usuario.abrirVentanaPrincipal();
                break;

        }
    }

}
