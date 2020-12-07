package modelo;

import java.util.ArrayList;

public class Administrador extends Usuario{

    public ArrayList<Usuario> users;

    public Administrador(String cor, String dni, String nombr, String apell, String contr) {
        super(cor, dni, nombr, apell, contr);
    }

    public Administrador(String cor) {
        super(cor);
    }

    public void eliminarusuario(Usuario u){
        users.remove(u);
    }

}
