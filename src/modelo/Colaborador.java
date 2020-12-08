package modelo;

import java.util.ArrayList;

public abstract class Colaborador extends Usuario{
    private String correoCorp;
    private ArrayList<Evento> creado;

    public Colaborador(String correoCorporativo, String cor, String usuario, String contr) {
        super(cor, usuario, contr);
        correoCorp = correoCorporativo;
        creado = new ArrayList<>();
    }

    public void crearEvento(Evento evento){
        creado.add(evento);
    }

    public void modificarInfomacionColaborador(String correoCorporativo, String cor, String usuario, String contr){
        this.modificarInformacion(cor, usuario, contr);
        BD bd = new BD();
        bd.Update("UPDATE Colaborador SET correo = '" + cor + "', correoCorp = '" + correoCorporativo + "';");
        correoCorp = correoCorporativo;
    }

    public void eliminarCuentaColaborador(){
        this.eliminarCuenta();
        BD bd = new BD();
        bd.Delete("DELETE FROM Colaborador WHERE correo = '" + this.getCorreo() + "';");
        correoCorp = null;
    }

    public String getCorreoCorp() {
        return correoCorp;
    }

    public ArrayList<Evento> getCreado() {
        return creado;
    }
}
