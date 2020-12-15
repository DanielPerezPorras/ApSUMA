package modelo;

import gui.VistaPrincipalAdmin;
import gui.VistaPrincipalTutor;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario{

    private ArrayList<Usuario> users;

    public Administrador(String cor, String usr, String contr) {
        super(cor, usr, contr);
        BD bd = new BD();
        bd.Insert("INSERT INTO Administrador VALUES ('" + cor + "');");
        users = null;
    }

    public Administrador(String cor) {
        super(cor);
        BD bd = new BD();
        List<Object[]> userList = bd.Select("SELECT * FROM Administrador WHERE correo = '" + cor + "';");
        if (userList.size() == 0) {
            throw new ErrorBD("No se ha encontrado un administrador con correo " + cor);
        }
        users = null;
    }

    @Override
    public void abrirVentanaPrincipal() {
        VistaPrincipalAdmin.abrirVentana();
    }

    public void modificarInformacionAdministrador(String cor, String usr, String contr){
        this.modificarInformacion(cor, usr, contr);
        BD bd = new BD();
        bd.Update("UPDATE Administrador SET correo = '" + cor + "';");
    }

    public void eliminarCuenta(){
        super.eliminarCuenta();
        BD bd = new BD();
        bd.Delete("DELETE FROM Administrador WHERE correo = '" + this.getCorreo() + "';");
    }

    @Override
    public String getCorp() {
        return null;
    }

    public void eliminarusuario(String correo){
        BD bd = new BD();
        long numUsuarios = (long)bd.SelectEscalar("SELECT COUNT(*) FROM Usuario WHERE " +
                "correo = '" + correo + "';");

        if (numUsuarios > 0){
            Usuario.buscarUsuario(correo).eliminarCuenta();
        }
    }



}
