package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Evento {

    private Integer id;
    private Date Fecha;
    private String Nombre;
    private List<Usuario> usuarios;
    private Usuario creador;
    private Administrador administrador;
    //TODO private Contenido[] contenido;

    public Evento(Date dia, String nom, Usuario dueno) {

        id = null;
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

    public void eliminarEvento(){
        id = null;
        Fecha = null;
        Nombre = null;
        for (Usuario usuario : usuarios){
            usuario.darseBajaEvento(this);
        }
        administrador.darseBajaEvento(this);
    }

    public Date getFecha() {
        return Fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public Usuario getCreador() {
        return creador;
    }

    public Administrador getAdministrador() {
        return administrador;
    }
}
