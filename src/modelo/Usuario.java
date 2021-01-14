package modelo;

import gui.VistaPrincipal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Usuario {

    private String correo;
    private String nombreUsuario;
    private String contra;
    private ArrayList<MensajeDirecto> mensajesDirectos;

    public Usuario(){}

    // Constructor para crear un nuevo usuario
    public Usuario(String cor, String nombreUs, String contr){
        BD bd = new BD();
        bd.Insert("INSERT INTO Usuario VALUES ('" + cor + "', '" + nombreUs + "', '" + contr + "');");
        correo = cor;
        nombreUsuario = nombreUs;
        contra = contr;
    }

    // Constructor para recuperar los datos de un usuario ya existente
    public Usuario(String cor) {

        BD bd = new BD();
        List<Object[]> userList = bd.Select("SELECT * FROM Usuario WHERE correo = '" + cor + "';");

        if (userList.size() > 0) {
            Object[] user = userList.get(0);
            correo = cor;
            nombreUsuario = (String)user[1];
            contra = (String)user[2];
        } else {
            throw new ErrorBD("No se ha encontrado un usuario con correo " + cor);
        }

    }

    public void modificarInformacion(String cor, String nombr, String corCorp){
        // Hacer sentencia SQL "UPDATE..."
        BD bd = new BD();
        bd.Update("UPDATE Usuario SET correo = '" + cor + "', nombreUsuario = '" + nombr + "' WHERE correo = '" + getCorreo() + "' ;");
        correo = cor;
        nombreUsuario = nombr;
    }

    public void eliminarCuenta(){
        // Hacer sentencia SQL "DELETE..."
        BD bd = new BD();
        bd.Delete("DELETE FROM Usuario WHERE correo = '" + correo + "';");
        correo = null;
        nombreUsuario = null;
        contra = null;
    }

    // Métodos para crear los eventos
    public void crearCurso(Date dia, String nombre, int numClases, int duracion){
    }
    public void crearActividad(Date dia, String nombre, String lugar){
    }
    public void crearConferencia(Date dia, String nombre, String link){
    }
    //Métodos para eliminar eventos
    public void eliminarEvento(Evento evento){
    }

    public void cargarMensajes(){
        if (mensajesDirectos == null){
            mensajesDirectos = new ArrayList<>();
        }

        BD bd = new BD();
        List<Object[]> idMensajes = bd.Select("SELECT idMensaje FROM MensajeDirecto WHERE receptor = '" + Sesion.getUsuarioLogueado().getCorreo() + "';");
        for (Object[] mensaje : idMensajes){
            MensajeDirecto md = new MensajeDirecto((int)mensaje[0]);
            mensajesDirectos.add(md);
        }
    }

    public ArrayList<MensajeDirecto> getMensajesDirectos(){
        return mensajesDirectos;
    }

    public boolean hayMensajesNuevos() {
        BD bd = new BD();
        long cuentaMensajes = (long)bd.SelectEscalar("SELECT COUNT(*) FROM MensajeDirecto WHERE receptor = '" + this.getCorreo() + "';");
        return cuentaMensajes > mensajesDirectos.size();
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombreUsuario(){
        return nombreUsuario;
    }

    public List<Evento> getEventosInscritos() {
        ArrayList<Evento> eventosInscritos = new ArrayList<>();
        BD bd = new BD();
        List<Object[]> resultados = bd.Select("SELECT nombre FROM UsuarioEvento WHERE correo ='" + getCorreo() + "'");
        for (Object[] tupla : resultados) {
            eventosInscritos.add(Evento.buscarEvento((String)tupla[0]));
        }
        return eventosInscritos;
    }

    // Devuelve una lista vacía si se llama en un estudiante.
    public List<Evento> getEventosCreados() {
        List<Evento> eventosCreados = new ArrayList<>();
        BD bd = new BD();
        List<Object[]> resultados = bd.Select("SELECT nombre FROM Evento WHERE correo='" + getCorreo() + "'");
        for (Object[] tupla : resultados) {
            eventosCreados.add(Evento.buscarEvento((String)tupla[0]));
        }
        return eventosCreados;
    }

    public void apuntarseEvento(Evento evento){
        BD bd = new BD();
        bd.Insert("INSERT INTO UsuarioEvento (correo, nombre) VALUES('" + getCorreo() + "', '" + evento.getNombre() + "');");
    }

    public void desapuntarseEvento(Evento evento){
        BD bd = new BD();
        bd.Delete("DELETE FROM UsuarioEvento WHERE nombre = '" + evento.getNombre() + "';");
    }

    public static Usuario buscarUsuario(String correo) {
        Usuario usuario = null;
        BD bd = new BD();
        String where = " WHERE correo='" + correo + "'";
        if ((long)bd.SelectEscalar("SELECT COUNT(*) FROM Estudiante" + where) > 0) {
            usuario = new Estudiante(correo);
        } else if ((long)bd.SelectEscalar("SELECT COUNT(*) FROM Tutor" + where) > 0) {
            usuario = new Tutor(correo);
        } else if ((long)bd.SelectEscalar("SELECT COUNT(*) FROM Colaborador" + where) > 0) {
            usuario = new Colaborador(correo);
        } else if ((long)bd.SelectEscalar("SELECT COUNT(*) FROM Administrador" + where) > 0) {
            usuario = new Administrador(correo);
        }
        return usuario;
    }

    public static Usuario crearInvitado(){
        Usuario usuario = null;
        try {
            usuario = new Invitado();
        } catch (ErrorBD ex1){
            throw new ErrorBD("No se ha podido registrar invitado");
        }
        return usuario;
    }

    public static boolean correoEstaUsado(String correo) {
        BD bd = new BD();
        Object cuenta = bd.SelectEscalar("SELECT COUNT(*) FROM Usuario WHERE correo='" + correo + "'");
        return (long)cuenta > 0;
    }
    
    public ArrayList<Evento> getEventosFecha(Date fecha) {
    	java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
    	ArrayList<Evento> eventos = new ArrayList<Evento>();

        BD bd = new BD();
        List<Object[]> resultados = bd.Select("SELECT nombre FROM Evento WHERE fecha='" + sqlfecha.toString() + "'");
        for (Object[] tupla : resultados) {
            eventos.add(Evento.buscarEvento((String)tupla[0]));
        }

        return eventos;
    }

    public String getCorp()
    {
    	return "";
    }

    public void abrirVentanaPrincipal() {
        VistaPrincipal.abrirVentana();
    }

}
