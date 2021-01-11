package gui;

import modelo.*;
import net.sourceforge.jdatepicker.impl.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class VistaPrincipal extends JFrame {

    private JPanel contentPane;
    private JTabbedPane tabbedPane;

    private JPanel panelEventos;
    private JPanel panelMensajeria;
    private JList<Evento> listaCursos;
    private JList<Evento> listaActividades;
    private JList<Evento> listaConferencias;
    private JButton btnPerfil;
    private JLabel lblUsuario;
    private JLabel lblDescripcion;
    private JButton btnEntrar;
    private JDatePickerImpl datePicker;
    private JComboBox<String> cbNuevoEvento;
    private JList<Evento> listaInscritos;
    private JList<Evento> listaCreados;
    private JButton btnAdmin;

    private JList<Evento> ultimaListaSeleccionada;

    public static void abrirVentana() {
        try {
            VistaPrincipal frame = new VistaPrincipal();
            frame.controlador(new ControladorPrincipal(frame));
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public VistaPrincipal() {
        super("ApS_UMA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Sesion.setVistaPrincipal(this);
        crearGUI();
        System.out.println("Cargando eventos...");
        cargarEventos();
        cargarEventosUsuario();
        System.out.println("Se han cargado los eventos");
        if (Sesion.getPermisos() < 3) {
            setNombreUsuario(Sesion.getUsuarioLogueado().getNombreUsuario());
        } else {
            setNombreUsuario("(invitado)");
        }

    }

    public void controlador(ControladorPrincipal ctr) {
        btnEntrar.setActionCommand("ENTRAR");
        btnPerfil.setActionCommand("PERFIL");

        btnEntrar.addActionListener(ctr);
        btnPerfil.addActionListener(ctr);

        if (Sesion.puedoCrearEventos()){
            cbNuevoEvento.setActionCommand("CREAR EVENTO");
            cbNuevoEvento.addActionListener(ctr);
        }


        datePicker.addActionListener(ctr);

        listaCursos.addListSelectionListener(ctr);
        listaActividades.addListSelectionListener(ctr);
        listaConferencias.addListSelectionListener(ctr);
        listaInscritos.addListSelectionListener(ctr);

        addWindowListener(ctr);

        if (Sesion.puedoCrearEventos()) {
            listaCreados.addListSelectionListener(ctr);
        }

        if (Sesion.logueadoComoAdmin()) {
            btnAdmin.setActionCommand("ADMIN");
            btnAdmin.addActionListener(ctr);
        }
    }

    // Cargar los eventos de las listas de la izquierda
    public void cargarEventos() {
        List<Evento> listaEventos = Evento.getEventos(getFechaSeleccionada());
        List<Evento> lCursos = listaEventos.stream().filter(ev -> ev instanceof Curso).collect(Collectors.toList());
        List<Evento> lActivs = listaEventos.stream().filter(ev -> ev instanceof ActividadSocial).collect(Collectors.toList());
        List<Evento> lConfs = listaEventos.stream().filter(ev -> ev instanceof Conferencia).collect(Collectors.toList());
        Evento[] cursos = new Evento[lCursos.size()];
        Evento[] actividades = new Evento[lActivs.size()];
        Evento[] conferencias = new Evento[lConfs.size()];
        lCursos.toArray(cursos);
        lActivs.toArray(actividades);
        lConfs.toArray(conferencias);
        listaCursos.setListData(cursos);
        listaActividades.setListData(actividades);
        listaConferencias.setListData(conferencias);
    }

    // cargar los eventos de las listas de la derecha
    public void cargarEventosUsuario() {
        Usuario usuario = Sesion.getUsuarioLogueado();

        List<Evento> lInscritos = usuario.getEventosInscritos();
        Evento[] inscritos = new Evento[lInscritos.size()];
        lInscritos.toArray(inscritos);
        listaInscritos.setListData(inscritos);

        if (Sesion.puedoCrearEventos()) {
            List<Evento> lCreados = usuario.getEventosCreados();
            Evento[] creados = new Evento[lCreados.size()];
            lCreados.toArray(creados);
            lCreados.toArray(creados);
            listaCreados.setListData(creados);
        }
    }

    public java.util.Date getFechaSeleccionada() {
        return (java.util.Date) datePicker.getModel().getValue();
    }

    public Evento getEventoSeleccionado() {
        if (listaCursos.getSelectedValue() != null) {
            return listaCursos.getSelectedValue();
        } else if (listaActividades.getSelectedValue() != null) {
            return listaActividades.getSelectedValue();
        } else if (listaConferencias.getSelectedValue() != null) {
            return listaConferencias.getSelectedValue();
        } else if (listaInscritos.getSelectedValue() != null) {
            return listaInscritos.getSelectedValue();
        } else if (listaCreados != null && listaCreados.getSelectedValue() != null) {
            return listaCreados.getSelectedValue();
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public void setUltimaListaSeleccionada(ListSelectionEvent ev) {
        ultimaListaSeleccionada = (JList<Evento>)ev.getSource();
        int indice = ultimaListaSeleccionada.getSelectedIndex();

        listaCursos.clearSelection();
        listaActividades.clearSelection();
        listaConferencias.clearSelection();
        listaInscritos.clearSelection();
        if (Sesion.puedoCrearEventos()) {
            listaCreados.clearSelection();
        }
        ultimaListaSeleccionada.setSelectedIndex(indice);

    }

    public void setTextoDescripcion(Evento ev) {
        if (ev != null) {
            if (ev.getCreador() == null){
                lblDescripcion.setText("<html>Evento seleccionado: " + ev + " [" + ev.getFecha() + "]</html>");
            } else {
                lblDescripcion.setText("<html>Evento seleccionado: " + ev + " [" + ev.getFecha() + "] (" + ev.getCreador().getNombreUsuario() + ")"+ "</html>");
            }
        } else {
            if (Sesion.puedoCrearEventos()) {
                lblDescripcion.setText("<html>Seleccione un evento para ver detalles aquí. " +
                        "Puede crear eventos seleccionando la fecha en el calendario y pulsando " +
                        "\"Crear...\".</html>");
            } else {
                lblDescripcion.setText("<html>Seleccione un evento para ver detalles aquí.</html>");
            }
        }
    }

    public void setNombreUsuario(String nombre) {
        lblUsuario.setText(nombre);
    }

    public String getTipoNuevoEvento() {
        return (String)cbNuevoEvento.getSelectedItem();
    }

    // Código para crear la GUI
    // ------------------------

    private void crearGUI() {
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(UtilidadesGUI.FUENTE);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        crearPanelEventos();
        tabbedPane.addTab("Eventos", null, panelEventos);

        crearPanelMensajeria();
        tabbedPane.addTab("Mensajería", null, panelMensajeria);

        if (Sesion.logueadoComoAdmin()) {
            btnAdmin = new JButton("Volver al Panel de Administrador");
            contentPane.add(btnAdmin, BorderLayout.NORTH);
        }
    }
    private void crearPanelEventos() {

        panelEventos = new JPanel();
        panelEventos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridLayout eventosLayout = new GridLayout(1, 2);
        eventosLayout.setHgap(10);
        panelEventos.setLayout(eventosLayout);

        panelEventos.add(crearZonaListasEventos());
        panelEventos.add(crearZonaUsuario());

    }
    private JPanel crearZonaListasEventos() {
        JPanel zonaListasEventos = new JPanel();
        GridLayout listasEventosLayout = new GridLayout(3, 1);
        listasEventosLayout.setVgap(10);
        zonaListasEventos.setLayout(listasEventosLayout);


        JPanel zonaCursos = new JPanel();
        zonaCursos.setLayout(new BoxLayout(zonaCursos, BoxLayout.Y_AXIS));

        JLabel lblCursos = new JLabel("Cursos");
        lblCursos.setFont(UtilidadesGUI.FUENTE_TITULOS);
        lblCursos.setAlignmentX(Component.LEFT_ALIGNMENT);
        zonaCursos.add(lblCursos);
        zonaCursos.add(Box.createRigidArea(new Dimension(0, 10)));

        listaCursos = new JList<>();
        listaCursos.setFont(UtilidadesGUI.FUENTE);
        JScrollPane listaCursosScroll = new JScrollPane(listaCursos);

        listaCursosScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        zonaCursos.add(listaCursosScroll);
        zonaListasEventos.add(zonaCursos);


        JPanel zonaActividades = new JPanel();
        zonaActividades.setLayout(new BoxLayout(zonaActividades, BoxLayout.Y_AXIS));

        JLabel lblActividades = new JLabel("Actividades sociales");
        lblActividades.setFont(UtilidadesGUI.FUENTE_TITULOS);
        lblActividades.setAlignmentX(Component.LEFT_ALIGNMENT);
        zonaActividades.add(lblActividades);
        zonaActividades.add(Box.createRigidArea(new Dimension(0, 10)));

        listaActividades = new JList<>();
        listaActividades.setFont(UtilidadesGUI.FUENTE);
        JScrollPane listaActividadesScroll = new JScrollPane(listaActividades);
        listaActividadesScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        zonaActividades.add(listaActividadesScroll);
        zonaListasEventos.add(zonaActividades);


        JPanel zonaConferencias = new JPanel();
        zonaConferencias.setLayout(new BoxLayout(zonaConferencias, BoxLayout.Y_AXIS));

        JLabel lblConferencias = new JLabel("Conferencias");
        lblConferencias.setFont(UtilidadesGUI.FUENTE_TITULOS);
        lblConferencias.setAlignmentX(Component.LEFT_ALIGNMENT);
        zonaConferencias.add(lblConferencias);
        zonaConferencias.add(Box.createRigidArea(new Dimension(0, 10)));

        listaConferencias = new JList<>();
        listaConferencias.setFont(UtilidadesGUI.FUENTE);
        JScrollPane listaConferenciasScroll = new JScrollPane(listaConferencias);
        listaConferenciasScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        zonaConferencias.add(listaConferenciasScroll);
        zonaListasEventos.add(zonaConferencias);
        return zonaListasEventos;
    }
    private JPanel crearZonaUsuario() {
        JPanel zonaUsuario = new JPanel();
        zonaUsuario.setLayout(new BoxLayout(zonaUsuario, BoxLayout.Y_AXIS));

        zonaUsuario.add(crearZonaIconoUsuario());
        zonaUsuario.add(Box.createRigidArea(new Dimension(0, 10)));
        zonaUsuario.add(crearZonaDetalles());
        zonaUsuario.add(Box.createRigidArea(new Dimension(0, 10)));
        zonaUsuario.add(crearCalendario());
        zonaUsuario.add(Box.createRigidArea(new Dimension(0, 10)));
        zonaUsuario.add(crearListaInscritosYPropuestos());

        return zonaUsuario;
    }
    private JPanel crearZonaIconoUsuario() {
        JPanel zonaIconoUsuario = new JPanel();
        zonaIconoUsuario.setLayout(new BoxLayout(zonaIconoUsuario, BoxLayout.X_AXIS));
        zonaIconoUsuario.setAlignmentX(Component.RIGHT_ALIGNMENT);

        lblUsuario = new JLabel("Fulanito");
        lblUsuario.setFont(UtilidadesGUI.FUENTE);
        zonaIconoUsuario.add(lblUsuario);
        zonaIconoUsuario.add(Box.createRigidArea(new Dimension(10, 0)));

        btnPerfil = new JButton();
        btnPerfil.setSize(new Dimension(50, 50));
        btnPerfil.setPreferredSize(new Dimension(50, 50));
        btnPerfil.setMaximumSize(new Dimension(50, 50));
        UtilidadesGUI.ajustarImagenAButton(btnPerfil, "../recursosApp/gato.png");
        zonaIconoUsuario.add(btnPerfil);
        return zonaIconoUsuario;
    }
    private JPanel crearZonaDetalles() {
        JPanel zonaDetalles = new JPanel(new BorderLayout());
        zonaDetalles.setAlignmentX(Component.RIGHT_ALIGNMENT);
        zonaDetalles.setPreferredSize(new Dimension(-1, 100));
        zonaDetalles.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        JPanel zonaDetallesNorte = new JPanel();
        zonaDetallesNorte.setLayout(new BoxLayout(zonaDetallesNorte, BoxLayout.X_AXIS));

        JLabel lblDetalles = new JLabel("Detalles");
        lblDetalles.setFont(UtilidadesGUI.FUENTE_TITULOS);
        lblDescripcion = new JLabel();
        setTextoDescripcion(null);
        lblDescripcion.setFont(UtilidadesGUI.FUENTE);
        btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(UtilidadesGUI.FUENTE);

        zonaDetallesNorte.add(lblDetalles);
        zonaDetallesNorte.add(Box.createHorizontalGlue());
        zonaDetallesNorte.add(btnEntrar);
        zonaDetalles.add(zonaDetallesNorte, BorderLayout.NORTH);
        zonaDetalles.add(lblDescripcion, BorderLayout.CENTER);

        return zonaDetalles;
    }
    private JPanel crearCalendario() {
        JPanel panelCalendario = new JPanel();
        panelCalendario.setLayout(new BoxLayout(panelCalendario, BoxLayout.X_AXIS));
        panelCalendario.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel lblDetalles = new JLabel("Calendario");
        lblDetalles.setFont(UtilidadesGUI.FUENTE_TITULOS);
        panelCalendario.add(lblDetalles);
        panelCalendario.add(Box.createRigidArea(new Dimension(10, 0)));

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        datePicker.setEnabled(false);
        panelCalendario.add(datePicker);

        return panelCalendario;
    }
    private JPanel crearListaInscritosYPropuestos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel norte = new JPanel();
        norte.setLayout(new BoxLayout(norte, BoxLayout.X_AXIS));

        JLabel lblMisEventos = new JLabel("Mis eventos");
        lblMisEventos.setFont(UtilidadesGUI.FUENTE_TITULOS);
        norte.add(lblMisEventos);
        norte.add(Box.createHorizontalGlue());

        if (Sesion.puedoCrearEventos()) {
            String[] opcionesCreacion;
            if (Sesion.getUsuarioLogueado() instanceof  Colaborador) {
                opcionesCreacion = new String[]{"Crear...", "Curso", "Actividad social", "Conferencia"};
            } else {
                opcionesCreacion = new String[]{"Crear...", "Curso", "Conferencia"};
            }
            cbNuevoEvento = new JComboBox<>(opcionesCreacion);
            cbNuevoEvento.setFont(UtilidadesGUI.FUENTE);
            cbNuevoEvento.setEnabled(false);
            norte.add(cbNuevoEvento);
        }

        panel.add(norte, BorderLayout.NORTH);


        JTabbedPane centro = new JTabbedPane();
        centro.setFont(UtilidadesGUI.FUENTE);

        listaInscritos = new JList<>();
        listaInscritos.setFont(UtilidadesGUI.FUENTE);
        JScrollPane scrollInscritos = new JScrollPane(listaInscritos);
        centro.addTab("Inscritos", null, scrollInscritos);

        if (Sesion.puedoCrearEventos()) {
            listaCreados = new JList<>();
            listaCreados.setFont(UtilidadesGUI.FUENTE);
            JScrollPane scrollCreados = new JScrollPane(listaCreados);
            centro.addTab("Creados", null, scrollCreados);
        }

        panel.add(centro, BorderLayout.CENTER);

        return panel;
    }



    private void crearPanelMensajeria() {
        panelMensajeria = new JPanel();
        panelMensajeria.setLayout(new GridLayout(1, 1));
        panelMensajeria.add(new JLabel("Próximamente"));
    }

    public void alternarCreacion() {
        cbNuevoEvento.setEnabled(datePicker.getModel().getValue() != null);
    }

    public void recargaDatos() {
        lblUsuario.setText(Sesion.getUsuarioLogueado().getNombreUsuario());
    }
}

