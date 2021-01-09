package modelo.contenido;

import gui.contenido.VistaContenido;
import gui.contenido.VistaContenidoTexto;
import modelo.*;

public class ContenidoTexto extends Contenido {

    private final String texto;

    // Crea un nuevo tipo de contenido
    public ContenidoTexto(Evento ev, String texto) {
        super(ev);
        BD bd = new BD();
        bd.Insert("UPDATE Contenido SET texto='" + texto + "' " +
                "WHERE id='" + getId() + "'");
        this.texto = texto;
    }

    public ContenidoTexto(int id) {
        super(id);
        BD bd = new BD();
        Object[] tupla = bd.Select("SELECT tipo, texto FROM Contenido WHERE id=" + id).get(0);

        // No comprobamos si la lista de contenido tiene el ítem, ya que en el super nos aseguramos
        // Comprobamos si coincide el tipo del ítem con el del constructor
        if (!tupla[0].toString().equals(getTipo())) {
            throw new ErrorBD("El tipo del contenido de id=" + id + " es " + tupla[0] + ", no " + getTipo());
        }

        texto = tupla[1].toString();

    }

    public String getTexto() { return texto; }

    public String getTipo() { return "texto"; }

    public VistaContenido getVista(boolean modoEdicion) {
        return new VistaContenidoTexto(this, modoEdicion);
    }

}
