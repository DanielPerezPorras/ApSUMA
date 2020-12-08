package modelo;

public class Estudiante extends Usuario{

    public Estudiante(String corr, String usr, String contr) {
        super(corr, usr, contr);
    }

    public Estudiante(String corr) {
        super(corr);
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
