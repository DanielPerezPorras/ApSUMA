package modelo;

import java.util.ArrayList;

public class Administrador extends Usuario{

    public ArrayList<Usuario> users;

    public Administrador(String cor, String dni, String nombr, String apell, String contr) {
        super(cor, dni, nombr, apell, contr);
        users=new ArrayList<>();
    }

    public Administrador(String cor) {
        super(cor);
        users=new ArrayList<>();
    }

    public void eliminarusuario(Usuario u){
        users.remove(u);
    }

}
