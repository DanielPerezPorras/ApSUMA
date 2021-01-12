package modelo;

import java.util.ArrayList;
import java.util.List;

public class Foro {
    private String nombre;
    private int id;
    private ArrayList<MensajeForo> mensajes;

    public Foro(String nombre, String evento){
        BD bd = new BD();
        List<Object[]> lista = bd.Select("SELECT MAX(idForo) FROM Foro");
        try {
            id = Integer.parseInt(lista.get(0)[0].toString())+1;
        } catch (NullPointerException ex) {
            id = 0;
        }
        bd = new BD();
        bd.Insert("INSERT INTO Foro VALUES(" + id + ", '" + nombre + "', '" + evento + "');");
        this.nombre = nombre;
    }

    public Foro(int id){
        BD bd = new BD();
        List<Object[]> foroList = bd.Select("SELECT nombre FROM Foro WHERE id = " + id + ";");
        if (foroList.size() > 0) {
            Object[] user = foroList.get(0);
            nombre = (String)user[0];
            this.id = id;
            cargarMensajes(id);
        } else {
            throw new ErrorBD("No se ha encontrado un foro con id " + id);
        }
    }

    private void cargarMensajes(int id){
        BD bd = new BD();
        List<Object[]> mensajeList = bd.Select("SELECT idMensajeForo FROM MensajeForo WHERE foro = " + id + ";");
        for (Object[] tupla : mensajeList)
            {
            MensajeForo mensaje = new MensajeForo((int)tupla[0]);
            mensajes.add(mensaje);
        }
    }

    public String getNombre(){ return this.nombre; }
    public int getId(){ return this.id; }
    public ArrayList<MensajeForo> getMensajes(){ return this.mensajes; }

    public void eliminarForo(){
        BD bd = new BD();
        bd.Delete("DELETE FROM Foro WHERE idForo = " + id + ";");
        this.id = 0;
        this.nombre = null;
        this.mensajes = null;
    }
}
