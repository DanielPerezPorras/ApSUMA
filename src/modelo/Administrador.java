package modelo;

import java.util.ArrayList;

public class Administrador extends Usuario{

    private ArrayList<Usuario> users;

    public Administrador(String cor, String usr, String contr) {
        super(cor, usr, contr);
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
