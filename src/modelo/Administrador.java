package modelo;

import java.util.ArrayList;

public class Administrador extends Usuario{

    private ArrayList<Usuario> users;

    public Administrador(String cor, String usr, String contr) {
        super(cor, usr, contr);
        BD bd = new BD();
        bd.Insert("INSERT INTO Administrador ('" + cor + "');");
        users = null;
    }

    public Administrador(String cor) {
        super(cor);
        users = null;
    }
    public void eliminarusuario(Usuario u){
        users.remove(u);
    }



}
