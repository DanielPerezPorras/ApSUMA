package modelo;

import gui.VistaPrincipalEstudiante;

import java.util.ArrayList;

public class Invitado extends Usuario{
    private ArrayList<Evento> ActividadSocial = null;
    private ArrayList<Evento> Conferencia = null;

    public Invitado(){
    }

    @Override
    public void abrirVentanaPrincipal() {
        VistaPrincipalEstudiante.abrirVentana();
    }
}
