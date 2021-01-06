package modelo.contenido;

import gui.contenido.VistaContenido;
import modelo.BD;
import modelo.ErrorBD;
import modelo.Evento;
import java.util.List;

public abstract class Contenido {

    private final Evento evento;

    // Crea un nuevo tipo de contenido
    public Contenido(Evento ev) {
        BD bd = new BD();
        bd.Insert("INSERT INTO Contenido (evento) VALUES ('" + ev.getNombre() + "')");
        evento = ev;
    }

    // Recupera un contenido
    public Contenido(int id) {
        BD bd = new BD();
        List<Object[]> contenidos = bd.Select("SELECT evento FROM Contenido WHERE id=" + id);
        if (contenidos.size() > 0) {
            evento = Evento.buscarEvento(contenidos.get(0)[0].toString());
        } else {
            throw new ErrorBD("No se ha encontrado un contenido con id " + id);
        }
    }

    public abstract String getTipo();

    public abstract VistaContenido getVista();

    public static Contenido buscarContenido(int id) {
        Contenido c = null;
        try {
            c = new ContenidoTexto(id);
        } catch (Exception ignored) { }
        return c;
    }

}
