package gui;

import gui.contenido.*;
import modelo.*;
import modelo.contenido.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControladorEvento implements ActionListener, MouseListener, ListSelectionListener {

    private final VistaEvento vista;
	private boolean ejecutandoListListener = false;
	private Usuario seleccionado;

    public ControladorEvento(VistaEvento vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	String comando = e.getActionCommand();
    	switch (comando) {

			case "BUSCAR":
				BD bd = new BD();
				String busqueda = vista.getTextBoxValue();

				String[] nombres;
				List<Object[]> listaResultado = bd.Select("SELECT * FROM Usuario WHERE nombreUsuario LIKE '%" + busqueda + "%';");
				nombres = new String[listaResultado.size()];
				int i = 0;
				for (Object[] objects : listaResultado)
				{
					nombres[i] =  (String)objects[0];
					i++;
				}
				if(listaResultado.size() < 1)
				{
					JOptionPane.showMessageDialog(vista, new Exception("Lo siento, no hay usuarios con ese nombre"),
							"No hay usuarios con ese nombre", JOptionPane.ERROR_MESSAGE);
				}else
				{
					vista.resultadoBusqueda(nombres);
				}
				vista.limpiar();
				break;
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
					case "Documento":
						DialogoNuevoDocumento dialogoNuevoDocumento = new DialogoNuevoDocumento(vista);
						dialogoNuevoDocumento.setVisible(true);
						if (dialogoNuevoDocumento.seHaConfirmado()) {
							new ContenidoDocumento(vista.getEvento(), dialogoNuevoDocumento.getEnlace(), dialogoNuevoDocumento.getTextoVisible());
							vista.refrescarContenido();
						}
						break;
					case "Llamada":
						DialogoNuevoLlamada dialogoNuevoLlamada = new DialogoNuevoLlamada(vista);
						dialogoNuevoLlamada.setVisible(true);
						if (dialogoNuevoLlamada.seHaConfirmado()) {
							new ContenidoLlamada(vista.getEvento(), dialogoNuevoLlamada.getEnlace(), dialogoNuevoLlamada.getTextoVisible());
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
			case "ELIM_SANCION" :
				bd = new BD();
				bd.Update("UPDATE Usuario SET fechaSancion = null WHERE correo = '" + seleccionado.getCorreo() + "';");
				JOptionPane.showMessageDialog(null, "Sanción Eliminada");
				break;
			case "SANCION" :
				bd = new BD();
				bd.Update("UPDATE Usuario SET fechaSancion = DATE_ADD(CURDATE(), INTERVAL 5 DAY) WHERE correo = '" + seleccionado.getCorreo() + "';");
				JOptionPane.showMessageDialog(null, "Usuario Sancionado");
				break;
			case "EXPULSAR" :
				seleccionado.desapuntarseEvento(vista.getEvento());
				JOptionPane.showMessageDialog(null, "Usuario Expulsado del Curso");
				break;
			case "ANYADIR" :
				seleccionado.apuntarseEvento(vista.getEvento());
				JOptionPane.showMessageDialog(null, "Usuario añadido al Curso");
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

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (vista.selectedRows()>=1 && !e.getValueIsAdjusting() && !ejecutandoListListener) {
			ejecutandoListListener = true;
			seleccionado =  Usuario.buscarUsuario(vista.getValorLista());
			vista.datosUsuario(seleccionado);

			ejecutandoListListener = false;
		}
	}
}
