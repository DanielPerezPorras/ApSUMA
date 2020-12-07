package modelo;

public class Administrador extends Usuario{

    public Administrador(String cor, String dni, String nombr, String apell, String contr) {
        super(cor, dni, nombr, apell, contr);
    }

    public Administrador(String cor) {
        super(cor);
    }
}
