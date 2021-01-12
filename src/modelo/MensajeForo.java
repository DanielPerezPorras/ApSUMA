package modelo;

import java.util.List;

public class MensajeForo {
    private String autor;
    private String contenido;

    public MensajeForo(String contenido, String autor, int idForo){
        BD bd = new BD();
        List<Object[]> lista = bd.Select("SELECT MAX(idMensajeForo) FROM MensajeForo");
        int id;
        try {
            id = Integer.parseInt(lista.get(0)[0].toString())+1;
        } catch (NullPointerException ex) {
            id = 0;
        }
        bd = new BD();
        bd.Insert("INSERT INTO MensajeForo(" + id + ", '" + autor + "', '" + contenido + "', " + idForo + ");");
        this.autor = autor;
        this.contenido = contenido;
    }

    public MensajeForo(int idMensaje){
        BD bd = new BD();
        List<Object[]> mensajeList = bd.Select("SELECT autor, contenido FROM Foro WHERE id = " + idMensaje + ";");
        if (mensajeList.size() > 0) {
            Object[] user = mensajeList.get(0);
            autor = (String)user[1];
            contenido = (String)user[2];
        } else {
            throw new ErrorBD("No se ha encontrado un foro con id " + idMensaje);
        }
    }

    public String getAutor(){ return this.autor; }
    public String getContenido(){ return this.contenido; }
}
