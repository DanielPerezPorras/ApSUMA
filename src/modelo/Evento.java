package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Evento {

    private int id;
    private Date Fecha;
    private String Nombre;
    private List<Usuario> usuarios;
    private Usuario creador;
    //TODO private Contenido[] contenido;

    public Evento(int identificador,Date dia, String nom, Usuario dueno) {
        id = identificador;
        Fecha = dia;
        Nombre = nom;
        usuarios = new ArrayList<>();
        if (dueno instanceof Tutor || dueno instanceof Colaborador) { //TODO dueno tambien puede ser instancia de colaborador
            creador = dueno;
        } else {
            throw new RuntimeException("El creador debe ser un tutor o un colaborador");
        }
    }

    public Evento(int identificador) {
        id = identificador;
    }

    public void inscripcionUsuario(Usuario user) {
        if (usuarios.contains(user)) {
            throw new RuntimeException("El usuario ya está registrado en el evento");
        } else {
            usuarios.add(user);
        }
    }

}
