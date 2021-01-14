package modelo;

import gui.UtilidadesGUI;

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
        bd.Insert("INSERT INTO MensajeForo VALUES (" + id + ", '" + autor + "', '" + contenido + "', " + idForo + ");");
        this.autor = autor;
        this.contenido = contenido;
    }

    public MensajeForo(int idMensaje){
        BD bd = new BD();
        List<Object[]> mensajeList = bd.Select("SELECT autor, contenido FROM MensajeForo WHERE idMensajeForo = " + idMensaje + ";");
        if (mensajeList.size() > 0) {
            Object[] user = mensajeList.get(0);
            autor = (String)user[0];
            contenido = (String)user[1];
        } else {
            throw new ErrorBD("No se ha encontrado un mensaje de foro con id " + idMensaje);
        }
    }

    public String getAutor(){ return this.autor; }
    public String getContenido(){ return this.contenido; }

    public String getHtml() {
        return "<b>" + UtilidadesGUI.escaparHtml(autor) + "</b><br/>" + UtilidadesGUI.escaparHtml(contenido) + "<br/><br/>";
    }

}
