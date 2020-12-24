package modelo;

import java.util.Date;

public class Conferencia extends Evento {

    private String link;

    public Conferencia(Date dia, String nom,Usuario dueno,  String link) {
        super(dia,nom,dueno);
        BD bd = new BD();
        bd.Insert("INSERT INTO Conferencia VALUES('" + nom + "', '" + link + "');");
        this.link = link;
    }

    public Conferencia(String nombre) {
        super(nombre);
    }

}
