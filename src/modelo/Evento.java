package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Evento {

    private Date Fecha;
    private String Nombre;
    private List<Usuario> usuarios;
    private Usuario creador;
    private Administrador administrador;
    //TODO private Contenido[] contenido;

    // Constructor para crear un nuevo evento
    public Evento(Date dia, String nom, Usuario dueno) {
        BD bd = new BD();
        bd.Insert("INSERT INTO Evento (" + dia + "', '" + nom + "', " + dueno.getCorreo() + ");");
        Fecha = dia;
        Nombre = nom;
        usuarios = new ArrayList<>();
        if (dueno instanceof Tutor || dueno instanceof Colaborador) { //TODO dueno tambien puede ser instancia de colaborador
            creador = dueno;
        } else {
            throw new RuntimeException("El creador debe ser un tutor o un colaborador");
        }
    }

    // Constructor para recuperar los datos de un evento ya existente
    public Evento(String nombre) {
        BD bd = new BD();
        List<Object[]> eventList = bd.Select("SELECT * FROM Evento WHERE nombre = '" + nombre + "';");

        if (eventList.size() > 0) {
            Object[] event = eventList.get(0);
            Nombre = nombre;
            Fecha = (Date)event[0];
            creador = Usuario.buscarUsuario((String)event[2]);
        } else {
            throw new ErrorBD("No se ha encontrado un evento con nombre " + nombre);
        }
    }

    public void inscripcionUsuario(Usuario user) {
        if (usuarios.contains(user)) {
            throw new RuntimeException("El usuario ya está registrado en el evento");
        } else {
            BD bd = new BD();
            bd.Insert("INSERT INTO Evento (" + Fecha + "', '" + nombreUs + "', '" + contr + "');");
            usuarios.add(user);
        }
    }

    public void eliminarEvento(){
        Fecha = null;
        Nombre = null;
        for (Usuario usuario : usuarios){
            usuario.darseBajaEvento(this);
        }
        administrador.darseBajaEvento(this);
    }
}
