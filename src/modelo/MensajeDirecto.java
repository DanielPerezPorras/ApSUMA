package modelo;

import gui.UtilidadesGUI;

import java.util.List;

public class MensajeDirecto {
    private String emisor;
    private String receptor;
    private String contenido;
    private String asunto;

    public MensajeDirecto(String emisor, String receptor, String contenido, String asunto){
        BD bd = new BD();
        List<Object[]> lista = bd.Select("SELECT MAX(idMensaje) FROM MensajeDirecto");
        int id;
        try {
            id = Integer.parseInt(lista.get(0)[0].toString())+1;
        } catch (NullPointerException ex) {
            id = 0;
        }
        bd = new BD();
        bd.Insert("INSERT INTO MensajeDirecto VALUES (" + id + ", '" + emisor + "', '" + receptor + "', '" + contenido + "', '" + asunto + "');");
        this.emisor = emisor;
        this.receptor = receptor;
        this.contenido = contenido;
        this.asunto = asunto;
    }

    public MensajeDirecto(int id){
        BD bd = new BD();
        List<Object[]> mensajeList = bd.Select("SELECT emisor, receptor, contenido, asunto FROM MensajeDirecto WHERE idMensaje = " + id + ";");
        if (mensajeList.size() > 0) {
            Object[] user = mensajeList.get(0);
            this.emisor = (String)user[0];
            this.receptor = (String)user[1];
            this.contenido = (String)user[2];
            this.asunto = (String)user[3];
        } else {
            throw new ErrorBD("No se ha encontrado un mensaje con id " + id);
        }
    }

    public String toString(){
        return this.getEmisor() + " : " + this.getAsunto();
    }

    public String getAsunto() {
        return asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getReceptor() {
        return receptor;
    }
}
