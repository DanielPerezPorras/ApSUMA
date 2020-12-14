package modelo;

import gui.VistaPerfilEstudiante;
import gui.VistaPrincipalEstudiante;

import java.util.List;

public class Estudiante extends Usuario{

    public Estudiante(String corr, String usr, String contr) {
        super(corr, usr, contr);
        BD bd = new BD();
        bd.Insert("INSERT INTO Estudiante VALUES ('" + corr + "');");
    }

    public Estudiante(String corr) {
        super(corr);
        BD bd = new BD();
        List<Object[]> userList = bd.Select("SELECT * FROM Estudiante WHERE correo = '" + corr + "';");
        if (userList.size() == 0) {
            throw new ErrorBD("No se ha encontrado un estudiante con correo " + corr);
        }
    }

    @Override
    public void abrirVentanaPrincipal() {
        VistaPrincipalEstudiante.abrirVentana();
    }

    public void modificarInformacionEstudiante(String corr,String usr,String contr){
        this.modificarInformacion(corr, usr, contr);
        BD bd = new BD();
        bd.Update("UPDATE Estudiante SET correo = '" + corr + "';");
    }

    public void eliminarCuentaEstudiante(){
        this.eliminarCuenta();
        BD bd = new BD();
        bd.Delete("DELETE FROM Estudiante WHERE correo = '" + this.getCorreo() + "';");
    }
}
