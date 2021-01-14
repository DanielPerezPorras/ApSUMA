package gui;

import gui.contenido.DialogoNuevoEnlace;
import gui.contenido.DialogoNuevoTexto;
import modelo.*;
import modelo.contenido.Contenido;
import modelo.contenido.ContenidoEnlace;
import modelo.contenido.ContenidoTexto;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

public class ControladorEvento implements ActionListener, MouseListener, ListSelectionListener {

    private final VistaEvento vista;

    public ControladorEvento(VistaEvento vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	String comando = e.getActionCommand();
    	switch (comando) {
    		case "VOLVER":
    			vista.dispose();
    			abreventana();
    			break;

			case "ANULAR":
				if (vista.getSoyCreadorEvento()) {
					vista.alternarModoEdicion();
				} else {
					Sesion.getUsuarioLogueado().desapuntarseEvento(vista.getEvento());
					vista.dispose();
					abreventana();
				}
				break;

			case "ELIMINAR":
				vista.getEvento().eliminar();
				vista.dispose();
				abreventana();
				break;

			case "NUEVO CONTENIDO":
				switch (vista.getSeleccionNuevoContenido()) {

					case "Texto":
						DialogoNuevoTexto dialogoNT = new DialogoNuevoTexto(vista);
						dialogoNT.setVisible(true);
						if (dialogoNT.seHaConfirmado()) {
							new ContenidoTexto(vista.getEvento(), dialogoNT.getTexto());
							vista.refrescarContenido();
						}
						break;

					case "Enlace":
						DialogoNuevoEnlace dialogoNE = new DialogoNuevoEnlace(vista);
						dialogoNE.setVisible(true);
						if (dialogoNE.seHaConfirmado()) {
							new ContenidoEnlace(vista.getEvento(), dialogoNE.getEnlace(), dialogoNE.getTextoVisible());
							vista.refrescarContenido();
						}
						break;

				}
				break;

			case "EDITAR_EVENTO":
				DialogoEditarEvento dialogoEditarEv = new DialogoEditarEvento(vista, vista.getEvento());
				dialogoEditarEv.setVisible(true);
				if (dialogoEditarEv.seHaConfirmado()) {

					Date fecha = dialogoEditarEv.getFecha();
					String nombre = dialogoEditarEv.getTextoNombre();
					int numClases = dialogoEditarEv.getClases();
					int duracion = dialogoEditarEv.getDuracion();
					String dato = dialogoEditarEv.getDato();
					Evento evento = vista.getEvento();

					if (evento instanceof Curso) {
						Curso curso = (Curso)evento;
						curso.modificar(fecha, nombre, numClases, duracion);
					} else if (evento instanceof ActividadSocial) {
						ActividadSocial actividad = (ActividadSocial)evento;
						actividad.modificar(fecha, nombre, dato);
					} else if (evento instanceof Conferencia) {
						Conferencia conferencia = (Conferencia)evento;
						conferencia.modificar(fecha, nombre, dato);
					} else {
						throw new RuntimeException("Tipo de evento no reconocido");
					}

					vista.actualizarTituloEvento();
					vista.refrescarContenido();
				}
				break;

			case "CREAR FORO":
				String nombre = JOptionPane.showInputDialog(
						vista,
						"Introduzca un nombre para el foro",
						"Crear foro",
						JOptionPane.PLAIN_MESSAGE);
				if (nombre != null) {
					new Foro(nombre, vista.getEvento().getNombre());
					vista.refrescarListaForos();
				}
				break;

			case "ELIMINAR FORO":
				Foro sel = vista.getForoSeleccionado();
				if (sel != null) {
					String[] opciones = {"Sí", "No"};
					int n = JOptionPane.showOptionDialog(vista,
							"¿Desea eliminar el foro \"" + sel.getNombre() + "\"?",
							"Eliminar foro",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
					if (n == 0) {
						sel.eliminarForo();
						vista.refrescarListaForos();
					}
				} else {
					JOptionPane.showMessageDialog(vista,
							"Seleccione el foro que desea eliminar.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
				break;

			case "ACTUALIZAR":
				Foro foroAActualizar = vista.getForoSeleccionado();
				if (foroAActualizar != null) {

					if (foroAActualizar.hayMensajesNuevos()) {
						foroAActualizar.cargarMensajes();
						vista.cargarMensajes();
					}

				} else {
					JOptionPane.showMessageDialog(vista,
							"Seleccione un foro.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
				break;

			case "ENVIAR MENSAJE":
				Foro selForo = vista.getForoSeleccionado();
				if (selForo != null) {
					String texto = vista.getTextoAEnviar();
					if (texto != null && texto.length() > 0) {
						new MensajeForo(texto, Sesion.getUsuarioLogueado().getCorreo(), selForo.getId());
						selForo.cargarMensajes();
						vista.cargarMensajes();
						vista.vaciarTextoAEnviar();
					} else {
						JOptionPane.showMessageDialog(vista,
								"Introduzca el texto a enviar.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(vista,
							"Seleccione el foro al que desea enviar.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
				break;

			default:

				// Para eliminar un ítem de contenido
				if (comando.startsWith("ELIMINAR_CONTENIDO_")) {
					int resultadoDialogo = JOptionPane.showConfirmDialog(vista, "¿Desea eliminar este contenido?", "Confirmación",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (resultadoDialogo == JOptionPane.YES_OPTION) {
						int idEliminar = Integer.parseInt(comando.substring("ELIMINAR_CONTENIDO_".length()));
						Contenido.buscarContenido(idEliminar).borrar();
						vista.refrescarContenido();
					}
				}

		}
    }

	private void abreventana() {
		if (Sesion.logueadoComoAdmin()) {
			VistaPrincipalAdmin.abrirVentana();
		} else {
			VistaPrincipal.abrirVentana();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	// Cuando cambia la selección de la lista de foros.
	public void valueChanged(ListSelectionEvent e) {
		vista.cargarMensajes();
	}

}
