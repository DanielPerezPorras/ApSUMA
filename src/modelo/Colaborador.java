package modelo;

import java.util.ArrayList;

public abstract class Colaborador {
    private String nombre;
    private ArrayList<Evento> creado;

    public Colaborador(String nombr){
        nombre = nombr;
        creado = new ArrayList<>();
    }

    public void crearEvento(Evento evento){
        creado.add(evento);
    }
}
