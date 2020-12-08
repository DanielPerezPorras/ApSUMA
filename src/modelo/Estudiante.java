package modelo;

public class Estudiante extends Usuario{

    public Estudiante(String corr, String usr, String contr) {
        super(corr, usr, contr);
        BD bd = new BD();
        bd.Insert("INSERT INTO Estudiante ('" + corr + "');");
    }

    public Estudiante(String corr) {
        super(corr);
    }

    public void modificarInformacionEstudiante(String corr,String usr,String contr){
        this.modificarInformacion(corr, usr, contr);
    }
}
