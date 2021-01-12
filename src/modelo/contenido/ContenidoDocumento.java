package modelo.contenido;

import gui.contenido.VistaContenido;
import gui.contenido.VistaContenidoDocumento;
import gui.contenido.VistaContenidoTest;
import modelo.BD;
import modelo.ErrorBD;
import modelo.Evento;

public class ContenidoDocumento extends Contenido {

    private String enlace;
    private String textoVisible;

    public ContenidoDocumento(Evento ev, String enlace, String textoVisible)
    {
        super(ev);
        BD bd = new BD();
        bd.Update("UPDATE Contenido SET texto=CONCAT('" + enlace + "', char(13), char(10), '" + textoVisible + "') " +
                "WHERE id='" + getId() + "'");
        this.enlace = enlace;
        this.textoVisible = textoVisible;
    }

    public ContenidoDocumento(int id)
    {
        super(id);
        BD bd = new BD();
        Object[] tupla = bd.Select("SELECT tipo, texto FROM Contenido WHERE id=" + id).get(0);

        // No comprobamos si la lista de contenido tiene el ítem, ya que en el super nos aseguramos
        // Comprobamos si coincide el tipo del ítem con el del constructor
        if (!tupla[0].equals(getTipo())) {
            throw new ErrorBD("El tipo del contenido de id=" + id + " es " + tupla[0] + ", no " + getTipo());
        }

        String[] lineas = tupla[1].toString().split("\\r?\\n");
        enlace = lineas[0];
        textoVisible = lineas[1];

    }

    public String getEnlace() { return enlace; }

    public String getTextoVisible() { return textoVisible; }

    @Override
    public String getTipo() { return "documento"; }

    @Override
    public VistaContenido getVista(boolean modoEdicion) {
        return new VistaContenidoDocumento(this, modoEdicion);
    }

}
