package modelo;

public class Estudiante extends Usuario{

    public Estudiante(String corr, String dni, String nombr, String apell) {
        super(corr, dni, nombr, apell);
    }

    public Estudiante(String corr) {
        super(corr);
    }
}
