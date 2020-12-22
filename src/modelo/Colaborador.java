package modelo;

import java.util.ArrayList;
import java.util.List;

public class Colaborador extends Usuario{
    private String correoCorp;
    private ArrayList<Evento> creado;

    public Colaborador(String correoCorporativo, String cor, String usuario, String contr) {
        super(cor, usuario, contr);
        BD bd = new BD();
        bd.Insert("INSERT INTO Colaborador VALUES ('" + cor + "', '" + correoCorporativo + "');");
        correoCorp = correoCorporativo;
        creado = null;
    }

    public Colaborador(String correo) {
        super(correo);
        BD bd = new BD();
        List<Object[]> userList = bd.Select("SELECT correoCorp FROM Colaborador WHERE correo = '" + correo + "';");

        if (userList.size() > 0) {
            Object[] user = userList.get(0);
            correoCorp = (String)user[0];
            creado = null;
        } else {
            throw new ErrorBD("No se ha encontrado un colaborador con correo " + correo);
        }
    }

    public void crearEvento(Evento evento){
        creado.add(evento);
    }

    public void modificarInformacion(String cor, String usuario, String correoCorporativo){
        super.modificarInformacion(cor, usuario, null);
        BD bd = new BD();
        bd.Update("UPDATE Colaborador SET correoCorp = '" + correoCorporativo + "' WHERE correo = '" + getCorreo() + "';");
        correoCorp = correoCorporativo;

    }

    public void eliminarCuenta(){
        BD bd = new BD();
        bd.Delete("DELETE FROM Colaborador WHERE correo = '" + this.getCorreo() + "';");
        correoCorp = null;
        super.eliminarCuenta();
    }

    @Override
    public String getCorp() {
        return this.correoCorp;
    }

    public String getCorreoCorp() {
        return correoCorp;
    }

}
