package modelo;

import java.util.ArrayList;

public abstract class Colaborador extends Usuario{
    private String nombre;
    private ArrayList<Evento> creado;

    public Colaborador(String cor, String dni, String nombr, String apell, String contr) {
        super(cor, dni, nombr, apell, contr);
        creado = new ArrayList<>();
    }

    public void crearEvento(Evento evento){
        creado.add(evento);
    }
}
