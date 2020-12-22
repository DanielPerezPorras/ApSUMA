package modelo;

import gui.VistaPrincipalInvitado;

import java.util.ArrayList;

public class Invitado extends Usuario{
    private ArrayList<Evento> eventos = new ArrayList<>();

    public Invitado(){
    }

    @Override
    public String getCorp() {
        return null;
    }

}
