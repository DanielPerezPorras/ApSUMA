package modelo;

public class Estudiante extends Usuario{

    public Estudiante(String corr, String dni, String nombr, String apell, String contr) {
        super(corr, dni, nombr, apell, contr);
    }

    public Estudiante(String corr) {
        super(corr);
    }
}
