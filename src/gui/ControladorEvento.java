package gui;

import gui.contenido.DialogoNuevoEnlace;
import gui.contenido.DialogoNuevoTest;
import gui.contenido.DialogoNuevoTexto;
import modelo.*;
import modelo.contenido.Contenido;
import modelo.contenido.ContenidoEnlace;
import modelo.contenido.ContenidoTest;
import modelo.contenido.ContenidoTexto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControladorEvento implements ActionListener, MouseListener {

    private final VistaEvento vista;

    public ControladorEvento(VistaEvento vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	String comando = e.getActionCommand();
    	switch (comando) {
    		//Este primer case contiene lo necesario para buscar un usuario
			// en el panel Usuario que aún hay que implementar

			/*case "NUEVO ALUMNO":
				BD bd = new BD();
				String busqueda = vista.getTextBoxValue();

				List<String> listaNombres = new ArrayList<String>();
				List<Object[]> listaResultado = bd.Select("SELECT * FROM Usuario WHERE nombreUsuario LIKE '%" + busqueda + "%';");
				for (Object[] objects : listaResultado)
				{
					listaNombres.add((String)objects[0]);
				}
				if(listaResultado.size() < 1)
				{
					JOptionPane.showMessageDialog(vista, new Exception("Lo siento, no hay usuarios con ese nombre"),
							"No hay usuarios con ese nombre", JOptionPane.ERROR_MESSAGE);
				}else
				{
					vista.anyadirTexto(listaNombres.toArray(new String[0]));
				}
				break;*/
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

					case "Test":
						DialogoNuevoTest dialogoNuevoTest = new DialogoNuevoTest(vista, "Test");
						dialogoNuevoTest.setVisible(true);
						if (dialogoNuevoTest.seHaConfirmado()) {
							new ContenidoTest(vista.getEvento(), dialogoNuevoTest.getEnlace(), dialogoNuevoTest.getTextoVisible());
							vista.refrescarContenido();
						}
						break;

					case "Cuestionario":
						DialogoNuevoTest dialogoNuevoCuestionario = new DialogoNuevoTest(vista, "Cuestionario");
						dialogoNuevoCuestionario.setVisible(true);
						if (dialogoNuevoCuestionario.seHaConfirmado()) {
							new ContenidoTest(vista.getEvento(), dialogoNuevoCuestionario.getEnlace(), dialogoNuevoCuestionario.getTextoVisible());
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
