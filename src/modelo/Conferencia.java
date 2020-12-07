package modelo;

import java.util.Date;

public class Conferencia extends Eventos{

    private String link;

    public Conferencia(Date dia, String nom, String link) {
        super(dia,nom);
        this.link = link;
    }

}
