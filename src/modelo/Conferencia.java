package modelo;

import java.util.Date;

public class Conferencia extends Evento {

    private String link;

    public Conferencia(Date dia, String nom,Usuario dueno,  String link) {
        super(dia,nom,dueno);
        this.link = link;
    }

    public Conferencia(int identificador) {
        super(identificador);
    }
}
