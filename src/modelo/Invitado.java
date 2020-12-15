package modelo;

import gui.VistaPrincipalEstudiante;
import gui.VistaPrincipalInvitado;

import java.util.ArrayList;
import java.util.List;

public class Invitado extends Usuario{
    private ArrayList<Evento> eventos = new ArrayList<>();

    public Invitado(){
    }

    @Override
    public void abrirVentanaPrincipal() {
        VistaPrincipalInvitado.abrirVentana();
    }
}
