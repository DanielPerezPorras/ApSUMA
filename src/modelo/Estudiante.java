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
    }
}
